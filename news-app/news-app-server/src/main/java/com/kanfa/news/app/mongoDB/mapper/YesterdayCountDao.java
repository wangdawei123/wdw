package com.kanfa.news.app.mongoDB.mapper;

import com.kanfa.news.app.mongoDB.entity.ViewContent;
import com.kanfa.news.app.mongoDB.util.StringDateToInt;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/26 14:46
 */
@Component
public class YesterdayCountDao extends AbstractMongodbBaseDao<ViewContent> {


    public List<Document> getYesterdayCount(int page, int limit){
        Date today = new Date();
        Long yesterday = today.getTime() - 86400000L;
        String start = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 00:00:00");
        String end = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 23:59:59");
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss"); //1525276800;
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss"); //1525363199;
        Document uvCond = Document.parse(" { \"$cond\":[\"$uid\",\"$uid\",\"$ip\"]}");
        Criteria matchFilter =  where("create_date").gte(startDate).lt(endDate).and("cid").gte(1);

        TypedAggregation<TodayCountItem> agg = newAggregation(TodayCountItem.class,
                match(matchFilter),

                group("cid")
                        .count().as("pv")
                        .sum("isProxy").as("proxyCount")
                        .addToSet(new Document(uvCond)).as("uv")
                        .sum(new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\":{\"if\":{\"$eq\":[\"$locf\",\"in\"]},then:1,else:0}}");
                            }
                        }).as("appIn")
                        .sum(new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\": { if: { \"$eq\": [ \"$request_source\", 1 ] }, then: 1, else: 0 }}");
                            }
                        }).as("appCount")
                        .sum(new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\": { if: { \"$eq\": [ \"$request_source\", 2 ] }, then: 1, else: 0 }}");
                            }
                        }).as("pcCount")
                        .sum(new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\": { if: { \"$eq\": [ \"$request_source\", 3 ] }, then: 1, else: 0 }}");
                            }
                        }).as("mCount")
                ,

                project()
                        .andExpression("1").as("pv")
                        .andExpression("size(uv)").as("uv")
                        .and("proxyCount").as("proxyCount")
                        .and("appIn").as("appIn")
                        .and("appCount").as("appCount")
                        .and("pcCount").as("pcCount")
                        .and("_id").as("cid")
                        .andExpression("0").as("_id")
                ,
                sort(ASC, "cid"),
                skip(page>1?(page-1)*limit:0L),
                limit(limit)

        );

        AggregationResults<Document> result = mongoTemplate.aggregate(agg, "view_content", Document.class);
        List<Document> list = result.getMappedResults();
        return list;

    }

    /**
     * 获取昨日视频直播统计
     * @param type
     * @return
     */
    public List<Document> getYesterdayVideoLiveCount(String type){
        Date today = new Date();
        Long yesterday = today.getTime() - 86400000L;
        String start = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 00:00:00");
        String end = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 23:59:59");
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss"); //1525276800;
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss"); //1525363199;

        String searchName = "";
        if("live".equals(type)){
            //直播
            searchName = "liveId";
        }else{
            //视频
            searchName = "vid";
        }

        Document uvCond = Document.parse(" { \"$cond\":[\"$uid\",\"$uid\",\"$ip\"]}");
        Criteria matchFilter =  where("create_date").gte(startDate).lt(endDate).and("searchName").gt(0);

        TypedAggregation<TodayCountItem> agg = newAggregation(TodayCountItem.class,
                match(matchFilter),

                group(searchName)
                        .sum(new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\":{\"if\":{\"$ne\":[\"$video_view\",1]},then:1,else:0}}");
                            }
                        }).as("pv")
                        .addToSet(new Document(uvCond)).as("uv")
                        .sum( new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\":{\"$and\":[ {\"$ne\":[\"$video_view\",1]},{\"$ne\":[\"$locf\",\"out\"]} ],then:1,else:0}}");
                            }
                        }).as("appPvIn")
                        .sum( new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\":{\"$and\":[ {\"$ne\":[\"$video_view\",1]},{\"$eq\":[\"$locf\",\"out\"]} ],then:1,else:0}}");
                            }
                        }).as("appPvOut")
                        .sum( new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\":{\"$and\":[ {\"$eq\":[\"$video_view\",1]},{\"$ne\":[\"$locf\",\"out\"]} ],then:1,else:0}}");
                            }
                        }).as("appVideoIn")
                        .sum( new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return Document.parse("{\"$cond\":{\"$and\":[ {\"$eq\":[\"$video_view\",1]},{\"$eq\":[\"$locf\",\"out\"]} ],then:1,else:0}}");
                            }
                        }).as("appVideoOut")
                ,

                project()
                        .andExpression("1").as("appPvIn")
                        .andExpression("1").as("appPvOut")
                        .andExpression("1").as("appVideoIn")
                        .andExpression("1").as("appVideoOut")
                        .andExpression("1").as("pv")
                        .and("_id").as(searchName)
                        .andExpression("0").as("_id")
        );

        AggregationResults<Document> result = mongoTemplate.aggregate(agg, "view_content", Document.class);
        List<Document> list = result.getMappedResults();
        return list;

    }
}

class TodayCountItem {
    @Field("cid")
    Integer cid;
    @Field("pv")
    Integer pv ;
    @Field("proxyCount")
    Integer isProxy;
    Double appIn;
    Double appCount;
    Double pcCount;
    Double mCount;
    @Field("uv")
    List uv;
}
