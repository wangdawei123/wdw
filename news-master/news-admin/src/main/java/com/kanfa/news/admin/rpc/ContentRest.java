package com.kanfa.news.admin.rpc;

import com.alibaba.fastjson.JSON;
import com.kanfa.news.admin.entity.*;
import com.kanfa.news.admin.feign.*;
import com.kanfa.news.admin.vo.AdminUserInfo;
import com.kanfa.news.admin.vo.channel.*;
import com.kanfa.news.admin.vo.comment.CommentInfo;
import com.kanfa.news.admin.vo.comment.CommentStandardInfo;
import com.kanfa.news.admin.vo.content.ContentArticleInfo;
import com.kanfa.news.admin.vo.content.ContentArticleLogInfo;
import com.kanfa.news.admin.vo.content.ContentPreviewInfo;
import com.kanfa.news.admin.vo.kpi.KpiCountContentInfo;
import com.kanfa.news.admin.vo.my.MyContentInfo;
import com.kanfa.news.admin.vo.video.*;
import com.kanfa.news.admin.vo.vr.VRContentAddInfo;
import com.kanfa.news.admin.vo.vr.VRShow;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import com.kanfa.news.common.util.ImgUtils;
import com.kanfa.news.common.util.PHPSerializer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Jiqc
 * @date 2018/3/5 15:26
 */
@RestController
@RequestMapping("content")
public class ContentRest extends BaseRPC{



    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private IChannelServiceFeign channelServiceFeign;
    @Autowired
    private IBaseUserServiceFeign baseUserServiceFeign;
    @Autowired
    private ICorpUserServiceFeign corpUserServiceFeign;
    @Autowired
    private IVideoDemandServiceFeign videoDemandServiceFeign;
    @Autowired
    private ICommentStandardServiceFeign commentStandardServiceFeign;
    @Autowired
    private IActivityLawPioneerServiceFeign activityLawPioneerServiceFeign;

