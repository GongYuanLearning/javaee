package com.gy.config;

import com.gy.service.PackageService;
import com.gy.service.impl.PackageServiceImpl;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.gy.apect")
@Configuration
public class AppConfig {
    @Bean
    PackageService packageService() {
        return new PackageServiceImpl();
    }

    @Bean
    ProxyFactoryBean packageServiceProxy(PackageService packageService) {
        ProxyFactoryBean pfb = new ProxyFactoryBean();

        pfb.setInterfaces(PackageService.class); // 配置代理接口
        pfb.setTarget(packageService); // 配置目标对象
        pfb.setInterceptorNames("beforeAdvice"); // 配置通知组件的名称
        pfb.setProxyTargetClass(true); // true代表是CGLIB实现代理
        return pfb;
    }
}
