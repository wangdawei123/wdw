package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.KpiContentBiz;
import com.kanfa.news.info.biz.DataCountBiz;
import com.kanfa.news.info.entity.KpiContent;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.vo.admin.kpicount.DataCountInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dataCount")
public class DataCountController extends BaseController<KpiContentBiz,KpiContent> {
    @Autowired
    private DataCountBiz dataCountBiz;

    /**
     * 数据统计列表
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/getList")
    public Map<String,Object> getList(@RequestParam(required = false) Integer categoryId,
                       @RequestParam(required = false) Integer channelId,
                       @RequestParam(required = false) String startDate,
                       @RequestParam(required = false) String endDate){
        List<DataCountInfo> list = dataCountBiz.getDataCountList(categoryId,channelId,startDate,endDate);
        Integer allCount = dataCountBiz.getAllCount();
        Integer publishCount = dataCountBiz.getPublishCount(categoryId,channelId);
        Map<String,Object> resultMap = new HashMap<String,Object>(3);
        resultMap.put("total",allCount);
        resultMap.put("publishCount",publishCount);
        resultMap.put("rows",list);
        Map<String,Object> map = new HashMap<String,Object>(1);
        map.put("data", resultMap);
        return map;
    }

    /**
     * 数据统计 导出Excel
     * @param excelName
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/getList/excel")
    public ExcelData getListToExcel(@RequestParam("excelName") String excelName,
                                    @RequestParam(value ="categoryId",required = false)Integer categoryId,
                                    @RequestParam(value ="channelId",required = false)Integer channelId,
                                    @RequestParam(value ="startDate",required = false) String startDate,
                                    @RequestParam(value ="endDate",required = false) String endDate){
        return   dataCountBiz.getListToExcel(excelName, categoryId, channelId, startDate, endDate);

    }
}