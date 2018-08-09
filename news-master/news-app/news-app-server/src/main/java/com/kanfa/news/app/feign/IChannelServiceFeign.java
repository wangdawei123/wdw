package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author wdw
 * APP  频道 模块
 *
 */
@FeignClient(name = "service-provider-news")
public interface IChannelServiceFeign {
    /**
     * 获取频道列表
     * @param cate
     * @return
     */
    @RequestMapping(value = "/channel/getList", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getList(@RequestParam("cate") String cate, @RequestParam(value = "devID", required = false) String devID,
                                                   @RequestParam(value = "uid", required = false) Integer uid);

    /**
     *  保存用户自定义频道
     * @param chan_ids
     * @param devID
     * @param hide_chan_ids
     * @return
     */
    @RequestMapping(value = "/channel/updateCustom", method = RequestMethod.POST)
    AppObjectResponse updateCustom(@RequestParam("chan_ids") String chan_ids,
                                   @RequestParam(value = "devID", required = false) String devID,
                                   @RequestParam("hide_chan_ids") String hide_chan_ids,
                                   @RequestParam(value = "uid", required = false) Integer uid);

    /**
     * 获取频道列表
     * @param cate
     * @return
     */
    @RequestMapping(value = "/channel/index/getChannelList", method = RequestMethod.POST)
    AppObjectResponse getChannelList(@RequestParam("cate") Integer cate);
}
