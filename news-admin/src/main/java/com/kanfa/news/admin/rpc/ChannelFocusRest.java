package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.entity.ChannelFocus;
import com.kanfa.news.admin.entity.Content;
import com.kanfa.news.admin.entity.Live;
import com.kanfa.news.admin.feign.IChannelFocusServiceFeign;
import com.kanfa.news.admin.feign.IChannelServiceFeign;
import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.feign.ILiveServiceFeign;
import com.kanfa.news.admin.vo.channel.ChannelFocusInfo;
import com.kanfa.news.admin.vo.channel.ChannelInfo;
import com.kanfa.news.admin.vo.channel.ContentInfo;
import com.kanfa.news.admin.vo.channel.ObjectInfo;
import com.kanfa.news.admin.vo.live.LiveInfo;
import com.kanfa.news.admin.vo.video.PcChannelFocusInfo;
import com.kanfa.news.admin.vo.video.VideoChannelFocusInfo;
import com.kanfa.news.admin.vo.video.VideoChannelFocusListInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Jiqc
 * @date 2018/3/7 18:13
 */
@RestController
@RequestMapping("channelFocus")
public class ChannelFocusRest {

    @Autowired
    private IChannelFocusServiceFeign channelFocusServiceFeign;
    @Autowired
    private IChannelServiceFeign channelServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private ILiveServiceFeign liveServiceFeign;


    /**
     * 添加焦点视图
     * @param channelFocusInfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<ChannelFocusInfo> add(@RequestBody ChannelFocusInfo channelFocusInfo) {
        if (StringUtils.isEmpty(channelFocusInfo.getTitle())){
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            objectRestResponse.setRel(true);
            objectRestResponse.setStatus(506);
            objectRestResponse.setMessage("焦点视图标题不能为空");
            return objectRestResponse;
        }
        channelFocusInfo.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return channelFocusServiceFeign.addChannelFocus(channelFocusInfo);
    }

    /**
     * 更新焦点视图
     * @param channelFocusInfo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ObjectRestResponse<ChannelFocus> update(@RequestBody ChannelFocusInfo channelFocusInfo) {
        ChannelFocus entity=new ChannelFocus();
        BeanUtils.copyProperties(channelFocusInfo,entity);
        if (entity.getId()==null){
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            objectRestResponse.setRel(true);
            objectRestResponse.setMessage("焦点视图id不能为空");
            return objectRestResponse;
        }
        if(channelFocusInfo.getJump().equals(NewsEnumsConsts.ChannelFocusOfJump.Live.key())){
            entity.setCid(channelFocusInfo.getLid());
        }
        entity.setCreateTime(new Date());
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return channelFocusServiceFeign.update(entity.getId(),entity);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ChannelFocusInfo> getChannel(@PathVariable("id") Integer id) {
        if(id==null){
            return new ObjectRestResponse<>();
        }
        ObjectRestResponse<ChannelFocus> channelFocusResp = channelFocusServiceFeign.get(id);
        ChannelFocus data = channelFocusResp.getData();
        if(data==null){
            return new ObjectRestResponse<>();
        }
        ChannelFocusInfo channelFocusInfo=new ChannelFocusInfo();
        BeanUtils.copyProperties(data,channelFocusInfo);
        channelFocusInfo.setIsDelete(null);
        channelFocusInfo.setIsPublish(null);
        channelFocusInfo.setOrderNumber(null);
        channelFocusInfo.setCreateTime(null);
        channelFocusInfo.setCreateUser(null);
        if(channelFocusInfo.getJump().equals(NewsEnumsConsts.ChannelFocusOfJump.Live.key())){
            channelFocusInfo.setLid(channelFocusInfo.getCid());
            channelFocusInfo.setCid(null);
        }
        List<Object> listContentOrLive=new ArrayList<>();
        Integer jump = data.getJump();
        if(jump.equals(NewsEnumsConsts.ChannelFocusOfJump.Content.key())){
            ObjectRestResponse<Content> contentObjectRestResponse = contentServiceFeign.get(data.getCid());
            Content content = contentObjectRestResponse.getData();
            if(content!=null){
                ContentInfo contentInfo=new ContentInfo();
                contentInfo.setId(content.getId());
                contentInfo.setTitle(content.getTitle());
                listContentOrLive.add(contentInfo);
            }
        }else if(jump.equals(NewsEnumsConsts.ChannelFocusOfJump.Live.key())){
            ObjectRestResponse<Live> liveResp=liveServiceFeign.get(data.getCid());
            Live data1 = liveResp.getData();
            if(data1!=null){
                LiveInfo liveInfo=new LiveInfo();
                liveInfo.setId(data1.getId());
                liveInfo.setTitle(data1.getTitle());
                listContentOrLive.add(liveInfo);
            }
        }
        channelFocusInfo.setOption(listContentOrLive);
        ObjectRestResponse<ChannelFocusInfo> response=new ObjectRestResponse<>();
        response.setData(channelFocusInfo);
        return response;
    }

    /**
     * 分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public TableResultResponse<ChannelFocusInfo> get(@RequestParam Map<String, Object> params) {
        params.put("isDelete",1);
        if(params.get("channelId")==null && "".equals(params.get("channelId").toString())){
            return new TableResultResponse<>();
        }
        TableResultResponse<ChannelFocusInfo> list = channelFocusServiceFeign.list(params);
        return list;
    }

    /**
     * 频道焦点视图列表
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getChannelList", method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> getChannelList(ChannelInfo entity) {
        entity.setChannelStatus(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        //查询集合
        ObjectRestResponse<List<ChannelInfo>> listChannelAll = channelServiceFeign.getListChannelAll(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        //搜索焦点集合
        List<ChannelFocusInfo> focusInfoList = channelFocusServiceFeign.all();
        List<ChannelInfo> infoList=listChannelAll.getData();
        for (ChannelInfo channelInfo : infoList) {
            Integer count=0;
            for (ChannelFocusInfo channelFocusInfo : focusInfoList) {
                if(channelFocusInfo.getChannelId().equals(channelInfo.getId())){
                    count++;
                }
            }
            channelInfo.setFocusCount(count);
        }
        ObjectRestResponse<List<ChannelInfo>> restResponse=new ObjectRestResponse<>();
        restResponse.setData(infoList);
        return restResponse;
    }

    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDelete(@RequestBody Map<String, List<Integer>> params) {
        return channelFocusServiceFeign.batchDelete(params.get("ids"));
    }

    /**
     * 搜索内容或视频
     * @param objectInfo
     * @return
     */
    @RequestMapping(value = "/searchContentOrLive", method = RequestMethod.POST)
    public ObjectRestResponse<Map<Integer,String>> searchContentOrLive(@RequestBody ObjectInfo objectInfo) {
        Integer type = objectInfo.getType();
        String title = objectInfo.getTitle();
        ObjectRestResponse<Map<Integer,String>> restResponse=new ObjectRestResponse<>();
        if(type.equals(1)){
            LiveInfo live=new LiveInfo();
            live.setTitle(title);
            live.setPage(1);
            live.setLimit(10);
            TableResultResponse<LiveInfo> livePage=liveServiceFeign.getLivePage(live);
            List<LiveInfo> rows = livePage.getData().getRows();
            Map<Integer,String> map=new LinkedHashMap<>(rows.size());
            if(rows!=null && rows.size()>0){
                for (LiveInfo info : rows) {
                    map.put(info.getId(),"[直播]" + info.getTitle());
                }
            }
            restResponse.setData(map);
            return restResponse;
        }else{
            ContentInfo entity=new ContentInfo();
            entity.setPage(1);
            entity.setLimit(10);
            entity.setTitle(title);
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
            TableResultResponse<ContentInfo> contentPage = contentServiceFeign.getContentPage(entity);
            List<ContentInfo> rows = contentPage.getData().getRows();
//            List<Map<Integer,String>> list=new ArrayList<>(rows.size());
            Map<Integer,String> map=new LinkedHashMap<>(rows.size());
            for (ContentInfo row : rows) {
                map.put(row.getId(),"["+NewsEnumsConsts.ContentOfContentType.getType(row.getContentType())+"]"+row.getTitle());
            }
            restResponse.setData(map);
            return restResponse;
         }
    }


