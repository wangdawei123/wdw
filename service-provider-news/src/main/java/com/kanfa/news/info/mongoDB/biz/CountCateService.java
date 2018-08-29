package com.kanfa.news.info.mongodb.biz;


import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.mongodb.mapper.CountCateDao;
import com.kanfa.news.info.mongodb.util.StringDateToInt;
import com.kanfa.news.info.vo.admin.kpicount.CountCateInfo;
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
@Service("countCateService")
public class CountCateService {
    //文章
    private static final String TYPE_ARTICLE="1";
    //视频
    private static final String TYPE_VIDEO="2";
    //VR
    private static final String TYPE_VR="3";
    //原创
    private static final String TYPE_ORIGINAL="20";
    //转载
    private static final String TYPE_REPRINT="21";
    //采编
    private static final String TYPE_EDITING="22";
    //法制
    private static final String TYPE_LEGAL="23";

    private static final String TYPE_ARTICLE_NAME="文章";
    private static final String TYPE_VIDEO_NAME="视频";
    private static final String TYPE_VR_NAME="VR";
    private static final String TYPE_ORIGINAL_NAME="原创";
    private static final String TYPE_REPRINT_NAME="转载";
    private static final String TYPE_EDITING_NAME="采编";
    private static final String TYPE_LEGAL_NAME="法制";
    //升序排序
    private static final Integer ASCDESC_ASC  = 1;
    //降序排序
    private static final Integer ASCDESC_DESC  = -1;

    @Resource
    private CountCateDao countCateDao;

    public Map<String,Object> getTotalAndAllPv(Integer page,Integer limit,Integer typeId,String start,String end){

        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        Map result =  new HashMap();
        result.put("status",200);
        result.put("data",getStringObjectMap(page,limit,typeId,startDate,endDate));
        return result;
    }

    /**
     * 每日类目统计
     * @param page
     * @param limit
     * @param typeId
     * @param startDate
     * @param endDate
     * @return
     */
    public Map<String, Object> getStringObjectMap(Integer page,Integer limit,Integer typeId,Integer startDate,Integer endDate) {
        AggregateIterable<Document> aggregate = countCateDao.getCountChannelContent(typeId,startDate,endDate);
        MongoCursor<Document> iterator=aggregate.iterator();
        Map<String,Object> map = new HashMap<String,Object>();
        List<CountCateInfo> realResult = new ArrayList<CountCateInfo>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        while(iterator.hasNext()){
            Document document=iterator.next();
            CountCateInfo countCateInfo = new CountCateInfo();

            long d = Long.valueOf(document.get("countDate").toString())*1000;
            countCateInfo.setCountDate(formatter.format(new Date(d)));
            countCateInfo.setContentCount(document.getInteger("contentCount"));
            if(document.get("type").toString().equals(TYPE_ARTICLE)){
                countCateInfo.setType("文章");
            }
            else if(document.get("type").toString().equals(TYPE_VIDEO)){
                countCateInfo.setType("视频");
            }
            else if(document.get("type").toString().equals(TYPE_VR)){
                countCateInfo.setType("vr");
            }
            else if(document.get("type").toString().equals(TYPE_ORIGINAL)){
                countCateInfo.setType("原创");
            }
            else if(document.get("type").toString().equals(TYPE_REPRINT)){
                countCateInfo.setType("转载");
            }
            else if(document.get("type").toString().equals(TYPE_EDITING)){
                countCateInfo.setType("采集");
            }
            else if(document.get("type").toString().equals(TYPE_LEGAL)){
                countCateInfo.setType("法制");
            }
            realResult.add(countCateInfo);
        }

    /*    List<String> typeList = new ArrayList<String>();
        typeList.add(TYPE_ARTICLE_NAME);
        typeList.add(TYPE_VIDEO_NAME);
        typeList.add(TYPE_VR_NAME);
        typeList.add(TYPE_ORIGINAL_NAME);
        typeList.add(TYPE_REPRINT_NAME);
        typeList.add(TYPE_EDITING_NAME);
        typeList.add(TYPE_LEGAL_NAME);
        List<CountCateInfo> rows =  createByDateAndType(new Date( Long.valueOf(startDate)*1000),new Date( Long.valueOf(endDate-86399)*1000),typeList);

        for (CountCateInfo row:realResult) {
            for (CountCateInfo countCateInfo:rows ) {
                if(countCateInfo.getCountDate().equals(row.getCountDate()) && countCateInfo.getType().equals(row.getType())){
                    countCateInfo.setContentCount(row.getContentCount());
                }
            }
        }*/
        sortDateCountList(realResult,ASCDESC_ASC);
        map.put("total",realResult.size());


        //是否有分页判断
        if(null != page && null != limit){
            Integer startRow = (page - 1) * limit;
            Integer endRow = page * limit;
            List<CountCateInfo> pagerows = new ArrayList<CountCateInfo>();
            Integer dataRows = endRow >= realResult.size()?realResult.size():endRow;
            for(int i = startRow; i < dataRows ; i++){
                pagerows.add(realResult.get(i));
            }
            //分页显示的数据
            map.put("rows",pagerows);
        }else{
            //不分页的数据，导出Excel可以直接从这里取
            map.put("rows",realResult);
        }

        return map;
    }

