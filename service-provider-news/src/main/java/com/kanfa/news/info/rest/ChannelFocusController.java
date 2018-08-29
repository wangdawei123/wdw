package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.ChannelFocusBiz;
import com.kanfa.news.info.biz.ContentBiz;
import com.kanfa.news.info.entity.ChannelFocus;
import com.kanfa.news.info.entity.Content;
import com.kanfa.news.info.vo.admin.info.ChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.PcChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.VideoChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.VideoChannelFocusListInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/channelFocus")
public class ChannelFocusController extends BaseController<ChannelFocusBiz,ChannelFocus> {

    @Autowired
    private ChannelFocusBiz channelFocusBiz;
    @Autowired
    private ContentBiz contentBiz;

    @RequestMapping(value = "/getPage",method = RequestMethod.GET)
    public TableResultResponse<ChannelFocusInfo> getPage(@RequestParam Map<String, Object> params){
        TableResultResponse<ChannelFocusInfo> page=channelFocusBiz.getPage(params);
        return page;
    }

    //批量删除
    @RequestMapping(value = "/getFocusAll",method = RequestMethod.POST)
    public List<ChannelFocusInfo> getFocusAll(@RequestBody ChannelFocusInfo entity){
        List<ChannelFocusInfo> list=channelFocusBiz.getFocusAll(entity);
        return list;
    }



    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDelete(@RequestBody List<Integer> ids){
        Integer deleteCount=channelFocusBiz.batchDelete(ids);
        ObjectRestResponse<Integer> restResponse=new ObjectRestResponse<>();
        if(deleteCount<=0){
            restResponse.setRel(true);
            restResponse.setData(0);
            restResponse.setMessage("刪除失败");
        }
        restResponse.setData(deleteCount);
        return restResponse;
    }

    @RequestMapping(value = "/getVideoChannelFocusPage",method = RequestMethod.GET)
    public TableResultResponse<VideoChannelFocusInfo> getVideoChannelFocusPage(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return channelFocusBiz.getVideoChannelFocusPage(query) ;
    }

    @RequestMapping(value = "/getVideoFocusList",method =RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<VideoChannelFocusListInfo> getVideoFocusList(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam Integer channelId){
        //根据标题搜索
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return channelFocusBiz.getVideoFocusList(query,channelId);
    }


    //VR管理-->焦点图管理
    @RequestMapping(value = "/getVRChannelFocusPage",method = RequestMethod.GET)
    public TableResultResponse<VideoChannelFocusInfo> getVRChannelFocusPage(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return channelFocusBiz.getVRChannelFocusPage(query) ;
    }

    //PC管理-->焦点图管理
    @RequestMapping(value = "/getPcChannelFocusPage",method = RequestMethod.GET)
    public ObjectRestResponse<List<PcChannelFocusInfo>> getPcChannelFocusPage() {
        return channelFocusBiz.getPcChannelFocusPage() ;
    }

    /**
     * 查询焦点图
     * @param ids
     * @return
     */
    @RequestMapping(value = "/getChannelFocusByIdsAndChannelId",method = RequestMethod.POST)
    public List<ChannelFocus> getChannelFocusByIdsAndChannelId(@RequestParam("ids") List<Integer> ids){
        return channelFocusBiz.getChannelFocusByIdsAndChannelId(ids);
    }

    /**
     * 批量更新
     * @param channelFocusList
     * @return
     */
    @RequestMapping(value = "/batchUpdate",method = RequestMethod.POST)
    public Integer batchUpdate(@RequestBody List<ChannelFocus> channelFocusList){
        return channelFocusBiz.batchUpdate(channelFocusList);
    }

    /**
     * 新增
     * @param channelFocusInfo
     * @return
     */
    @RequestMapping(value = "/addChannelFocus",method = RequestMethod.POST)
    public ObjectRestResponse<ChannelFocus> addChannelFocus(@RequestBody ChannelFocusInfo channelFocusInfo) {
        ChannelFocus entity = new ChannelFocus();
        BeanUtils.copyProperties(channelFocusInfo,entity);
        if(NewsEnumsConsts.ChannelFocusOfJump.Live.key().equals(channelFocusInfo.getJump())){
            entity.setCid(channelFocusInfo.getLid());
            entity.setType(22);//新直播
        }else if(NewsEnumsConsts.ChannelFocusOfJump.Content.key().equals(channelFocusInfo.getJump())){
            Content content = contentBiz.selectById(channelFocusInfo.getCid());
            entity.setType(content.getContentType());
        }
        Integer orderNumber=channelFocusBiz.getMaxOrderNumber();
        if(orderNumber!=null){
            entity.setOrderNumber(orderNumber+1);
        }else {
            entity.setOrderNumber(1);
        }
        entity.setCreateTime(new Date());
        entity.setFullshot(0);
        entity.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.NOTPUBLISH.key());
        return super.add(entity);
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortChannelFocus",method = RequestMethod.POST)
    public ObjectRestResponse<Boolean> sortChannelFocus(@RequestBody Map<String, Object> params){
        List<Integer> newIds = (List<Integer>) params.get("newIds");
        List<Integer> oldIds = (List<Integer>) params.get("oldIds");
        if (newIds == null || newIds.size() <= 0 || oldIds == null || oldIds.size() <= 0) {
            return getObjectRestResponse("请传入新旧id集合");
        }
        int count = newIds.size();
        for (int i = 0; i < oldIds.size(); i++) {
            if (!(oldIds.get(i).equals(newIds.get(i)))) {
                count = i;
                break;
            }
        }
        List<ChannelFocus> oldList = channelFocusBiz.getChannelFocusByIdsAndChannelId(oldIds);
        //新的
        List<ChannelFocus> newList = channelFocusBiz.getChannelFocusByIdsAndChannelId(newIds);
        List<Integer> sort = new ArrayList<>(oldList.size());
        if (oldList != null && oldList.size() > 0) {
            for (ChannelFocus channel : oldList) {
                sort.add(channel.getOrderNumber());
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            ChannelFocus channelContent = newList.get(i);
            channelContent.setId(newList.get(i).getId());
            channelContent.setOrderNumber(sort.get(i));
        }
        List<ChannelFocus> channelFocusList = newList.subList(count, newList.size());
        ObjectRestResponse<Boolean> restResponse = new ObjectRestResponse<>();
        if (channelFocusList != null && channelFocusList.size() > 0) {
            Integer succNum = channelFocusBiz.batchUpdate(channelFocusList);
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