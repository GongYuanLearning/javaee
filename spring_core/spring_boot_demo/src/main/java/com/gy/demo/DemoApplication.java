package com.gy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// https://www.baeldung.com/spring-boot-jsp
@SpringBootApplication
public class DemoApplication implements WebMvcConfigurer {
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        // viewResolver.setViewClass(JstlView.class); // 这个属性通常并不需要手动配置，高版本的Spring会自动检测
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler指的是访问路径，addResourceLocations指的是文件放置的目录
        registry.addResourceHandler("/**").addResourceLocations("classpath:/res/");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