   /* public List<CountCateInfo> createByDateAndType(Date startDate,Date dEnd,List<String> typeList){
        List<CountCateInfo> list = new ArrayList<CountCateInfo>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List < Date > lDate = findDates(startDate, dEnd);
        CountCateInfo countCateInfo = null;
        for (Date date: lDate) {
            for (String type:typeList) {
                countCateInfo = new CountCateInfo();
                countCateInfo.setType(type);
                countCateInfo.setCountDate(formatter.format(date));
                countCateInfo.setContentCount(0);
                list.add(countCateInfo);
            }
        }

        return list;
    }*/

    /**
     * 获取某段时间内的所有日期
      * @param dBegin
     * @param dEnd
     * @return
     */
    public static List <Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 导出Excel
     * @param excelName
     * @param typeId
     * @param startDate
     * @param endDate
     */
    public ExcelData getExcel(String excelName, Integer typeId,  String startDate, String endDate) {

        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("统计日期");
        titles.add("类目");
        titles.add("数量");
        data.setTitles(titles);
        Map map = getTotalAndAllPv(null,null,typeId,startDate,endDate);
        Map rmap =  (Map)map.get("data");
        List<CountCateInfo> resultList = (List<CountCateInfo>)rmap.get("rows");
        List<List<Object>> rows = new ArrayList();
        List<Object> row = null;
        for (CountCateInfo countCateInfo:resultList ) {
            row = new ArrayList();
            row.add(countCateInfo.getCountDate());
            row.add(countCateInfo.getType());
            row.add(countCateInfo.getContentCount());
            rows.add(row);
        }
        data.setRows(rows);
        return  data;
        //生成Excel并导出
//        ExportExcelUtils.exportExcel(response,exceName+".xlsx",data);
    }

    /**
     * 按日期、数量排序
     * @param list
     * @param ascDesc 1 升序  -1降序
     */
    public void sortDateCountList(List list, Integer ascDesc) {
        Collections.sort(list, new Comparator<CountCateInfo>() {
            @Override
            public int compare(CountCateInfo c1, CountCateInfo c2) {
                if (ascDesc == 1) {
                    //升序
                    if (c1.getCountDate().compareTo(c2.getCountDate()) > 0) {
                        return 1;
                    } else if (c1.getCountDate().compareTo(c2.getCountDate()) == 0) {
                        if (c1.getContentCount() > c2.getContentCount()) {
                            return -1;
                        } else if (c1.getContentCount().compareTo(c2.getContentCount())==0) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }

                } else {


                    //降序
                    if (c2.getCountDate().compareTo(c1.getCountDate()) > 0) {
                        return 1;
                    } else if (c2.getCountDate().compareTo(c1.getCountDate()) == 0) {
                        if (c2.getContentCount() > c1.getContentCount()) {
                            return -1;
                        } else if (c2.getContentCount().compareTo(c1.getContentCount())==0) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                }
                return 0;
            }

        });
    }

}
