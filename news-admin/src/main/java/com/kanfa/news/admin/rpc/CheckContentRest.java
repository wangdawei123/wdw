package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Content;
import com.kanfa.news.admin.feign.IChannelServiceFeign;
import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.vo.channel.ChannelInfo;
import com.kanfa.news.admin.vo.channel.ContentInfo;
import com.kanfa.news.admin.vo.channel.ObjectInfo;
import com.kanfa.news.admin.vo.content.ContentListInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 审核使用
 * @author Jiqc
 * @date 2018/3/26 17:19
 */
@RestController
@RequestMapping("/check")
public class CheckContentRest extends BaseRPC {

    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private IChannelServiceFeign channelServiceFeign;

    /**
     * 查询对应频道的审核文章  频道id，状态
     * @return
     */
    @RequestMapping(value = "/getCheckContent",method = RequestMethod.POST)
    public TableResultResponse<ContentInfo> getCheckContent(@RequestBody ContentInfo contentInfo){
        if(contentInfo.getSearchType()!=null && contentInfo.getSearchType().equals(1)){
            contentInfo.setTitle(contentInfo.getKeyword());
        }else if(contentInfo.getSearchType()!=null && contentInfo.getSearchType().equals(2)){
            contentInfo.setUpdateUser(contentInfo.getKeyword());
        }
        contentInfo.setContentState(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        TableResultResponse<ContentInfo> contentPage = contentServiceFeign.getContentNotCheckPage(contentInfo);
        return contentPage;
    }

    /**
     * 查询频道待审核数量//0-待审核，2-驳回
     * @return
     */
    @RequestMapping(value = "/getCheckChannel",method = RequestMethod.GET)
    public ObjectRestResponse<List<ObjectInfo>> getCheckChannel(@RequestParam("checkStatus") Integer checkStatus){
        //0-待审核，2-驳回
        ObjectRestResponse<List<ChannelInfo>> listChannelAll = channelServiceFeign.getChannelCheck(checkStatus);
        List<ChannelInfo> data = listChannelAll.getData();
        Map<Integer,List<ChannelInfo>> map = new HashMap<>(16);
        if(data!=null && data.size()>0){
            int size = data.size();
            List<ChannelInfo> list = null;
            for (ChannelInfo datum : data) {
                if(map.get(datum.getCategory())==null){
                    list = new ArrayList<>(size);
                    list.add(datum);
                    map.put(datum.getCategory(),list);
                }else {
                    List<ChannelInfo> channelInfos = map.get(datum.getCategory());
                    channelInfos.add(datum);
                    map.put(datum.getCategory(),channelInfos);
                }
            }
        }
        List<ObjectInfo> channelInfoList = new ArrayList<>(map.size());
        for (Map.Entry<Integer, List<ChannelInfo>> integerListEntry : map.entrySet()) {
            ObjectInfo objectInfo=new ObjectInfo();
            if(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key().equals(integerListEntry.getKey())){
                objectInfo.setName("资讯管理");
                objectInfo.setChannelInfoList(integerListEntry.getValue());
                channelInfoList.add(objectInfo);
            }else if(NewsEnumsConsts.ChannelOfCategory.VIDEO_APP.key().equals(integerListEntry.getKey())){
                objectInfo.setName("视频管理");
                objectInfo.setChannelInfoList(integerListEntry.getValue());
                channelInfoList.add(objectInfo);
            }else if(NewsEnumsConsts.ChannelOfCategory.VR_APP.key().equals(integerListEntry.getKey())){
                objectInfo.setName("VR管理");
                objectInfo.setChannelInfoList(integerListEntry.getValue());
                channelInfoList.add(objectInfo);
            }else if(NewsEnumsConsts.ChannelOfCategory.INFO_PC.key().equals(integerListEntry.getKey())){
                objectInfo.setName("PC管理");
                objectInfo.setChannelInfoList(integerListEntry.getValue());
                channelInfoList.add(objectInfo);
            }
        }
        ObjectRestResponse<List<ObjectInfo>> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(channelInfoList);
        return objectRestResponse;
    }

    /**
     * 审核通过与驳回
     * @param id
     * @param checkStatus
     * @return
     */
    @RequestMapping(value = "/checkPass",method = RequestMethod.GET)
    public ObjectRestResponse<Content> checkPass(HttpServletRequest request,@RequestParam("id") Integer id, @RequestParam("checkStatus") Integer checkStatus, @RequestParam("fromType") Integer fromType){
        if(id==null){
            getObjectRestResponse("id不能为空");
        }else if(checkStatus==null){
            getObjectRestResponse("checkStatus不能为空");
        }else if(fromType==null){
            getObjectRestResponse("fromType不能为空");
        }
        ContentInfo content=new ContentInfo();
        content.setCheckStatus(checkStatus);
        content.setId(id);
        content.setFromType(fromType);
        content.setIp(ClientUtil.getClientIp(request));
        content.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        content.setUpdateTime(new Date());
        ObjectRestResponse<Content> update = contentServiceFeign.checkPassContent(content);
        return update;
    }

    /**
     * 批量删除内容
     * @param contentListInfo
     * @return
     */
    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST)
    public ObjectRestResponse<Integer> checkContentBatchDelete(@RequestBody ContentListInfo contentListInfo, HttpServletRequest request){
        if(contentListInfo.getList() == null && contentListInfo.getList().size()<=0){
            return getObjectRestResponse("内容不能空");
        }
        contentListInfo.setIp(ClientUtil.getClientIp(request));
        contentListInfo.setUid(Integer.valueOf(getCurrentUserId()));
        return contentServiceFeign.batchDeleteByList(contentListInfo);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
