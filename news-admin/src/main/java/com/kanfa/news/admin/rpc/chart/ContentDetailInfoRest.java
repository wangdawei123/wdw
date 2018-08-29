package com.kanfa.news.admin.rpc.chart;

import com.kanfa.news.admin.feign.chart.IContentDetailInfoServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/23 15:51
 */
@RestController
@RequestMapping("contentDetailInfo")
public class ContentDetailInfoRest {

    @Autowired
    private IContentDetailInfoServiceFeign iContentDetailInfoServiceFeign;

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
    @RequestMapping(value = "/getList")
    public Object getList(@RequestParam Integer page,
                            @RequestParam Integer limit,
                            @RequestParam String startDate,
                            @RequestParam String endDate,
                            @RequestParam String pvEndDate,
                            @RequestParam(required=false) Integer uid,
                            @RequestParam(required=false) Integer ascDesc,
                            @RequestParam(required=false) String columnName){
       return iContentDetailInfoServiceFeign.getList(page, limit, startDate, endDate, pvEndDate, uid, ascDesc, columnName);
    }

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
    @RequestMapping(value = "/getDetailList")
    public Object getDetailList(@RequestParam Integer page,
                                @RequestParam Integer limit,
                                @RequestParam String startDate,
                                @RequestParam String endDate,
                                @RequestParam String pvEndDate,
                                @RequestParam(required=false) Integer uid,
                                @RequestParam(required=false) Integer ascDesc,
                                @RequestParam(required=false) String columnName,
                                @RequestParam(required=false) Integer type,
                                @RequestParam(required=false) Integer sourceType,
                                @RequestParam(required=false) String title){
        return iContentDetailInfoServiceFeign.getDetailList(page, limit, startDate, endDate, pvEndDate, uid, ascDesc, columnName, type, sourceType, title);
    }
}
