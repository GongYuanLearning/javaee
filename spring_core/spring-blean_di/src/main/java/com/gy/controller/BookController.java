package com.gy.controller;

import com.gy.entity.Book;
import com.gy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class BookController {
    // 默认按名称查找
    //@Resource(name = "bookService", type = BookService.class)
    @Autowired
    @Qualifier("bookService")
    private BookService bookService;

    /*@Autowired
    public void setBookServiceImpl(BookService bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }*/

    public void addBook(Book book) {
        this.bookService.add(book);
    }

    public void getAll() {
        this.bookService.getAll();
    }
}
