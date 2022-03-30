package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面类。
 *
 */
@Aspect // 申明该类是一个切面类
@Component("logsAspect")
public class LogsAspect {
    // 切入点的定义
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void pointCut() {
    }
    /**
     * 前置通知。参数是JoinPoint类型。返回类型为void。
     *
     * @param jp
     */
    @Before(value =
            "pointCut()")
    public void before(JoinPoint jp) {
        // getTarget获取目标对象
        System.out.println("前置通知：" + jp.getTarget());
        // getSignature获取目标方法的签名
        System.out.println("进入方法：" + jp.getSignature().getName());
    }

    /**
     * 环绕通知。参数是ProceedingJoinPoint类型。返回类型为Object。
     *
     * @param pjp
     */
    @Around(value =
            "execution(* com.example.service.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知, 方法执行前：");
        // proceed方法调用目标方法。返回的就是目标方法的返回值。
        final Object result = pjp.proceed();
        System.out.println("环绕通知, 方法执行后：");
        return result;
    }

    /**
     * 后置返回通知。参数是JoinPoint类型。返回类型为void。
     *
     * @param jp
     */
    @AfterReturning(value = "pointCut()")
    public void afterReturning(JoinPoint jp) {
        System.out.println("后置返回通知："
                + jp.getSignature().getName());
    }

    /**
     * 后置通知。参数是JoinPoint类型。返回类型为void。
     *
     * @param jp
     */
    @After(value = "pointCut()")
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
    @AfterThrowing(value =
            "execution(* com.example.service.*.*(..))",
        throwing = "ex")
    public void exception(Throwable ex) {
        System.out.println("异常通知："
                + ex.getMessage());
    }
}
