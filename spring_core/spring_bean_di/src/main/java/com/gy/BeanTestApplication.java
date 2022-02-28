package com.gy;

import com.gy.configuration.ApplicationConfig;
import com.gy.controller.BookController;
import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTestApplication {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        // 获取@Component注解的组件
        // 获取@Repository注解的组件
        BookDao bookDao = ac.getBean("bookDaoImpl", BookDao.class);
        System.out.println("bookDaoImpl存在：" + (bookDao != null));

        // 获取@Service注解的组件
        BookService bookServiceImpl = ac.getBean("bookServiceImpl", BookService.class);
        System.out.println("bookServiceImpl存在：" + (bookServiceImpl != null));

        // 通过ApplicationConfig配置的组件
        BookService bookService = ac.getBean("bookService", BookService.class);
        System.out.println("bookService存在：" + (bookService != null));

        BookController bookController = ac.getBean("bookController", BookController.class);
        bookController.getAll();
        Book book = new Book();
        book.setIsbn("123456");
        book.setName("JavaEE实战");
        book.setPrice(100);
        book.setAuthor("张三");
        bookController.addBook(book);

        bookController.getAll();
    }
}
