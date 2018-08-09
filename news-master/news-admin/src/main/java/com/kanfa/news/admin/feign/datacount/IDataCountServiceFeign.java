package com.kanfa.news.admin.feign.datacount;

import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 9:56
 */
@FeignClient(name = "service-provider-news")
public interface IDataCountServiceFeign {
    /**
     * 数据统计列表
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/dataCount/getList")
    Map<String,Object> getList(@RequestParam(name = "categoryId") Integer categoryId,
                              @RequestParam(name ="channelId" ) Integer channelId,
                              @RequestParam(name = "startDate")  String startDate,
                              @RequestParam(name = "endDate")  String endDate);

    /**
     * 数据统计 导出Excel
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/dataCount/getList/excel")
    ExcelData getListToExcel(@RequestParam(name = "excelName") String excelName,
                             @RequestParam(name = "categoryId") Integer categoryId,
                             @RequestParam(name = "channelId") Integer channelId,
                             @RequestParam(name = "startDate") String startDate,
                             @RequestParam(name = "endDate") String endDate);
}
