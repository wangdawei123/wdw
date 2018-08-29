package com.kanfa.news.admin.feign.operationexcel;

import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 15:29
 */
@FeignClient(name = "service-provider-news")
public interface IOperationExportDataServiceFeign {
    @RequestMapping("/operation/excel")
    ExcelData getExcel(@RequestParam("excelName") String excelName, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate);
}
