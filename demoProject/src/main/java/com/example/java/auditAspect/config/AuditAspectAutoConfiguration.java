package com.example.java.auditAspect.config;

import com.example.java.auditAspect.AuditAspect;
import com.example.java.auditAspect.handler.AuditLogHandler;
import com.example.java.auditAspect.save.AuditLogSaver;
import com.example.java.auditAspect.save.impl.DefaultAuditLogSaver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditAspectAutoConfiguration {
    public AuditAspectAutoConfiguration() {
    }
    @Bean
    @ConditionalOnMissingBean({AuditAspect.class})
    public  AuditAspect auditAspect(){
        return  new AuditAspect();
    }



    @Bean
    @ConditionalOnMissingBean({AuditLogSaver.class})
    public  AuditLogSaver auditLogSaver(){
        return  new DefaultAuditLogSaver();
    }
    @Bean
    @ConditionalOnMissingBean({AuditLogHandler.class})
    public AuditLogHandler auditLogHandler(){
        return  new AuditLogHandler();
    }


}
