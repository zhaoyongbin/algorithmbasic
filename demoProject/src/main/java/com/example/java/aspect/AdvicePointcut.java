package com.example.java.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(-989)
@Aspect
public class AdvicePointcut {

    @Pointcut("@annotation(com.example.java.aspect.testAspect)")
    public void point(){}

    @Around("point()")
    public Object checkFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();

        System.out.println("id1->>>>>>>>>>>>>>>>>>>>>>" + "id");
        System.out.println("name1->>>>>>>>>>>>>>>>>>>>>>" + "name");

        // id小于0则抛出非法id的异常
        if (1 < 0) {
            return "{\"message\":\"illegal id\",\"code\":403}";
        }
         joinPoint.proceed();
        System.out.println("=========colleter方法执行之后==========");

        return "around";
    }
}
