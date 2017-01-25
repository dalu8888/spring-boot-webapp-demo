package com.digitalchina.appname.api.service;


import com.digitalchina.appname.api.model.Book;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by zhouxiaa
 */
@Service
public class ExcelService {

    @Autowired
    private BookService bookService;

    public XSSFWorkbook getExel() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startIndex",0);
        params.put("endIndex", 10);
        //获取需要导出的数据
        List<Book> list = bookService.findAll(params);
        //创建excel，2007版本的用XSSFWorkbook，2003版本的用HSSFWorkbook
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建sheet
        XSSFSheet sheet = wb.createSheet("表单1");
        for (int i = 0; i < list.size() + 1; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i);
            if (i == 0) {
                //创建列
                XSSFCell cell = row.createCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell = row.createCell((short) 0);
                cell.setCellValue("书名");
                cell = row.createCell((short) 1);
                cell.setCellValue("作者");
                cell = row.createCell((short) 2);
                cell.setCellValue("简介");
            } else {
                XSSFCell cell = row.createCell(i);
                Book book = list.get(i - 1);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell = row.createCell((short) 0);
                cell.setCellValue(book.getTitle());
                cell = row.createCell((short) 1);
                cell.setCellValue(book.getAuthor());
                cell = row.createCell((short) 2);
                cell.setCellValue(book.getDescription());
            }
        }
        return wb;
    }

}
