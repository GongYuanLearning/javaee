package com.gy.dao.impl;

import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int add(Book book) throws CustomDataException {

/*        String sql = "INSERT INTO BOOK(ISBN, NAME, PRICE, COUNT)" +
                " VALUES(:isbn, :name, :price, :count)";
        Map<String, Object> params = new HashMap<>();
        params.put("isbn", book.getIsbn());
        params.put("name", book.getName());
        params.put("price", book.getPrice());
        params.put("count", book.getCount());

        jdbcTemplate.update(sql, params, keyHolder);*/

        String sql = "INSERT INTO BOOK(ISBN, NAME, PRICE, COUNT) VALUES(?, ?, ?, ?)";
        // jdbcTemplate.update(sql, book.getIsbn(), book.getName(), book.getPrice(), book.getCount());
//        jdbcTemplate.update(sql, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                ps.setString(1, book.getIsbn());
//                ps.setString(2, book.getName());
//                ps.setInt(3, book.getPrice());
//                ps.setInt(4, book.getCount());
//            }
//        });

         // KeyHolder主键容纳类
         KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, book.getIsbn());
                ps.setString(2, book.getName());
                ps.setInt(3, book.getPrice());
                ps.setInt(4, book.getCount());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue(); // 返回主键
        // return 0;
    }

    @Override
    public Book update(Book book) {
        String sql = "UPDATE BOOK SET COUNT=:count WHERE id=:id";
        Map<String, Object> params = new HashMap<>();
        params.put("count", book.getCount());
        params.put("id", book.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return book;
    }

    @Override
    public Book get(long id) {
        String sql = "SELECT id, isbn, name, price, count FROM BOOK WHERE id=?";
        /*List<Book> books = jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getInt("price"));
                book.setCount(rs.getInt("count"));
                return book;
            }
        }, id);
        return !books.isEmpty() ? books.get(0) : null;*/
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
/*        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setIsbn(rs.getString("isbn"));
            book.setName(rs.getString("name"));
            book.setPrice(rs.getInt("price"));
            book.setCount(rs.getInt("count"));
            return book;
        }, id);*/
    }

    @Override
    public List<Book> query(BookCriteria criteria) {
        if(null == criteria) {
            throw new IllegalArgumentException("criteria can not be null");
        }
        StringBuilder sql = new StringBuilder("SELECT * FROM BOOK WHERE 1=1 ");
        if(null != criteria.getName() &&
                !criteria.getName().trim().isEmpty()) {
            sql.append(" AND NAME like '%" + criteria.getName().trim() + "%' ");
        }
        if(criteria.getMinPrice() > 0) {
            sql.append(" AND PRICE >= " + criteria.getMinPrice());
        }
        if(criteria.getMaxPrice() > 0) {
            sql.append(" AND PRICE <= " + criteria.getMaxPrice());
        }
        List<Book> books = jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            Book b = new Book();
            b.setId(rs.getLong("ID"));
            b.setIsbn(rs.getString("ISBN"));
            b.setName(rs.getString("NAME"));
            b.setPrice(rs.getInt("PRICE"));
            b.setCount(rs.getInt("COUNT"));
            return b;
        });
        return books;
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM BOOK WHERE ID=?";
        jdbcTemplate.update(sql, id);
    }
}
