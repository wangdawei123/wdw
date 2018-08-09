package com.kanfa.news.info.mongodb.biz;


import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.mongodb.mapper.CountChannelContentDao;
import com.kanfa.news.info.mongodb.util.StringDateToInt;
import com.kanfa.news.info.vo.admin.kpicount.CountChannelContentInfo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Service("countChannelContentService")
public class CountChannelContentService {

    @Resource
    private CountChannelContentDao countChannelContentDao;

    public Map<String,Object> getTotalAndAllPv(Integer page,Integer limit,Integer categoryId,Integer channelId,String start,String end){

        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        Map result =  new HashMap();
        result.put("status",200);
        result.put("data",getStringObjectMap(page,limit,categoryId,channelId,startDate,endDate));
        return result;
    }

    /**
     * 渠道发稿量统计
     * @param page
     * @param limit
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    public Map<String, Object> getStringObjectMap(Integer page,Integer limit,Integer categoryId,Integer channelId,Integer startDate,Integer endDate) {
        AggregateIterable<Document> aggregate = countChannelContentDao.getCountChannelContent(categoryId,channelId,startDate,endDate);
        MongoCursor<Document> iterator=aggregate.iterator();
        Map<String,Object> map = new HashMap<String,Object>();
        int count = 0;
        int total = 0;
        List<CountChannelContentInfo> rows = new ArrayList<CountChannelContentInfo>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        CountChannelContentInfo countChannelContentInfo = null;
        while(iterator.hasNext()){
            Document document=iterator.next();
            count += Integer.valueOf(document.get("contentCount").toString());
            countChannelContentInfo = new CountChannelContentInfo();

            long d = Long.valueOf(document.get("countDate").toString())*1000;
            countChannelContentInfo.setCountDate(formatter.format(new Date(d)));
            countChannelContentInfo.setChannelId(document.getInteger("channelId"));
            countChannelContentInfo.setChannelName(document.get("channelName").toString());
            countChannelContentInfo.setContentCount(document.getInteger("contentCount"));
            rows.add(countChannelContentInfo);
            total ++;
        }
        map.put("total",total);
        map.put("allContentCount",count);


        //是否有分页判断
        if(null != page && null != limit){
            Integer startRow = (page - 1) * limit;
            Integer endRow = page * limit;
            List<CountChannelContentInfo> pagerows = new ArrayList<CountChannelContentInfo>();
            Integer dataRows = endRow >= rows.size()?rows.size():endRow;
            for(int i = startRow; i < dataRows ; i++){
                pagerows.add(rows.get(i));
            }
            //分页显示的数据
            map.put("rows",pagerows);
        }else{
            //不分页的数据，导出Excel可以直接从这里取
            map.put("rows",rows);
        }

        return map;
    }




    /**
     * 导出Excel
     * @param excelName
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @throws Exception
     */
    public ExcelData getExcel(String excelName, Integer categoryId, Integer channelId, String startDate, String endDate) {

        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("统计日期");
        titles.add("渠道名");
        titles.add("渠道Id");
        titles.add("数量");
        data.setTitles(titles);
        Map map = getTotalAndAllPv(null,null,categoryId,channelId,startDate,endDate);
        Map rmap =  (Map)map.get("data");
        List<CountChannelContentInfo> resultList = (List<CountChannelContentInfo>)rmap.get("rows");
        List<List<Object>> rows = new ArrayList();
        List<Object> row = null;
        for (CountChannelContentInfo countChannelContentInfo:resultList ) {
            row = new ArrayList();
            row.add(countChannelContentInfo.getCountDate());
            row.add(countChannelContentInfo.getChannelName());
            row.add(countChannelContentInfo.getChannelId());
            row.add(countChannelContentInfo.getContentCount());
            rows.add(row);
        }
        data.setRows(rows);
        return  data;
        //生成Excel并导出
//        ExportExcelUtils.exportExcel(response,exceName+".xlsx",data);
    }


}
