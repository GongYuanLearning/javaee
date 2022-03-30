package com.example.aspect;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("beforeAspect")
public class BeforeAspect implements MethodBeforeAdvice {
    @Override
    public void before(Method method,
                       Object[] args,
                       Object target)
            throws Throwable {
        System.out.println("前置通知");
    }
}
