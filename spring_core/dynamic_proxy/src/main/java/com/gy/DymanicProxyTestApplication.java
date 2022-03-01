package com.gy;

import com.gy.entity.Package;
import com.gy.proxy.PackageServiceDynamicProxy;
import com.gy.service.PackageService;
import com.gy.service.impl.PackageServiceImpl;

public class DymanicProxyTestApplication {
    public static void main(String[] args) {
        final PackageServiceDynamicProxy dynamicProxy = new PackageServiceDynamicProxy();
        final PackageService proxy = (PackageService) dynamicProxy.getPackageServiceProxy(new PackageServiceImpl());
        Package aPackage = proxy.getPackage("123456");
        System.out.println(aPackage);
        proxy.getPackage("123"); // 抛出异常
    }
}
