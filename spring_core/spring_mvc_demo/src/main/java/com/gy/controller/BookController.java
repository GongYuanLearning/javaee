package com.gy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.print.Book;

/**
 * 对Book的增删改查请求进行响应。
 */
// value只要配置资源部分/book：http://localhost:8080/demo/book
@RequestMapping(value = {"/book", "/books"})
@Controller
public class BookController {
    // 标识该方法是一个请求处理方法
    @RequestMapping("/add")
    public String addBook(Book book) {
        return "book";
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET)
    public String getBook(long id) {
        return "book";
    }
}
