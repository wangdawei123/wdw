package com.kanfa.news.info.biz;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.mongodb.mapper.SiteChannelDao;
import com.kanfa.news.info.mongodb.mapper.SiteContentDao;
import com.kanfa.news.info.vo.site.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/4/3 13:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SiteContentBiz {

    @Autowired
    private SiteContentDao siteContentDao;
    @Autowired
    private SiteChannelDao siteChannelDao;


    /**
     * 查询站点信息
     * @param entity
     * @return
     */
    public List<SiteInfo> getSiteInfo(SiteInfo entity) {
        return siteContentDao.getSiteInfo(entity);
    }

    /**
     * 获取站点和频道信息
     * @return
     */
    public List<SiteResponseInfo> getSiteAndChannel() {
        List<SiteInfo> siteInfos = siteContentDao.findAll();
        List<SiteResponseInfo> channelList = new ArrayList<>(siteInfos.size());
        if(siteInfos!=null && siteInfos.size()>0){
            for (SiteInfo siteInfo : siteInfos) {
                SiteResponseInfo siteResponseInfo=new SiteResponseInfo();
                BeanUtils.copyProperties(siteInfo,siteResponseInfo);
                SiteChannelResponseInfo siteChannelResponseInfo=new SiteChannelResponseInfo();
                siteChannelResponseInfo.setSite_id(siteInfo.getId());
                siteResponseInfo.setChannelList(siteChannelDao.findChannelInfo(siteChannelResponseInfo));
                channelList.add(siteResponseInfo);
            }
        }
        return channelList;
    }

    /**
     * 查询文章列表 site_id,channel_id
     * @param params
     * @return
     */
    public TableResultResponse<SiteContentInfo> getSiteContentPage(Map<String, Object> params) {
        return siteContentDao.getSiteContentPage(params);
    }

    /**
     * 查询站点频道设置信息
     * @param params
     * @return
     */
    public SiteChannelEditResponseInfo getSiteChannelSetting(Map<String, Object> params) {
        SiteChannelInfo siteChannelInfo = siteChannelDao.get(params.get("channel_id").toString());
        SiteChannelEditResponseInfo siteChannelEditResponseInfo=new SiteChannelEditResponseInfo();
        BeanUtils.copyProperties(siteChannelInfo,siteChannelEditResponseInfo);
        return siteChannelEditResponseInfo;
    }

    /**
     * 更新设置
     * @param siteChannelEditResponseInfo
     * @return
     */
    public SiteChannelEditResponseInfo updateSiteChannelSetting(SiteChannelEditResponseInfo siteChannelEditResponseInfo) {
        SiteChannelInfo siteChannel=new SiteChannelInfo();
        BeanUtils.copyProperties(siteChannelEditResponseInfo,siteChannel);
        siteChannel.set_modified(new Date());
        siteChannelDao.updateById(siteChannelEditResponseInfo.getId(),siteChannel);
        return siteChannelEditResponseInfo;
    }
}
