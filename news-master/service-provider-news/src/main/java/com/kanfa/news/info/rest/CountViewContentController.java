package com.kanfa.news.info.rest;


import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.mongodb.biz.CountViewContentService;
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
public class CountViewContentController {


    @Resource
    private CountViewContentService countViewContentService;


    /**
     * 网站访问量数据统计
     * @param startDate
     * @param endDate
     * @param sortName
     * @param isCount
     * @return
     */
    @GetMapping("/countviewcontent")
    public Map<String,Object> listByDate(@RequestParam Integer page,
                                            @RequestParam Integer limit,
                                            @RequestParam(required = false) Integer type,
                                            @RequestParam (required = false) String sortName,
                                            @RequestParam (required = false) Integer sortOrder,
                                            @RequestParam String startDate,
                                            @RequestParam String endDate,
                                            @RequestParam (required = false,defaultValue = "false") Boolean isCount){

        return countViewContentService.getTotalAndAllPv(page,limit,type,sortName,sortOrder,startDate,endDate,isCount) ;
    }
    /**
     * 网站访问量数据统计 导出Excel
     * @param startDate
     * @param endDate
     * @param sortName
     * @param isCount
     * @return
     */
    @GetMapping("/countviewcontent/excel")
    public ExcelData getExcel(@RequestParam String excelName,
                              @RequestParam(required = false) Integer type,
                              @RequestParam (required = false) String sortName,
                              @RequestParam (required = false) Integer sortOrder,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam (required = false,defaultValue = "false") Boolean isCount){

          return  countViewContentService.getExcel(excelName,type,sortName,sortOrder,startDate,endDate,isCount);
    }


}
