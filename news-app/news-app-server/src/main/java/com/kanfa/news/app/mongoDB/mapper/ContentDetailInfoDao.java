package com.kanfa.news.app.mongoDB.mapper;


import com.kanfa.news.app.mongoDB.entity.ContentDetailInfo;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.Filter.filter;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/4 13:02
 */
@Component
public class ContentDetailInfoDao extends AbstractMongodbBaseDao<ContentDetailInfo> {

    /**
     * 发稿查询
     * @param startDate
     * @param endDate
     * @return
     */
    public List getList(Integer startDate, Integer endDate,Integer pvEndDate,Integer uid,Integer page,Integer limit,Integer ascDesc,String orderColumn){
        //过滤符合条件的day
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String start = dateFormat.format(new Date( new Long(startDate)*1000));
        String end= dateFormat.format(new Date( new Long(pvEndDate)*1000));
        Document filterCond = Document.parse(" { \"$and\" :[{\"$gte\": [ \"$$dayt.t\", "+start+" ] },{\"$lte\": [ \"$$dayt.t\", "+end+" ]}]}");
        Criteria matchFilter =  where("first_check_time").gte(startDate).lt(endDate);
        if(null != uid){
            matchFilter.andOperator(matchFilter,where("editor.uid").is(uid));
        }

        TypedAggregation<InventoryItem> agg = newAggregation(InventoryItem.class,
                match(matchFilter),
                project()
                        .andExpression("1").as("editor.uid")
                        .and("article").applyCondition(
                                when(where("type").is(2))
                                        .then(1)
                                        .otherwise(0))
                        .and("image").applyCondition(
                                when(where("type").is(3))
                                        .then(1)
                                        .otherwise(0))
                        .and("video").applyCondition(
                                when(where("type").is(4))
                                        .then(1)
                                        .otherwise(0))
                        .and("original_article").applyCondition(
                                when(where("").andOperator(where("type").in(2,3),where("source_type").is(1)))
                                        .then(1)
                                        .otherwise(0))
                        .and("original_video").applyCondition(
                                when(where("").andOperator(where("type").is(4),where("source_type").is(1)))
                                        .then(1)
                                        .otherwise(0))
                        .and("column_live").applyCondition(
                                when(where("live_type").is(1))
                                        .then(1)
                                        .otherwise(0))
                        .and("ordinary_live").applyCondition(
                                when(where("live_type").is(2))
                                        .then(1)
                                        .otherwise(0))
                        .and(filter("day").as("dayt").by(new Document(filterCond))).as("day")
                ,
                project()
                        .and("editor.uid").as("editor.uid")
                        .and("article").as("article")
                        .and("image").as("image")
                        .and("video").as("video")
                        .and("original_article").as("original_article")
                        .and("original_video").as("original_video")
                        .and("column_live").as("column_live")
                        .and("ordinary_live").as("ordinary_live")
                        //PV(不含视频、直播页面)合计
                        .andExpression("sum(day.pv_total)").as("pv_total")
//                        .andExpression("sum(day.app_pv)").as("app_pv")
                        //播放量(包括视频、直播)合计
                        .andExpression("sum(day.play_total)").as("play_total")
//                        .andExpression("sum(day.play_app)").as("play_app")
                ,
                group("editor.uid")
                        .sum("article").as("articleSum")
                        .sum("image").as("imageSum")
                        .sum("video").as("videoSum")
                        .sum("original_article").as("originalArticleSum")
                        .sum("original_video").as("originalVideoSum")
                        .sum("column_live").as("columnLiveSum")
                        .sum("ordinary_live").as("ordinaryLiveSum")
                        .sum("pv_total").as("pvTotal")
//                        .sum("app_pv").as("app_pv")
                        .sum("play_total").as("playTotal")
//                        .sum("play_app").as("play_app")
                ,
                sort(ascDesc==1?ASC:DESC, orderColumn),
                skip(page>1?(page-1)*limit:0L),
                limit(limit)
        );

        AggregationResults<Document> result = mongoTemplate.aggregate(agg, "content_detail_info", Document.class);
        List<Document> list = result.getMappedResults();
        return list;
    }

