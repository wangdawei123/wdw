package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.vo.adminlog.AdminLogInfo;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/4/23 17:44
 */
@FeignClient(name = "service-provider-news")
public interface IAdminLogServiceFeign {

    /**
     * 日志分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/adminLog/getAdminLogPage",method = RequestMethod.GET)
    TableResultResponse<AdminLogInfo> getAdminLogPage(@RequestParam Map<String, Object> params);

    /**
     * 导出excel
     * @param params
     * @return
     */
    @RequestMapping(value = "/adminLog/getExcelData",method = RequestMethod.GET)
    ExcelData getExcelData(@RequestParam Map<String, Object> params);
}
