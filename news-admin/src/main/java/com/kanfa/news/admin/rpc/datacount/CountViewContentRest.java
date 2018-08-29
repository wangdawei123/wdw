package com.kanfa.news.admin.rpc.datacount;

import com.kanfa.news.admin.feign.datacount.ICountviewContentServiceFeign;
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
public class CountViewContentRest {

    @Autowired
    private ICountviewContentServiceFeign iCountviewContentServiceFeign;

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
                                         @RequestParam (required = false) Boolean isCount){

        return iCountviewContentServiceFeign.listByDate(page, limit, type, sortName, sortOrder, startDate, endDate, isCount);
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
    public void getExcel(HttpServletResponse response,
                              @RequestParam(required = false) Integer type,
                              @RequestParam (required = false) String sortName,
                              @RequestParam (required = false) Integer sortOrder,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam (required = false) Boolean isCount){
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("渠道发稿量统计报表");
        ExcelData data = iCountviewContentServiceFeign.getExcel(excelName.toString(),type,sortName,sortOrder,startDate,endDate,isCount);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
