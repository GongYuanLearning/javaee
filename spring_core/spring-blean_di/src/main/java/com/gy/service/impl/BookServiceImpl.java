package com.gy.service.impl;

import com.gy.dao.BookDao;
import com.gy.dao.BookDao;
import com.gy.service.BookService;

import com.gy.entity.Book;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 实现业务逻辑(添加，修改，删除书)的服务类
 */
// 标注为服务层的组件
@Service
@Data
//public class BookServiceImpl implements BookService, BeanNameAware, ApplicationContextAware, InitializingBean {
public class BookServiceImpl implements BookService, BeanNameAware, ApplicationContextAware, DisposableBean {
    private BookDao dao;
    private String username;
    private String beanName;
    private ApplicationContext ac;

    // 构造方法依赖注入必须要有带参数的构造方法
    // 方法当中，依次把参数赋值给字段
    public BookServiceImpl(BookDao dao, String username) {
        this.dao = dao;
        this.username = username;
    }

    public BookServiceImpl() {
    }

    public void setDao(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public void add(Book book) {
        System.out.println("进入add方法");
        System.out.println(String.format("当前组件名称是：%s", this.beanName));
        try {
            dao.add(book);
        } catch (Exception e) {
            System.err.println(String.format("add方法发生异常：%s", e.getMessage()));
        }
        System.out.println("退出add方法");
    }

    @Override
    public void edit(Book book) {
        // entrance
        System.out.println("进入edit方法");
        dao.edit(book);
        // exit
    }

    @Override
    public void delete(String isbn) {
        dao.delete(isbn);
    }

    @Override
    public void getAll() {
        dao.getAll();
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

    public void afterPropertiesSet() {
        if(Objects.isNull(this.dao)) {
            throw new IllegalStateException("dao字段不能为null!");
        }
        if(Objects.isNull(this.username) || StringUtils.isEmpty(this.username)) {
            throw new IllegalStateException("username字段不能为null/空字符!");
        }
    }

    // init方法用来检测组件是否满足一定的条件，比如字段不能为null。
    public void init() {
        // afterPropertiesSet();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(String.format("DisposableBean的destroy方法: %s销毁之前，释放资源。", this.beanName));
    }

    // @PreDestroy
    public void customDestroy() throws Exception {
        System.out.println(String.format("customDestroy方法: %s销毁之前，释放资源。", this.beanName));
    }

//    @PostConstruct
//    public void postConstruct() {
//        afterPropertiesSet();
//    }
}
