package com.kanfa.news.info.mongodb.mapper;


import com.kanfa.news.info.config.RequestConfiguration;
import com.kanfa.news.info.mongodb.entity.ViewContent;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Component
public class ViewContentDao extends AbstractMongodbBaseDao<ViewContent> {
    @Autowired
    protected HttpServletRequest request;

    @Resource
    private RequestConfiguration requestConfiguration;

    /**
     * 插入一条阅读数量的数据
     */
    public void insertViewContent(ViewContent v){
        v.setRequest_source(3);
        v.setVid(0);
        v.setIp(request.getRemoteAddr());
        v.setUser_agent(request.getHeader(requestConfiguration.getUserAgent()));
        v.setIs_proxy(1);
        v.setReferer(request.getHeader(requestConfiguration.getReferer()));
        //app内外访问
        if(request.getMethod().equals("post")){
            v.setLocf("in");
        }else{
            v.setLocf("out");
        }
        ////存当前时间的时间戳
        v.setCreate_date((int)System.currentTimeMillis());
        mongoTemplate.save(v);
    }


    /**
     *
     * @param groupName
     * @param collectionArray
     * @param start
     * @param end
     * @return
     */
    public GroupByResults<ViewContent> listViewContent(String groupName,Set collectionArray, Integer start, Integer end){
        GroupBy groupBy = GroupBy.key("locf",groupName).initialDocument("{appPv:0,allPv:0}").reduceFunction("function(curr, result){  result.allPv += 1; if(result.locf == 'in') { result.appPv += 1;}}");

        Criteria criteria = new Criteria();
        criteria = Criteria.where(groupName).in(collectionArray).and("create_date").gte(start).lte(end);
        GroupByResults<ViewContent> rst = mongoTemplate.group(criteria,"view_content",groupBy, ViewContent.class);
        return rst;
    }

    public GroupByResults<ViewContent> listViewContentVideo(String groupName,Set collectionArray, Integer start, Integer end){
        GroupBy groupBy = GroupBy.key("locf","uid","ip",groupName).initialDocument("{appPv:0,allPv:0}").reduceFunction("function(curr, result){  result.allPv += 1; if(result.locf == 'in') { result.appPv += 1;}}");
        Criteria criteria = new Criteria();
        if(null != collectionArray && collectionArray.size() > 0){
             criteria = Criteria.where(groupName).in(collectionArray).and("create_date").gte(start).lte(end);
        }else{
            criteria = Criteria.where("create_date").gte(start).lte(end);
        }


        GroupByResults<ViewContent> rst = mongoTemplate.group(criteria,"view_content",groupBy, ViewContent.class);
        return rst;
    }

    /**
     * 从mongo获取7\30\60天 pv
     * @param start
     * @param end
     * @return
     */
    public GroupByResults<ViewContent> getPv(Integer start, Integer end){
        GroupBy groupBy = GroupBy.key("date").initialDocument("{pv:0}").reduceFunction("function(curr, result){  result.pv += 1;}");
        Criteria criteria = new Criteria();
        criteria = Criteria.where("cid").ne(0).and("type").in(2,3).and("create_date").gte(start).lte(end).and("video_view").ne(1);

        GroupByResults<ViewContent> rst = mongoTemplate.group(criteria,"view_content",groupBy, ViewContent.class);
        return rst;
    }

    /**
     * 从mongo获取7\30\60天 vv
     * @param start
     * @param end
     * @return
     */
    public GroupByResults<ViewContent> getVv(Integer start, Integer end){
        GroupBy groupBy = GroupBy.key("date").initialDocument("{vv:0}").reduceFunction("function(curr, result){  result.vv += 1;}");
        Criteria criteria =Criteria.where("cid").ne(0).and("type").is(4).and("create_date").gte(start).lte(end).and("video_view").is(1);
        GroupByResults<ViewContent> rst = mongoTemplate.group(criteria,"view_content",groupBy, ViewContent.class);
        return rst;
    }

    /**
     * 获取视频排行
     * @param startDate
     * @param endDate
     * @return
     */
    public AggregateIterable<Document> topViewContentVideo(Integer startDate, Integer endDate){

        //match
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("create_date", new Document("$gte", startDate)));
        l.add(new Document("create_date", new Document("$lt", endDate)));
        l.add(new Document("type", new Document("$eq", 4)));
        l.add(new Document("vid", new Document("$gt", 0)));
        l.add(new Document("video_view", new Document("$eq", 1)));

        Document match = new Document("$and", l);

        //group
        Document group =  new Document();
        group.append("_id", "$cid")
                .append("pv", new Document("$sum","$video_view"));

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

        AggregateIterable<Document> aggregate = mongoTemplate.getCollection("view_content").aggregate(list);

