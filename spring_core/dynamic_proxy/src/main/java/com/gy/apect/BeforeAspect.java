package com.gy.apect;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 前置通知。
 */
@Component("beforeAdvice")
public class BeforeAspect implements MethodBeforeAdvice {
    @Override
    public void before(Method method,
                       Object[] args,
                       Object target) throws Throwable {
        System.out.println(target.getClass().getName() +"前置通知");
    }
}
