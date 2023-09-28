package com.byd.demo.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAnnotationAspect {
    @Pointcut("@annotation(com.byd.demo.annotation.TestAnnotation)")
    public void testPointCut(){

    }

    @Around("testPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("=============================");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("=============================");
        return proceed;
    }
}
