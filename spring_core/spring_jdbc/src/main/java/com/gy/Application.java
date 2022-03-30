package com.gy;

import com.gy.config.AppConfig;
import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) throws CustomDataException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        BookDao bookDao = ac.getBean("bookDao", BookDao.class);
        Book book = new Book();
        book.setName("Java2EE");
        book.setIsbn("12445");
        book.setPrice(50);
        book.setCount(100);
        int id = bookDao.add(book);
        book = bookDao.get(id);
        System.out.println(book);
        BookCriteria criteria = new BookCriteria();
        criteria.setName("Java");
        List<Book> books = bookDao.query(criteria);
        System.out.println(books.size());
    }
}
