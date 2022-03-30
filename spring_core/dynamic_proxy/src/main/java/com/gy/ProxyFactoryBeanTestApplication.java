package com.gy;

import com.gy.config.AppConfig;
import com.gy.service.PackageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProxyFactoryBeanTestApplication {
    public static void main(String[] args) {
        final ApplicationContext ac =
                new AnnotationConfigApplicationContext(AppConfig.class);
        // 获取PackageService代理组件
        final PackageService packageServiceProxy =
                (PackageService) ac.getBean("packageServiceProxy");
        packageServiceProxy.getPackage("123456");
    }
}
