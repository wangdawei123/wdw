package com.kanfa.news.admin.feign.kpicount;

import com.kanfa.news.admin.vo.kpicount.KpiCountUserContentInfo;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author JiaYunwei
 * @date 2018/06/14 14:13
 */
@FeignClient(name = "service-provider-news")
public interface IKpiCountUserContentServiceFeign {

    /**
     * 人员发布统计列表
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param dayShow
     * @param uid
     * @param sourceType
     * @param dutyType
     * @return
     */
    @GetMapping("/kpiCountUserContent/pageList")
    TableResultResponse<KpiCountUserContentInfo> getPageList(@RequestParam (value = "page",required = false) Integer page,
                                                             @RequestParam (value = "limit",required = false) Integer limit ,
                                                             @RequestParam (value = "startDate",required = false) String startDate,
                                                             @RequestParam (value = "endDate",required = false) String endDate,
                                                             @RequestParam (value = "dayShow",required = false) Integer dayShow,
                                                             @RequestParam (value = "uid",required = false) Integer uid,
                                                             @RequestParam (value = "sourceType",required = false) Integer sourceType,
                                                             @RequestParam (value = "dutyType",required = false) Integer dutyType);
    /**
     * 人员发布统计导出Excel
     * @param excelName
     * @param startDate
     * @param endDate
     * @param dayShow
     * @param uid
     * @param sourceType
     * @param dutyType
     * @return
     */
    @GetMapping("/kpiCountUserContent/pageList/excel")
    ExcelData pageKpiCountExcel(@RequestParam(value = "excelName") String excelName,
                                @RequestParam (value = "startDate",required = false) String startDate,
                                @RequestParam (value = "endDate",required = false) String endDate,
                                @RequestParam (value = "dayShow",required = false) Integer dayShow,
                                @RequestParam (value = "uid",required = false) Integer uid,
                                @RequestParam (value = "sourceType",required = false) Integer sourceType,
                                @RequestParam (value = "dutyType",required = false) Integer dutyType);
}
