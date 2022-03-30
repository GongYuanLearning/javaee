package com.gy.controller;

import com.gy.config.AppConfig;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class BookControllerTest {
    @Autowired
    private BookController bookController;

    @Before
    public void setUp() throws Exception {
        bookController.deleteAllBooks();
    }

    @After
    public void tearDown() throws Exception {
        // bookController.deleteAllBooks();
    }

    @Test
    public void addBooks() throws CustomDataException {
        BookCriteria criteria = new BookCriteria();
        List<Book> booksInDB = bookController.getBooks(criteria);
        assertEquals(0, booksInDB.size());

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

        bookController.addBooks(books);

        booksInDB = bookController.getBooks(criteria);
        assertEquals(2, booksInDB.size());
    }


    // DuplicateKeException
    @Test(expected = CustomDataException.class)
    public void addBooks_CustomDataException() throws CustomDataException {
        BookCriteria criteria = new BookCriteria();
        List<Book> booksInDB = bookController.getBooks(criteria);
        assertEquals(0, booksInDB.size());

        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setName("Java2EE");
        book1.setIsbn("11113");
        book1.setPrice(50);
        book1.setCount(100);
        books.add(book1);

        Book book2 = new Book();
        book2.setName("MySQL");
        book2.setIsbn("11113"); // isbn是unique约束，book2插入失败
        book2.setPrice(50);
        book2.setCount(100);
        books.add(book2);

        bookController.addBooks(books);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addBooks_IllegalArgumentException() throws CustomDataException {
        BookCriteria criteria = new BookCriteria();
        List<Book> booksInDB = bookController.getBooks(criteria);
        assertEquals(0, booksInDB.size());

        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setName("Java2EE");
        book1.setIsbn("11113");
        book1.setPrice(50);
        book1.setCount(100);
        books.add(book1);

        Book book2 = new Book();
        book2.setIsbn("11114"); // isbn是unique约束，book2插入失败
        book2.setPrice(50);
        book2.setCount(100);
        books.add(book2);

        bookController.addBooks(books);
    }

    @Test
    public void getBooks() {
    }
}