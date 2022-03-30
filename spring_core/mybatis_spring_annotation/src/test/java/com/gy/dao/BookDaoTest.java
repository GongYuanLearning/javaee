package com.gy.dao;

import com.gy.config.MyBatisDB;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyBatisDB.class)
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    private List<Long> ids = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        bookDao.deleteAll();
        ids.clear();
        for(int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setName("Java " + i);
            book.setIsbn(UUID.randomUUID().toString());
            book.setPrice(50 + i * 10);
            book.setCount(10 + i * 10);
            bookDao.add(book);
            ids.add(book.getId());
        }
    }

    @After
    public void tearDown() throws Exception {
        //bookDao.deleteAll();
    }

    @Test
    public void getById() {
        bookDao.getById(ids.get(0));
    }

    @Test
    public void add() throws CustomDataException {
        bookDao.add(new Book());
    }

    @Test
    public void update() {
        BookCriteria criteria = new BookCriteria();
        criteria.setName("java 1");
        List<Book> books = bookDao.query(criteria);
        Book book = books.get(0);
        book.setName("java updated");
        // book.setCount(0); // 设为0，表示不更新count
        bookDao.update(book); // 更新book
        Book bookUpdated = bookDao.getById(book.getId());
        assertEquals(book.getName(), bookUpdated.getName());
    }

    @Test
    public void query_if() {
        BookCriteria criteria = new BookCriteria();
        List<Book> books = bookDao.query(criteria);
        assertEquals(5, books.size());
        criteria.setName("java 1");
        books = bookDao.query(criteria);
        assertEquals(1, books.size());
        criteria.setName("java");
        criteria.setMinPrice(66);
        criteria.setMaxPrice(80);
        books = bookDao.query(criteria);
        assertEquals(2, books.size());
        assertEquals("Java 2", books.get(0).getName());
        assertEquals(70, books.get(0).getPrice());
        assertEquals("Java 3", books.get(1).getName());
        assertEquals(80, books.get(1).getPrice());
    }

    @Test
    public void query_choose_when_otherwise() {
        BookCriteria criteria = new BookCriteria();
        //List<Book> books = bookDao.query(criteria);
        //assertEquals(4, books.size());
        criteria.setName("java 1");
        List<Book>  books = bookDao.query(criteria);
        assertEquals(1, books.size());
        criteria.setName("java");
        criteria.setMinPrice(66);
        criteria.setMaxPrice(80);
        books = bookDao.query(criteria);
        assertEquals(2, books.size());
        assertEquals("Java 2", books.get(0).getName());
        assertEquals(70, books.get(0).getPrice());
        assertEquals("Java 3", books.get(1).getName());
        assertEquals(80, books.get(1).getPrice());
    }

    @Test
    public void queryWithMap_where() {
        Map<String, Object> criteria = new HashMap<>();
        List<Book> books = bookDao.queryWithMap(criteria);
        assertEquals(5, books.size());
        criteria.put("name", "java 1");
        books = bookDao.queryWithMap(criteria);
        assertEquals(1, books.size());
        criteria.put("name", "java");
        criteria.put("minPrice", 66);
        criteria.put("maxPrice", 80);
        books = bookDao.queryWithMap(criteria);
        assertEquals(2, books.size());
        assertEquals("Java 2", books.get(0).getName());
        assertEquals(70, books.get(0).getPrice());
        assertEquals("Java 3", books.get(1).getName());
        assertEquals(80, books.get(1).getPrice());
    }
}