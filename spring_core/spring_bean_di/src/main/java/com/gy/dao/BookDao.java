package com.gy.dao;

import com.gy.entity.Book;

public interface BookDao {
    void add(Book book);
    void edit(Book book);
    void delete(String isbn);
    void getAll();
}
