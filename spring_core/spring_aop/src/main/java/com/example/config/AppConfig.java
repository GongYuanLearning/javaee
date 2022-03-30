package com.example.config;

import com.example.service.PackageService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.example")
// 开启基于AspectJ的aop编程
@EnableAspectJAutoProxy
public class AppConfig {
}
