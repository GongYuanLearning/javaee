package com.gy;

import com.gy.configuration.ApplicationConfig;
import com.gy.service.HelloService;
import com.gy.service.impl.DefaultHelloService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class HelloApplication {
    /**
     * 基于注解的spring应用
     * @param args
     */
    public static void main(String[] args) {
        testXmlApplicationContext();

        //  testAnnotationApplicationContext();

        // testBeanFactory();
    }


    private static void testBeanFactory() {
//        Resource resource = new ClassPathResource("hello.xml");
        Resource resource = new FileSystemResource("D:\\teach\\Javaee\\sample2022\\first_spring_project\\src\\main\\resources\\hello.xml");
        // IoC容器初始化
        BeanFactory bf = new XmlBeanFactory(resource);
        final HelloService helloService =
                (HelloService) bf.getBean("helloService");
        helloService.sayHello("zhijie");
    }

    private static void testAnnotationApplicationContext() {
        // 1. 基于注解的形式初始化spring上下文（spring容器），提供配置类的类class
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // 2. 从spring容器获取helloService组件
        HelloService helloService = ac.getBean("helloService", HelloService.class);
        // 3. 调用helloService组件的方法sayHello
        helloService.sayHello("张三");
    }

    /**
     * 基于xml的spring应用
     */
    private static void testXmlApplicationContext() {
        // 1. 基于类路径下的hello.xml配置文件初始化spring上下文（spring容器）
        // ApplicationContext ac = new ClassPathXmlApplicationContext("config/hello.xml");
        // 1. 基于文件路径的hello.xml配置文件初始化spring上下文（spring容器）
        // 相对路径是相对于当前项目的根路径而言
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/config/hello.xml");
        // 2. 从spring容器获取helloService组件
//        HelloService helloService = (HelloService) ac.getBean("helloService");
        HelloService helloService = ac.getBean("helloService", HelloService.class);
        // 3. 调用helloService组件的方法sayHello
        helloService.sayHello("张三");
    }
}
