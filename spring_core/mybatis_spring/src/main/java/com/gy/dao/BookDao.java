package com.gy.dao;

import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
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
    public Book getById(long id);

    public List<Book> query(BookCriteria crteria);
    public void delete(long id);
}
