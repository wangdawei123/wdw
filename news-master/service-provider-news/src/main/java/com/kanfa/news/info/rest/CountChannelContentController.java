package com.kanfa.news.info.rest;


import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ChannelBiz;
import com.kanfa.news.info.entity.Channel;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.mongodb.biz.CountChannelContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("kpi")
public class CountChannelContentController extends BaseController<ChannelBiz,Channel> {

    private Integer IS_PUBLSH = 1;
    private Integer STATUS = 1;
    @Resource
    private CountChannelContentService countChannelContentService;
    @Autowired
    private ChannelBiz channelBiz;

    /**
     * 渠道列表
     * @param categoryId
     * @return
     */
    @GetMapping(value = "/channellist")
    public List<Channel> getChannelList(@RequestParam Integer categoryId){
            Channel channel = new Channel();
            channel.setCategory(categoryId);
            channel.setIsPublish(IS_PUBLSH);
            channel.setChannelStatus(STATUS);
            return channelBiz.selectList(channel);
    }

    /**
     * 渠道发稿量统计
     * @param page
     * @param limit
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/countchannelcontent")
    public Map<String,Object> listByDate(@RequestParam Integer page,
                                            @RequestParam Integer limit,
                                            @RequestParam(required = false) Integer categoryId,
                                            @RequestParam(required = false) Integer channelId,
                                            @RequestParam String startDate,
                                            @RequestParam String endDate){

        return countChannelContentService.getTotalAndAllPv(page,limit,categoryId,channelId,startDate,endDate) ;
    }

    /**
     * 渠道发稿量统计 导出Excel
     * @param excelName
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     */
    @GetMapping(value = "/countchannelcontent/excel")
    public ExcelData getExcel(@RequestParam String excelName,
                              @RequestParam(required = false) Integer categoryId,
                              @RequestParam(required = false) Integer channelId,
                              @RequestParam String startDate,
                              @RequestParam String endDate){
           return countChannelContentService.getExcel(excelName,categoryId,channelId,startDate,endDate);
    }


}
