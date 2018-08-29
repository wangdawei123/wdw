package com.kanfa.news.app.mongoDB.mapper;


import com.kanfa.news.app.mongoDB.entity.CountCategoryContent;
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
public class CountCateDao extends AbstractMongodbBaseDao<CountCategoryContent> {

    /**
     * 每日类目统计内容数据
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public AggregateIterable<Document> getCountChannelContent(Integer typeId,Integer startDate,Integer endDate){
        //match
        List<Document> l = new ArrayList<Document>();
        l.add(new Document("count_date", new Document("$gte", startDate)));
        l.add(new Document("count_date", new Document("$lte", endDate)));
        if(null != typeId){
            l.add(new Document("type", new Document("$eq", typeId)));
        }

        Document match = new Document("$and", l);

        //group
        Document group =  new Document();
            group.append("_id", "$_id")
                    .append("countDate", new Document("$first","$count_date"))
                    .append("contentCount", new Document("$first","$content_count"))
                    .append("type", new Document("$first","$type"));
        //sort
        Document docSort = new Document("$sort",new Document("count_date",1));
        Document documentMatch = new Document("$match", match);
        Document documentGroup = new Document("$group",group);
        List<Document> list = new ArrayList<Document>();
        list.add(documentMatch);
        list.add(documentGroup);
        list.add(docSort);
        AggregateIterable<Document> aggregate = mongoTemplate.getCollection("count_category_content").aggregate(list);
        return aggregate ;
    }


}
