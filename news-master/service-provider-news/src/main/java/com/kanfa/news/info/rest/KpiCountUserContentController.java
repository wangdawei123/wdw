package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.biz.KpiCountUserContentBiz;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountUserContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("kpiCountUserContent")
public class KpiCountUserContentController {

    @Autowired
    private KpiCountUserContentBiz kpiCountUserContentBiz;
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
    @GetMapping("/pageList")
    public TableResultResponse<KpiCountUserContentInfo> getPageList(@RequestParam (required = false) Integer page,
                                                         @RequestParam (required = false) Integer limit ,
                                                         @RequestParam (required = false) String startDate,
                                                         @RequestParam (required = false) String endDate,
                                                         @RequestParam (required = false) Integer dayShow,
                                                         @RequestParam (required = false) Integer uid,
                                                         @RequestParam (required = false) Integer sourceType,
                                                         @RequestParam (required = false) Integer dutyType){
        TableResultResponse<KpiCountUserContentInfo> result = kpiCountUserContentBiz.getPageList(page,limit,startDate,endDate,dayShow,uid,sourceType,dutyType);
        return result;
    }


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
    @GetMapping("/pageList/excel")
    public ExcelData pageKpiCountExcel(@RequestParam String excelName,
                                       @RequestParam (required = false) String startDate,
                                        @RequestParam (required = false) String endDate,
                                        @RequestParam (required = false) Integer dayShow,
                                        @RequestParam (required = false) Integer uid,
                                        @RequestParam (required = false) Integer sourceType,
                                        @RequestParam (required = false) Integer dutyType){
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("日期");
        titles.add("姓名");
        titles.add("是否原创");
        titles.add("值班状态");
        titles.add("纯文");
        titles.add("图文");
        titles.add("图集");
        titles.add("视频");
        titles.add("直播");
        titles.add("总计");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<KpiCountUserContentInfo> list =kpiCountUserContentBiz.getList(startDate,endDate,dayShow,uid,sourceType,dutyType);
        for (KpiCountUserContentInfo kpiCountUserContentInfo:list  ) {
            List<Object> row = new ArrayList();
            row.add(kpiCountUserContentInfo.getCountDate());
            row.add(kpiCountUserContentInfo.getEditName());
            row.add(kpiCountUserContentInfo.getSourceType());
            row.add(kpiCountUserContentInfo.getDutyType());
            row.add(kpiCountUserContentInfo.getPureText());
            row.add(kpiCountUserContentInfo.getArticle());
            row.add(kpiCountUserContentInfo.getImages());
            row.add(kpiCountUserContentInfo.getVideo());
            row.add(kpiCountUserContentInfo.getLive());
            row.add(kpiCountUserContentInfo.getTotal());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }
}