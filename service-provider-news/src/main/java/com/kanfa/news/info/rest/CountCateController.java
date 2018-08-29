package com.kanfa.news.info.rest;


import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.mongodb.biz.CountCateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("kpi")
public class CountCateController   {
    @Resource
    private CountCateService countCateService;

    /**
     * 每日类目统计
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/countcate")
    public Map<String,Object> listByDate(@RequestParam Integer page,
                                            @RequestParam Integer limit,
                                            @RequestParam(required = false) Integer typeId,
                                            @RequestParam String startDate,
                                            @RequestParam String endDate){

        return countCateService.getTotalAndAllPv(page,limit,typeId,startDate,endDate) ;
    }

    /**
     * 每日类目统计 导出Excel
     * @param response
     * @param typeId
     * @param startDate
     * @param endDate
     */
    @GetMapping(value = "/countcate/excel")
    public ExcelData getExcel(@RequestParam String excelName,
                              @RequestParam(required = false) Integer typeId,
                              @RequestParam String startDate,
                              @RequestParam String endDate){
          return  countCateService.getExcel(excelName,typeId,startDate,endDate);
    }
}
