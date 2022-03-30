package com.gy.dao.impl;

import com.gy.config.AppConfig;
import com.gy.dao.BookDao;
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

import java.util.List;

import static org.junit.Assert.*;

// 运行spring测试
@RunWith(SpringRunner.class)
// 根据提供的配置类，自动初始化spring容器
@ContextConfiguration(classes = AppConfig.class)
public class BookDaoImplTest {
    @Autowired
    private BookDaoImpl bookDaoImpl;

    /**
     * 调用测试方法之前，要执行的逻辑。
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * 调用测试方法之后，要执行的逻辑。
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test // 代表是一个测试方法
    public void testAdd() throws CustomDataException {
        Book book = new Book();
        book.setName("MySQL");
        book.setIsbn("22222");
        book.setPrice(88);
        book.setCount(10);
        int id = bookDaoImpl.add(book);
        assertTrue(id > 0);
        // 查询数据库对应id的book
        Book bookInDB = bookDaoImpl.get(id);
        assertEquals(book.getIsbn(), bookInDB.getIsbn());
        assertEquals(book.getName(), bookInDB.getName());
        assertEquals(book.getPrice(), bookInDB.getPrice());
        assertEquals(book.getCount(), bookInDB.getCount());
    }

    @Test
    public void update() {
    }

    @Test
    public void testGet() {
        Book book = bookDaoImpl.get(1);
        assertEquals("123", book.getIsbn());
        assertEquals("Java2EE", book.getName());
        assertEquals(100, book.getPrice());
        assertEquals(50, book.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuery_IAE() {
        bookDaoImpl.query(null);
    }

    @Test
    public void testQuery_with_name() {
        BookCriteria criteria = new BookCriteria();
        criteria.setName("sql");
        List<Book> books = bookDaoImpl.query(criteria);
        assertEquals(1, books.size());
        assertEquals("MySQL", books.get(0).getName());
        assertEquals(88, books.get(0).getPrice());
        assertEquals(10, books.get(0).getCount());
    }

    @Test
    public void testQuery_with_min_max_price() {
        BookCriteria criteria = new BookCriteria();
        criteria.setMinPrice(88);
        criteria.setMaxPrice(100);
        List<Book> books = bookDaoImpl.query(criteria);
        assertEquals(2, books.size());
        assertEquals("Java2EE", books.get(0).getName());
        assertEquals(100, books.get(0).getPrice());
        assertEquals(50, books.get(0).getCount());

        assertEquals("MySQL", books.get(1).getName());
        assertEquals(88, books.get(1).getPrice());
        assertEquals(10, books.get(1).getCount());
    }

    @Test
    public void delete() {
    }
}