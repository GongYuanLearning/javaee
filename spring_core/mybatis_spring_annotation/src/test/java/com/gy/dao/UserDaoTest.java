package com.gy.dao;

import com.gy.config.MyBatisDB;
import com.gy.entity.Person;
import com.gy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyBatisDB.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void getById() {
        User user = userDao.getById(1);
        assertUser(user);
    }

    @Test
    public void getByIdWithOneQuery() {
        User user = userDao.getByIdWithOneQuery(1);
        assertUser(user);
    }

    private void assertUser(User user) {
        assertEquals(1L, +user.getId());
        assertEquals("张九", user.getName());
        assertEquals("女", user.getSex());
        assertNotNull(user.getOrders());
        assertEquals(2, user.getOrders().size());
        assertEquals(1, +user.getOrders().get(0).getId());
        assertEquals("999999", user.getOrders().get(0).getOrdersn());
        assertEquals(2, +user.getOrders().get(1).getId());
        assertEquals("88888", user.getOrders().get(1).getOrdersn());
    }
}