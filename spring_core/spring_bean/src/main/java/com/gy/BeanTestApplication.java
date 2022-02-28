package com.gy;

import com.gy.configuration.ApplicationConfig;
import com.gy.entity.Book;
import com.gy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTestApplication {
    public static void main(String[] args) {
        // testBeanInitialization();
        // testBeanScope();
        testBeanLifeCycle();
    }


    private static void testBeanLifeCycle() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean_config.xml");
        // ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        BookService bookService = ac.getBean("bookService1", BookService.class);
        bookService.getAll();
        Book book = new Book();
        book.setIsbn("123456");
        book.setName("JavaEE实战");
        book.setPrice(100);
        book.setAuthor("张三");
        bookService.add(book);

        bookService.getAll();

        ac.close(); // 关闭容器，会销毁所有组件
    }

    private static void testBeanScope() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        // singleton 作用域对象
        BookService bookService1 = ac.getBean("bookService", BookService.class);
        BookService bookService2 = ac.getBean("bookService", BookService.class);
        System.out.println(String.format("Singleton作用域获取的组件是否是同一实例：%s", bookService1 == bookService2));

        // prototype 作用域对象
        BookService bookService3 = ac.getBean("bookService1", BookService.class);
        BookService bookService4 = ac.getBean("bookService1", BookService.class);
        System.out.println(String.format("Prototype作用域获取的组件是否是同一实例：%s", bookService3 == bookService4));
    }

    private static void testBeanInitialization() {
        // ApplicationContext ac = new ClassPathXmlApplicationContext("bean_config.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        BookService bookService = ac.getBean("bookService2", BookService.class);
        bookService.getAll();
        Book book = new Book();
        book.setIsbn("123456");
        book.setName("JavaEE实战");
        book.setPrice(100);
        book.setAuthor("张三");
        bookService.add(book);

        bookService.getAll();
    }
}
