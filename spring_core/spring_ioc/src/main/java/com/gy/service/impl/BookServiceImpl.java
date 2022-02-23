package com.gy.service.impl;

import com.gy.service.BookDao;
import com.gy.service.BookService;

import com.gy.entity.Book;

/**
 * 实现业务逻辑(添加，修改，删除书)的服务类
 */
public class BookServiceImpl implements BookService {
    private BookDao dao;
    private String username;

    // 构造方法依赖注入必须要有带参数的构造方法
    // 方法当中，依次把参数赋值给字段
    public BookServiceImpl(BookDao dao, String username) {
        this.dao = dao;
        this.username = username;
    }

    public BookServiceImpl() {
    }

    public void setDao(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public void add(Book book) {
        dao.add(book);
    }

    @Override
    public void edit(Book book) {
        dao.edit(book);
    }

    @Override
    public void delete(String isbn) {
        dao.delete(isbn);
    }

    @Override
    public void getAll() {
        dao.getAll();
    }
}
