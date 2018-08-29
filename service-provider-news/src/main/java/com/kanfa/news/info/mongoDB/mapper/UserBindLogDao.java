package com.kanfa.news.info.mongodb.mapper;

import com.kanfa.news.info.mongodb.entity.UserBindLogInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jiqc
 * @date 2018/6/5 16:01
 */
@Component
public class UserBindLogDao extends AbstractMongodbBaseDao<UserBindLogInfo> {

    /**
     * 查询用户日志
     * @param id
     * @return
     */
    public List<UserBindLogInfo> getUserLogByUid(Integer id) {
        return mongoTemplate.find(new Query(new Criteria().where("uid").is(id)), UserBindLogInfo.class);
    }
}
