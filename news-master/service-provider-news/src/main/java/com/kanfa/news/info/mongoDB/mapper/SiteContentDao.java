package com.kanfa.news.info.mongodb.mapper;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.mongodb.entity.ViewContent;
import com.kanfa.news.info.vo.site.SiteContentInfo;
import com.kanfa.news.info.vo.site.SiteInfo;
import com.mongodb.client.AggregateIterable;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author jiqc
 * @email jiqingchao@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Component
public class SiteContentDao extends AbstractMongodbBaseDao<SiteContentInfo> {

    /**
     * 查询站点信息
     * @param entity
     * @return
     */
    public List<SiteInfo> getSiteInfo(SiteInfo entity) {
        List<SiteInfo> siteInfos = mongoTemplate.findAll(SiteInfo.class);
        return siteInfos;
    }

    /**
     * 查询所有
     * @return
     */
    public List<SiteInfo> findAll() {
        List<SiteInfo> siteInfos = mongoTemplate.findAll(SiteInfo.class);
        return siteInfos;
    }

    /**
     * 内容分页
     * @param params
     */
    public TableResultResponse<SiteContentInfo> getSiteContentPage(Map<String, Object> params) {
        Query query = new Query();
        if(params.get("title")!=null && StringUtils.isNotEmpty(params.get("title").toString())){
            //模糊匹配
            Pattern pattern = Pattern.compile("^.*"+params.get("title").toString()+".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(new Criteria().where("site_id").is(new ObjectId(params.get("site_id").toString())).and("channel_id").is(new ObjectId(params.get("channel_id").toString())).and("title").regex(pattern));
        }else {
            query.addCriteria(new Criteria().where("site_id").is(new ObjectId(params.get("site_id").toString())).and("channel_id").is(new ObjectId(params.get("channel_id").toString())));
        }
        long count = mongoTemplate.count(query, SiteContentInfo.class);
        Integer page = Integer.valueOf(params.get("page").toString());
        Integer limit = Integer.valueOf(params.get("limit").toString());
        query.skip((page-1)*limit);// skip相当于从那条记录开始
        query.limit(limit);// 从skip开始,取多少条记录
        query.with(new Sort(Sort.Direction.DESC, "_created"));
        Page<SiteContentInfo> result = PageHelper.startPage(page, limit);
        List<SiteContentInfo> siteContentInfos = mongoTemplate.find(query, SiteContentInfo.class);
        return new TableResultResponse<>(count,siteContentInfos);
    }
}
