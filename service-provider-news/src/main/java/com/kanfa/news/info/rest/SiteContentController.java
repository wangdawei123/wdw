package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.biz.SiteContentBiz;
import com.kanfa.news.info.vo.site.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/4/3 13:11
 */
@RestController
@RequestMapping("siteContent")
public class SiteContentController {

    @Autowired
    private SiteContentBiz siteContentBiz;

    /**
     * 查询mogo站点信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getSiteInfo", method = RequestMethod.POST)
    public ObjectRestResponse<List<SiteInfo>> getSiteInfo(@RequestBody SiteInfo entity){
        ObjectRestResponse<List<SiteInfo>> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(siteContentBiz.getSiteInfo(entity));
        return objectRestResponse;
    }

    /**
     *  获取站点和频道信息
     * @return
     */
    @RequestMapping(value = "/getSiteAndChannel", method = RequestMethod.GET)
    public ObjectRestResponse<List<SiteResponseInfo>> getSiteAndChannel(){
        ObjectRestResponse<List<SiteResponseInfo>> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(siteContentBiz.getSiteAndChannel());
        return objectRestResponse;
    }

    /**
     * 查询文章列表 site_id,channel_id
     * @param params
     * @return
     */
    @RequestMapping(value = "/getSiteContentPage", method = RequestMethod.GET)
    public TableResultResponse<SiteContentResponseInfo> getSiteContentPage(@RequestParam Map<String, Object> params){
        TableResultResponse<SiteContentInfo> siteContentPage = siteContentBiz.getSiteContentPage(params);
        List<SiteContentInfo> rows = siteContentPage.getData().getRows();
        List<SiteContentResponseInfo> responseInfos=new ArrayList<>(rows.size());
        for (SiteContentInfo row : rows) {
            SiteContentResponseInfo siteContentResponseInfo=new SiteContentResponseInfo();
            BeanUtils.copyProperties(row,siteContentResponseInfo);
            responseInfos.add(siteContentResponseInfo);
        }
        return new TableResultResponse<>(siteContentPage.getData().getTotal(),responseInfos);
    }

    /**
     * 初始化编辑设置 site_id,channel_id
     * @param params
     * @return
     */
    @RequestMapping(value = "/getSiteChannelSetting", method = RequestMethod.GET)
    public ObjectRestResponse<SiteChannelEditResponseInfo> getSiteChannelSetting(@RequestParam Map<String, Object> params){
        SiteChannelEditResponseInfo siteChannelEditResponseInfo=siteContentBiz.getSiteChannelSetting(params);
        ObjectRestResponse<SiteChannelEditResponseInfo> objectRestResponse=new ObjectRestResponse<>();
        objectRestResponse.setData(siteChannelEditResponseInfo);
        return objectRestResponse;
    }

    /**
     * 更新设置
     * @param siteChannelEditResponseInfo
     * @return
     */
    @RequestMapping(value = "/updateSiteChannelSetting", method = RequestMethod.POST)
    public ObjectRestResponse<SiteChannelEditResponseInfo> updateSiteChannelSetting(@RequestBody SiteChannelEditResponseInfo siteChannelEditResponseInfo){
        SiteChannelEditResponseInfo editResponseInfo=siteContentBiz.updateSiteChannelSetting(siteChannelEditResponseInfo);
        ObjectRestResponse<SiteChannelEditResponseInfo> objectRestResponse=new ObjectRestResponse<>();
        objectRestResponse.setData(editResponseInfo);
        return objectRestResponse;
    }
}
