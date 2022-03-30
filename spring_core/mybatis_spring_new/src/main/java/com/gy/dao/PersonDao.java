package com.gy.dao;

import com.gy.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonDao {
    Person getById(long id);
    Person getByIdWithOneQuery(long id);
}
