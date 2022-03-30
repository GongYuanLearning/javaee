package com.example.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component("aroundAspect")
public class AroundAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("方法执行前!");
        final Object res = methodInvocation.proceed();
        System.out.println("方法执行完成！");
        return res;
    }
}
