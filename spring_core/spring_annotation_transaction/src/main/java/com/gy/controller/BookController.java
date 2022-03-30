package com.gy.controller;

import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import com.gy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    public List<Book> addBooks(List<Book> books) throws CustomDataException {
        return bookService.addBooks(books);
    }

    public List<Book> getBooks(BookCriteria criteria) {
        return bookService.getBooks(criteria);
    }

    public void deleteAllBooks() {
        bookService.deleteAllBooks();
    }
}
