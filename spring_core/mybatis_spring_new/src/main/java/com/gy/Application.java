package com.gy;

import com.gy.config.MyBatisDB;
import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Application {
    public static void main(String[] args) throws CustomDataException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MyBatisDB.class);

        BookDao bookDao = ac.getBean("bookDao", BookDao.class);
        Book book = new Book();
        book.setName("Java2EE");
        book.setIsbn(UUID.randomUUID().toString());
        book.setPrice(50);
        book.setCount(100);
        int count = bookDao.add(book);
        System.out.println(book.getId());

        System.out.println(bookDao.getById(4L));

        Map<String, Object> criteriaMap = new HashMap<>();
        criteriaMap.put("name", "Java");
        criteriaMap.put("isbn", "123");
        criteriaMap.put("price", "50");

        bookDao.queryWithMap(criteriaMap).forEach(b -> System.out.println(b));

        BookCriteria bookCriteria = new BookCriteria();
        bookCriteria.setName("Java");
        bookCriteria.setMinPrice(20);
        bookCriteria.setMaxPrice(100);
        System.out.println("通过query查询Book：");
        bookDao.query(bookCriteria).forEach(b -> System.out.println(b));
    }
}
