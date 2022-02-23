package com.gy.service.impl;

import com.gy.entity.Book;
import com.gy.service.BookDao;

import java.util.HashMap;
import java.util.Map;

public class BookDaoImpl implements BookDao {
    private Map<String, Book> books = new HashMap<>();

    public BookDaoImpl(Map<String, Book> books) {
        this.books = books;
    }

    @Override
    public void add(Book book) {
        books.put(book.getIsbn(), book);
        System.out.println(String.format(
                "添加图书[isbn=%s, name=%s, author=%s, price=%.2f]成功！",
                book.getIsbn(), book.getName(), book.getAuthor(), book.getPrice()));
    }

    @Override
    public void edit(Book book) {
        books.put(book.getIsbn(), book);
        System.out.println(String.format(
                "编辑图书[isbn=%s, name=%s, author=%s, price=%.2f]成功！",
                book.getIsbn(), book.getName(), book.getAuthor(), book.getPrice()));
    }

    @Override
    public void delete(String isbn) {
        books.remove(isbn);
        System.out.println(String.format(
                "删除图书[isbn=%s]成功！", isbn));
    }

    @Override
    public void getAll() {
        System.out.println("所有图书：-------------------------------");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            System.out.println(String.format(
                    "图书[isbn=%s, name=%s, author=%s, price=%.2f]",
                    book.getIsbn(), book.getName(), book.getAuthor(), book.getPrice()));
        }
        System.out.println("--------------------------------------");
    }
}
