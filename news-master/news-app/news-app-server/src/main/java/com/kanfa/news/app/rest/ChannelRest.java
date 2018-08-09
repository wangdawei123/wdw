package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.ChannelBiz;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wdw
 */
@RestController
@RequestMapping("channel")
public class ChannelRest extends BaseRest {
    @Autowired
    private ChannelBiz channelBiz;

    /**
     * 获取频道列表
     * @param cate
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String,Object>> getList(@RequestParam("cate") String cate, @RequestParam(value = "devID" ,required = false) String devID,
                                                          @RequestParam(value = "uid" ,required = false,defaultValue = "0") Integer uid) {
        return channelBiz.getList(cate,devID,uid);
    }

    /**
     *  保存用户自定义频道
     * @param chan_ids
     * @param devID
     * @param hide_chan_ids
     * @return
     */
    @RequestMapping(value = "/updateCustom", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String,Object>> updateCustom(@RequestParam("chan_ids") String chan_ids,
                                                         @RequestParam(value = "devID",required = false) String devID,
                                                         @RequestParam(value = "uid",required = false) Integer uid,
                                                         @RequestParam("hide_chan_ids") String hide_chan_ids) {
        return channelBiz.updateCustom(chan_ids,devID,hide_chan_ids,uid);
    }

    /**
     * 获取频道列表
     * @param cate
     * @return
     */
    @RequestMapping(value = "/index/getChannelList", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String,Object>> getChannelList(@RequestParam("cate") Integer cate) {
        //从headers获取参数
        String app_version = request.getHeader("VERSION");
        String pushId = request.getHeader("PUSHID");
        String app_platform = request.getHeader("PLATFORM");
        return channelBiz.getChannelList(cate,app_version,pushId,app_platform);
    }

}
