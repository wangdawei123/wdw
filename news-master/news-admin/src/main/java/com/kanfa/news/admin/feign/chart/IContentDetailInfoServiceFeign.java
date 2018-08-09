package com.kanfa.news.admin.feign.chart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 15:29
 */
@FeignClient(name = "service-provider-news")
public interface IContentDetailInfoServiceFeign {
    /**
     * 发稿查询
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param uid
     * @param ascDesc
     * @param columnName
     * @return
     */
    @RequestMapping(value = "contentDetailInfo/getList")
    Object getList(@RequestParam("page") Integer page,
                                    @RequestParam("limit") Integer limit,
                                    @RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate,
                                    @RequestParam("pvEndDate") String pvEndDate,
                                    @RequestParam(required=false,name = "uid") Integer uid,
                                    @RequestParam(required=false,name = "ascDesc") Integer ascDesc,
                                    @RequestParam(required=false,name = "columnName") String columnName);

    /**
     * 发稿明细
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param uid
     * @param ascDesc
     * @param columnName
     * @return
     */
    @RequestMapping(value = "contentDetailInfo/getDetailList")
    Object getDetailList(@RequestParam("page") Integer page,
                                    @RequestParam("limit") Integer limit,
                                    @RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate,
                                    @RequestParam("pvEndDate") String pvEndDate,
                                    @RequestParam(required=false,name = "uid") Integer uid,
                                    @RequestParam(required=false,name = "ascDesc") Integer ascDesc,
                                    @RequestParam(required=false,name = "columnName") String columnName,
                                    @RequestParam(required=false,name = "type") Integer type,
                                    @RequestParam(required=false,name = "sourceType") Integer sourceType,
                                    @RequestParam(required=false,name = "title") String title);
}
