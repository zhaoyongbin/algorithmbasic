package com.example.java.asyncAspect.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Async {
    long timeout() default  1L;
    TimeUnit timeUnit() default TimeUnit.HOURS;
}
