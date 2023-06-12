package com.example.java.auditAspectImpl;

import com.example.java.User;
import com.example.java.auditAspect.save.AuditLogSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.util.Date;

public class DefaultAuditLogConfig  implements AuditLogSaver {


    private static Logger log= LoggerFactory.getLogger(DefaultAuditLogConfig.class);

    public DefaultAuditLogConfig() {
    }

    @Autowired
    @Qualifier("DefaultAuditLogConfig")
    private DataSource dataSource;
    @Override
    public Integer saveRequestParam(User userInfo, Date date, String requestURI, String description, String requestParamJSONstr, String fileStoragePath) {
        return null;
    }

    @Override
    public void saveException(Integer logId, Date date, String exInfo) {

    }

    @Override
    public void saveResponse(Integer logId, Date endTime, String response) {

    }
}
