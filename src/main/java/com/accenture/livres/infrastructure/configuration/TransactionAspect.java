package com.accenture.livres.infrastructure.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class TransactionAspect {

    @Around("@annotation(com.accenture.livres.application.annotation.TransactionMethod)")
    @Transactional
    public Object transactionalMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("transactionalMethod");
        return joinPoint.proceed();
    }
}
