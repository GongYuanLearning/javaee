package com.gy.apect;

import java.lang.reflect.Method;

public class LogAspect {
    /**
     * 记录方法的进入
     * @param method
     * @param args
     */
    public void logEntrance(Method method, Object[] args) {
        final StringBuilder methodName = getMethodName(method);

        StringBuilder methodArgs = new StringBuilder();
        for (Object arg : args) {
            methodArgs.append(String.valueOf(arg));
        }

        System.out.println(String.format("进入方法：%s，参数是：%s", methodName, methodArgs.toString()));
    }

    private StringBuilder getMethodName(Method method) {
        final StringBuilder methodName = new StringBuilder(method.getName());
        methodName.append("(");
        for (Class<?> parameterType : method.getParameterTypes()) {
            methodName.append(parameterType.getTypeName());
            methodName.append(" ");
            methodName.append(parameterType.getName());
            methodName.append(",");
        }
        methodName.deleteCharAt(methodName.length() - 1);
        methodName.append(")");
        return methodName;
    }

    /**
     * 记录方法的退出
     * @param method
     * @param result
     */
    public void logExit(Method method, Object result) {
        final StringBuilder methodName = getMethodName(method);
        System.out.println(String.format("退出方法: %s, 返回：%s", methodName, String.valueOf(result)));
    }

    /**
     * 记录方法的异常
     * @param method
     * @param e
     */
    public void logException(Method method, Throwable e) {
        final StringBuilder methodName = getMethodName(method);
        System.err.println(String.format("方法: %s 发生异常：%s",
                methodName, e.getMessage()));
    }
}

