package com.gy.dao.impl;

import com.gy.dao.BookDao;
import com.gy.entity.Book;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// 声明该类为spring容器中的组件，
// 组件名默认就是首字母小写后的类名,
// 在这个类中，组件名默认就是bookDaoImpl
// @Component(value = "bookDaoImpl")

// 标注为数据访问层的组件
@Repository
@Data
public class BookDaoImpl implements BookDao {
    private Map<String, Book> books = new HashMap<>();

    public BookDaoImpl() {}

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