    @RequestMapping(value = "/getVideoChannelFocusPage", method = RequestMethod.GET)
    public TableResultResponse<VideoChannelFocusInfo> getVideoChannelFocusPage(@RequestParam Map<String, Object> params) {
        return channelFocusServiceFeign.getVideoChannelFocusPage(params);
    }

    /**
     * 分页显示 视频管理里的 焦点图
     * @param page limit channelId
     * @return
     */
    @RequestMapping(value = "/getVideoFocusList", method = RequestMethod.GET)
    public TableResultResponse<VideoChannelFocusListInfo> getSearchPage(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam Integer channelId) {
        ObjectRestResponse<Channel> channelObjectRestResponse = channelServiceFeign.get(channelId);
        return channelFocusServiceFeign.getVideoFocusList(page, limit, channelId);
    }

    /**
     * 焦点图 发布
     * @param id
     * @return
     */
    @RequestMapping(value = "/onPublish/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse onPublic(@PathVariable("id")Integer id){
       ChannelFocus entity = new ChannelFocus();
        entity.setIsPublish(1);
        entity.setId(id);
        entity.setCreateTime(new Date());
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return channelFocusServiceFeign.update(id,entity );
    }


    /**
     * 焦点图 取消发布
     * @param id
     * @return
     */
    @RequestMapping(value = "/calPublish/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse calPublish(@PathVariable("id")Integer id){
        ChannelFocus channelFocus = new ChannelFocus();
        channelFocus.setIsPublish(0);
        channelFocus.setId(id);
        channelFocus.setCreateTime(new Date());
        channelFocus.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return channelFocusServiceFeign.update(id,channelFocus );
    }

    /**
     *  视频管理 焦点图 移除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteVideoFocus/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse deleteVideoFocus(@PathVariable("id")Integer id){
        ChannelFocus channelFocus = new ChannelFocus();
        channelFocus.setIsDelete(0);
        channelFocus.setId(id);
        channelFocus.setCreateTime(new Date());
        channelFocus.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return channelFocusServiceFeign.update(id,channelFocus );
    }

    /**
     *  VR管理-->焦点图管理
     * @param
     * @return
     */
    @RequestMapping(value = "/getVRChannelFocusPage", method = RequestMethod.GET)
    public TableResultResponse<VideoChannelFocusInfo> getVRChannelFocusPage(@RequestParam Map<String, Object> params) {
        return channelFocusServiceFeign.getVRChannelFocusPage(params);
    }

    /**
     *  PC管理-->焦点图管理
     * @param
     * @return
     */
    @RequestMapping(value = "/getPcChannelFocusPage", method = RequestMethod.GET)
    public ObjectRestResponse<List<PcChannelFocusInfo>> getPcChannelFocusPage() {
        return channelFocusServiceFeign.getPcChannelFocusPage();
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortChannelFocus", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> sortChannelFocus( @RequestBody Map<String, Object> params) {
        return channelFocusServiceFeign.sortChannelFocus(params);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
