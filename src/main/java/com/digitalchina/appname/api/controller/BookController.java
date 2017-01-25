package com.digitalchina.appname.api.controller;

import com.digitalchina.appname.api.model.Book;
import com.digitalchina.appname.api.model.Category;
import com.digitalchina.appname.api.service.BookService;
import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转测试
 * Created by xingding on 2016/8/17.
 */
@Controller
public class BookController {

    public static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * 查询,列表页面
     * 1. 增加书名模糊查询
     * 2. 增加分页处理
     *
     * @return
     */
    @RequestMapping(value = "/main.html", method = RequestMethod.GET)
    public String list() {
        return "book/main";
    }

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public String booklist(@RequestParam(required = false, defaultValue = "10") long pageSize,
                           @RequestParam(required = false, defaultValue = "1") long page,
                           @RequestParam(required = false) String title, ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title", title);
        int totalSize = bookService.findTotalCount(params);
        Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
        params.put("startIndex", pagination.getStartIndex());
        params.put("endIndex", pagination.getEndIndex());
        List books = bookService.findAll(params);

        pagination.setUrl(request.getRequestURI());
        map.put("page", pagination);
        map.put("books", books);
        map.put("title", title);//回到页面,保留搜索关键字
        return "book/list :: bookList";
    }


    /**
     * 进入新增页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String add(ModelMap map) {
        List<Category> categorys = bookService.findCategorys();
        map.put("categorys", categorys);
        return "book/add";
    }

    /**
     * 新增
     *
     * @param book
     * @param map
     * @return
     */
    @RequestMapping(value = "/addbook.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(Book book, ModelMap map) {
        bookService.insertBook(book);
        return RtnData.ok("新增book成功");
    }

    /**
     * 进入编辑页面
     *
     * @param bookid
     * @param map
     * @return
     */
    @RequestMapping(value = "/edit.html")
    public String edit(@RequestParam String bookid, ModelMap map) {
        Long id = Long.parseLong(bookid);
        Book book = bookService.selectBookById(id);
        List<Category> categorys = bookService.findCategorys();
        map.put("categorys", categorys);
        map.put("book", book);
        return "book/edit";
    }

    /**
     * 更新
     *
     * @param book
     * @param map
     * @return
     */
    @RequestMapping(value = "/updatebook.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(Book book, ModelMap map) {
        bookService.updateBook(book);
        return RtnData.ok("修改book成功");
    }

    /**
     * 删除
     *
     * @param bookid
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public RtnData delete(@RequestParam String bookid) {
        Long id = Long.parseLong(bookid);
        bookService.deleteBook(id);
        return RtnData.ok("删除book成功");
    }

    @RequestMapping(value = "/listBooks.do")
    @ResponseBody
    public List<Book> listBooks(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                @RequestParam(required = false, defaultValue = "1") long currentPage) {
        Map<String, Object> params = new HashMap<String, Object>();
        int totalSize = bookService.findTotalCount(params);
        Page page = PaginationUtils.getPageParam(totalSize, pageSize, currentPage); //计算出分页查询时需要使用的索引
        params.put("startIndex", page.getStartIndex());
        params.put("endIndex", page.getEndIndex());
        List<Book> books = bookService.findAll(params);
        return books;
    }
}
