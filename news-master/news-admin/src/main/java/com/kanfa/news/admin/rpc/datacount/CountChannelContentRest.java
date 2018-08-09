package com.kanfa.news.admin.rpc.datacount;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.feign.datacount.ICountCateServiceFeign;
import com.kanfa.news.admin.feign.datacount.ICountChannelContentServiceFeign;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/23 10:21
 */
@RestController
@RequestMapping("kpi")
public class CountChannelContentRest {

    @Autowired
    private ICountChannelContentServiceFeign iCountChannelContentServiceFeign;

    /**
     * 渠道列表
     * @param categoryId
     * @return
     */
    @GetMapping(value = "/channellist")
    public List<Channel> getChannelList(@RequestParam Integer categoryId){
        return iCountChannelContentServiceFeign.getChannelList(categoryId);
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

        return iCountChannelContentServiceFeign.listByDate(page, limit, categoryId, channelId, startDate, endDate) ;
    }

    /**
     * 渠道发稿量统计 导出Excel
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     */
    @GetMapping(value = "/countchannelcontent/excel")
    public void getExcel(HttpServletResponse response,
                          @RequestParam(required = false) Integer categoryId,
                          @RequestParam(required = false) Integer channelId,
                          @RequestParam String startDate,
                          @RequestParam String endDate){
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("渠道发稿量统计报表");
        ExcelData data = iCountChannelContentServiceFeign.getExcel(excelName.toString(),categoryId,channelId,startDate,endDate);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
