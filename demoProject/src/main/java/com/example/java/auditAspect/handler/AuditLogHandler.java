package com.example.java.auditAspect.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.java.User;
import com.example.java.asyncAspect.config.TaskExecutor;
import com.example.java.auditAspect.save.AuditLogSaver;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditLogHandler {
    private static Logger LOG = LoggerFactory.getLogger(AuditLogHandler.class);
    @Autowired
    private AuditLogSaver auditLogSaver;
    @Autowired
    private User userInfoFetcher;
    private TaskExecutor executor = new TaskExecutor(3, 3, "audit-log-record-thread:");

    public AuditLogHandler() {
    }

    public Integer saveRequestParam(HttpServletRequest request, ProceedingJoinPoint joinPoint, String description, User userInfo) {
        Integer recordId = null;
        String requestURI = request.getRequestURI();
        try {
            String fileStoragePath = this.resolveFileStoragePath(request);
            String requestParamJSONstr = this.processRequsetParam(joinPoint);
            this.auditLogSaver.saveRequestParam(userInfo, new Date(), requestURI, description, requestParamJSONstr, fileStoragePath);
        } catch (Exception e) {
            LOG.error("添加审计日志出错", e);
        }
        return recordId;

    }

    private String processRequsetParam(ProceedingJoinPoint joinPoint) {
        Object[] parameterValues = joinPoint.getArgs();
        List<String> paramJsonList = new ArrayList<>();
        if (parameterValues == null) {
            return paramJsonList.toString();
        } else {
            Object[] var4 = parameterValues;
            int var5 = parameterValues.length;
            for (int var6 = 0; var6 < var5; ++var6) {
                Object pavamValue = var4[var6];

                try {
                } catch (Exception e) {
                    paramJsonList.add(pavamValue.getClass().getName());
                }

            }
            return paramJsonList.toString();
        }

    }

    private String resolveFileStoragePath(HttpServletRequest request) {
        List<String> fileStoragePathList = (List) request.getAttribute("file-aspect-save-file-path");
        return CollectionUtils.isEmpty(fileStoragePathList) ? null : fileStoragePathList.toString();


    }


    public void saveResponse(Integer logId, Object result, boolean auditResponse, boolean async) {
        if(logId!=null){
            try {
                String responseStr=this.processResponse(result,auditResponse);
                if(async){
                    AuditLogHandler.SaveResponseTask task= new AuditLogHandler.SaveResponseTask(logId,new Date(),responseStr);
                    this.executor.execute(task);
                }
            }catch (Exception e){
                LOG.error("添加审计日志出错",e);
            }
        }
    }

    private String processResponse(Object result, boolean auditResponse) {
        String responseStr="";
        if(!auditResponse){
            return responseStr;
        }else {
            try {
                responseStr= JSONObject.toJSONString(result);
            }catch (Exception e){
                responseStr=result.getClass().getName();
            }
        }
        return responseStr;
    }





    public void saveException(Integer logId, boolean async, Throwable var12) {
        if(logId!=null){
            String exInfo= var12.getMessage();
            try {
                if(async){
                    AuditLogHandler.SaveResponseTask task=new AuditLogHandler.SaveResponseTask(logId,new Date(),exInfo);
                    this.executor.execute(task);
                }else {
                    this.auditLogSaver.saveException(logId,new Date(),exInfo);
                }
            }catch (Exception e){
                LOG.error("添加审计日志出错",e);
            }
        }
    }


    private class SaveResponseTask implements  Runnable{
       private  Integer logId;
       private  Date endTime;
       private  String response;

        public SaveResponseTask(Integer logId, Date endTime, String response) {
            this.logId = logId;
            this.endTime = endTime;
            this.response = response;
        }

        @Override
        public void run() {
            AuditLogHandler.LOG.debug("切面异步保存响应信息");
            AuditLogHandler.this.auditLogSaver.saveResponse(this.logId,this.endTime,this.response);
        }
    }


    private  class  SaveRequestParamTask implements Runnable{
        private  User userInfo;
        private Date endTime;
        private  String requestURI;
        private  String description;
        private  String requestParam;
        private  String filePath;

        public SaveRequestParamTask(User userInfo, Date endTime, String requestURI, String description, String requestParam, String filePath) {
            this.userInfo = userInfo;
            this.endTime = endTime;
            this.requestURI = requestURI;
            this.description = description;
            this.requestParam = requestParam;
            this.filePath = filePath;
        }

        @Override
        public void run() {
            AuditLogHandler.LOG.debug("切面异步保存请求入参");
            AuditLogHandler.this.auditLogSaver.saveRequestParam(this.userInfo,this.endTime,this.requestURI,this.description,this.requestParam,this.filePath);
        }
    }

}
