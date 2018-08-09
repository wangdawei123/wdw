package com.kanfa.news.admin.rpc.operationexportdata;

import com.kanfa.news.admin.feign.operationexcel.IOperationExportDataServiceFeign;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/23 15:51
 */
@RestController
@RequestMapping("operation")
public class OperationExportDataRest {

        @Autowired
        private IOperationExportDataServiceFeign iOperationExportDataServiceFeign;

        @RequestMapping("/excel")
        public void getExcel(HttpServletResponse response, @RequestParam String startDate, @RequestParam String endDate){

            StringBuilder excelName = new StringBuilder();
            if(null != startDate && null != endDate){
                excelName.append(startDate.split(" " )[0]).append("至").append(endDate.split(" ")[0]);
            }
            excelName.append("KpiExport数据统计");
            ExcelData data = iOperationExportDataServiceFeign.getExcel(excelName.toString(), startDate, endDate);
            try {
                ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
            } catch (Exception e){
                e.printStackTrace();
            }

    }
}
