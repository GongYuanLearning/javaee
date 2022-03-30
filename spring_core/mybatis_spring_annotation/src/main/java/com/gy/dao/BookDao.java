package com.gy.dao;

import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookDao {
    /**
     * 添加book
     *
     * @param book
     * @return
     * @throws CustomDataException
     */
    @Insert("insert into BOOK(id, isbn, name, price, count) values(#{id}, #{isbn}, #{name}, #{price}, #{count})")
    @SelectKey(
            statement = "select if(max(id) is null, 1, max(id)+1) as newId from BOOK",
            keyProperty = "id",
            before = true,
            resultType = int.class)
    int add(Book book) throws CustomDataException;

    @Update("update BOOK set name=#{name} where id=#{id}")
    int update(Book book);

    /**
     * 根据主键id获取book
     *
     * @param id
     * @return
     */
    @Select("select * from BOOK where id=#{id}")
    @Results(value = {
            @Result(id=true, property="id", column = "ID"),
            @Result(property="isbn", column = "ISBN"),
            @Result(property="name", column = "NAME"),
            @Result(property="price", column = "PRICE"),
            @Result(property="count", column = "COUNT")
    })
    Book getById(long id);

    List<Book> getByIds(List<Long> ids);

    // @SelectProvider
    List<Book> query(BookCriteria criteria);

    List<Book> queryWithMap(Map<String, Object> criteria);

    @Delete("delete from BOOK where id=#{id}")
    void deleteById(long id);

    @Delete("delete from BOOK")
    void deleteAll();
}
