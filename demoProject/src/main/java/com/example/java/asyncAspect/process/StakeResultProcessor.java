package com.example.java.asyncAspect.process;

import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

public interface StakeResultProcessor<T> {
    boolean isFinishedResult(T var1);

    T createStakeResultWithDetails(Map<String, Object> details);

    T createStakeResultWithDetail(String impl, Object detail);

    T createFallbackResult(Exception e);

    Class<?> getSupportClazz();
}
