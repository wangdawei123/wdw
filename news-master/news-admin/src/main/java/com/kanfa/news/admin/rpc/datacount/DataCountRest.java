package com.kanfa.news.admin.rpc.datacount;

import com.kanfa.news.admin.feign.datacount.IDataCountServiceFeign;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 9:55
 */
@RestController
@RequestMapping("dataCount")
public class DataCountRest {
    @Autowired
    private IDataCountServiceFeign iDataCountServiceFeign;
    /**
     * 数据统计列表
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/getList")
    public Map<String,Object> getList(@RequestParam(required = false)Integer categoryId,
                                      @RequestParam(required = false)Integer channelId,
                                      @RequestParam(required = false) String startDate,
                                      @RequestParam(required = false) String endDate){
        return iDataCountServiceFeign.getList(categoryId, channelId, startDate, endDate);
    }

    /**
     * 数据统计 导出Excel
     * @param response
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/getList/excel")
    public void getListToExcel(HttpServletResponse response,
                                    @RequestParam(required = false)Integer categoryId,
                                    @RequestParam(required = false)Integer channelId,
                                    @RequestParam(required = false) String startDate,
                                    @RequestParam(required = false) String endDate){
        StringBuilder excelName = new StringBuilder();
        if(null != startDate && null != endDate){
            excelName.append(startDate.split(" " )[0]).append("至").append(endDate.split(" ")[0]);
        }
        excelName.append("数据统计TOP100");
        ExcelData data = iDataCountServiceFeign.getListToExcel(excelName.toString(), categoryId, channelId, startDate, endDate);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
