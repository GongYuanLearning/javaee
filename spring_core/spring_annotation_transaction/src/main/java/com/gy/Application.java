package com.gy;

import com.gy.config.AppConfig;
import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import com.gy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws CustomDataException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = ac.getBean("bookService", BookService.class);

        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setName("Java2EE");
        book1.setIsbn("11111");
        book1.setPrice(50);
        book1.setCount(100);
        books.add(book1);
        Book book2 = new Book();
        book2.setName("MySQL");
        book2.setIsbn("11112"); // isbn是unique约束，book2插入失败
        book2.setPrice(50);
        book2.setCount(100);
        books.add(book2);
        bookService.addBooks(books);
    }
}
