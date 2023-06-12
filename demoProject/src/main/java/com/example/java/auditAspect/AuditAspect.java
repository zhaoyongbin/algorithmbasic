package com.example.java.auditAspect;


import com.example.java.User;
import com.example.java.auditAspect.anno.Audit;
import com.example.java.auditAspect.handler.AuditLogHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Order(-4342)
public class AuditAspect {
    private  static Logger LOG= LoggerFactory.getLogger(AuditAspect.class);
    @Autowired
    private AuditLogHandler auditLogHandler;
    @Autowired
    private User userInfoFetcher;

    public AuditAspect() {
    }

    @Pointcut("execution(*com..**(..))")
    public void auditPointCut(){

    }


    @Around("auditPointCut()")
    public  Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Audit auditAnno = AnnotationUtils.findAnnotation(method, Audit.class);
        Object result;
        if(auditAnno!=null){
            boolean auditResponse = auditAnno.auditResponse();
            boolean async = auditAnno.async();
            String description = auditAnno.description();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            User userInfo = null;
//                    this.userInfoFetcher.getUser(request);
           Integer logId= this.auditLogHandler.saveRequestParam(request,joinPoint,description,userInfo);
           try{
               result=joinPoint.proceed();
               this.auditLogHandler.saveResponse(logId,result,auditResponse,async);
               return result;
           }catch (Throwable var12){
               this.auditLogHandler.saveException(logId,async,var12);
               throw var12;
           }

        }else {
         result=joinPoint.proceed();
         return  result;
        }

    }





}
