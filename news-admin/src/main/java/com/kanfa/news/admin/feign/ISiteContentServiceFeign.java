package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.vo.site.SiteChannelEditResponseInfo;
import com.kanfa.news.admin.vo.site.SiteContentResponseInfo;
import com.kanfa.news.admin.vo.site.SiteInfo;
import com.kanfa.news.admin.vo.site.SiteResponseInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/4/3 13:05
 */
@FeignClient(name = "service-provider-news")
public interface ISiteContentServiceFeign {

    /**
     * 查询mogo站点信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/siteContent/getSiteInfo", method = RequestMethod.POST)
    ObjectRestResponse<List<SiteInfo>> getSiteInfo(@RequestBody SiteInfo entity);

    /**
     *  获取站点和频道信息
     * @return
     */
    @RequestMapping(value = "/siteContent/getSiteAndChannel", method = RequestMethod.GET)
    ObjectRestResponse<List<SiteResponseInfo>> getSiteAndChannel();

    /**
     * 查询文章列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/siteContent/getSiteContentPage", method = RequestMethod.GET)
    TableResultResponse<SiteContentResponseInfo> getSiteContentPage(@RequestParam Map<String, Object> params);

    /**
     * 初始化编辑设置 site_id,channel_id
     * @param params
     * @return
     */
    @RequestMapping(value = "/siteContent/getSiteChannelSetting", method = RequestMethod.GET)
    ObjectRestResponse<SiteChannelEditResponseInfo> getSiteChannelSetting(@RequestParam Map<String, Object> params);

    /**
     * 更新设置
     * @param siteChannelEditResponseInfo
     * @return
     */
    @RequestMapping(value = "/siteContent/updateSiteChannelSetting", method = RequestMethod.POST)
    ObjectRestResponse<SiteChannelEditResponseInfo> updateSiteChannelSetting(@RequestBody SiteChannelEditResponseInfo siteChannelEditResponseInfo);
}
