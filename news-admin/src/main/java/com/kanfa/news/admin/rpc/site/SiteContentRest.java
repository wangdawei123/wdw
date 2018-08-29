package com.kanfa.news.admin.rpc.site;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.feign.IChannelServiceFeign;
import com.kanfa.news.admin.feign.ISiteContentServiceFeign;
import com.kanfa.news.admin.vo.channel.ChannelInfo;
import com.kanfa.news.admin.vo.site.*;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/4/3 12:53
 */
@RestController
@RequestMapping("siteContent")
public class SiteContentRest {

    @Autowired
    private ISiteContentServiceFeign siteContentServiceFeign;
    @Autowired
    private IChannelServiceFeign channelServiceFeign;

    @RequestMapping(value = "/getSiteInfo", method = RequestMethod.POST)
    public ObjectRestResponse<List<SiteInfo>> getSiteInfo(@RequestBody SiteInfo siteInfo) {
        return siteContentServiceFeign.getSiteInfo(siteInfo);
    }

    /**
     * 获取站点和频道信息
     * @return
     */
    @RequestMapping(value = "/getSiteAndChannel", method = RequestMethod.GET)
    public ObjectRestResponse<List<SiteResponseInfo>> getSiteAndChannel() {
        return siteContentServiceFeign.getSiteAndChannel();
    }

    /**
     * 查询站点内容分页 page，limit，title
     * @param params
     * @return
     */
    @RequestMapping(value = "/getSiteContentPage", method = RequestMethod.GET)
    public TableResultResponse<SiteContentResponseInfo> getSiteContentPage(@RequestParam Map<String,Object> params) {
        return siteContentServiceFeign.getSiteContentPage(params);
    }

    /**
     * 初始化编辑设置 site_id,channel_id
     * @param params
     * @return
     */
    @RequestMapping(value = "/getSiteChannelSetting", method = RequestMethod.GET)
    public ObjectRestResponse<SiteChannelEditResponseInfo> getSiteChannelSetting(@RequestParam Map<String,Object> params) {
        return siteContentServiceFeign.getSiteChannelSetting(params);
    }

    /**
     * 更新设置
     * @param siteChannelEditResponseInfo
     * @return
     */
    @RequestMapping(value = "/updateSiteChannelSetting", method = RequestMethod.PUT)
    public ObjectRestResponse<SiteChannelEditResponseInfo> updateSiteChannelSetting(@RequestBody SiteChannelEditResponseInfo siteChannelEditResponseInfo) {
        return siteContentServiceFeign.updateSiteChannelSetting(siteChannelEditResponseInfo);
    }

    /**
     * 查询app频道
     * @return
     */
    @RequestMapping(value = "/getChannelList", method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> updateSiteChannelSetting() {
        ChannelInfo channel=new ChannelInfo();
        channel.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        channel.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
        channel.setChannelStatus(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        ObjectRestResponse<List<Channel>> channelList = channelServiceFeign.getChannelList(channel);
        List<Channel> data = channelList.getData();
        List<ChannelInfo> list=new ArrayList<>(data.size());
        if(data!=null && data.size()>0){
            for (Channel datum : data) {
                ChannelInfo channelInfo=new ChannelInfo();
                channelInfo.setId(datum.getId());
                channelInfo.setName(datum.getName());
                list.add(channelInfo);
            }
        }
        ObjectRestResponse<List<ChannelInfo>> objectRestResponse=new ObjectRestResponse<>();
        objectRestResponse.setData(list);
        return objectRestResponse;
    }

}
