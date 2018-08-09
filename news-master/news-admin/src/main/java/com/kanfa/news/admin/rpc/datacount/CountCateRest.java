package com.kanfa.news.admin.rpc.datacount;

import com.kanfa.news.admin.feign.datacount.ICountCateServiceFeign;
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
 * @date 2018/4/23 10:21
 */
@RestController
@RequestMapping("kpi")
public class CountCateRest {

    @Autowired
    private ICountCateServiceFeign iCountCateServiceFeign;

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

        return iCountCateServiceFeign.listByDate(page, limit, typeId, startDate, endDate);
    }

    /**
     * 每日类目统计 导出Excel
     * @param response
     * @param typeId
     * @param startDate
     * @param endDate
     */
    @GetMapping(value = "/countcate/excel")
    public void getExcel(HttpServletResponse response,
                              @RequestParam(required = false) Integer typeId,
                              @RequestParam String startDate,
                              @RequestParam String endDate){
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("每日类目统计报表");
        ExcelData data = iCountCateServiceFeign.getExcel(excelName.toString(),typeId,startDate,endDate);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