    @RequestMapping(value = "/addContent", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> addContent(@RequestBody ContentInfo entity, HttpServletRequest request) {
        if (StringUtils.isEmpty(entity.getTitle())) {
            String msg = "内容标题不能为空";
            return getObjectRestResponse(msg);
        } else if (entity.getTagNameList() == null || entity.getTagNameList().size() <= 0) {
            String msg = "内容标签不能为空";
            return getObjectRestResponse(msg);
        } else if (NewsEnumsConsts.ChannelOfCategory.INFO_APP.key().equals(entity.getCategory())&&(entity.getAppChannelIdList()==null || entity.getAppChannelIdList().size()<=0)) {
            String msg = "内容频道不能为空";
            return getObjectRestResponse(msg);
        } else if (entity.getCardType() == null) {
            String msg = "内容card类型不能为空";
            return getObjectRestResponse(msg);
        } else if (StringUtils.isEmpty(entity.getContentDetail())) {
            String msg = "内容文章详情不能为空";
            return getObjectRestResponse(msg);
        } else if (entity.getSourceType() == null) {
            String msg = "内容来源类型不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key())||entity.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.BigImg.key())){
            if(StringUtils.isEmpty(entity.getImage())){
                return getObjectRestResponse("图片不能为空");
            }
        }else if(entity.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.ThreeImg.key())){
            String contentDetail = entity.getContentDetail();
            List<String> imgStr = ImgUtils.getImgStr(contentDetail);
            //判断有图的类型，三张
            if(imgStr.size()<3){
                return getObjectRestResponse("文章图片不能少于三张");
            }
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(entity.getIsCheck())){
            //是否为审核 0未审核
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            entity.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
        if(NewsEnumsConsts.ContentOfSourceType.Original.getKey().equals(entity.getSourceType())){
            List<KpiCountContentInfo> kpiCountContentInfoList = entity.getKpiCountContentInfoList();
            if(kpiCountContentInfoList!=null && kpiCountContentInfoList.size()>0){
                int size = kpiCountContentInfoList.size();
                List<Integer> ids=new ArrayList<>(size);
                kpiCountContentInfoList.forEach(e->ids.add(e.getUid()));
                Set<Integer> set = new HashSet<>(ids);
                if(size != set.size()){
                    return getObjectRestResponse("来源记者不能重复添加");
                }
            }else{
                return getObjectRestResponse("来源记者不能为空");
            }
        }
        if(entity.getTitle().length()>60){
            return getObjectRestResponse("标题限60个字符");
        }
        if(entity.getLongTitle().length()>60){
            return getObjectRestResponse("长标题限60个字符");
        }
        if(entity.getVideoDemand()!=null){
            entity.setVideo(entity.getVideoDemand().getUrl());
            entity.setDid(entity.getVideoDemand().getId());
        }
        //初始化频道属性值
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        //entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        entity.setCreateTime(new Date());
        entity.setContentState(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.Article.getKey());
        entity.setUpdateTime(null);
        String clientIp = ClientUtil.getClientIp(request);
        entity.setIp(clientIp);
        if (entity.getVideoType() == null) {
            entity.setVideoType(0);
        }
        if (entity.getCreateUid() == null) {
            entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        }
        if (entity.getSourceType() == null) {
            entity.setSourceType(0);
        }
        if (entity.getIsLegal() == null) {
            entity.setIsLegal(0);
        }
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
     * 根据id查询内容
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContentById(@PathVariable("id") Integer id) {
        //查询包含，内容频道信息（pc和app），card类型，标签，编辑，文章记者+类型，关联内容，操作记录
        if (id == null) {
            String msg = "内容未找到";
            return getObjectRestResponse(msg);
        }
        return contentServiceFeign.getContent(id);
    }

    /**
     * 查询评论（标准库）分页五条
     * @param params
     * @return
     */
    @RequestMapping(value = "/getCommentStandard", method = RequestMethod.POST)
    public TableResultResponse<CommentStandardInfo> getCommentPage(@RequestBody Map<String, Object> params) {
        params.put("page", 1);
        params.put("limit", 5);
        TableResultResponse<CommentStandardInfo> page = commentStandardServiceFeign.page(params);
        return page;
    }

    /**
     * 更换
     * @param params
     * @return
     */
    @RequestMapping(value = "/changeCommentStandard", method = RequestMethod.POST)
    public TableResultResponse<CommentStandardInfo> changeCommentStandard(@RequestBody Map<String, Object> params) {
        params.put("page", 1);
        params.put("limit", 1);
        TableResultResponse<CommentStandardInfo> page = commentStandardServiceFeign.page(params);
        return page;
    }

    /**
     * 查询对应标签集合
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTagByCid/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<List<VideoTagInfo>> getTagByCid(@PathVariable("id") Integer id) {
        // 1.标签
        List<VideoTag> data = contentServiceFeign.allTag();
        ObjectRestResponse<List<VideoTag>> listObjectRestResponse = contentServiceFeign.getTagListByConentId(id);

        List<VideoTag> data1 = listObjectRestResponse.getData();
        //遍历集合，封装选中
        List<VideoTagInfo> infoList = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (VideoTag tag : data) {
                VideoTagInfo info = new VideoTagInfo();
                BeanUtils.copyProperties(tag, info);
                for (VideoTag videoTag : data1) {
                    if (videoTag.getId().equals(tag.getId())) {
                        info.setIsSelect(1);//选中
                        break;
                    }
                }
                infoList.add(info);
            }
        }
        ObjectRestResponse<List<VideoTagInfo>> restResponse = new ObjectRestResponse<>();
        restResponse.setData(infoList);
        return restResponse;
    }

    @RequestMapping(value = "/getUserByCid", method = RequestMethod.GET)
    public ObjectRestResponse<List<AdminUserInfo>> getUserByCid() {
        // 1.编辑人加上选中的人@PathVariable("id") Integer id
        //所有编辑人
        List<AdminUserInfo> data = baseUserServiceFeign.getAllBaseUser();
        //文章对应的编辑人
        ObjectRestResponse<List<AdminUserInfo>> baseUserResp = new ObjectRestResponse<>();
        baseUserResp.setData(data);
        return baseUserResp;
    }

    /**
     * 查询文章对应的频道包含pc和
     *
     * @return
     */
    @RequestMapping(value = "/getChannelForContent", method = RequestMethod.GET)
    public ObjectRestResponse<Map<String,List<ChannelInfo>>> getChannelForContent() {
        //分为两个字段
        ObjectRestResponse<List<ChannelInfo>> channelForContent = channelServiceFeign.getChannelAllForContent();
        List<ChannelInfo> data = channelForContent.getData();
        Map<String,List<ChannelInfo>> map=new HashMap<>(5);
        List<ChannelInfo> appList = new ArrayList<>(data.size());
        List<ChannelInfo> pcList = new ArrayList<>(16);
        for (ChannelInfo channelInfo : data) {
            if(channelInfo.getCategory().equals(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key())){
                appList.add(channelInfo);
            }else if (channelInfo.getCategory().equals(NewsEnumsConsts.ChannelOfCategory.INFO_PC.key())){
                pcList.add(channelInfo);
            }
        }
        map.put("appList",appList);
        map.put("pcList",pcList);
        //查询vr频道
        List<Channel> vrLeftChannel = channelServiceFeign.getVRLeftChannel();
        List<ChannelInfo> vrList = new ArrayList<>(vrLeftChannel.size());
        for (Channel channel: vrLeftChannel) {
            ChannelInfo channelInfo = new ChannelInfo();
            BeanUtils.copyProperties(channel,channelInfo);
            vrList.add(channelInfo);
        }
        map.put("vrList",vrList);
        //查询发证先锋
        ActivityLawPioneerInfo entity=new ActivityLawPioneerInfo();
        entity.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        ObjectRestResponse<List<ActivityLawPioneerInfo>> list = activityLawPioneerServiceFeign.getList(entity);
        List<ActivityLawPioneerInfo> data1 = list.getData();
        List<ChannelInfo> childChannelIdList=new ArrayList<>();
        for (ActivityLawPioneerInfo activityLawPioneerInfo : data1) {
            ChannelInfo channelInfo=new ChannelInfo();
            channelInfo.setId(activityLawPioneerInfo.getId());
            channelInfo.setName(activityLawPioneerInfo.getTitle());
            childChannelIdList.add(channelInfo);
        }
        map.put("childChannelIdList",childChannelIdList);
        ObjectRestResponse<Map<String,List<ChannelInfo>>> restResponse=new ObjectRestResponse<>();
        restResponse.setData(map);


        return restResponse;
    }

    /**
     * 查询记者编辑
     *@RequestParam Integer contentId
     * @return
     */
    @RequestMapping(value = "/getReporterForContent", method = RequestMethod.GET)
    public ObjectRestResponse<List<ObjectInfo>> getReporterForContent() {
        CorpUserAndDeptInfo reporterAndDept = corpUserServiceFeign.getReporterAndDept();
        List<CorpUser> corpUsers = reporterAndDept.getCorpUsers();
        List<CorpDept> corpDepts = reporterAndDept.getCorpDepts();
        if (corpUsers==null || corpUsers.size() <= 0) {
            return null;
        }
        Map<Integer, List<CorpUserInfo>> map = new HashMap<>();
        for (CorpUser corpUser : corpUsers) {
            CorpUserInfo corpUserInfo = new CorpUserInfo();
            BeanUtils.copyProperties(corpUser, corpUserInfo);
            if (map.get(corpUser.getLevel2Id()) == null) {
                List<CorpUserInfo> corpUserInfoList = new ArrayList<>(corpUsers.size());
                corpUserInfoList.add(corpUserInfo);
                map.put(corpUser.getLevel2Id(), corpUserInfoList);
            } else {
                List<CorpUserInfo> corpUserInfoList = map.get(corpUser.getLevel2Id());
                corpUserInfoList.add(corpUserInfo);
                map.put(corpUser.getLevel2Id(), corpUserInfoList);
            }
        }
        //封装对象
        List<ObjectInfo> list = new ArrayList<>(map.size());
        for (Map.Entry<Integer, List<CorpUserInfo>> integerListEntry : map.entrySet()) {
            for (CorpDept corpDept : corpDepts) {
                if(integerListEntry.getKey().equals(corpDept.getId())){
                    ObjectInfo objectInfo = new ObjectInfo();
                    objectInfo.setName(corpDept.getName());
                    objectInfo.setListObject(integerListEntry.getValue());
                    list.add(objectInfo);
                }
            }
        }
        ObjectRestResponse<List<ObjectInfo>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(list);
        return listObjectRestResponse;
    }

    /**
     * 分页查询
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public TableResultResponse<ContentInfo> page(@RequestBody ContentInfo entity) {
        //查询咨询分页，条件：类型，状态，开始结束日期，文章标题，id，创建人，频道id
        entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
        entity.setContentState(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        if(entity.getCategory()==null){
            entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        }
        if(StringUtils.isNotEmpty(entity.getKeyword())){
            if (entity.getSearchType() != null && entity.getSearchType().equals(NewsEnumsConsts.ContentOfSearchType.Title.getKey())) {
                entity.setTitle(entity.getKeyword());
            } else if (entity.getSearchType() != null && entity.getSearchType().equals(NewsEnumsConsts.ContentOfSearchType.ContentId.getKey())) {
                if(StringUtils.isNumeric(entity.getKeyword())){
                    entity.setId(Integer.valueOf(entity.getKeyword()));
                }else {
                    return new TableResultResponse();
                }
            } else if (entity.getSearchType() != null && entity.getSearchType().equals(NewsEnumsConsts.ContentOfSearchType.CreateUser.getKey())) {
                entity.setCreateUser(entity.getKeyword());
            }
            if (entity.getChannelId() == null || entity.getChannelId().equals(-1)) {
                entity.setChannelId(null);
            }
        }
        TableResultResponse<ContentInfo> list = contentServiceFeign.getContentPage(entity);
        return list;
    }

    /**
     * 绑定内容查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getBindContent", method = RequestMethod.POST)
    public TableResultResponse<ContentInfo> getBindContent(@RequestBody ContentInfo entity) {
        //查询咨询分页，条件：类型，状态，开始结束日期，文章标题，id，创建人，频道id
        entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
        entity.setContentState(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.Article.getKey());
        Integer contentId=entity.getId();
        entity.setId(null);
        //排除已经绑定的
        ContentBroadcastBind contentBroadcastBind=new ContentBroadcastBind();
        contentBroadcastBind.setContentId(contentId);
        List<ContentBroadcastBindInfo> allBind = contentServiceFeign.getContentBind(contentBroadcastBind);
        List<Integer> notInIds=new ArrayList<>(allBind.size());
        for (ContentBroadcastBindInfo broadcastBind : allBind) {
            if(broadcastBind.getId()!=null){
                notInIds.add(broadcastBind.getId());
            }
        }
        entity.setNotInIds(notInIds);
        TableResultResponse<ContentInfo> list = contentServiceFeign.getContentPage(entity);
        List<ContentInfo> rows = new ArrayList<>();
        for (ContentInfo row : list.getData().getRows()) {
            ContentInfo contentInfo=new ContentInfo();
            contentInfo.setId(row.getId());
            contentInfo.setTitle("[文章]"+row.getTitle());
            rows.add(contentInfo);
        }
        list.getData().setRows(rows);
        return list;
    }

    /**
     * 绑定内容
     * @param entity
     * @return
     */
    @RequestMapping(value = "/bindContent", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> bindContent(@RequestBody ContentBroadcastBind entity) {
        if(entity.getBindId()==null){
            return getObjectRestResponse("绑定内容Id不能为空");
        }else if (entity.getContentId()==null){
            return getObjectRestResponse("源内容Id不能为空");
        }
        //绑定
        contentServiceFeign.bindContent(entity);
        //查询文章
        ObjectRestResponse<ContentInfo> content = contentServiceFeign.getContentForBind(entity.getBindId());
        return content;
    }

    /**
     * 解除绑定
     * @param entity
     * @return
     */
    @RequestMapping(value = "/unBindContent", method = RequestMethod.POST)
    public ObjectRestResponse<ContentBroadcastBind> unBindContent(@RequestBody ContentBroadcastBind entity) {
        if(entity.getBindId()==null){
            return getObjectRestResponse("绑定内容Id不能为空");
        }else if (entity.getContentId()==null){
            return getObjectRestResponse("源内容Id不能为空");
        }
        //绑定解除
        return contentServiceFeign.unBindContent(entity);
    }

    /**
     * 根据对应的id查询绑定
     * @param params
     * @return
     */
    @RequestMapping(value = "/getBindContentByContentId", method = RequestMethod.GET)
    public TableResultResponse<ContentBroadcastBindInfo> getBindContentByContentId(@RequestParam Map<String, Object> params) {
        //排除已经绑定的
        if(params.get("contentId")==null){
            return new TableResultResponse();
        }
        return contentServiceFeign.getContentBindPage(params);
//        ContentBroadcastBind contentBroadcastBind=new ContentBroadcastBind();
//        contentBroadcastBind.setContentId(contentId);
//        ObjectRestResponse< List<ContentBroadcastBindInfo>> objectRestResponse=new ObjectRestResponse<>();
//        objectRestResponse.setData(allBind);
//        return new TableResultResponse(allBind.size(),allBind);
    }

    /**
     * 更新内容
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> update(@RequestBody ContentInfo contentInfo , HttpServletRequest request) {
        //查询咨询分页，条件：类型，状态，开始结束日期，文章标题，id，创建人，频道id
        if (contentInfo.getId() == null) {
            String msg = "内容ID不能为空";
            return getObjectRestResponse(msg);
        }
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
        contentInfo.setContentType(NewsEnumsConsts.ContentOfContentType.Article.getKey());
        contentInfo.setUpdateTime(new Date());
        contentInfo.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        ObjectRestResponse<ContentInfo> update = contentServiceFeign.update(contentInfo);
        return update;
    }

    /**
     * 模糊搜索视频
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/getContentPageForVideo", method = RequestMethod.GET)
    public ObjectRestResponse<List<VideoDemandInfo>> getContentPage(@RequestParam("title") String title) {
        if (StringUtils.isEmpty(title.trim())) {
            return new ObjectRestResponse<>();
        }
        TableResultResponse<VideoDemandInfo> searchPage = videoDemandServiceFeign.getSearchPage(1, 10, title);
        ObjectRestResponse<List<VideoDemandInfo>> objectRestResponse = new ObjectRestResponse<>();
        List<VideoDemandInfo> rows = searchPage.getData().getRows();
        for (VideoDemandInfo row : rows) {
            row.setCreateTime(null);
            row.setCreateUser(null);
            row.setStatus(null);
            row.setStatusCh(null);
            //拼接测试视频url地址
            String url = row.getUrl();
            if(StringUtils.isNotEmpty(url)){
                String url2 = url.split("/")[2];
                String http = "http://devvideotest.oss-cn-beijing.aliyuncs.com/outactmvp/";
                String testHttp = http+url2;
                row.setUrl(testHttp);
            }
        }
        objectRestResponse.setData(rows);
        return objectRestResponse;
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }

    /**
     * saveVideoDraftBox 保存视频到草稿箱
     * @param entity
     * @return
     */
    @RequestMapping(value = "/saveVideoDraftBox", method = RequestMethod.POST)
    public ObjectRestResponse saveVideoDraftBox(@RequestBody VideoContentInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())) {
            String msg = "内容标题不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getSourceId()==null){
            String msg = "视频地址不能为空";
            return getObjectRestResponse(msg);
        } else if (entity.getVideoDuration() == null) {
            String msg = "视频时间不能为空";
            return getObjectRestResponse(msg);
        }
        //初始化属性
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());

        //状态，1：正常，0：删除
        entity.setContentState(1);
        //视频 1
        entity.setCategory(1);
        entity.setContentType(4);
        //'显示状态，0审核列不显示，1审核列表显示',
        entity.setIsCheck(0);
        //审核状态（0待审核，1审核通过，2审核不通过）
        entity.setCheckStatus(3);
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        entity.setUpdateTime(null);
        if(entity.getVideoType()==null){
            entity.setVideoType(0);
        }
        if(entity.getCreateUid()==null){
            entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        }
        if(entity.getSourceType()==null){
            entity.setSourceType(0);
        }
        if(entity.getIsLegal()==null){
            entity.setIsLegal(0);
        }
        if(entity.getVideoType()==null){
            entity.setVideoType(0);
        }
        if(StringUtils.isEmpty(entity.getLongTitle())){
            entity.setLongTitle("");
        }
        if(StringUtils.isEmpty(entity.getIntroduction())){
            entity.setIntroduction("");
        }
        if(StringUtils.isEmpty(entity.getImage())){
            entity.setImage("");
        }
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        contentServiceFeign.addVideoContent(entity);
        String meg = "已经保存到草稿箱";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(meg);
        return stringObjectRestResponse;
    }

