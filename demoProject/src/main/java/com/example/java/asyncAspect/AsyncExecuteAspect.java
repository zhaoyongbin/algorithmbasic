package com.example.java.asyncAspect;

import com.example.java.User;
import com.example.java.asyncAspect.anno.Async;
import com.example.java.asyncAspect.config.TaskExecutor;
import com.example.java.asyncAspect.other.LoginUserHeaderFilter;
import com.example.java.asyncAspect.other.MethodparameterUtil;
import com.example.java.asyncAspect.other.UserInfoFetcher;
import com.example.java.asyncAspect.process.ExecuteResultStorager;
import com.example.java.asyncAspect.process.StakeResultProcessor;
import com.example.java.auditAspect.anno.Audit;
import com.example.java.auditAspect.handler.AuditLogHandler;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Aspect
@Order(-1553)
public class AsyncExecuteAspect {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncExecuteAspect.class);
    public static final String ASYNC_EXECUTE_RESULT_TICKET = "async_execute_result_ticket";
    @Autowired
    private StakeResultProcessor stakeResultProcessor;
    @Autowired
    private ExecuteResultStorager executeResultStorager;
    @Autowired
    private AuditLogHandler auditLogHandler;
    @Autowired
    private UserInfoFetcher userInfoFetcher;
    @Value("${async.wait.time:2000}")
    private long asyncWaitTime;


    private TaskExecutor taskExecutor = new TaskExecutor(5, 10, "async_execute_result_ticket");


    public AsyncExecuteAspect() {
    }

    @Pointcut("execution(* com..service..*(..))")
    protected void asyncExecutePointCut() {
    }

    @Around("asyncExecutePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Async asyncAnno = AnnotationUtils.findAnnotation(method, Async.class);
        if (!this.shouldAsyncExecute(asyncAnno, method)) {
            Object result = joinPoint.proceed();
            return result;
        } else {
            LOG.info("方法{}满足异步执行任务得条件，可以异步执行", method.getName());
            String ticketInRequest = MethodparameterUtil.resolveParameterRequst("async_execute_result_ticket");
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (!StringUtils.isEmpty(ticketInRequest) && this.executeResultStorager.existResult(ticketInRequest)) {
                Object executeResult = this.executeResultStorager.getResult(ticketInRequest);
                if (executeResult != null) {
                    if (!this.stakeResultProcessor.isFinishedResult(executeResult)) {
                        Map<String, Object> details = new HashMap<>();
                        details.put("", ticketInRequest);
                        details.put("imply", "当前操作正在执行异步中请稍后");
                        return this.stakeResultProcessor.createStakeResultWithDetails(details);
                    }
                    this.executeResultStorager.remove(ticketInRequest);
                    return executeResult;
                }
            }
            Audit auditAnno = AnnotationUtils.findAnnotation(method, Audit.class);
            long timeout = asyncAnno.timeout();
            TimeUnit timeUnit = asyncAnno.timeUnit();
            User userInfo = this.userInfoFetcher.getUserInfo(request);
            String ticket = this.executeResultStorager.generateTicket(method, userInfo);
            Object stakeResult = this.stakeResultProcessor.createStakeResultWithDetail("async_execute_result_ticket", ticket);
            this.executeResultStorager.saveResult(ticket, stakeResult, timeout, timeUnit);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.asyncExcuteHandlerMethod(method, joinPoint, ticket, asyncAnno, auditAnno, joinPoint, request, countDownLatch);
            if (this.shouldWait(method, joinPoint)) {
                this.waitForAsyncExecutThreadStart(countDownLatch);
            }
            return stakeResult;
        }
    }

    private void waitForAsyncExecutThreadStart(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.warn("请求线程等待异步工作线程超时，自动返回，可能会导致某些具有文件上传的功能接口报错");
        }

        try {
            TimeUnit.MILLISECONDS.sleep(this.asyncWaitTime);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }
    }


    private boolean shouldWait(Method method, ProceedingJoinPoint joinPoint) {
        List<MultipartFile> multipartFileList = MethodparameterUtil.getMultiPartFile(method, joinPoint);
        if (CollectionUtils.isEmpty(multipartFileList)) {
            return false;
        } else {
            String ticket = MethodparameterUtil.resolveParameterRequst("async_execute_result_ticket");
            return StringUtils.isEmpty(ticket);
        }
    }

    private void asyncExcuteHandlerMethod(Method method, ProceedingJoinPoint joinPoint, String ticket, Async asyncAnno, Audit auditAnno, ProceedingJoinPoint joinPoint1, HttpServletRequest request, CountDownLatch countDownLatch) {
        User user = this.userInfoFetcher.getUserInfo(request);
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        long timeout = asyncAnno.timeout();
        TimeUnit timeUnit = asyncAnno.timeUnit();
        boolean shouldAuditLof = auditAnno != null;
        User sysUser = new User();
        AsyncExecuteAspect.InvokeHandlerMethodTask task = new AsyncExecuteAspect.InvokeHandlerMethodTask(method, target, args, ticket, timeout, timeUnit, shouldAuditLof, auditAnno, joinPoint, request, user, countDownLatch, sysUser);

        this.taskExecutor.execute(task);


    }

    private boolean shouldAsyncExecute(Async asyncAnno, Method method) {

        Class<?> returnType = method.getReturnType();
        Class<?> supportAsyncReturnType = this.stakeResultProcessor.getSupportClazz();
        return asyncAnno != null && returnType.isAssignableFrom(supportAsyncReturnType);
    }


    class InvokeHandlerMethodTask implements Runnable {
        private Method method;
        private Object[] args;
        private Object target;
        private String ticket;
        private long timeout;
        private TimeUnit timeUnit;
        private Audit auditAnno;
        private ProceedingJoinPoint joinPoint;
        private boolean shouldAuitLog;
        private HttpServletRequest request;
        private User userInfo;
        private CountDownLatch CountDownLatch;
        private User sysUser;

               //InvokeHandlerMethodTask(method,               target,         args,         ticket,      timeout,         timeUnit, shouldAuditLof, auditAnno, joinPoint, request, user, countDownLatch, sysUser);
        public InvokeHandlerMethodTask(Method method, Object target, Object[] args, String ticket, long timeout, TimeUnit timeUnit,  boolean shouldAuitLog,Audit auditAnno, ProceedingJoinPoint joinPoint, HttpServletRequest request, User userInfo, java.util.concurrent.CountDownLatch countDownLatch, User sysUser) {
            this.method = method;
            this.args = args;
            this.target = target;
            this.ticket = ticket;
            this.timeout = timeout;
            this.timeUnit = timeUnit;
            this.auditAnno = auditAnno;
            this.joinPoint = joinPoint;
            this.shouldAuitLog = shouldAuitLog;
            this.request = request;
            this.userInfo = userInfo;
            CountDownLatch = countDownLatch;
            this.sysUser = sysUser;
        }

        @Override
        public void run() {
            Integer longId = null;
            try {
                if (this.shouldAuitLog) {
                    AsyncExecuteAspect.this.auditLogHandler.saveRequestParam(this.request, this.joinPoint, this.auditAnno.description(), this.userInfo);
                }
                LoginUserHeaderFilter.threadLocal.set(this.sysUser);
                this.CountDownLatch.countDown();
                Object result = this.method.invoke(this.target, this.args);

                AsyncExecuteAspect.this.executeResultStorager.saveResult(this.ticket,result,this.timeout,this.timeUnit);
                if(this.shouldAuitLog){
                    AsyncExecuteAspect.this.auditLogHandler.saveResponse(longId,request,this.auditAnno.auditResponse(),this.auditAnno.async());
                }


            }catch (Exception e){
                AsyncExecuteAspect.LOG.error("异步执行方法"+this.method.getName()+"出现异常",e);
                if(this.shouldAuitLog){
                    AsyncExecuteAspect.this.auditLogHandler.saveException(longId,this.auditAnno.async(),e);
                }
                Object fallbackResult = AsyncExecuteAspect.this.stakeResultProcessor.createFallbackResult(e);
                AsyncExecuteAspect.this.executeResultStorager.saveResult(this.ticket,fallbackResult,this.timeout,this.timeUnit);
            }
        }
    }
}
