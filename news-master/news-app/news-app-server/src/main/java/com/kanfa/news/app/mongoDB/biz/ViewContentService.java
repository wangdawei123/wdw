package com.kanfa.news.app.mongoDB.biz;


import com.kanfa.news.app.mongoDB.entity.ViewContent;
import com.kanfa.news.app.mongoDB.mapper.ViewContentDao;
import com.kanfa.news.app.mongoDB.util.StringDateToInt;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
  *@author jiayw
  * @email jiayunwei@kanfanews.com
  * @date 2018/3/29 18:02
  */
@Service("viewContentService")
public class ViewContentService {

    @Resource
    private ViewContentDao viewContentDao;


    public GroupByResults<ViewContent> listByDate(String groupName, Set collectionArray, String  start, String end){
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        return viewContentDao.listViewContent(groupName,collectionArray,startDate,endDate);
    }

    public GroupByResults<ViewContent> listVideoByDate(String groupName,Set collectionArray, String  start, String end){

        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        return viewContentDao.listViewContentVideo(groupName,collectionArray,startDate,endDate);
    }
    public Map<String,Object> getPvAndVv(String  start, String end){

        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        GroupByResults<ViewContent> pv =viewContentDao.getPv(startDate,endDate);
        List<Document> pvList = (List<Document>)pv.getRawResults().get("retval");
        GroupByResults<ViewContent> vv =viewContentDao.getVv(startDate,endDate);
        List<Document> vvList = (List<Document>)vv.getRawResults().get("retval");
        Map<String,Object> map = new HashMap<String,Object>(2);
        map.put("pv",pvList);
        map.put("vv",vvList);
        return map;
    }

    public Map<String,Integer> getPvAndPlayCount(Integer startDate, Integer endDate){

        int pvCount =viewContentDao.getPvCount(startDate,endDate);
        int playCount =viewContentDao.getPlayCount(startDate,endDate);
        Map<String,Integer> map = new HashMap<String,Integer>(2);
        map.put("pv",pvCount);
        map.put("play",playCount);
        return map;
    }

    /**视频排行榜*/
    public List<Map<String,Object>> getTopViewContentVideo(String start,String end){
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        AggregateIterable<Document> aggregate = viewContentDao.topViewContentVideo(startDate,endDate);
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

    /**直播排行榜*/
    public List<Map<String,Object>> getTopViewContentLive(String start,String end){
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        AggregateIterable<Document> aggregate = viewContentDao.topViewContentLive(startDate,endDate);
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


}