    /**
     * 新增视频
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addVideoContent", method = RequestMethod.POST)
    public ObjectRestResponse<VideoContentShow> addVideoContent(@RequestBody VideoContentInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())) {
            String msg = "内容标题不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getSourceId()==null){
            String msg = "视频地址不能为空";
            return getObjectRestResponse(msg);
        } else if (entity.getVideoDuration() == null) {
            String msg = "视频时间不能为空";
            return getObjectRestResponse(msg);
        }
        //初始化属性
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setUpdateTime(new Date());
        //状态，1：正常，0：删除
        entity.setContentState(1);
        //视频
        entity.setCategory(1);
        entity.setContentType(4);
        //'显示状态，0审核列不显示，1审核列表显示',
        entity.setIsCheck(0);
        //审核状态（0待审核，1审核通过，2审核不通过）
        entity.setCheckStatus(0);
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        entity.setUpdateTime(null);
        if(entity.getVideoType()==null){
            entity.setVideoType(0);
        }
        if(entity.getCreateUid()==null){
            entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        }
        if(entity.getSourceType()==null){
            entity.setSourceType(0);
        }
        if(entity.getIsLegal()==null){
            entity.setIsLegal(0);
        }
        if(entity.getVideoType()==null){
            entity.setVideoType(0);
        }
        if(StringUtils.isEmpty(entity.getLongTitle())){
            entity.setLongTitle("");
        }
        if(StringUtils.isEmpty(entity.getIntroduction())){
            entity.setIntroduction("");
        }
        if(StringUtils.isEmpty(entity.getImage())){
            entity.setImage("");
        }
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        ObjectRestResponse<VideoContentInfo> videoContentInfoObjectRestResponse = contentServiceFeign.addVideoContent(entity);
        VideoContentInfo data = videoContentInfoObjectRestResponse.getData();
        VideoContentShow videoContentShow = new VideoContentShow();
        BeanUtils.copyProperties(data,videoContentShow);
        ObjectRestResponse<VideoContentShow> videoContentShowObjectRestResponse = new ObjectRestResponse<>();
        videoContentShowObjectRestResponse.setData(videoContentShow);
        return videoContentShowObjectRestResponse;
    }



    /**
     * 已删除列表 的分页显示
     *
     * @param page,limit
     * @return
     */

