package com.kanfa.news.admin.feign.datacount;

import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 13:47
 */
@FeignClient(name = "service-provider-news")
public interface ICountviewContentServiceFeign {
    /**
     * 网站访问量数据统计
     * @param startDate
     * @param endDate
     * @param sortName
     * @param isCount
     * @return
     */
    @GetMapping("/kpi/countviewcontent")
    Map<String,Object> listByDate(@RequestParam("page") Integer page,
                                         @RequestParam("limit") Integer limit,
                                         @RequestParam(name="type",required = false) Integer type,
                                         @RequestParam (name="sortName",required = false) String sortName,
                                         @RequestParam (name="sortOrder",required = false) Integer sortOrder,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam (name = "isCount",required = false) Boolean isCount);
    /**
     * 网站访问量数据统计 导出Excel
     * @param startDate
     * @param endDate
     * @param sortName
     * @param isCount
     * @return
     */
    @GetMapping("/kpi/countviewcontent/excel")
    ExcelData getExcel(@RequestParam("excelName") String excelName,
                       @RequestParam(name = "type",required = false) Integer type,
                       @RequestParam (name="sortName",required = false) String sortName,
                       @RequestParam (name="sortOrder",required = false) Integer sortOrder,
                       @RequestParam("startDate") String startDate,
                       @RequestParam("endDate") String endDate,
                       @RequestParam (name = "isCount",required = false) Boolean isCount);
}
