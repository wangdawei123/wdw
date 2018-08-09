package com.kanfa.news.info.mongodb.mapper;


import com.kanfa.news.info.vo.site.SiteChannelInfo;
import com.kanfa.news.info.vo.site.SiteChannelResponseInfo;
import com.kanfa.news.info.vo.site.SiteInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiqc
 * @email jiqingchao@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Component
public class SiteChannelDao extends AbstractMongodbBaseDao<SiteChannelInfo> {

    public List<SiteChannelResponseInfo> findChannelInfo(SiteChannelResponseInfo siteChannelResponseInfo) {
        return mongoTemplate.find(new Query(new Criteria().where("site_id").is(new ObjectId(siteChannelResponseInfo.getSite_id()))),SiteChannelResponseInfo.class);
    }
}
