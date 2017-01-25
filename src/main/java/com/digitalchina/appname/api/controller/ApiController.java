package com.digitalchina.appname.api.controller;

import java.util.Map;

import com.digitalchina.appname.api.model.Book;
import com.digitalchina.appname.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalchina.common.data.RtnData;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 接口服务 Created by xingding on 2016/11/29.
 */
@RestController
@CacheConfig(cacheNames = "CacheNameForThisObject")
public class ApiController {

	@Autowired
	private BookService bookService;

	@ApiOperation(value = "查询Book信息", notes = "根据id来查询Book详细信息，等等等 ...")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Book ID", required = true, paramType = "query", dataType = "string", defaultValue = "36"),
			@ApiImplicitParam(name = "book", value = "Book实体", required = false, dataType = "Book") })
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@Cacheable
	public RtnData selectBookById(@RequestParam(required = false) String id) {
		Book book = bookService.selectBookById(Long.valueOf(id));
		return RtnData.ok(book);
	}

	@ApiOperation(value = "查询Book信息", notes = "根据id来查询Book详细信息，等等等 ...")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "person", value = "包含book id的Map对象，Map中必须包含key 'id'，key 'id'对应的值就是book id。示例：{\"id\":\"36\"}", required = true, dataType = "string", defaultValue = "{\"id\":\"36\"}") })
	@RequestMapping(value = "/queryPost", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@Cacheable
	public RtnData selectBookByIdOfPost(@RequestBody(required = false) Map<String, String> person) {
		Book book = bookService.selectBookById(Long.valueOf(person.get("id")));
		return RtnData.ok(book);
	}

}
