package com.gy.service.impl;

import com.gy.dao.BookDao;
import com.gy.service.BookService;
import lombok.Data;

@Data
public class BookServiceFactory {
    private BookDao bookDao;
    private String username;

    public BookService createBookService() {
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.setDao(bookDao);
        bookService.setUsername(username);
        return bookService;
    }
}
