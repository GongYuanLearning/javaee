package com.gy.controller;

import com.gy.entity.Book;
import com.gy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @Autowired
    private BookService bookServiceImpl;

    public void addBook(Book book) {
        this.bookServiceImpl.add(book);
    }

    public void getAll() {
        this.bookServiceImpl.getAll();
    }
}
