package com.kanfa.news.info.mongodb.mapper;


import com.kanfa.news.info.mongodb.entity.ViewContent;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Component
public class CountChannelContentDao extends AbstractMongodbBaseDao<ViewContent> {

    /**
     * 获取总条数，总pv,总uv,内容数据
     * @param categoryId
     * @param channelId
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public AggregateIterable<Document> getCountChannelContent(Integer categoryId,Integer channelId,Integer startDate,Integer endDate){
        //match
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("count_date", new Document("$gte", startDate)));
        l.add(new Document("count_date", new Document("$lte", endDate)));
        if(null != categoryId){
            l.add(new Document("cate", new Document("$eq", categoryId)));
        }
        if(null != channelId){
            l.add(new Document("channel_id", new Document("$eq", channelId)));
        }
        Document match = new Document("$and", l);

        //group
        Document group =  new Document();
            group.append("_id", "$_id")
                    .append("countDate", new Document("$first","$count_date"))
                    .append("contentCount", new Document("$first","$content_count"))
                    .append("channelId", new Document("$first","$channel_id"))
                    .append("channelName", new Document("$first","$channel_name"))
                    .append("cate", new Document("$first","cate"));

        Document documentMatch = new Document("$match", match);
        Document documentGroup = new Document("$group",group);
        List<Document> list = new ArrayList<Document>();
        list.add(documentMatch);
        list.add(documentGroup);
        AggregateIterable<Document> aggregate = mongoTemplate.getCollection("count_channel_content").aggregate(list);

        return aggregate ;
    }


}
