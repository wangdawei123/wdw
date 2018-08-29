package com.kanfa.news.info.mongodb.biz;


import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.mongodb.entity.ContentDetailInfo;
import com.kanfa.news.info.mongodb.entity.Day;
import com.kanfa.news.info.mongodb.mapper.ContentDetailInfoDao;
import com.kanfa.news.info.mongodb.mapper.CountViewContentDao;
import com.kanfa.news.info.mongodb.util.StringDateToInt;
import com.kanfa.news.info.vo.admin.kpicount.CountViewContentInfo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Service("countViewContentService")
public class CountViewContentService {
    /**专题*/
    private static final String TYPE_SPECIAL = "1";
    /**文章*/
    private static final String TYPE_ARTICLE = "2";
    /**图集*/
    private static final String TYPE_PIC = "3";
    /**视频*/
    private static final String TYPE_VIDEO = "4";
    /**直播*/
    private static final String TYPE_LIVE = "9";
    /**VR*/
    private static final String TYPE_VR = "11";
    /**问答*/
    private static final String TYPE_QA = "12";
    /**活动*/
    private static final String TYPE_ACTIVITY = "13";
    /**律师直播*/
    private static final String TYPE_LAWYERLIVE = "21";
    /**直播*/
    private static final String TYPE_LIVE22 = "22";
    /**政法先锋*/
    private static final String TYPE_POLITICLAW = "25";
    /** 爆料 */
    private static final String TYPE_BROKENEW = "33";
    /**按日期排序*/
    private static final String SORT_COUNT_DATE = "count_date";
    /** 按pv排序 SORT_UV*/
    private static final String SORT_PV = "pv";
    /** 按uv排序 */
    private static final String SORT_UV = "uv";



    @Resource
    private CountViewContentDao countViewContentDao;

    @Autowired
    private ContentDetailInfoDao contentDetailInfoDao;

    public Map<String,Object> getTotalAndAllPv(Integer page,Integer limit,Integer type,String sortName,Integer sortOrder,String start,String end,Boolean isCount){

        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        Map result =  new HashMap(2);
        result.put("status",200);
        result.put("data",getStringObjectMap(page,limit,type,sortName,sortOrder,startDate,endDate,isCount));
        return result;
    }

    public List<Map<String,Object>> getTopCountContentView(String start,String end){
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        AggregateIterable<Document> aggregate = countViewContentDao.topCountContentView(startDate,endDate);
        MongoCursor<Document> iterator=aggregate.iterator();
        List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>(16);
        Map<String,Object> map = new HashMap<String,Object>(0);
        Document document = null;
        while(iterator.hasNext()) {
            map = new HashMap<String,Object>(2);
            document = iterator.next();
            map.put("cid",document.get("_id").toString());
            map.put("pv",document.get("pv").toString());
            listMap.add(map);
        }
        return listMap;
    }

