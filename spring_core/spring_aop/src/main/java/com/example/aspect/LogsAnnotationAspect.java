package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切点表达式（Spring中只能用于方法），表达式还有使用and(&&)，or(||), not(!)连接：
 * - args() : 限制参数为指定类型，如arg(Student, String)
 * - @args(): 限制参数为指定注解类型，
 * - execution：匹配具体的方法
 * - this()：限制指定类型
 * - target：限制目标对象为制定类型
 * - @target()：限制目标对象为指定注解类型
 * -within()： 限制为具体包下
 * - @within()：限制为具体包下指定注解类型
 * - @annotation()：限制带有指定注解
 * - bean(): 限制指定的组件名
 */
@Aspect
@Component
public class LogsAnnotationAspect {
    @Pointcut("execution(* com.example.service.impl.*.*(..))")
    public void logsPointcut() {}
//    @Pointcut("within(com.example.service.impl)")
//    public void withinPointcut() {}

    @Pointcut("@annotation(com.example.annotation.Action)")
    public void annotationPointcut() {}

    //@Before("execution(* com.example.service.impl.*.*(..))")
    @Before("logsPointcut() && annotationPointcut()")
    /**
     * 前置通知。参数是JoinPoint类型。返回类型为void。
     *
     * @param jp
     */
    public void before(JoinPoint jp) {
        System.out.println("前置通知：" + jp.getTarget());
        System.out.println("进入方法：" + jp.getSignature().getName());
    }

    /**
     * 环绕通知。参数是ProceedingJoinPoint类型。返回类型为Object。
     *
     * @param pjp
     */
    @Around("logsPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知, 方法执行前：");
        final Object result = pjp.proceed();
        System.out.println("环绕通知, 方法执行后：");
        return result;
    }

    /**
     * 后置返回通知。参数是JoinPoint类型。返回类型为void。
     *
     * @param jp
     */
    public void afterReturning(JoinPoint jp) {
        System.out.println("后置返回通知："
                + jp.getSignature().getName());
    }


    /**
     * 后置通知。参数是JoinPoint类型。返回类型为void。
     *
     * @param jp
     */
    public void after(JoinPoint jp) {
        System.out.println("后置最终通知："
                + jp.getSignature().getName());
    }

    /**
     * 异常通知。参数是Throwable类型以及子类IllegalArgumentException。
     * 返回类型为void。
     *
     * @param ex
     */
    public void exception(Throwable ex) {
        System.out.println("异常通知："
                + ex.getMessage());
    }
}
