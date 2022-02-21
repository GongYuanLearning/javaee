package com.gy;

import com.gy.configuration.ApplicationConfig;
import com.gy.service.HelloService;
import com.gy.service.impl.DefaultHelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApplication {
    public static void main(String[] args) {
        // testXmlApplicationContext();

        // 1. 基于注解的形式初始化spring上下文（spring容器），提供配置类的类class
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // 2. 从spring容器获取helloService组件
        HelloService helloService = ac.getBean("helloService", HelloService.class);
        // 3. 调用helloService组件的方法sayHello
        helloService.sayHello("张三");
    }

    private static void testXmlApplicationContext() {
        // 1. 基于类路径下的hello.xml配置文件初始化spring上下文（spring容器）
        ApplicationContext ac = new ClassPathXmlApplicationContext("hello.xml");
        // 2. 从spring容器获取helloService组件
//        HelloService helloService = (HelloService) ac.getBean("helloService");
        HelloService helloService = ac.getBean("helloService", HelloService.class);
        // 3. 调用helloService组件的方法sayHello
        helloService.sayHello("张三");
    }
}
