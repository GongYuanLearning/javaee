package com.gy.configuration;

import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.service.BookService;
import com.gy.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 配置组件扫描路径，value属性指定包名
@ComponentScan(value = { "com.gy.dao", "com.gy.service", "com.gy.controller" })
@Configuration // 表示当前类是一个配置类
public class ApplicationConfig {
    // 加上Bean注解后，表示该方法返回的是组件，组件的名称就是方法名
    @Bean
    public Book book1() {
        Book book = new Book("123451", "JavaEE实战", 100, "张三");
        return book;
    }

    @Bean
    public Book book2() {
        Book book = new Book("123452", "MySQL实战", 50, "李四");
        return book;
    }

    @Bean
    public Map<String, Book> books() {
        Map<String, Book> books = new HashMap<>();
        books.put("123451", book1());
        books.put("123452", book2());
        return books;
    }

    /*
     * @Bean
     * public BookDao bookDao() {
     * return new BookDaoImpl(books());
     * }
     */

    /**
     * 通过参数来提供组件，首先按参数名查找组件，再按组件类型查找
     * 
     * @param bookDao
     * @return
     */
    @Bean
    public BookService bookService(BookDao bookDao) {
        // 基于setter来注入依赖
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.setDao(bookDao);
        bookService.setUsername("zhijie");
        return bookService;
    }

    @Bean
    public List<String> users() {
        List<String> users = new ArrayList<>();
        users.add("user1");
        users.add("user2");
        return users;
    }
}
