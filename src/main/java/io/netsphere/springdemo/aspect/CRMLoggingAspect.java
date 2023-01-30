package io.netsphere.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* io.netsphere.springdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* io.netsphere.springdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* io.netsphere.springdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    // add @Before advice
    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {

        // display method called
        String method = joinPoint.getSignature().toShortString();
        logger.info("======> calling method: " + method);

        // display arguments passed
        Object[] args = joinPoint.getArgs();
        for(Object o : args) {
            logger.info("======> argument: " + o);
        }

    }

    // add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    private void afterReturning(JoinPoint joinPoint, Object result) {

        // display method finished
        String method = joinPoint.getSignature().toShortString();
        logger.info("======> in @AfteerReturning: from method: " + method);

        // display data returned
        logger.info("======> result: " + result);
    }
}
