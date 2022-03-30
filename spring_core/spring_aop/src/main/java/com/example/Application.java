package com.example;

import com.example.config.AppConfig;
import com.example.service.PackageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        final ApplicationContext ac =
                new AnnotationConfigApplicationContext(AppConfig.class);
        // aspectJ
        final PackageService packageService = (PackageService) ac.getBean("packageService");
        packageService.sendPackage("test");
        packageService.getPackage("test");
    }
}
