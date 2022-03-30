package com.gy.service;

import com.gy.service.impl.BookServiceImpl;

public class BookServiceFactory {
    public static BookService createBookService1() {
        return new BookServiceImpl();
    }
    public static BookService createBookService2(BookDao bookDao, String username) {
        BookService bookService = new BookServiceImpl(bookDao, username);
        return bookService;
    }
}
