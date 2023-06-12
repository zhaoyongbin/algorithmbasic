package com.example.java.asyncAspect.config;

import com.example.java.asyncAspect.AsyncExecuteAspect;
import com.example.java.asyncAspect.other.AuditLogHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncExcuteAspectConfiguration {
    public AsyncExcuteAspectConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean({AsyncExecuteAspect.class})
    public AsyncExecuteAspect asyncExecuteAspect(){
        return new AsyncExecuteAspect();
    }

    @Bean
    public AuditLogHandler auditLogHandler(){
        return  new AuditLogHandler();
    }
}
