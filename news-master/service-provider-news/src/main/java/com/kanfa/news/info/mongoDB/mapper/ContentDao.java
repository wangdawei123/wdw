package com.kanfa.news.info.mongodb.mapper;

import com.kanfa.news.info.mongodb.entity.ContentArticleLog;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import com.kanfa.news.info.vo.admin.info.ContentLocationInfo;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/28 18:50
 */
@Component
public class ContentDao extends AbstractMongodbBaseDao<ContentLocationInfo> {


    /**
     * 查询对应内容的地理位置
     * @param contentLocationInfo
     * @return
     */
    public ContentLocationInfo findContentLocation(ContentLocationInfo contentLocationInfo) {
        return mongoTemplate.findOne(new Query(Criteria.where("cid").is(contentLocationInfo.getCid())), ContentLocationInfo.class);
    }

    /**
     * 删除
     * @param contentLocationInfo
     * @return
     */
    public Long removeMogoLocation(ContentLocationInfo contentLocationInfo) {
        DeleteResult cityid = mongoTemplate.remove(new Query(Criteria.where("cid").is(contentLocationInfo.getCid())), ContentLocationInfo.class);
        return cityid.getDeletedCount();
    }

    /**
     * 更新地理位置信息
     * @param entity
     */
    public Long updateLocation(ContentInfo entity) {
        UpdateResult upsert = mongoTemplate.upsert(new Query(Criteria.where("cid").is(entity.getId())), new Update().set("location", entity.getLocationList()), ContentLocationInfo.class);
        return upsert.getMatchedCount();
    }

    /**
     * 添加日志mongo
     * @param entity
     * @return
     */
    public void addContentAritcleLog(ContentArticleLog entity) {
        mongoTemplate.save(entity);
    }

    /**
     * 查询日志信息
     * @param contentArticleLog
     * @return
     */
    public List<ContentArticleLog> getContentLog(ContentArticleLog contentArticleLog) {
        Query query = new Query();
        query.addCriteria(new Criteria().where("cid").is(contentArticleLog.getCid()));
        query.with(new Sort(Sort.Direction.DESC, "create_time"));
        return mongoTemplate.find(query, ContentArticleLog.class);
    }

    /**
     * 修改内容
     * @param contentInfo
     * @param stat
     * @return
     */
    public UpdateResult updateSiteContentDetail(ContentInfo contentInfo, Update stat,Integer channelId) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("cid").is(contentInfo.getId()),Criteria.where("channel").elemMatch(Criteria.where("id").is(channelId))));
        return mongoTemplate.updateMulti(query,stat,ContentLocationInfo.class);
    }
}
