package com.gy.configuration;

import com.gy.service.HelloService;
import com.gy.service.impl.DefaultHelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration // 表示当前类是一个配置类
public class ApplicationConfig {
    // 加上Bean注解后，表示该方法返回的是组件，组件的名称就是方法名
    @Bean
    HelloService helloService() {
        return new DefaultHelloService();
    }
}
