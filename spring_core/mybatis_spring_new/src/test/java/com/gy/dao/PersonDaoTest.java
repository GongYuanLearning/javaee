package com.gy.dao;

import com.gy.config.MyBatisDB;
import com.gy.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyBatisDB.class)
public class PersonDaoTest {
    @Autowired
    private PersonDao personDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
        Person person = personDao.getById(1);
        assertEquals(1L, +person.getId());
        assertEquals("陈恒", person.getName());
        assertEquals(88, +person.getAge());
        assertNotNull(person.getCard());
    }

    @Test
    public void getByIdWithOneQuery() {
        Person person = personDao.getByIdWithOneQuery(1);
        assertEquals(1L, +person.getId());
        assertEquals("陈恒", person.getName());
        assertEquals(88, +person.getAge());
        assertNotNull(person.getCard());
        assertEquals(1, +person.getCard().getId());
        assertEquals("123456789123456789", person.getCard().getCode());
    }
}