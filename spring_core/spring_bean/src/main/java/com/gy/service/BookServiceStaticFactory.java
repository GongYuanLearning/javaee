package com.gy.service;

import com.gy.service.impl.BookServiceImpl;

public class BookServiceStaticFactory {
    // 单例
    private static BookService bookService = new BookServiceImpl();
    public static BookService createBookService1() {
        return bookService;
    }
    public static BookService createBookService2(BookDao bookDao, String username) {
        BookService bookService = new BookServiceImpl(bookDao, username);
        return bookService;
    }
}
