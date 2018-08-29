package com.kanfa.news.admin.feign.datacount;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 14:48
 */
@FeignClient(name = "service-provider-news")
public interface ICountChannelContentServiceFeign {
    /**
     * 渠道列表
     * @param categoryId
     * @return
     */
    @GetMapping(value = "/kpi/channellist")
    List<Channel> getChannelList(@RequestParam("categoryId") Integer categoryId);

    /**
     * 渠道发稿量统计
     * @param page
     * @param limit
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/kpi/countchannelcontent")
    Map<String,Object> listByDate(@RequestParam("page") Integer page,
                                         @RequestParam("limit") Integer limit,
                                         @RequestParam(name = "categoryId",required = false) Integer categoryId,
                                         @RequestParam(name = "channelId",required = false) Integer channelId,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate);

    /**
     * 渠道发稿量统计 导出Excel
     * @param excelName
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     */
    @GetMapping(value = "/kpi/countchannelcontent/excel")
    ExcelData getExcel(@RequestParam("excelName") String excelName,
                      @RequestParam(name = "categoryId",required = false) Integer categoryId,
                      @RequestParam(name = "channelId",required = false) Integer channelId,
                      @RequestParam("startDate") String startDate,
                      @RequestParam("endDate") String endDate);

}
