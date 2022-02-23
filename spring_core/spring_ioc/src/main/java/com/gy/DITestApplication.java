package com.gy;

import com.gy.configuration.ApplicationConfig;
import com.gy.entity.Book;
import com.gy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DITestApplication {
    public static void main(String[] args) {
        //ApplicationContext ac = new ClassPathXmlApplicationContext("di_config.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        BookService bookService = ac.getBean("bookService", BookService.class);
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
