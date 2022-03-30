package com.gy.dao;

import com.gy.config.AppConfig;
import com.gy.config.DataAccessConfig;
import com.gy.entity.User;
import com.gy.entity.UserCriteria;
import com.gy.exception.CustomDataException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserDaoImplTest {
    @Autowired
    private UserDao userDaoImpl;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetById() throws CustomDataException {
        User user = userDaoImpl.getById(1);
        assertEquals(1, user.getId());
        assertEquals("x", user.getName());
        assertEquals("y", user.getSex());
        user = userDaoImpl.getById(31);
        assertEquals(31, user.getId());
        assertEquals("张四", user.getName());
        assertEquals("男", user.getSex());
    }

    @Test
    public void testGet() {
        List<User> users = userDaoImpl.get(new UserCriteria());
        assertEquals(5, users.size());
    }

    @Test
    public void testDelete() throws CustomDataException {
        userDaoImpl.delete(1);
        User user = userDaoImpl.getById(1);
        assertNull(user);
    }

    @Test
    public void testCreate() throws CustomDataException {
        User user = new User();
        user.setName("lzj2");
        user.setSex("Male");
        User created = userDaoImpl.create(user);
        assertEquals(user.getName(), created.getName());
        assertEquals(user.getSex(), created.getSex());
    }

    @Test
    public void testUpdate() throws CustomDataException {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        User updatedUser = userDaoImpl.update(user);
        assertEquals("张三", updatedUser.getName());
    }
}