    /**
     * 发稿明细
     * @param startDate
     * @param endDate
     * @return
     */
    public List getDetailList(Integer startDate, Integer endDate,Integer pvEndDate,Integer uid,Integer page,Integer limit,Integer ascDesc,String orderColumn,Integer type,Integer sourceType,String title){
        //过滤符合条件的day
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String start = dateFormat.format(new Date( new Long(startDate)*1000));
        String end= dateFormat.format(new Date( new Long(pvEndDate)*1000));
        Document filterCond = Document.parse(" { \"$and\" :[{\"$gte\": [ \"$$dayt.t\", "+start+" ] },{\"$lte\": [ \"$$dayt.t\", "+end+" ]}]}");
        Criteria matchFilter = null;
        List<Criteria> whereList = new ArrayList<>(5);
        whereList.add(where("first_check_time").gte(startDate).lt(endDate));

            if(null != uid){
                whereList.add(where("editor.uid").is(uid));
            }
            if(null != type){
                whereList.add(where("type").is(type));
            }
            if(null != sourceType){
                whereList.add(where("sourceType").is(sourceType));
            }

        if(null != title && isNumeric(title)){
            whereList.add(where("cid").is(Integer.valueOf(title)));

        }else if(null != title && !isNumeric(title)){
//            whereList.add(where("title").regex(".*?\\" +title+ ".*"));
            whereList.add(where("title").regex(title));
        }
        matchFilter =  where("").andOperator(whereList.toArray(new Criteria[0]));
        TypedAggregation<InventoryItem> agg = newAggregation(InventoryItem.class,
                match(matchFilter),
                project()
                        .andExpression("1").as("cid")
                        .andExpression("1").as("title")
                        .andExpression("1").as("editor.uid")
                        .andExpression("1").as("editor.name")
                        .andExpression("1").as("check.name")
                        .andExpression("1").as("check.time")
                        .andExpression("1").as("first_check_time")
                        .andExpression("1").as("source_type")
                        .andExpression("1").as("type")
                        .andExpression("1").as("live_type")
                        .and(filter("day").as("dayt").by(filterCond)).as("day")
                ,
                project()
                        .andExpression("1").as("cid")
                        .andExpression("1").as("title")
                        .and("editor.uid").as("editorUid")
                        .and("editor.name").as("editorName")
                        .and("check.name").as("checkName")
                        .and("check.time").as("checkTime")
                        .and("first_check_time").as("firstCheckTime")
                        .and("source_type").as("sourceType")
                        .and("type").as("type")
                        .and("live_type").as("liveType")
                        //PV(不含视频、直播页面)合计
                        .andExpression("sum(day.pv_total)").as("pvTotal")
                        .andExpression("sum(day.app_pv)").as("appPv")
                        //播放量(包括视频、直播)合计
                        .andExpression("sum(day.play_total)").as("playTotal")
                        .andExpression("sum(day.play_app)").as("playApp")
                ,
                sort(ascDesc==1?ASC:DESC, orderColumn==null?"cid":orderColumn),
                skip(page>1?(page-1)*limit:0L),
                limit(limit)
        );

        AggregationResults<Document> result = mongoTemplate.aggregate(agg, "content_detail_info", Document.class);
        List<Document> detailList = result.getMappedResults();
        return detailList;
    }

    public Integer getTotalCount(Integer startDate, Integer endDate, Integer uid,Integer type,Integer sourceType,String title, Boolean isDetail) {
        Criteria matchFilter =  null;
        List<Criteria> whereList = new ArrayList<>(5);
        whereList.add(where("first_check_time").gte(startDate).lt(endDate));

        if(null != uid){
            whereList.add(where("editor.uid").is(uid));
        }
        if(null != type){
            whereList.add(where("type").is(type));
        }
        if(null != sourceType){
            whereList.add(where("sourceType").is(sourceType));
        }

        if(null != title && isNumeric(title)){
            whereList.add(where("cid").is(Integer.valueOf(title)));

        }else if(null != title && !isNumeric(title)){
            whereList.add(where("title").regex(".*?\\" +title+ ".*"));
        }
        matchFilter =  where("").andOperator(whereList.toArray(new Criteria[0]));
        TypedAggregation<InventoryItem> agg = null;
        if(!isDetail){
           agg = newAggregation(InventoryItem.class,
                    match(matchFilter),
                    project()
                            .andExpression("1").as("editor.uid"),
                    group("editor.uid")
            );
        }else{
            agg = newAggregation(InventoryItem.class,
                    match(matchFilter),
                    project()
                            .andExpression("1").as("editor.uid")
            );
        }


        AggregationResults<Document> result = mongoTemplate.aggregate(agg, "content_detail_info", Document.class);
        List<Document> stateStatsList = result.getMappedResults();
        return stateStatsList.size();
    }
    /**
     * 匹配是否为数字
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     * @author Jiayw
     */
    public  boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            //异常 说明包含非数字。
            return false;
        }
        return true;
    }
}

class InventoryItem {
    String project;
    List<Object> day;
}

