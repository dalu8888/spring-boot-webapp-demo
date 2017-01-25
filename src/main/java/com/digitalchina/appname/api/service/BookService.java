package com.digitalchina.appname.api.service;

import com.digitalchina.appname.api.dao.BookDao;
import com.digitalchina.appname.api.model.Book;
import com.digitalchina.appname.api.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by xingding on 2016/11/12.
 */
@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public int findTotalCount(Map<String,Object> params){
        return bookDao.findTotalCount(params);
    }

    public List<Book> findAll(Map<String,Object> params){
        return bookDao.findAll(params);
    }

    public int insertBook(Book book){
        return bookDao.insertBook(book);
    }

    public int updateBook(Book book){
        return bookDao.updateBook(book);
    }

    public int deleteBook(Long id){
        return bookDao.deleteBook(id);
    }

    public Book selectBookById(Long id){
        return bookDao.selectBookById(id);
    }


    public List<Category> findCategorys(){
        return bookDao.findCategorys();
    }

    /**
     * 1. 加入事务管理, 框架会自动默认注入DataSourceTransactionManager
     * 2. 默认的隔离级别是DEFAULT，表示使用底层数据库的默认隔离级别。通常为 READ_COMMITTED 。
     * 3. 默认的传播行为是REQUIRED，如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
     * 4. 方法中第一次成功的插入会回滚
     */
    @Transactional
    public void processWithTransaction(){
        //演示代码，实际开发中如果需要事务管理，加上Transactional注解
        Book book1 = new Book();
        book1.setTitle("java入门");
        book1.setAuthor("张三");
        book1.setCategory("1");
        book1.setDescription("讲解hello world");
        bookDao.insertBook(book1);//插入成功
        Book book2 = new Book();
        book2.setTitle("异常管理");
        book2.setAuthor("李四");
        book2.setCategory("123");  //这里会抛出异常,因为category长度超长
        book2.setDescription("将会抛出异常,整个事务回滚");
        bookDao.insertBook(book2);
    }
}
