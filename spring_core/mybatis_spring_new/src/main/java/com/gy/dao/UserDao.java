package com.gy.dao;

import com.gy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getById(long id);
    User getByIdWithOneQuery(long id);
}
