package com.gy;

import com.gy.config.AppConfig;
import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.entity.BookCriteria;
import com.gy.exception.CustomDataException;
import com.gy.service.BookService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Application {
    public static void main(String[] args) throws CustomDataException {
        InputStream in = Application.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(in);

        try (SqlSession session = ssf.openSession()) {
            Book book = new Book();
            book.setIsbn(UUID.randomUUID().toString());
            book.setName("JSP");
            book.setPrice(50);
            book.setCount(10);
            // 调用session的insert方法插入数据，第一个参数对应的是Mapper文件中的namespace+.+节点insert的id值
            int count = session.insert("com.gy.dao.BookDao.add", book);
            session.commit(); // 提交事务
/*

        book.setId(4);
        book.setName("JAVA");
        book.setCount(100);
        count = session.update("com.gy.model.BookMapper.update", book);
        session.commit();
*/
            Book bookInDB = session.selectOne("com.gy.dao.BookDao.getById", 4L);
            System.out.println(bookInDB);
//
//        Assert.assertEquals(book.getName(), bookInDB.getName());
//        Assert.assertEquals(book.getCount(), bookInDB.getCount());

            session.close(); // 关闭session
        }
    }
}