    @RequestMapping(value = "/selectDeletes", method = RequestMethod.GET)
    public TableResultResponse<RecycleBinInfo> selectDeletes(@RequestParam Integer page,
                                                             @RequestParam Integer limit) {
        return contentServiceFeign.selectDeletes(page, limit);
    }

    /**
     * 已删除列表 按标题搜索
     *
     * @param page,limit,title
     * @return
     */
    @RequestMapping(value = "/selectSearchDeletes", method = RequestMethod.GET)
    public TableResultResponse<RecycleBinInfo> selectSearchDeletes(@RequestParam Integer page,
                                                                   @RequestParam Integer limit,
                                                                   @RequestParam String title) {
        return contentServiceFeign.selectSearchDeletes(page, limit, title);
    }

    /**
     * 已删除列表  删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ObjectRestResponse<Content> remove(@PathVariable("id") int id) {
        return contentServiceFeign.remove(id);
    }

    /**
     * 已删除列表 恢复
     *
     * @param id
     * @return
     */

    @RequestMapping(value = "/restore/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse restore(@PathVariable("id") Integer id) {
        //恢复状态
        Content content = new Content();
        content.setId(id);
        content.setUpdateTime(new Date());
        content.setContentState(1);
        content.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return contentServiceFeign.update(id, content);
    }

