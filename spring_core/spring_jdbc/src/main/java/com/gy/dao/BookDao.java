package com.gy.dao;

import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;

import java.util.List;

public interface BookDao {
    /**
     * 添加book
     * @param book
     * @return
     * @throws CustomDataException
     */
    public int add(Book book) throws CustomDataException;
    public Book update(Book book);

    /**
     * 根据主键id获取book
     * @param id
     * @return
     */
    public Book get(long id);
    public List<Book> query(BookCriteria crteria);
    public void delete(long id);
}
