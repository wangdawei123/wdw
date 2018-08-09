package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.biz.ContentKpiCountBiz;
import com.kanfa.news.info.biz.KpiCountUserContentBiz;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.vo.admin.kpicount.ContentKpiCountInfo;
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
@RequestMapping("contentKpiCount")
public class ContentKpiCountController {

    @Autowired
    private ContentKpiCountBiz contentKpiCountBiz;

    /**
     * 发稿明细统计列表
     *
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param sourceType
     * @param viewType
     * @return
     */
    @GetMapping("/pageList")
    public TableResultResponse<ContentKpiCountInfo> getPageList(@RequestParam(required = false) Integer page,
                                                                @RequestParam(required = false) Integer limit,
                                                                @RequestParam(required = false) String startDate,
                                                                @RequestParam(required = false) String endDate,
                                                                @RequestParam(required = false) Integer sourceType,
                                                                @RequestParam(required = false) Integer viewType) {
        TableResultResponse<ContentKpiCountInfo> result = contentKpiCountBiz.getPageList(page, limit, startDate, endDate, sourceType, viewType);
        return result;
    }


    /**
     * 发稿明细统计导出Excel
     *
     * @param excelName
     * @param startDate
     * @param endDate
     * @param sourceType
     * @return
     */
    @GetMapping("/pageList/excel")
    public ExcelData pageKpiCountExcel(@RequestParam String excelName,
                                       @RequestParam(required = false) String startDate,
                                       @RequestParam(required = false) String endDate,
                                       @RequestParam(required = false) Integer sourceType,
                                       @RequestParam(required = false) Integer viewType) {
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        if (1 == viewType) {
            titles.add("首次审核时间");
            titles.add("内容ID");
            titles.add("标题");
            titles.add("发布人");
            titles.add("是否关联视频");
            titles.add("视频ID");
            titles.add("工作人员");
            titles.add("同一工种参与人数");
            titles.add("参与比例");
            titles.add("是否原创");
            titles.add("稿件类型");
            titles.add("工作类型");
            titles.add("图集图片张数");
            titles.add("截止次日appPV");
            titles.add("截止次日appUV");
            titles.add("APP PV/UV值");

        } else {
            titles.add("首次审核时间");
            titles.add("内容ID");
            titles.add("标题");
            titles.add("稿件类型");
            titles.add("发布人");
            titles.add("是否原创");
            titles.add("是否关联视频");
            titles.add("工作人员");
            titles.add("截止次日appPV");
            titles.add("截止次日appUV");
            titles.add("APP PV/UV值");
        }

        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<ContentKpiCountInfo> list = contentKpiCountBiz.getList(startDate, endDate, sourceType, viewType);
        for (ContentKpiCountInfo contentKpiCountInfo : list) {
            List<Object> row = new ArrayList();
            if (1 == viewType) {
                row.add(contentKpiCountInfo.getFirstCheckTime());
                row.add(contentKpiCountInfo.getCid());
                row.add(contentKpiCountInfo.getTitle());
                row.add(contentKpiCountInfo.getCreateName());
                row.add(contentKpiCountInfo.getIsConnectVideo());
                row.add(contentKpiCountInfo.getVideoId());
                row.add(contentKpiCountInfo.getWorkUsers());
                row.add(contentKpiCountInfo.getWorkTypeNum());
                row.add(contentKpiCountInfo.getWorkScale());
                row.add(contentKpiCountInfo.getSourceType());
                row.add(contentKpiCountInfo.getContentType());
                row.add(contentKpiCountInfo.getWorkType());
                row.add(contentKpiCountInfo.getImgNum());
                row.add(contentKpiCountInfo.getAppPv());
                row.add(contentKpiCountInfo.getAppUv());
                row.add(contentKpiCountInfo.getAppScale());
            } else {
                row.add(contentKpiCountInfo.getFirstCheckTime());
                row.add(contentKpiCountInfo.getCid());
                row.add(contentKpiCountInfo.getTitle());
                row.add(contentKpiCountInfo.getContentType());
                row.add(contentKpiCountInfo.getCreateName());
                row.add(contentKpiCountInfo.getSourceType());
                row.add(contentKpiCountInfo.getIsConnectVideo());
                row.add(contentKpiCountInfo.getWorkUsers());
                row.add(contentKpiCountInfo.getAppPv());
                row.add(contentKpiCountInfo.getAppUv());
                row.add(contentKpiCountInfo.getAppScale());
            }
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }
}