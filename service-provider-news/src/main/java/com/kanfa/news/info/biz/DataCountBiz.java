package com.kanfa.news.info.biz;

import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.mapper.DataCountMapper;
import com.kanfa.news.info.vo.admin.kpicount.DataCountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据统计
 *
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-27 11:51:59
 */
@Service
public class DataCountBiz  {

    private final static Integer TYPE_SPECIAL = 1;     //"专题"
    private final static Integer TYPE_ARTICLE = 2;     //"文章"
    private final static Integer TYPE_PIC = 3;         //"图集"
    private final static Integer TYPE_VIDEO = 4;       //"视频"
    private final static Integer TYPE_LIVE = 9;        //"直播"
    private final static Integer TYPE_VR = 11;         //"VR"
    private final static Integer TYPE_QA = 12;         //"问答"
    private final static Integer TYPE_ACTIVITY = 13;   //"活动"
    @Autowired
    private DataCountMapper dataCountMapper;

    /**
     * 统计列表
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<DataCountInfo> getDataCountList(Integer categoryId, Integer channelId, String startDate,String endDate){
        List<DataCountInfo> list =  dataCountMapper.dataCountTop100List(categoryId,channelId,startDate,endDate);
        for (DataCountInfo dataCountInfo:list) {

            dataCountInfo.setPublishTime(dataCountInfo.getPublishTime().replace(".0",""));
            if(dataCountInfo.getContentType().equals(TYPE_SPECIAL)){
                dataCountInfo.setTypeName("专题");
            }else if(dataCountInfo.getContentType().equals(TYPE_ARTICLE)){
                dataCountInfo.setTypeName("文章");
            }else if(dataCountInfo.getContentType().equals(TYPE_PIC)){
                dataCountInfo.setTypeName("图集");
            }else if(dataCountInfo.getContentType().equals(TYPE_VIDEO)){
                dataCountInfo.setTypeName("视频");
            }else if(dataCountInfo.getContentType().equals(TYPE_LIVE)){
                dataCountInfo.setTypeName("直播");
            }else if(dataCountInfo.getContentType() ==TYPE_VR){
                dataCountInfo.setTypeName("vr");
            }else if(dataCountInfo.getContentType() ==TYPE_QA){
            }else if(dataCountInfo.getContentType().equals(TYPE_VR)){
                dataCountInfo.setTypeName("VR");
            }else if(dataCountInfo.getContentType().equals(TYPE_QA)){
                dataCountInfo.setTypeName("问答");
            }else if(dataCountInfo.getContentType().equals(TYPE_ACTIVITY)){
                dataCountInfo.setTypeName("活动");

            }
        }
        return list;
    }

    public Integer getAllCount(){
        return dataCountMapper.allViewContent();
    }

    public Integer getPublishCount(Integer categoryId, Integer channelId){
        return dataCountMapper.publishCount(categoryId,channelId);
    }

    public ExcelData getListToExcel(String excelName, Integer categoryId, Integer channelId, String startDate, String endDate) {
//        String exceName = "";
//        if(null != startDate && null != endDate){
//            exceName += startDate.split(" " )[0]+"至"+endDate.split(" ")[0];
//        }
//        exceName += "数据统计TOP100";
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("标题");
        titles.add("类型");
        titles.add("浏览量");
        titles.add("创建人");
        titles.add("发布时间");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<DataCountInfo> list = getDataCountList(categoryId,channelId,startDate,endDate);
        List<Object> row = null;
        for (DataCountInfo dataCountInfo:list  ) {
            row = new ArrayList();
            row.add(dataCountInfo.getId());
            row.add(dataCountInfo.getTitle());
            row.add(dataCountInfo.getTypeName());
            row.add(dataCountInfo.getViewCount());
            row.add(dataCountInfo.getRealName());
            row.add(dataCountInfo.getPublishTime());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
        //生成Excel并导出
//        ExportExcelUtils.exportExcel(response,exceName+".xlsx",data);
    }
}