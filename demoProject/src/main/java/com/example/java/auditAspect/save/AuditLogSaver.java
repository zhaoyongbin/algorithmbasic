package com.example.java.auditAspect.save;

import com.example.java.User;

import java.util.Date;

public interface AuditLogSaver {
    Integer saveRequestParam(User userInfo, Date date, String requestURI, String description, String requestParamJSONstr, String fileStoragePath);

    void saveException(Integer logId, Date date, String exInfo);

    void saveResponse(Integer logId, Date endTime, String response);
}
