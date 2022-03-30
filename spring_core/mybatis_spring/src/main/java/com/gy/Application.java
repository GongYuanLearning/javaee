package com.gy;

import com.gy.config.AppConfig;
import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.exception.CustomDataException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) throws CustomDataException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        BookDao bookDao = ac.getBean("bookDao", BookDao.class);
        Book book = new Book();
        book.setName("Java2EE");
        book.setIsbn("123");
        book.setPrice(50);
        book.setCount(100);
        int id = bookDao.add(book);

        System.out.println(bookDao.getById(4L));
    }
}
