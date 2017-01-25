package com.digitalchina.appname.api.controller;

import com.digitalchina.appname.api.service.ExcelService;
import com.digitalchina.common.data.RtnData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 页面跳转测试
 * Created by xingding on 2016/8/17.
 */
@Controller
public class ExcelController {

    public static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/initData", method = RequestMethod.GET)
    @ResponseBody
    public RtnData initData(HttpServletResponse response) {

        XSSFWorkbook xssfWorkbook = excelService.getExel();
        OutputStream output= null;
        try {
            output = response.getOutputStream();
            //输出excel文件，前台就直接下载了
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=details.xls");
            response.setContentType("application/msexcel");
            xssfWorkbook.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RtnData.ok("导出成功");
    }



}
