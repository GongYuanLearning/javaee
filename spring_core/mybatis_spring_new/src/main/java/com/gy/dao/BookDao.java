package com.gy.dao;

import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookDao {
    /**
     * 添加book
     * @param book
     * @return
     * @throws CustomDataException
     */
    public int add(Book book) throws CustomDataException;

    public int update(Book book);

    /**
     * 根据主键id获取book
     * @param id
     * @return
     */
    public Book getById(long id);
    public List<Book> getByIds(List<Long> ids);

    public List<Book> query(BookCriteria criteria);
    List<Book> queryWithMap(Map<String, Object> criteria);
    public void deleteById(long id);
    void deleteAll();
}
