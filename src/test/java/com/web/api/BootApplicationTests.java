package com.web.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalchina.appname.api.service.BookService;

@RunWith(SpringRunner.class) //spring junit支持
@SpringBootTest
public class BootApplicationTests {

	@Autowired
	private BookService bookService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void processWithTransaction(){
//		bookService.processWithTransaction();
	}

}
