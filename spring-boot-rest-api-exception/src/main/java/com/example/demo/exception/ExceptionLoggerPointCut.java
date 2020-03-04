package com.example.demo.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class ExceptionLoggerPointCut {
    private final Logger logger = LoggerFactory.getLogger(ExceptionLoggerPointCut.class);

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            //" || within(@org.springframework.web.bind.annotation. *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {}

    @Pointcut("within(com.example.demo..*)" +
            " || within(com.example.demo.dto..*)" +
            " || within(com.example.demo.security..*)" +
            " || within(com.example.demo.exception..*)" +
            " || within(com.example.demo.controller..*)")
    public void applicationPackagePointcut() {}

    //@AfterThrowing(pointcut = "execution(* com.example.demo.*.*.*(..))", throwing = "ex")
    @AfterThrowing(pointcut = "applicationPackagePointcut() || springBeanPointcut()", throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String stuff = signature.toString();
        String arguments = Arrays.toString(joinPoint.getArgs());

        logger.error("Write something in the log... We have caught exception in method: "
                + methodName + " with arguments "
                + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
                + ex.getMessage());

    }
}
