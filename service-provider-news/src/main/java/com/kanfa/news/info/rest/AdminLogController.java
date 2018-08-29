package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.AdminLogBiz;
import com.kanfa.news.info.entity.AdminLog;
import com.kanfa.news.info.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("adminLog")
public class AdminLogController extends BaseController<AdminLogBiz,AdminLog> {

    @Autowired
    private AdminLogBiz adminLogBiz;


    /**
     * 日志分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getAdminLogPage",method = RequestMethod.GET)
    public TableResultResponse getAdminLogPage(@RequestParam Map<String, Object> params){
        return adminLogBiz.getAdminLogPage(params);
    }

    /**
     * 导出excel
     * @param params
     * @return
     */
    @RequestMapping(value = "/getExcelData",method = RequestMethod.GET)
    public ExcelData getExcelData(@RequestParam Map<String, Object> params){
        return adminLogBiz.getExcelData(params);
    }
}