package com.akhtyamov.springeshop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.akhtyamov.springeshop.service.UserServiceImpl.*(..))")
    public void allMethods(){}

    @Pointcut("execution(* com.akhtyamov.springeshop.service.UserServiceImpl.save*(..))")
    public void saveMethod(){}

    @Pointcut("@annotation(LogExecutionTime)")
    public void allLogExecutionTime(){}
}