    /**
     * 网站访问量数据统计列表统计
     * @param page
     * @param limit
     * @param paramType
     * @param sortName
     * @param sortOrder
     * @param startDate
     * @param endDate
     * @param isCount
     * @return
     */
    public Map<String, Object> getStringObjectMap(Integer page,Integer limit,Integer paramType,String sortName,Integer sortOrder,Integer startDate,Integer endDate,Boolean isCount) {
        AggregateIterable<Document> aggregate = countViewContentDao.getTotalAndAllPv(paramType,sortName, sortOrder,startDate,endDate, isCount);
        MongoCursor<Document> iterator=aggregate.iterator();
        Map<String,Object> map = new HashMap<String,Object>(4);
        int count = 0;
        Set<String> setCount = new HashSet<String>();
        int allPv = 0;
        int allUv = 0;
        List<CountViewContentInfo> rows = new ArrayList<CountViewContentInfo>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        CountViewContentInfo countViewContentInfo = null;
        List<Map<String,Object>> channelMap = null;
        boolean flag = false;
        while(iterator.hasNext()){
            Document document=iterator.next();
            count += Integer.valueOf(document.get("count").toString());
            setCount.add(document.get("_id").toString());
            allPv += Integer.valueOf(document.get("allPv")==null?"0":document.get("allPv").toString()).intValue();
            allUv += Integer.valueOf(document.get("allUv")==null?"0":document.get("allUv").toString()).intValue();
            countViewContentInfo = new CountViewContentInfo();
            countViewContentInfo.setId(Integer.valueOf(document.get("cid").toString()));

            long d = Long.valueOf(document.get("countDate").toString())*1000;
            countViewContentInfo.setCountDate(formatter.format(new Date(d)));
            countViewContentInfo.setTitle(document.getString("title"));
            if(document.get("type").toString().equals(TYPE_SPECIAL)){
                countViewContentInfo.setType("专题");
            }
            else if(document.get("type").toString().equals(TYPE_ARTICLE)){
                countViewContentInfo.setType("文章");
            }
            else if(document.get("type").toString().equals(TYPE_PIC)){
                countViewContentInfo.setType("图集");
            }
            else  if(document.get("type").toString().equals(TYPE_VIDEO)){
                countViewContentInfo.setType("视频");
            }
            else if(document.get("type").toString().equals(TYPE_LIVE)){
                countViewContentInfo.setType("直播");
            }
            else if(document.get("type").toString().equals(TYPE_VR)){
                countViewContentInfo.setType("vr");
            }
            else if(document.get("type").toString().equals(TYPE_QA)){
                countViewContentInfo.setType("问答");
            }
            else if(document.get("type").toString().equals(TYPE_ACTIVITY)){
                countViewContentInfo.setType("活动");
            }
            else if(document.get("type").toString().equals(TYPE_LAWYERLIVE)){
                countViewContentInfo.setType("律师直播");
            }
            else if(document.get("type").toString().equals(TYPE_LIVE22)){
                countViewContentInfo.setType("直播");
            }
            else if(document.get("type").toString().equals(TYPE_POLITICLAW)){
                countViewContentInfo.setType("政法先锋");
            }
            else if(document.get("type").toString().equals(TYPE_BROKENEW)){
                countViewContentInfo.setType("爆料");
            }

            String reg = "[^\u4e00-\u9fa5]";

            countViewContentInfo.setCreateName(document.get("createName")==null?"":document.get("createName").toString());
            countViewContentInfo.setAuthor(document.get("author")==null?"":document.get("author").toString().replaceAll(reg, ""));
            countViewContentInfo.setSrc(document.get("src")==null?"":document.get("src").toString());
            countViewContentInfo.setTag(document.get("tag")==null?"":document.getString("tag"));
            countViewContentInfo.setTitle(document.get("title")==null?"":document.get("title").toString());
            channelMap = (List<Map<String,Object>>)document.get("channel");
            String channelName="";
            if(null != channelMap){
                for (Map<String,Object> tmap :channelMap ) {
                    if(null == tmap.get("name")){
                        continue;
                    }
                    channelName += tmap.get("name")+" ";
                }
            }
            countViewContentInfo.setChannel(document.get("channel")==null?"":channelName);
            countViewContentInfo.setPv(Integer.valueOf(document.get("allPv")==null?"0":document.get("allPv").toString()));
            countViewContentInfo.setUv(Integer.valueOf(document.get("allUv")==null?"0":document.get("allUv").toString()));

            for(CountViewContentInfo row:rows){
                if(isCount && row.getId().intValue() == Integer.valueOf(document.get("_id").toString()).intValue()){
                    row.setPv(row.getPv() + countViewContentInfo.getPv());
                    row.setUv(row.getUv() + countViewContentInfo.getUv());
                    flag = true;
                    break;
                }
            }

            if(!flag){
                rows.add(countViewContentInfo);
            }

        }
        //是否合计
        if(isCount){
            map.put("total",setCount.size());
        }else{
            map.put("total",count);
        }

        map.put("allPv",allPv);
        map.put("allUv",allUv);

        //排序
        if(SORT_COUNT_DATE.equals(sortName)){
            sortDateList(rows,sortOrder);
        }
        else if(SORT_PV.equals(sortName)){
            sortPvList(rows,sortOrder);
        }
        else if(SORT_UV.equals(sortName)){
            sortUvList(rows,sortOrder);
        }

        //是否有分页判断
        if(null != page && null != limit){
            Integer startRow = (page - 1) * limit;
            Integer endRow = page * limit;
            List<CountViewContentInfo> pagerows = new ArrayList<CountViewContentInfo>();
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
     * list 日期排序
     * @param list
     * @param ascDesc
     */
    public void sortDateList(List list, Integer ascDesc) {
        Collections.sort(list, new Comparator<CountViewContentInfo>(){
            @Override
            public int compare(CountViewContentInfo c1, CountViewContentInfo c2) {
                if (ascDesc == 1) {
                    //升序
                    int res = c1.getCountDate().compareTo(c2.getCountDate());
                    return res;
                }
                //降序
                int res = c2.getCountDate().compareTo(c1.getCountDate());
                return res;

            }
        });
    }

    /**
     * list pv排序
     * @param list
     * @param ascDesc
     */
    public void sortPvList(List list, Integer ascDesc) {
        Collections.sort(list, new Comparator<CountViewContentInfo>(){
            @Override
            public int compare(CountViewContentInfo c1, CountViewContentInfo c2) {
                if (ascDesc == 1) {
                    //升序
                    int res = c1.getPv().compareTo(c2.getPv());
                    return res;
                }
                //降序
                int res = c2.getPv().compareTo(c1.getPv());
                return res;

            }
        });
    }

    /**
     * list uv排序
     * @param list
     * @param ascDesc
     */
    public void sortUvList(List list, Integer ascDesc) {

        Collections.sort(list, new Comparator<CountViewContentInfo>(){
            @Override
            public int compare(CountViewContentInfo c1, CountViewContentInfo c2) {
                if (ascDesc == 1) {
                    //升序
                    int res = c1.getUv().compareTo(c2.getUv());
                    return res;
                }
                //降序
                int res = c2.getUv().compareTo(c1.getUv());
                return res;

            }
        });
    }


    /**
     * 导出Excel
     * @param excelName
     * @param type
     * @param sortName
     * @param sortOrder
     * @param startDate
     * @param endDate
     * @param isCount
     * @throws Exception
     */
    public ExcelData getExcel(String excelName, Integer type, String sortName, Integer sortOrder, String startDate, String endDate, Boolean isCount){
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("日期");
        titles.add("标题");
        titles.add("类型");
        titles.add("添加人");
        titles.add("作者");
        titles.add("来源");
        titles.add("标签");
        titles.add("频道");
        titles.add("PV量");
        titles.add("UV量");
        data.setTitles(titles);
        Map map = getTotalAndAllPv(null,null,type,sortName,sortOrder,startDate,endDate,isCount);
        Map rmap =  (Map)map.get("data");
        List<CountViewContentInfo> resultList = (List<CountViewContentInfo>)rmap.get("rows");
        List<List<Object>> rows = new ArrayList();
        List<Object> row = null;
        for (CountViewContentInfo countViewContentInfo:resultList ) {
            row = new ArrayList();
            row.add(countViewContentInfo.getId());
            row.add(countViewContentInfo.getCountDate());
            row.add(countViewContentInfo.getTitle());
            row.add(countViewContentInfo.getType());
            row.add(countViewContentInfo.getCreateName());
            row.add(countViewContentInfo.getAuthor());
            row.add(countViewContentInfo.getSrc());
            row.add(countViewContentInfo.getTag());
            row.add(countViewContentInfo.getChannel());
            row.add(countViewContentInfo.getPv());
            row.add(countViewContentInfo.getUv());

            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }


    public Map<String,List<Document>> getViewContent(Integer startDate, Integer endDate, Integer days) {
        Map<String,List<Document>> resultMap = countViewContentDao.getViewContent(startDate,endDate);
       return resultMap;

    }

    public void savePvData(Map<String, List<Document>> resultMap) {
        List<Document> contentList = resultMap.get("content");
        int contentListSize = contentList.size();
        if(null != contentList && contentListSize > 0 ){
            for (int i = 0; i < contentListSize; i++) {
                Document content = contentList.get(i);
                Query query = new Query();
                query.addCriteria(where("from_id").is("1").and("cid").is(content.get("cid").toString()));
                contentDetailInfoDao.update(query,new Update()
                                                            .inc("pv_total",content.getInteger("pv_total"))
                                                            .inc("app_pv",content.getInteger("app_pv"))
                                                            .inc("play_total",content.getInteger("play_total"))
                                                            .inc("play_app",content.getInteger("play_app"))
                                                            );
            }
        }
        List<Document> liveList = resultMap.get("live");
        int liveListSize = liveList.size();
        if(null != liveList && liveListSize > 0 ){
            for (int i = 0; i < liveListSize; i++) {
                Document live = liveList.get(i);
                Query query = new Query();
                query.addCriteria(where("from_id").is("2").and("cid").is(live.get("cid").toString()));
                contentDetailInfoDao.update(query,new Update()
                        .inc("pv_total",live.getInteger("pv_total"))
                        .inc("app_pv",live.getInteger("app_pv"))
                        .inc("play_total",live.getInteger("play_total"))
                        .inc("play_app",live.getInteger("play_app"))
                );
            }
        }


    }

    public void saveDayPvPlayData(Map<String, List<Document>> resultMap, Integer startDate) {
         String date = StringDateToInt.TimeStamp2Date(startDate.toString());
         List<Document> contentList = resultMap.get("content");
         int contentListSize = contentList.size();
         if(null != contentList && contentListSize > 0 ){
             for (int i = 0; i < contentListSize; i++) {
                 Document content = contentList.get(i);
                 Query query = new Query();
                 query.addCriteria(where("from_id").is("1").and("cid").is(content.get("cid").toString()));
//                 ContentDetailInfo contentDetailInfo = contentDetailInfoDao.findOne(query);
                 Day day = new Day();
                 day.setT(Integer.valueOf(date));
                 day.setPlay_total(content.getInteger("pv_total"));
                 day.setApp_pv(content.getInteger("app_pv"));
                 day.setPlay_total(content.getInteger("play_total"));
                 day.setPlay_app(content.getInteger("play_app"));
                 List<Day> dayList = new ArrayList<Day>(1);
                 dayList.add(day);
//                 contentDetailInfo.setDay(dayList);
//                 contentDetailInfoDao.updateById(contentDetailInfo.getId().toString(),contentDetailInfo);
                 contentDetailInfoDao.update(query,new Update().addToSet("day",dayList));
             }
         }


         List<Document> liveList = resultMap.get("live");
         int liveListSize = liveList.size();
         if(null != liveList && liveListSize > 0 ){
             for (int i = 0; i < liveListSize; i++) {
                 Document live = liveList.get(i);
                 Query query = new Query();
                 query.addCriteria(where("from_id").is("2").and("cid").is(live.get("cid").toString()));
//                 ContentDetailInfo contentDetailInfo = contentDetailInfoDao.findOne(query);
                 Day day = new Day();
                 day.setT(Integer.valueOf(date));
                 day.setPlay_total(live.getInteger("pv_total"));
                 day.setApp_pv(live.getInteger("app_pv"));
                 day.setPlay_total(live.getInteger("play_total"));
                 day.setPlay_app(live.getInteger("play_app"));
                 List<Day> dayList = new ArrayList<Day>(1);
                 dayList.add(day);
//                 contentDetailInfo.setDay(dayList);
                 contentDetailInfoDao.update(query,new Update().addToSet("day",dayList));
             }
         }


    }


}
