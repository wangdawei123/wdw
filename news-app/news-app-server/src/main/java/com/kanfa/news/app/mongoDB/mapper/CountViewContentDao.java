package com.kanfa.news.app.mongoDB.mapper;


import com.kanfa.news.app.mongoDB.entity.ViewContent;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Component
public class CountViewContentDao extends AbstractMongodbBaseDao<ViewContent> {

    /**
     * 获取总条数，总pv,总uv,内容数据
     * @param paramType 类型
     * @param sortName 排序名称
     * @param sortOrder 排序方式
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @isCount isCount 是否查看总计
     * @return
     */
    public AggregateIterable<Document> getTotalAndAllPv(Integer paramType,String sortName,Integer sortOrder,Integer startDate,Integer endDate,Boolean isCount){
        //match
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("count_date", new Document("$gt", startDate)));
        l.add(new Document("count_date", new Document("$lte", endDate)));
        if(null != paramType){
            l.add(new Document("type", new Document("$eq", paramType)));
        }
        Document match = new Document("$and", l);

        //group
        Document group =  new Document();
        //是否合计，如果合计则按cid分组统计
        if(isCount){
            group.append("_id", "$cid")
                        .append("cid", new Document("$first","$cid"))
                    .append("count", new Document("$sum",1))
                    .append("title", new Document("$first","$title"))
                    .append("type", new Document("$first","$type"))
                    .append("createName", new Document("$first","$create_name"))
                    .append("author", new Document("$first","$author"))
                    .append("src", new Document("$first","$src"))
                    .append("tag", new Document("$first","$tag "))
                    .append("channel", new Document("$first","$channel"))
                    .append("countDate", new Document("$first","$count_date"))
                    .append("allPv", new Document("$sum","$pv"))
                    .append("allUv", new Document("$sum","$uv"));


        }else{
            group.append("_id", "$_id")
                    .append("cid", new Document("$first","$cid"))
                    .append("count", new Document("$sum",1))
                    .append("title", new Document("$first","$title"))
                    .append("type", new Document("$first","$type"))
                    .append("createName", new Document("$first","$create_name"))
                    .append("author", new Document("$first","$author"))
                    .append("src", new Document("$first","$src"))
                    .append("tag", new Document("$first","$tag "))
                    .append("channel", new Document("$first","$channel"))
                    .append("countDate", new Document("$first","$count_date"))
                    .append("allPv", new Document("$sum","$pv"))
                    .append("allUv", new Document("$sum","$uv"));
        }
        Document documentMatch = new Document("$match", match);
        Document documentGroup = new Document("$group",group);
        List<Document> list = new ArrayList<Document>();
        list.add(documentMatch);
        list.add(documentGroup);
        //sort
        Document docSort = new Document();
        if(null != sortName &&  null != sortOrder){
            docSort = new Document("$sort",new Document(sortName,sortOrder));
            list.add(docSort);
        }


        AggregateIterable<Document> aggregate = mongoTemplate.getCollection("count_content_view").aggregate(list);

        return aggregate ;
    }

    /**
     * 获取文章排行
     * @param startDate
     * @param endDate
     * @return
     */
    public AggregateIterable<Document>  topCountContentView( Integer startDate, Integer endDate){

            //match
            List<Document> l = new ArrayList<Document>();
            l.add(new Document("count_date", new Document("$gte", startDate)));
            l.add(new Document("count_date", new Document("$lt", endDate)));

            Document match = new Document("$and", l);

            //group
            Document group =  new Document();
            group.append("_id", "$cid")
                    .append("pv", new Document("$sum","$pv"));

            Document documentMatch = new Document("$match", match);
            Document documentGroup = new Document("$group",group);
            List<Document> list = new ArrayList<Document>();
            list.add(documentMatch);
            list.add(documentGroup);
            //sort
            Document docSort = new Document("$sort",new Document("pv",-1));
            list.add(docSort);
            //limit
            Document docLimit =  new Document("$limit",10);
            list.add(docLimit);

            AggregateIterable<Document> aggregate = mongoTemplate.getCollection("count_content_view").aggregate(list);

            return aggregate ;

    }


    public Map<String,List<Document>> getViewContent(Integer startDate, Integer endDate) {
        Criteria matchFilter =  where("create_date").gt(startDate).lte(endDate);
        matchFilter.andOperator(matchFilter,where("cid").ne(0));

        TypedAggregation<ViewContentItem> agg = newAggregation(ViewContentItem.class,
                match(matchFilter),
                project()
                        .and("pv").applyCondition(
                            when(where("video_view").ne(1))
                                    .then(1)
                                    .otherwise(0))
                        .and("app_pv").applyCondition(
                            when(where("").andOperator(where("video_view").ne(1),where("locf").is("in")))
                                    .then(1)
                                    .otherwise(0))
                        .and("play_total").applyCondition(
                            when(where("video_view").is(1))
                                    .then(1)
                                    .otherwise(0))
                        .and("play_app").applyCondition(
                            when(where("").andOperator(where("video_view").is(1),where("locf").is("in")))
                                    .then(1)
                                    .otherwise(0))
                        .andExpression("1").as("cid")
                ,

                group("cid")
                        .sum("1").as("sum")
                        .sum("pv").as("pv")
                        .sum("app_pv").as("app_pv")
                        .sum("play_total").as("play_total")
                        .sum("play_app").as("play_app")
        );
        AggregationResults<Document> pvResult = mongoTemplate.aggregate(agg, "view_content", Document.class);
        List<Document> pvList = pvResult.getMappedResults();

        Criteria liveMatchFilter =  where("create_date").gt(startDate).lte(endDate);
        liveMatchFilter.andOperator(matchFilter,where("live_id").ne(0));

        TypedAggregation<ViewContentItem> liveAgg = newAggregation(ViewContentItem.class,
                match(liveMatchFilter),
                project()
                        .and("pv").applyCondition(
                            when(where("video_view").ne(1))
                                    .then(1)
                                    .otherwise(0))
                        .and("app_pv").applyCondition(
                            when(where("").andOperator(where("video_view").ne(1),where("locf").is("in")))
                                    .then(1)
                                    .otherwise(0))
                        .and("play_total").applyCondition(
                            when(where("video_view").is(1))
                                    .then(1)
                                    .otherwise(0))
                        .and("play_app").applyCondition(
                            when(where("").andOperator(where("video_view").is(1),where("locf").is("in")))
                                    .then(1)
                                    .otherwise(0))
                        .andExpression("1").as("live_id")
                ,

                group("live_id")
                        .sum("1").as("sum")
                        .sum("pv").as("pv")
                        .sum("app_pv").as("app_pv")
                        .sum("play_total").as("play_total")
                        .sum("play_app").as("play_app")
        );
        AggregationResults<Document> liveResult = mongoTemplate.aggregate(agg, "view_content", Document.class);
        List<Document> liveList = liveResult.getMappedResults();
        Map<String,List<Document>> resultMap = new HashMap<>(2);
        resultMap.put("content",pvList);
        resultMap.put("live",liveList);
        return resultMap;
    }
}

class ViewContentItem{
    String project;
}