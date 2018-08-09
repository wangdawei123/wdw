package com.kanfa.news.app.mongoDB.mapper;

import com.kanfa.news.app.mongoDB.entity.CountVideoView;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/4 17:46
 */
@Component
public class CountVideoViewMongoDao extends AbstractMongodbBaseDao<CountVideoView> {

    public AggregateIterable<Document> sumView(String type,Integer id,Integer startDate,Integer endDate){
        //match
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("count_date", new Document("$gt", startDate)));
        l.add(new Document("count_date", new Document("$lte", endDate)));


        //group
        Document group =  new Document();
        //是否合计，如果合计则按cid分组统计
        if("live".equals(type)){
            l.add(new Document("live_id", id));
            group.append("_id", "$live_id")
                    .append("allPv", new Document("$sum","$pv"))
                    .append("allUv", new Document("$sum","$uv"));


        }else{
            l.add(new Document("vid", id));
            group.append("_id", "$vid")
                    .append("allPv", new Document("$sum","$pv"))
                    .append("allUv", new Document("$sum","$uv"));
        }
        Document match = new Document("$and", l);
        Document documentMatch = new Document("$match", match);
        Document documentGroup = new Document("$group",group);
        List<Document> list = new ArrayList<Document>();
        list.add(documentMatch);
        list.add(documentGroup);

        AggregateIterable<Document> aggregate = mongoTemplate.getCollection("count_content_view").aggregate(list);

        return aggregate ;
    }

}
