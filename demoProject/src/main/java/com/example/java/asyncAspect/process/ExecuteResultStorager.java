package com.example.java.asyncAspect.process;

import com.example.java.User;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public interface ExecuteResultStorager<T> {
    boolean existResult(String var1);

    T getResult(String var1);

    void remove(String ticketInRequest);

    String generateTicket(Method method, User userInfo);

    void saveResult(String ticket, T stakeResult, long timeout, TimeUnit timeUnit);
}
