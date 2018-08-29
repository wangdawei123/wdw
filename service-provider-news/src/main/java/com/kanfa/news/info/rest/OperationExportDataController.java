package com.kanfa.news.info.rest;

import com.kanfa.news.info.biz.OperationExportBiz;
import com.kanfa.news.info.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("operation")
public class OperationExportDataController {

    @Autowired
    private OperationExportBiz operationExportBiz;

    @RequestMapping("/excel")
    public ExcelData getExcel(@RequestParam String excelName, @RequestParam String startDate, @RequestParam String endDate){

          return  operationExportBiz.getOperationExportDate(excelName,startDate,endDate);

    }
}