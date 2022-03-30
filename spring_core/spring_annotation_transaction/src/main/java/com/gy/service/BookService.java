package com.gy.service;

import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;

import java.util.List;

public interface BookService {
    List<Book> addBooks(List<Book> books) throws CustomDataException;

    List<Book> getBooks(BookCriteria criteria);

    void deleteAllBooks();
}
