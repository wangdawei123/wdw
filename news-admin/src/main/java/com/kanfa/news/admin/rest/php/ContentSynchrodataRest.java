package com.kanfa.news.admin.rest.php;

import cn.jiguang.common.utils.StringUtils;
import com.kanfa.news.admin.feign.IContentBroadcastBindServiceFeign;
import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.vo.video.VideoContentInfo;
import com.kanfa.news.admin.vo.vr.VRContentAddInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import com.kanfa.news.data.client.feign.IContentSynDataServiceFeign;
import com.kanfa.news.data.common.vo.channel.ContentInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * php数据同步
 * @author Jiqc
 * @date 2018/3/18 17:59
 */
@RestController
@RequestMapping("/contentSynchrodataRest")
public class ContentSynchrodataRest extends BaseRPC{

    @Autowired
    private IContentSynDataServiceFeign contentSynDataServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private IContentBroadcastBindServiceFeign contentBroadcastBindServiceFeign;

    /**
     * 添加文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/addContent", method = RequestMethod.GET)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> addContent(@RequestParam("id") Integer id) {
        if (null==id){
            String msg="id不能为空";
            return getObjectRestResponse(msg);
        }
        com.kanfa.news.admin.vo.channel.ContentInfo entity=new com.kanfa.news.admin.vo.channel.ContentInfo();
        ObjectRestResponse<ContentInfo> contentInfo = contentSynDataServiceFeign.getContentInfo(id);
        ContentInfo data = contentInfo.getData();
        BeanUtils.copyProperties(data,entity);
        //初始化频道属性值
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        String clientIp = ClientUtil.getClientIp(request);
        entity.setIp(clientIp);
        if (entity.getVideoType() == null) {
            entity.setVideoType(0);
        }
        if (StringUtils.isEmpty(entity.getLongTitle())) {
            entity.setLongTitle("");
        }
        if (StringUtils.isEmpty(entity.getIntroduction())) {
            entity.setIntroduction("");
        }
        if (StringUtils.isEmpty(entity.getImage())) {
            entity.setImage("");
        }
        if (entity.getRecommendWeight() == null) {
            entity.setRecommendWeight(60);
        }
        return contentServiceFeign.addContent(entity);
    }


    /**
     * 更新内容
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> update(@RequestParam("id") Integer id) {
        if (null==id) {
            String msg = "内容ID不能为空";
            return getObjectRestResponse(msg);
        }
        com.kanfa.news.admin.vo.channel.ContentInfo contentInfo = new com.kanfa.news.admin.vo.channel.ContentInfo();
        ObjectRestResponse<ContentInfo> objectRestResponse = contentSynDataServiceFeign.getContentInfo(id);
        ContentInfo data = objectRestResponse.getData();
        BeanUtils.copyProperties(data,contentInfo);
        if(contentInfo.getVideoDemand()!=null){
            contentInfo.setVideo(contentInfo.getVideoDemand().getUrl());
            contentInfo.setDid(contentInfo.getVideoDemand().getId());
        }
        List<Integer> appChannelIdList = contentInfo.getAppChannelIdList();
        appChannelIdList.addAll(contentInfo.getPcChannelIdList());
        contentInfo.setChannelIdList(appChannelIdList);
        String clientIp = ClientUtil.getClientIp(request);
        contentInfo.setIp(clientIp);
        //标识文章更新
        ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> update = contentServiceFeign.update(contentInfo);
        return update;
    }

    /**
<<<<<<< HEAD
     * 绑定内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/bindContent", method = RequestMethod.POST)
    public ObjectRestResponse bindContent(@RequestParam("id") Integer id) {
        List<Integer> ids=contentSynDataServiceFeign.getContentBindIds(id);
        //绑定
        return contentBroadcastBindServiceFeign.batchBindAndDelete(ids,id);
    }

    /**
     * 新增视频
     * @return
     */
    @RequestMapping(value = "/addvideo", method = RequestMethod.GET)
    public ObjectRestResponse<String> addvideo(@RequestParam("id")Integer id){
        com.kanfa.news.data.common.vo.video.VideoContentInfo videoDetail = contentSynDataServiceFeign.getVideoDetail(id);
        VideoContentInfo videoContentInfo = new VideoContentInfo();
        BeanUtils.copyProperties(videoDetail, videoContentInfo);
        contentServiceFeign.addVideoContent(videoContentInfo);
        String msg = "新增视频成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 编辑视频
     * @return
     */
    @RequestMapping(value = "/updatevideo", method = RequestMethod.GET)
    public ObjectRestResponse<String> updatevideo(@RequestParam("id")Integer id){
        com.kanfa.news.data.common.vo.video.VideoContentInfo videoDetail = contentSynDataServiceFeign.getVideoDetail(id);
        VideoContentInfo videoContentInfo = new VideoContentInfo();
        BeanUtils.copyProperties(videoDetail, videoContentInfo);
        contentServiceFeign.updateVideoContent(videoContentInfo);
        String msg = "修改视频成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 新增vr
     * @return
     */
    @RequestMapping(value = "/addvr", method = RequestMethod.GET)
    public ObjectRestResponse<String> addvr(@RequestParam("id")Integer id){
        com.kanfa.news.data.common.vo.vr.VRContentAddInfo vrDetail = contentSynDataServiceFeign.getVrDetail(id);
        VRContentAddInfo vrContentAddInfo = new VRContentAddInfo();
        BeanUtils.copyProperties(vrDetail, vrContentAddInfo);
        contentServiceFeign.addVRContent(vrContentAddInfo);
        String msg = "新增vr成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 编辑vr
     * @return
     */
    @RequestMapping(value = "/updatevr", method = RequestMethod.GET)
    public ObjectRestResponse<String> updatevr(@RequestParam("id")Integer id){
        com.kanfa.news.data.common.vo.vr.VRContentAddInfo vrDetail = contentSynDataServiceFeign.getVrDetail(id);
        VRContentAddInfo vrContentAddInfo = new VRContentAddInfo();
        BeanUtils.copyProperties(vrDetail, vrContentAddInfo);
        contentServiceFeign.updateVR(vrContentAddInfo);
        String msg = "编辑vr成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
