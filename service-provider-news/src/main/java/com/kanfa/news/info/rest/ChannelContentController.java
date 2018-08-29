package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ChannelContentBiz;
import com.kanfa.news.info.entity.ChannelContent;
import com.kanfa.news.info.vo.admin.info.ChannelContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("channelContent")
public class ChannelContentController extends BaseController<ChannelContentBiz,ChannelContent> {

    @Autowired
    private ChannelContentBiz channelContentBiz;

    @RequestMapping(value = "/updateChannelConent",method = RequestMethod.POST)
    public ObjectRestResponse<Integer> updateChannelConent(@RequestBody ChannelContent channelContent){
        ObjectRestResponse<Integer> restResponse = new ObjectRestResponse<>();
        Integer update=channelContentBiz.updateChannelConent(channelContent);
        restResponse.setData(update);
        return restResponse;
    }

    /**
     * 搜索对应内容id的绑定数据
     * @param ids
     * @param channelId
     * @return
     */
    @RequestMapping(value = "/getChannelContent",method = RequestMethod.GET)
   public List<ChannelContentInfo> getChannelContent(@RequestParam("oldIds") List<Integer> ids,@RequestParam("channelId") Integer channelId){
        return channelContentBiz.getChannelContent(ids,channelId);
    }

    /**
     * 批量跟新内容排序
     * @param channelContentList
     * @return
     */
    @RequestMapping(value = "/batchUpdate",method = RequestMethod.POST)
    public Integer batchUpdate(@RequestBody List<ChannelContent> channelContentList){
        return channelContentBiz.batchUpdate(channelContentList);
    }

    @GetMapping("/getChannelContentForEs")
    public List<Map<String,Object>> selectByContentIdFromType(@RequestParam("contentId")Integer contentId, @RequestParam("fromType")Integer fromType){
        return channelContentBiz.selectByContentIdFromType(contentId,fromType);
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortContent",method = RequestMethod.POST)
    public ObjectRestResponse<Boolean> sortContent(@RequestBody Map<String, Object> params){
        List<Integer> newIds = (List<Integer>) params.get("newIds");
        List<Integer> oldIds = (List<Integer>) params.get("oldIds");
        Integer channelId = (Integer) params.get("channelId");
        if (newIds == null || newIds.size() <= 0 || oldIds == null || oldIds.size() <= 0 || channelId == null) {
            return getObjectRestResponse("请传入新旧id集合和频道id");
        }
        int count = newIds.size();
        for (int i = 0; i < oldIds.size(); i++) {
            if (!(oldIds.get(i).equals(newIds.get(i)))) {
                count = i;
                break;
            }
        }
        List<ChannelContentInfo> oldList = channelContentBiz.getChannelContent(oldIds, channelId);
        //新的
        List<ChannelContentInfo> newList = channelContentBiz.getChannelContent(newIds, channelId);
        List<Integer> sort = new ArrayList<>(oldList.size());
        if (oldList != null && oldList.size() > 0) {
            for (ChannelContentInfo channel : oldList) {
                sort.add(channel.getOrderNumber());
            }
        }
        List<ChannelContent> newChannelList = new ArrayList<>(oldList.size());
        for (int i = 0; i < newList.size(); i++) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setId(newList.get(i).getId());
            channelContent.setOrderNumber(sort.get(i));
            newChannelList.add(channelContent);
        }
        List<ChannelContent> channelContentList = newChannelList.subList(count, newChannelList.size());
        ObjectRestResponse<Boolean> restResponse = new ObjectRestResponse<>();
        if (channelContentList != null && channelContentList.size() > 0) {
            int succNum = channelContentBiz.batchUpdate(channelContentList);
            if (succNum <= 0) {
                restResponse.setData(true);
                restResponse.setMessage("排序失败");
            }
        }
        restResponse.setRel(false);
        return restResponse;
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}