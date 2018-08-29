package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IAdminLogServiceFeign;
import com.kanfa.news.admin.vo.adminlog.AdminLogInfo;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 日志
 * @author Jiqc
 */
@RestController
@RequestMapping("log")
public class AdminLogRest {
    @Autowired
    private IAdminLogServiceFeign adminLogServiceFeign;

    /**
     * 导出
     * @param params
     * @return
     */
    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response,@RequestParam Map<String, Object> params) {
        if(params.get("endTime")!=null){
            params.put("endTime",params.get("endTime").toString());
        }
        if(params.get("startTime")!=null){
            params.put("startTime",params.get("startTime").toString());
        }
        StringBuilder excelName = new StringBuilder();
        excelName.append("系统日志表");
        params.put("excelName",excelName.toString());
        ExcelData excelData = adminLogServiceFeign.getExcelData(params);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),excelData);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 日志查询分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/adminLogPage", method = RequestMethod.GET)
    public TableResultResponse<AdminLogInfo> adminLogPage(@RequestParam Map<String, Object> params) {
        if(params.get("page")==null){
            params.put("page",1);
        }
        if(params.get("limit")==null){
            params.put("limit",10);
        }
        if(params.get("endTime")!=null){
            params.put("endTime",params.get("endTime").toString());
        }
        if(params.get("startTime")!=null){
            params.put("startTime",params.get("startTime").toString());
        }
        return adminLogServiceFeign.getAdminLogPage(params);
    }
}
