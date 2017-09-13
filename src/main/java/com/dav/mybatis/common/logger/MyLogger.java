package com.dav.mybatis.common.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class MyLogger.
 */
@Aspect
@Component
public class MyLogger {

    /**  Handle to the log file. */
    private final Log log = LogFactory.getLog(getClass());

    /**
     * Instantiates a new my logger.
     */
    public MyLogger () {}

    /**
     * Log method access after.
     *
     * @param joinPoint the join point
     */
    @AfterReturning("execution(* com.dav.mybatis.controller..*.*(..))")
    public synchronized void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("***** Completed: " + joinPoint.getSignature().getName() + " *****");
    }

    /**
     * Log method access before.
     *
     * @param joinPoint the join point
     */
    @Before("execution(* com.dav.mybatis.controller..*.*(..))")
    public synchronized void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("***** Starting: " + joinPoint.getSignature().getName() + " *****");
    }
}