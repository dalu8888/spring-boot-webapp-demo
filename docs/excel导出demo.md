>第一步：引入poi依赖包
>
        <dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>3.5-FINAL</version>
		</dependency>
>
>第二步：新建Excel
* 获取需要导出的数据：List<Book> list = bookService.findAll(params);
* 创建excel，2007版本的用XSSFWorkbook，2003版本的用HSSFWorkbook：XSSFWorkbook wb = new XSSFWorkbook();
* 创建sheet：XSSFSheet sheet = wb.createSheet("表单1");
* 创建行：XSSFRow row = sheet.createRow(0)
* 创建列：XSSFCell cell = row.createCell(0)
* 具体代码见ExcelService
>第三步：输出Excel，前台直接下载,具体代码见ExcelController
* output = response.getOutputStream();
* response.reset();
* response.setHeader("Content-disposition", "attachment; filename=details.xls");
* response.setContentType("application/msexcel");
* xssfWorkbook.write(output);
* output.close();

		