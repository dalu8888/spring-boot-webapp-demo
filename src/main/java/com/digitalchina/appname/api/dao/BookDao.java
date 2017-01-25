package com.digitalchina.appname.api.dao;


import com.digitalchina.appname.api.model.Book;
import com.digitalchina.appname.api.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xingding on 2016/11/12.
 */
@Mapper
public interface BookDao {

    /* 不使用mybatis java注解
    缺点: 当业务逻辑复杂时,sql代码阅读困难不直观
    @Select("select a.id, a.title, a.author, b.desc as category, a.description from book a left join category b on a.category = b.code")
    List<Book> findAll();

    @Select("select a.id, a.title, a.author, b.desc as category, a.description from book a left join category b on a.category = b.code " +
            "where a.title like concat('%', #{title}, '%')")
    List<Book> findByTitle(String title);

    @Insert("insert into book(title, author, category, description) " +
            "values (#{title},#{author},#{category}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBook(Book book);

    @Update("update book set title = #{title}, author = #{author}, category = #{category}, description = #{description} where id = #{id}")
    int updateBook(Book book);

    @Delete("delete from book where id = #{id}")
    int deleteBook(Long id);

    @Select("select * from book where id = #{id}")
    Book selectBookById(Long id);

    @Select("select * from category")
    List<Category> findCategorys();
    */

    /*
     * 改用传统的mapper xml操作数据库
     */
    List<Book> findAll(Map<String, Object> params);

    int insertBook(Book book);

    int updateBook(Book book);

    int deleteBook(Long id);

    Book selectBookById(Long id);

    List<Category> findCategorys();

    int findTotalCount(Map<String, Object> params);

}
