package com.gy.dao.impl;

import com.gy.dao.UserDao;
import com.gy.entity.User;
import com.gy.entity.UserCriteria;
import com.gy.exception.CustomDataException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JdbcDaoSupport
// NamedParameterJdbcTemplate
// NamedParameterJdbcDaoSupport
@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User create(User user) throws CustomDataException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement psmt = con.prepareStatement("INSERT INTO user(name, sex) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getSex());
            return psmt;
        }, keyHolder);
        return getById(keyHolder.getKey().intValue());
    }

    @Override
    public User update(User user) throws CustomDataException {
        this.jdbcTemplate.update(con -> {
            StringBuilder sql = new StringBuilder("UPDATE USER ");
            if(StringUtils.hasText(user.getName()) && StringUtils.hasText(user.getSex())) {
                sql.append(" SET name=?, sex=?");
            } else if(StringUtils.hasText(user.getName())) {
                sql.append(" SET name=?");
            } else if(StringUtils.hasText(user.getSex())) {
                sql.append(" SET sex=?");
            }
            sql.append(" WHERE id=?");
            PreparedStatement psmt = con.prepareStatement(sql.toString());
            if(StringUtils.hasText(user.getName()) && StringUtils.hasText(user.getSex())) {
                psmt.setString(1, user.getName());
                psmt.setString(2, user.getSex());
                psmt.setInt(3, user.getId());
            } else if(StringUtils.hasText(user.getName())) {
                psmt.setString(1, user.getName());
                psmt.setInt(2, user.getId());
            } else if(StringUtils.hasText(user.getSex())) {
                psmt.setString(1, user.getSex());
                psmt.setInt(2, user.getId());
            }
            return psmt;
        });
        return getById(user.getId());
    }

    @Override
    public User getById(int id) throws CustomDataException {
//        List<User> users = this.jdbcTemplate.query(String.format("SELECT * FROM user where id=%d", id), new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet resultSet, int i) throws SQLException {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setSex(resultSet.getString("sex"));
//                return user;
//            }
//        });
//        return users.get(0);
        String query = "SELECT id, name, sex FROM user where id=:id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(query, params,
                BeanPropertyRowMapper.newInstance(User.class));

//        String query = String.format("SELECT id, name, sex FROM user where id=%d", id);
////        User user = this.jdbcTemplate.queryForObject(query, (resultSet, i) -> {
////            User user1 = new User();
////            user1.setId(resultSet.getInt("id"));
////            user1.setName(resultSet.getString("name"));
////            user1.setSex(resultSet.getString("sex"));
////            return user1;
////        });
//        User user = null;
//        try {
//            user = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class));
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        } catch (DataAccessException e) {
//            throw new CustomDataException("不能获取User数据", e);
//        }
//        User user =  jdbcTemplate.queryForObject(query, User.class);
    }

    @Override
    public List<User> get(UserCriteria criteria) {
        StringBuilder query = new StringBuilder("SELECT * FROM user WHERE 1=1 ");
        if (StringUtils.hasText(criteria.getName())) {
            query.append(" AND name like '%").append(criteria.getName()).append("%'").append(" ");
        }
        if (StringUtils.hasText(criteria.getSex())) {
            query.append(" AND sex='").append(criteria.getSex()).append("'");
        }

        /*List<User> users = jdbcTemplate.query(query.toString(), (rs, rowNum) -> {
            User user1 = new User();
            user1.setId(rs.getInt("id"));
            user1.setName(rs.getString("name"));
            user1.setSex(rs.getString("sex"));
            return user1;
        });
        return users;*/
        return jdbcTemplate.queryForList(query.toString(), User.class);
    }

    @Override
    public void delete(int id) {
        PreparedStatementCreator preparedStatementCreator = conn -> {
            PreparedStatement psmt = conn.prepareStatement("DELETE FROM user WHERE id=?");
            psmt.setInt(1, id);
            return psmt;
        };
        this.jdbcTemplate.update(preparedStatementCreator);
    }
}
