package com.kanfa.news.common.mongoDBUtil.mapper;

import com.kanfa.news.common.mongoDBUtil.entity.ViewContent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class ViewContentDao extends  MongodbBaseDao<ViewContent>{
    /**
     * 通过条件去查询
     *
     * @return
     */
    public ViewContent findViewContent(Map params) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.where("create_date").is(1514529903);
        query.addCriteria(criteria);
        return super.findOne(query);
    }

    public List<ViewContent> listViewContent(Integer start,Integer end){
        Query query = new Query();
        query.addCriteria(Criteria.where("create_date").gte(start).lte(end));
        return super.find(query);
    }

}