    /**
     * 已删除列表 批量删除
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/moreDeletes", method = RequestMethod.POST)
    public ObjectRestResponse moreDeletes(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> ids = params.get("ids");
        int count = 0;
        if (ids.size() > 0) {
            for (Integer id : ids) {
                contentServiceFeign.remove(id);
                count++;
            }
        } else {
            String msg = "请选择要删除的id不能为空";
            return getObjectRestResponse(msg);
        }
        String msg = "删除" + count + "个";
        return getObjectRestResponse(msg);
    }

    /**
     * 批量删除
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDelete(@RequestBody Map<String, List<Integer>> params, HttpServletRequest request) {
        return contentServiceFeign.batchDelete(params.get("ids"),ClientUtil.getClientIp(request),Integer.valueOf(getCurrentUserId()));
    }

    /**
     * 发布与取消
     *
     * @param channelId
     * @param contentId
     * @param isPublish
     * @return
     */
    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public ObjectRestResponse<Integer> publish(@RequestParam Integer channelId, @RequestParam Integer contentId, @RequestParam Integer isPublish) {
        ChannelContent channelContent = new ChannelContent();
        channelContent.setChannelId(channelId);
        channelContent.setContentId(contentId);
        channelContent.setIsPublish(isPublish);
        if (channelContent.getContentId() == null) {
            String msg = "内容Id不能为空";
            return getObjectRestResponse(msg);
        } else if (channelContent.getIsPublish() == null) {
            String msg = "发布状态不能为空";
            return getObjectRestResponse(msg);
        }
        if (channelContent.getIsPublish().equals(1)) {
            channelContent.setPublishTime(new Date());
        }
        return contentServiceFeign.updateChannelConent(channelContent);
    }

