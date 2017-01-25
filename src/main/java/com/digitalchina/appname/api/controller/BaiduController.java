package com.digitalchina.appname.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digitalchina.resttemplate.httpclient.RestTemplateWithHttpClientUtil;

@RestController
public class BaiduController {
	@Autowired
	RestTemplateWithHttpClientUtil restTemplate;

	@RequestMapping(value = "/baidu", method = RequestMethod.GET, produces = "application/json")
	public String baidu(HttpServletRequest req) {
		String result = restTemplate.get("http://www.baidu.com", null);
		return result;
	}
}
