package com.example.java.asyncAspectImpl;

import com.example.java.User;
import com.example.java.asyncAspect.process.ExecuteResultStorager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;

import javax.print.DocFlavor;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@EnableRetry
public class ExecuteResultRedisStorager implements ExecuteResultStorager<ReturnMsg> {

    @Autowired
    private RedisTemplate redisTemplate;

    private String keyPrefix;

    public ExecuteResultRedisStorager(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public ExecuteResultRedisStorager() {

    }

    @Override
    @Retryable(value = Exception.class, maxAttempts = 10)
    public boolean existResult(String ticket) {
        Object executeResult = redisTemplate.opsForValue().get(ticket);
        return executeResult != null;
    }

    @Override
    @Retryable(value = Exception.class, maxAttempts = 10)
    public ReturnMsg getResult(String ticket) {

        return (ReturnMsg) redisTemplate.opsForValue().get(ticket);
    }

    @Override
    @Retryable(value = Exception.class, maxAttempts = 10)
    public void remove(String ticket) {
        redisTemplate.delete(ticket);
    }

    @Override
    public String generateTicket(Method method, User userInfo) {

        return keyPrefix + method.getName() + ":" + userInfo.getUserId() + ":" + UUID.randomUUID().toString();
    }

    @Override
    @Retryable(value = Exception.class, maxAttempts = 10)
    public void saveResult(String ticket, ReturnMsg stakeResult, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(ticket, stakeResult, timeout, timeUnit);
    }
}
