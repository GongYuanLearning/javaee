package com.gy.service.impl;

import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import com.gy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 该类所有的public方法都拥有事务管理
// @Transactional
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    // 加上该注解后，该方法拥有事务管理功能
    // 默认在发生RuntimeException异常的时候，进行回滚
    // 使用rollbackFor来对自定义异常CustomDataException进行回滚
    // @Transactional(rollbackFor = {Exception.class})
    @Transactional(rollbackForClassName = {"java.lang.Exception"},
    noRollbackFor = {IllegalArgumentException.class})
    public List<Book> addBooks(List<Book> books) throws CustomDataException {
        for (int i = 0; i < books.size(); i++) {
            if(null == books.get(i).getName()) {
                throw new IllegalArgumentException("书名不能为null");
            }
            int id = this.bookDao.add(books.get(i));
            books.get(i).setId(id);
        }
        return books;
    }

    // 方法只读取数据库，要申明readOnly=true，表名这是一个只读事务
    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooks(BookCriteria criteria) {
        return this.bookDao.query(criteria);
    }

    @Override
    public void deleteAllBooks() {
        this.bookDao.deleteAllBooks();
    }
}
