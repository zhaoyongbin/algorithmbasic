package com.example.java.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface testAspect {
    //名称，如果不给就是要默认的
    String name() default "testAspect";
}
