package com.digitalchina.appname.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digitalchina.resttemplate.ribbon.retryable.RetryableLoadbalancedRestTemplateUtil;

@RestController
public class ApiConsumerController {
	@Autowired
	RetryableLoadbalancedRestTemplateUtil restTemplate;
	
	@Value("${spring.datasource.url}")
	String url;
	

	@RequestMapping(value = "/consume", method = RequestMethod.GET, produces = "application/json")
	public String consume(HttpServletRequest req) {
		
		System.out.println("===================================url:"+url);
		String result=restTemplate.get("spring-boot-demo", "/query.do?id=36", null);
		return result;
	}
	
	@RequestMapping(value = "/consumePost", method = RequestMethod.GET, produces = "application/json")
	public String consumePost(HttpServletRequest req) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", 36);
		String result=restTemplate.post("spring-boot-demo", "/queryPost.do", map, null);
		return result;
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
	public String info(HttpServletRequest req) {
		//这个接口与Eureka的接口冲突，在访问时需访问/info.do
		return "To prove project api is not conflict with eureka api.";
	}
}