        return aggregate ;

    }

    /**
     * 获取直播排行榜
     * @param startDate
     * @param endDate
     * @return
     */
    public AggregateIterable<Document> topViewContentLive(int startDate, int endDate) {
        //match
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("create_date", new Document("$gte", startDate)));
        l.add(new Document("create_date", new Document("$lt", endDate)));
        l.add(new Document("live_id", new Document("$gt", 0)));
        l.add(new Document("video_view", new Document("$eq", 1)));

        Document match = new Document("$and", l);

        //group
        Document group =  new Document();
        group.append("_id", "$live_id")
                .append("pv", new Document("$sum","$video_view"));

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

        AggregateIterable<Document> aggregate = mongoTemplate.getCollection("view_content").aggregate(list);

        return aggregate ;

    }

    public Integer getPvCount(Integer startDate, Integer endDate) {
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("create_date", new Document("$gt", startDate)));
        l.add(new Document("create_date", new Document("$lte", endDate)));
        l.add(new Document("cid", new Document("$ne", 0)));
        l.add(new Document("video_view", new Document("$ne", 1)));
        l.add(new Document("type", new Document("$in",new Integer[]{2,3})));

        Document match = new Document("$and", l);
        Long count = mongoTemplate.getCollection("view_content").count(match);
        return count.intValue();
    }

    public Integer getPlayCount(Integer startDate, Integer endDate) {
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("create_date", new Document("$gt", startDate)));
        l.add(new Document("create_date", new Document("$lte", endDate)));
        l.add(new Document("cid", new Document("$ne", 0)));
        l.add(new Document("video_view", new Document("$eq", 1)));
        l.add(new Document("type", new Document("$eq",4)));

        Document match = new Document("$and", l);
        Long count = mongoTemplate.getCollection("view_content").count(match);
        return count.intValue();
    }

    /**
     * 添加浏览量
     * @param viewContent
     */
    public void insertViewContentContent(ViewContent viewContent) {
        mongoTemplate.save(viewContent);
    }


    /*
     * 获取某篇文章一段时间的PV 和UV数据，总的和app内的
     * cidOrLiveId 文章ID
     *  cidOrLiveIdValue 读取cid还是其他id
     *  startDate 第一次审核时间或创建时间
     *  endDate 统计截止日期
     */
    public List<Document> getCidPvUvCount(String cidOrLiveId, Integer cidOrLiveIdValue, Integer startDate, Integer endDate) {
        List<Document> list = null;
        if("cid".equals(cidOrLiveId)){
            Criteria matchFilter =  where("create_date").gt(startDate).lte(endDate);
            matchFilter.andOperator(matchFilter,where(cidOrLiveId).is(cidOrLiveIdValue))
                        .andOperator(matchFilter,where("video_view").ne(1));

            TypedAggregation<ViewContentItem> agg = newAggregation(ViewContentItem.class,
                    match(matchFilter),

                    group(cidOrLiveId)
                            .sum(new AggregationExpression() {
                                @Override
                                public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                    return Document.parse("{\"$cond\":{\"if\":{\"$ne\":[\"$locf\".\"out\"]},then:1,else:1}}");
                                }
                            }).as("all_pv")
                            .addToSet(new Document(Document.parse(" { \"$cond\":[\"$uid\",\"$uid\",\"$ip\"]}"))).as("all_uv")
                    ,

                    project()
                            .and("all_pv").as("allPv")
                            .and("all_uv").as("allUv")
                            .and("_id").as("cid")
                            .andExpression("0").as("_id")
            );
            AggregationResults<Document> pvResult = mongoTemplate.aggregate(agg, "view_content", Document.class);
            list = pvResult.getMappedResults();
        }else {
            Criteria liveMatchFilter = where("create_date").gt(startDate).lte(endDate);
            liveMatchFilter.andOperator(liveMatchFilter,where(cidOrLiveId).is(cidOrLiveIdValue))
                            .andOperator(liveMatchFilter, where("locf").is("in"));

            TypedAggregation<ViewContentItem> appPvAgg = newAggregation(ViewContentItem.class,
                    match(liveMatchFilter),

                    group(cidOrLiveId)
                            .sum("1").as("all_pv")
                            .addToSet(new Document(Document.parse(" { \"$cond\":[\"$uid\",\"$uid\",\"$ip\"]}"))).as("all_uv")
                    ,

                    project()
                            .and("app_pv").as("appPv")
                            .and("app_uv").as("appUv")
                            .and("_id").as("cid")
                            .andExpression("0").as("_id")
            );
            AggregationResults<Document> liveResult = mongoTemplate.aggregate(appPvAgg, "view_content", Document.class);
            list = liveResult.getMappedResults();
        }
        return list;
    }
}
