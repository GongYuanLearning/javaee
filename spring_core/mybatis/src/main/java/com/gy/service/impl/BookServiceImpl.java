package com.gy.service.impl;

import com.gy.service.BookService;
import com.gy.dao.BookDao;
import com.gy.entity.Book;
import com.gy.exception.CustomDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    // 事务管理器
    @Autowired
    PlatformTransactionManager transactionManager;
    // 事务模板
    @Autowired
    TransactionTemplate transactionTemplate;

    public List<Book> addBooks(List<Book> books) throws CustomDataException {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        // 获取事务，标志事务的开始
        TransactionStatus transaction = transactionManager.getTransaction(transactionDefinition);

        try {
            for (int i = 0; i < books.size(); i++) {
                int id = this.bookDao.add(books.get(i));
                books.get(i).setId(id);
            }
            // 提交事务
            transactionManager.commit(transaction);
        } catch (Exception e) {
            // 回滚事务
            transactionManager.rollback(transaction);
        }
        return books;
    }

    /**
     * 使用transactionTemplate进行事务管理。
     *
     * @param books
     * @return
     */
    public List<Book> addBooks1(List<Book> books) {
        transactionTemplate.execute((TransactionCallback<Object>) transactionStatus -> {
            String errorMessage = "";
            int id = 0;
            try {
                for (int i = 0; i < books.size(); i++) {
                    id = this.bookDao.add(books.get(i));
                    books.get(i).setId(id);
                }
            } catch (CustomDataException e) {
                errorMessage = e.getMessage();
                e.printStackTrace();
            }
            return errorMessage;
        });
        return books;
    }
}
