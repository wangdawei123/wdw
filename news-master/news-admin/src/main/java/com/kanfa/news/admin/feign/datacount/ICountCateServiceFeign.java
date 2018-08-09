package com.kanfa.news.admin.feign.datacount;

import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 15:29
 */
@FeignClient(name = "service-provider-news")
public interface ICountCateServiceFeign {
    /**
     * 每日类目统计
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/kpi/countcate")
    Map<String,Object> listByDate(@RequestParam("page") Integer page,
                                  @RequestParam("limit") Integer limit,
                                  @RequestParam(required = false,name = "typeId")Integer typeId,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate);

    /**
     * 每日类目统计 导出Excel
     * @param excelName
     * @param typeId
     * @param startDate
     * @param endDate
     */
    @GetMapping("/kpi/countcate/excel")
    ExcelData getExcel(@RequestParam("excelName") String excelName,
                       @RequestParam(required = false,name = "typeId")Integer typeId,
                       @RequestParam("startDate") String startDate,
                       @RequestParam("endDate") String endDate);
}
