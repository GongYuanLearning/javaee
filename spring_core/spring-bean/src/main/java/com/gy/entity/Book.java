package com.gy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

// @AllArgsConstructor // 提供带所有参数的构造方法
@Data
public class Book {
    private String isbn;
    private String name; // 书名
    private double price;
    private String author;

    public Book() {
    }

    public Book(String isbn, String name, double price, String author) {
        this.isbn = isbn;
        this.name = name;
        this.price = price;
        this.author = author;
    }
}