    /**
     * 置顶
     *
     * @param channelId
     * @param contentId
     * @param top
     * @return
     */
    @RequestMapping(value = "/topContent", method = RequestMethod.GET)
    public ObjectRestResponse<Integer> topContent(@RequestParam Integer channelId, @RequestParam Integer contentId, @RequestParam Integer top) {
        ChannelContent channelContent = new ChannelContent();
        channelContent.setChannelId(channelId);
        channelContent.setContentId(contentId);
        channelContent.setTop(top);
        if (channelContent.getChannelId() == null) {
            String msg = "频道Id不能为空";
            return getObjectRestResponse(msg);
        } else if (channelContent.getContentId() == null) {
            String msg = "内容Id不能为空";
            return getObjectRestResponse(msg);
        } else if (channelContent.getTop() == null) {
            String msg = "置顶状态不能为空";
            return getObjectRestResponse(msg);
        }
        if (channelContent.getTop() != 0) {
            Long timeStamp = System.currentTimeMillis() / 1000;
            channelContent.setTop(timeStamp.intValue());
        }
        return contentServiceFeign.updateChannelConent(channelContent);
    }

    /**
     * 样式修改查询
     *
     * @param channelId
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getCardType", method = RequestMethod.GET)
    public ObjectRestResponse<ChannelContentCardInfo> getCardType(@RequestParam Integer channelId, @RequestParam Integer contentId) {
        //查询文章样式类型及图片
        if (contentId == null) {
            return getObjectRestResponse("内容Id不能空");
        }
        if (channelId == null) {
            return getObjectRestResponse("频道Id不能空");
        }
        ChannelContentCardInfo channelContentCardInfo = new ChannelContentCardInfo();
        channelContentCardInfo.setChannelId(channelId);
        channelContentCardInfo.setContentId(contentId);
        ObjectRestResponse<ChannelContentCardInfo> cardType = contentServiceFeign.getCardType(channelContentCardInfo);
        ChannelContentCardInfo data = cardType.getData();
        //搜索三图
        ObjectRestResponse<ContentArticle> articleImg = contentServiceFeign.getArticleImg(contentId);
        if(articleImg.getData()!=null && StringUtils.isNotEmpty(articleImg.getData().getContent())){
            List<String> imgStr = ImgUtils.getImgStr(articleImg.getData().getContent());
//            if(imgStr==null||imgStr.size()<3){
//                return getObjectRestResponse("内容图片不足三张");
//            }
            data.setImageList(imgStr);
        }
        cardType.setData(data);
        return cardType;
    }

    /**
     * 解析三图(弃用)
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getArticleImg", method = RequestMethod.GET)
    public ObjectRestResponse<List<String>> getArticleImg(@RequestParam Integer contentId) {
        ObjectRestResponse<ContentArticle> articleImg = contentServiceFeign.getArticleImg(contentId);
        ObjectRestResponse<List<String>> listObjectRestResponse = new ObjectRestResponse<>();
        if(articleImg.getData()!=null && StringUtils.isNotEmpty(articleImg.getData().getContent())){
            List<String> imgStr = ImgUtils.getImgStr(articleImg.getData().getContent());
            if(imgStr==null||imgStr.size()<3){
                return getObjectRestResponse("内容图片不足三张");
            }
            listObjectRestResponse.setData(imgStr);
        }
        return listObjectRestResponse;
    }

    /**
     * 更新样式
     * @param channelContentCardInfo
     * @return
     */
    @RequestMapping(value = "/updateCardType", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> updateCardType(@RequestBody ChannelContentCardInfo channelContentCardInfo) {
        /*if(channelContentCardInfo.getId()==null){
            return getObjectRestResponse("id不能为空");
        }else */
        if(channelContentCardInfo.getCardType()==null){
            return getObjectRestResponse("样式类型不能为空");
        }
        ChannelContentCard channelContentCard=new ChannelContentCard();
        BeanUtils.copyProperties(channelContentCardInfo,channelContentCard);
        //样式对应的图片不能空
        if(channelContentCardInfo.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key()) || channelContentCardInfo.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.BigImg.key())){
            if(StringUtils.isEmpty(channelContentCardInfo.getImage())){
                return getObjectRestResponse("图片不能为空");
            }
        }else if(channelContentCardInfo.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.ThreeImg.key())){
            if(channelContentCardInfo.getImageList()==null || channelContentCardInfo.getImageList().size()<=2){
                return getObjectRestResponse("三图的图片数组不能少于三张");
            }
            String serialize = new String(PHPSerializer.serialize(channelContentCardInfo.getImageList()));
            channelContentCard.setImage(serialize);
        }
        if(channelContentCard.getId()==null){
            if(channelContentCard.getChannelId()==null||channelContentCard.getContentId()==null){
                return getObjectRestResponse("创建请传入内容和频道id");
            }
        }else{
            channelContentCard.setChannelId(null);
            channelContentCard.setContentId(null);
        }
        ObjectRestResponse<Integer> restResponse = contentServiceFeign.updateCardType(channelContentCard);
        return restResponse;
    }

    /**
     * 获取文章详情
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
    public ObjectRestResponse<ContentArticleInfo> getArticle(@RequestParam Integer contentId) {
        ContentArticleInfo contentArticleInfo = new ContentArticleInfo();
        contentArticleInfo.setId(contentId);
        if (contentArticleInfo.getId() == null) {
            return getObjectRestResponse("内容Id不能为空");
        }
        return contentServiceFeign.getContentArticle(contentArticleInfo);
    }

    /**
     * 分页查询评论
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/getMoreComment", method = RequestMethod.GET)
    public TableResultResponse<CommentInfo> getMoreComment(@RequestParam Map<String, Object> params) {
        if (params.get("contentId") == null) {
            return new TableResultResponse<>();
        }
        if (params.get("page") == null || params.get("limit") == null) {
            return new TableResultResponse<>();
        }
        return contentServiceFeign.getCommentPage(params);
    }

    /**
     * 视频列表的分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getVideoPage", method = RequestMethod.POST)
    public TableResultResponse<VideoContentSelectInfo> getVideoPage(@RequestBody VideoSelectionInfo entity) {
        TableResultResponse<VideoContentSelectInfo> list=contentServiceFeign.getVideoPage(entity);
        return list;
    }

    /**
     * 查找视频相详情
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getVideoContent", method = RequestMethod.GET)
    public ObjectRestResponse<VideoContentShow> getVideoContent(@RequestParam Integer contentId) {
        ObjectRestResponse<VideoContentInfo> videoContent = contentServiceFeign.getVideoContent(contentId);
        VideoContentInfo data = videoContent.getData();
        VideoContentShow videoContentShow = new VideoContentShow();
        BeanUtils.copyProperties(data,videoContentShow);
        ObjectRestResponse<VideoContentShow> videoContentShowObjectRestResponse = new ObjectRestResponse<>();
        videoContentShowObjectRestResponse.setData(videoContentShow);
        return videoContentShowObjectRestResponse;
    }

    /**
     * 修改视频
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateVideoContent", method = RequestMethod.POST)
    public ObjectRestResponse<VideoContentShow> updateVideoContent(@RequestBody VideoContentInfo entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        ObjectRestResponse<VideoContentInfo> videoContentInfoObjectRestResponse = contentServiceFeign.updateVideoContent(entity);
        VideoContentInfo data = videoContentInfoObjectRestResponse.getData();
        VideoContentShow videoContentShow = new VideoContentShow();
        BeanUtils.copyProperties(data,videoContentShow);
        ObjectRestResponse<VideoContentShow> videoContentShowObjectRestResponse = new ObjectRestResponse<>();
        videoContentShowObjectRestResponse.setData(videoContentShow);
        return videoContentShowObjectRestResponse;
    }

    /**
     * 添加预览
     * @param contentInfo
     * @return
     */
    @RequestMapping(value = "/previewArticle", method = RequestMethod.POST)
    public ObjectRestResponse<ContentPreviewInfo> previewArticle(@RequestBody ContentInfo contentInfo) {
        ContentPreviewInfo entity=new ContentPreviewInfo();
        Long timeStamp=System.currentTimeMillis() / 1000;
        entity.setCreateTime(timeStamp.intValue());
        entity.setUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setType(NewsEnumsConsts.ContentOfContentType.Article.getKey());
        entity.setJson(JSON.toJSONString(contentInfo));
        return contentServiceFeign.addPreviewArticle(entity);
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> sortContent( @RequestBody Map<String, Object> params) {
        return contentServiceFeign.sortContent(params);
    }

    /**
     * 新增VR
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addVRContent", method = RequestMethod.POST)
    public ObjectRestResponse<VRShow> addVRContent(@RequestBody VRContentAddInfo entity) {
        VRShow vrShow = new VRShow();
        if (entity.getTitle() == null) {
            return getObjectRestResponse("标题不能为空");
        }
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        contentServiceFeign.addVRContent(entity);
        BeanUtils.copyProperties(entity, vrShow);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(vrShow);
        return objectRestResponse;
    }

    /**
     * 得到VR详情
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getVrContent", method = RequestMethod.GET)
    public ObjectRestResponse<VRShow> getVrContent(@RequestParam Integer contentId) {
        VRShow vrShow = new VRShow();
        ObjectRestResponse<VRContentAddInfo> vrContent = contentServiceFeign.getVrContent(contentId);
        VRContentAddInfo data = vrContent.getData();
        BeanUtils.copyProperties(data, vrShow);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(vrShow);
        return objectRestResponse;
    }

    /**
     * 编辑VR
     * @param
     * @return
     */
    @RequestMapping(value = "/updateVR", method = RequestMethod.POST)
    public ObjectRestResponse<VRShow> updateVR(@RequestBody VRContentAddInfo entity) {
        VRShow vrShow = new VRShow();

        entity.setUpdateTime(new Date());

        ObjectRestResponse<VRContentAddInfo> vrContentAddInfoObjectRestResponse = contentServiceFeign.updateVR(entity);
        VRContentAddInfo data = vrContentAddInfoObjectRestResponse.getData();
        BeanUtils.copyProperties(data, vrShow);
        ObjectRestResponse<VRShow> vrShowObjectRestResponse = new ObjectRestResponse<>();
        vrShowObjectRestResponse.setData(vrShow);
        return  vrShowObjectRestResponse;
    }

    /**
     * VR表的分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getVRPage", method = RequestMethod.POST)
    public TableResultResponse<VideoContentSelectInfo> getVRPage(@RequestBody VideoSelectionInfo entity) {

        TableResultResponse<VideoContentSelectInfo> list=contentServiceFeign.getVRPage(entity);
        return list;
    }

    /**
     * 保存到草稿
     * @param
     * @return
     */
    @RequestMapping(value = "/saveVRDraftBox", method = RequestMethod.POST)
    public ObjectRestResponse saveVRDraftBox(@RequestBody VRContentAddInfo entity) {
        //保存到草稿箱
        if (entity.getTitle() == null) {
            return getObjectRestResponse("标题不能为空");
        }
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        //进入我的 不进审核
        entity.setIsCheck(0);
        //待审核
        entity.setCheckStatus(3);
        contentServiceFeign.addVRContent(entity);
        String meg = "已经保存到草稿箱";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(meg);
        return stringObjectRestResponse;

    }

     /* 查询日志信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentLog", method = RequestMethod.GET)
    public ObjectRestResponse<List<ContentArticleLogInfo>> getContentLog(@RequestParam("id") Integer id) {
        List<ContentArticleLogInfo> list = contentServiceFeign.getContentLog(id);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(list);
        return objectRestResponse;
    }

    /**
     * 我的内容 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getMyContentPage",method = RequestMethod.POST)
    public TableResultResponse<MyContentInfo> getMyContentPage(@RequestBody MyContentInfo entity){
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return  contentServiceFeign.getMyContentPage(entity);
    }

}
