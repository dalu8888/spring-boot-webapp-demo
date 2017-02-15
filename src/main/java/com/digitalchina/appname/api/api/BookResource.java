package com.digitalchina.appname.api.api;

import com.digitalchina.appname.api.controller.BookController;
import com.digitalchina.appname.api.service.BookService;
import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/2/15.
 */
@RestController
@RequestMapping("/api")
public class BookResource {

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

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    @ResponseBody
    public RtnData booklist(@RequestParam(required = false, defaultValue = "10") long pageSize,
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
        return RtnData.ok(map);
    }
}
