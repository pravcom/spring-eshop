package com.akhtyamov.springeshop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Around("Pointcuts.allLogExecutionTime()")
    public Object aroundAllAdvice(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getMethod().getName();
        Instant startTime = Instant.now();

        Object result = null;
        try {
            result = joinPoint.proceed();
            String additionalMessage = methodSignature.getMethod().getAnnotation(LogExecutionTime.class).additionalMessage();
            long elapsedTime = Duration.between(startTime, Instant.now()).toMillis();
            log.info("Class Name: {}, Method Name: {}, Additional Message: {}, Elapsed Time: {}ms",
                    className, methodName, additionalMessage, elapsedTime);
            log.info("Result: {}", result);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }


    }
}
