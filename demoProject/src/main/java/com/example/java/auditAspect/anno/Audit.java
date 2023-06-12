package com.example.java.auditAspect.anno;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Audit {
    boolean auditResponse() default true;
    boolean async() default  true;

    String description();
}
