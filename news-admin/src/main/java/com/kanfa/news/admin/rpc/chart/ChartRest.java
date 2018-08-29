package com.kanfa.news.admin.rpc.chart;

import com.kanfa.news.admin.feign.chart.IChartServiceFeign;
import com.kanfa.news.admin.feign.operationexcel.IOperationExportDataServiceFeign;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/23 15:51
 */
@RestController
@RequestMapping("chart")
public class ChartRest {

    @Autowired
    private IChartServiceFeign iChartServiceFeign;

    @RequestMapping("/getData")
    public Map<String,Object> getData(@RequestParam("days")Integer days){
       return iChartServiceFeign.getData(days);
    }
}
