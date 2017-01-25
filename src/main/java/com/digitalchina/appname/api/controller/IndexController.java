package com.digitalchina.appname.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Created by xingding on 2016/8/17.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map){
        map.put("msg","Welcome to study spring boot!");
        return "index"; //html模板名称,对应/templates下的index.html
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
