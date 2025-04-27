package com.rituraj.addressBook.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.rituraj.addressBook.service..*(..)) || execution(* com.rituraj.addressBook.controller..*(..))")
    public void applicationPackagePointcut() {
    }

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("Started method: {}() with arguments = {}", joinPoint.getSignature().getName(), joinPoint.getArgs());

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Finished method: {}() with result = {} | Time taken: {} ms", joinPoint.getSignature().getName(), result, elapsedTime);

        return result;
    }

    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.debug("Entering method: {}() with arguments = {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.debug("Method {}() returned: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Exception in method {}() with cause = {}", joinPoint.getSignature().getName(), error.getMessage());
    }
}

