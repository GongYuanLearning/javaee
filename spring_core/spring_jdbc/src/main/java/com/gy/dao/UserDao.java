package com.gy.dao;

import com.gy.entity.User;
import com.gy.entity.UserCriteria;
import com.gy.exception.CustomDataException;

import java.util.List;

public interface UserDao {
    User create(User user) throws CustomDataException;
    User update(User user) throws CustomDataException;
    User getById(int id) throws CustomDataException;
    List<User> get(UserCriteria criteria);
    void delete(int id);
}
