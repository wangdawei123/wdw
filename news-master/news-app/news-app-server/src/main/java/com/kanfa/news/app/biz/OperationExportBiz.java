package com.kanfa.news.app.biz;

import com.kanfa.news.app.mapper.OperationExportMapper;
import com.kanfa.news.app.vo.admin.kpicount.OperationExportDataInfo;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *导出运维数据
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-28 14:33:33
 */
@Service
public class OperationExportBiz {
    @Autowired
    private OperationExportMapper operationExportMapper;

    public ExcelData getOperationExportDate(String excelName, String startDate, String endDate) {
//        String exceName = "";
//        if(null != startDate && null != endDate){
//            exceName += startDate.split(" " )[0]+"至"+endDate.split(" ")[0];
//        }
//        exceName += "KpiExport数据统计";
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("内容类型");
        titles.add("内容类型ID");
        titles.add("文章标题");
        titles.add("展示类型");
        titles.add("内容类型");
        titles.add("文章ID");
        titles.add("内容浏览量");
        titles.add("来源");
        titles.add("工作类型");
        titles.add("人员");
        titles.add("发布时间");
        titles.add("参与人数");
        titles.add("个人浏览量");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<OperationExportDataInfo> list = operationExportMapper.list(startDate,endDate);
        for (OperationExportDataInfo operationExportDataInfo:list  ) {
            List<Object> row = new ArrayList();
            row.add(operationExportDataInfo.getType());
            row.add(operationExportDataInfo.getTypeId());
            row.add(operationExportDataInfo.getTitle());
            row.add(operationExportDataInfo.getArticleType());
            row.add(operationExportDataInfo.getCtype());
            row.add(operationExportDataInfo.getCid());
            row.add(operationExportDataInfo.getViews());
            row.add(operationExportDataInfo.getSourceType());
            row.add(operationExportDataInfo.getWtype());
            row.add(operationExportDataInfo.getName());
            row.add(operationExportDataInfo.getCreateTime().replace(".0",""));
            row.add(operationExportDataInfo.getCount());
            row.add(operationExportDataInfo.getMePv());

            rows.add(row);
        }
        data.setRows(rows);
        return data;
        //生成Excel并导出
//        ExportExcelUtils.exportExcel(response,exceName+".xlsx",data);
    }

}