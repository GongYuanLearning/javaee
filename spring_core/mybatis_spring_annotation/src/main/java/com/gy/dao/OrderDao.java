package com.gy.dao;

import com.gy.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    List<OrderDao> getByUserId(long userId);
    Order getById(long id);
}
