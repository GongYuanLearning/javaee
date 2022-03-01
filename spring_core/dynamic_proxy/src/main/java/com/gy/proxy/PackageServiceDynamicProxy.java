package com.gy.proxy;

import com.gy.apect.LogAspect;
import com.gy.service.PackageService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PackageServiceDynamicProxy implements InvocationHandler {
    // 被代理的接口
    private PackageService packageService;

    /**
     * 调用方法。
     *
     * @param proxy 代理对象
     * @param method 要调用的方法
     * @param args 要调用方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogAspect logAspect = new LogAspect();
        // 记录方法的进入
        logAspect.logEntrance(method, args);
        // 调用被代理接口的方法
        Object res = null;
        try {
            res = method.invoke(packageService, args);
        } catch(Throwable e) {
            // 记录异常
            logAspect.logException(method, e.getCause());
        }
        // 日志
        logAspect.logExit(method, res);
        return res;
    }

    /**
     * 获取代理对象
     * @param packageService 被代理的接口的具体实现
     * @return
     */
    public Object getPackageServiceProxy(PackageService packageService) {
        this.packageService = packageService;
        final ClassLoader classLoader = PackageServiceDynamicProxy.class.getClassLoader();
        final Class<?>[] interfaces = packageService.getClass().getInterfaces();
        // 实例化代理对象，使用Proxy.newProxyInstance
        // 第1个参数，类加载器，要跟被代理接口一致
        // 第2个参数，被代理对象必须实现的接口
        // 第3个参数，实现了InvocationHandler的类
        return Proxy.newProxyInstance(
                classLoader, interfaces, this);
    }
}
