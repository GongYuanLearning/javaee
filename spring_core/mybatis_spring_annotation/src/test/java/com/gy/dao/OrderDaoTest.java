package com.gy.dao;

import com.gy.config.MyBatisDB;
import com.gy.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyBatisDB.class)
public class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;

    @Test
    public void getById() {
        Order order = orderDao.getById(1);
        assertEquals(1L, +order.getId());
        assertEquals("999999", order.getOrdersn());
        assertNotNull(order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals(1, +order.getProducts().get(0).getId());
        assertEquals("好书", order.getProducts().get(0).getName());
        assertEquals(2, +order.getProducts().get(1).getId());
        assertEquals("坏书", order.getProducts().get(1).getName());
    }
}