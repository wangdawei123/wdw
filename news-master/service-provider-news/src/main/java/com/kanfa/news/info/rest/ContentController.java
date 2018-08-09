package com.kanfa.news.info.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.constant.web.WebCommonParams;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.*;
import com.kanfa.news.info.biz.*;
import com.kanfa.news.info.entity.*;
import com.kanfa.news.info.mongodb.biz.ViewContentService;
import com.kanfa.news.info.mongodb.entity.ContentArticleLogInfo;
import com.kanfa.news.info.mongodb.entity.ViewContent;
import com.kanfa.news.info.vo.admin.activity.ActivityWeb;
import com.kanfa.news.info.vo.admin.comment.CommentWebInfo;
import com.kanfa.news.info.vo.admin.info.*;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountContentInfo;
import com.kanfa.news.info.vo.admin.my.MyContentInfo;
import com.kanfa.news.info.vo.admin.video.*;
import com.kanfa.news.info.vo.admin.vr.VRContentAddInfo;
import com.kanfa.news.info.vo.admin.web.ContentDetailBindInfo;
import com.kanfa.news.info.vo.admin.web.ContentDetailInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/content")
public class ContentController extends BaseController<ContentBiz, Content> {

    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private SpecialCatalogBiz specialCatalogBiz;
    @Autowired
    private ContentSpecialBiz contentSpecialBiz;
    @Autowired
    private ChannelFocusBiz channelFocusBiz;
    @Autowired
    private ChannelBiz channelBiz;
    @Autowired
    private ChannelContentBiz channelContentBiz;
    @Autowired
    private VideoTagBiz videoTagBiz;
    @Autowired
    private KpiCountBiz kpiCountBiz;
    @Autowired
    private ContentImageBiz contentImageBiz;
    @Autowired
    private CommentBiz commentBiz;
    @Autowired
    private FavBiz favBiz;
    @Autowired
    private LoveBiz loveBiz;
    @Autowired
    private ContentBroadcastBindBiz contentBroadcastBindBiz;
    @Autowired
    private SettingBiz settingBiz;
    @Autowired
    private ContentImageGroupBiz contentImageGroupBiz;
    @Autowired
    private ActivityContentBindBiz activityContentBindBiz;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ContentVideoBiz contentVideoBiz;
    @Autowired
    private ContentVrBiz contentVrBiz;
    @Autowired
    private ViewContentService viewContentService;
    @Autowired
    private VideoSourceBiz videoSourceBiz;
    @Autowired
    private BaseUserBiz baseUserBiz;
    @Autowired
    private VideoAlbumBiz videoAlbumBiz;
    @Autowired
    private VideoAlbumBindBiz videoAlbumBindBiz;
    @Autowired
    private ContentArticleBiz contentArticleBiz;

    /**
     * 微博登陆的id
     */
    @Value("${pc.weibo.client_id}")
    private String client_id;

    //个人浏览redis
    private final String IS_VIEW="is_view_";

    @RequestMapping(value = "/addContent", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> addContent(@RequestBody ContentInfo entity) {
        ContentInfo info = contentBiz.addContent(entity);
        ObjectRestResponse<ContentInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public TableResultResponse<ContentInfo> getContentPage(@RequestBody ContentInfo entity) {
        return contentBiz.getPage(entity);
    }

    /**
     * 增加阅读量
     */
    @RequestMapping(value = "/updateViews",method = RequestMethod.GET)
    Integer updateViews(@RequestParam(value = "id") Integer id){
        //增加阅读数量
       contentBiz.updateVideoViews(id ,1);
       return 1;
    }


    /**
     * 更新文章专题
     * @param contentInfo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> update(@RequestBody ContentInfo contentInfo) {
        Integer update = contentBiz.update(contentInfo);
        ObjectRestResponse<ContentInfo> restResponse = new ObjectRestResponse<>();
        if (update <= 0) {
            restResponse.setMessage("更新失败");
            restResponse.setRel(true);
            return restResponse;
        }
        restResponse.setData(contentInfo);
        return restResponse;
    }

    /**
     * 更新文章专题
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateContentSubject",method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> updateContentSubject(@RequestBody ContentInfo entity){
        Integer update = contentBiz.updateContentSubject(entity);
        ObjectRestResponse<ContentInfo> restResponse = new ObjectRestResponse<>();
        if (update <= 0) {
            restResponse.setMessage("更新失败");
            restResponse.setRel(true);
            return restResponse;
        }
        restResponse.setData(entity);
        return restResponse;
    }

    /**
     * 異步請求獲取內容
     *
     * @return
     */
    @RequestMapping(value = "/getContentActive", method = RequestMethod.POST)
    public List<ContentImageInfo> getContentActive(@RequestBody ContentImageInfo content) {
        List<ContentImageInfo> list = contentBiz.getPageentityPC(content);
        return list;
    }

    /**
     * web端请求首页
     * @param content
     * @return
     */
    @RequestMapping(value = "/pagePC", method = RequestMethod.POST)
    public Map<String, Object> getContentPagePC(@RequestBody ContentImageInfo content) {
        //查詢輪播圖
        ChannelFocusInfo entity = new ChannelFocusInfo();
        //'状态，1：正常，0：删除',
        entity.setIsDelete(1);
        entity.setChannelId(content.getChannelId());
        List<ChannelFocusInfo> list = channelFocusBiz.getFocusAll(entity);

        //查詢頻道
        Channel channel = new Channel();
        //频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯',
        channel.setCategory(4);
        //发布状态。1：发布，0：未发布'
        channel.setIsPublish(1);
        //状态，1：正常，0：删除'
        channel.setChannelStatus(1);
        List<ChannelInfo> channelList = channelBiz.selectChannlList(channel ,content.getChannelId());

        //查詢內容-多錶
        if (content.getChannelId() != null) {
            //当根据频道id查询时，第一次请求查询10条
            content.setLimit(10);
        }
        //给一个初始值，每页展示10条数据
        content.setLimit(7);
        //给一个初始值，当前页是第1页--这里把page当第几条开始使用
        content.setPage(0);
        //是否是删除状态，1.正常 0.删除
        content.setContentState(1);
        //审核状态 0.待审核  1.通过审核 3.审核不通过
        content.setCheckStatus(1);
        List<ContentImageInfo> contentList = contentBiz.getPageentityPC(content);

        //
        List<ContentImageInfo> contentImageInfos = changeList(contentList);

        Map<String, Object> map = new HashMap<>(6);
        map.put("content", contentImageInfos);
        map.put("channel", channelList);
        map.put("focus", list);
        return map;
    }


    /**
     * 用于在上面的方法中处理数据重复的方法
     */
    private List<ContentImageInfo> changeList(List<ContentImageInfo> rows) {
        //创建一个新的集合，放ContentImageInfo
        HashMap<Integer, ContentImageInfo> map = new HashMap<>(16);
        Iterator<ContentImageInfo> it = rows.iterator();
        while (it.hasNext()) {
            ContentImageInfo c = it.next();
            if (c.getContentType() == 3) {
                if (map.containsKey(c.getId())) {
                    ContentImageInfo contentImageInfo = map.get(c.getId());
                    contentImageInfo.getImageList().add(c.getImageGroup());
                    it.remove();
                } else {
                    map.put(c.getId(), c);
                    c.getImageList().add(c.getImageGroup());
                }
            }
        }
        return rows;
    }

    @RequestMapping(value = "/addVideoContent", method = RequestMethod.POST)
    public ObjectRestResponse<VideoContentInfo> addVideoContent(@RequestBody VideoContentInfo entity) {
        VideoContentInfo info = contentBiz.addVideoContentInfo(entity);
        ObjectRestResponse<VideoContentInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 已回收列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectDeletes", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<RecycleBinInfo> selectDeletes(@RequestParam Integer page,
                                                             @RequestParam Integer limit) {
        //查询列表数据
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return contentBiz.selectDeletes(query);
    }

    /**
     * 按标题搜索已回收列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectSearchDeletes", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<RecycleBinInfo> selectSearchDeletes(@RequestParam Integer page,
                                                                   @RequestParam Integer limit,
                                                                   @RequestParam String title) {
        //根据标题搜索
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return contentBiz.selectSearchDeletes(query, title);
    }

    /**
     * web页--内容表和内容详情表一起查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/content_Article/{id}", method = RequestMethod.GET)
    public ContentInfo getContentArticle(@PathVariable(name = "id") int id) {
        return contentBiz.getContentArticle(id);
    }

    /**
     * 获取热门--web
     */
    @RequestMapping(value = "/hotgetlist", method = RequestMethod.GET)
    public List<ContentInfo> hotgetlist(@RequestParam("contentType") Integer contentType){
        return contentBiz.hotgetlist(contentType);
    }

    /**
     * web页--模糊搜索
     * @param searchKey
     * @return
     */
    @RequestMapping(value = "/searchKey",method = RequestMethod.GET)
    public List<ContentInfo> searchKey(@RequestParam("searchKey") String searchKey){
        List<ContentInfo> list = contentBiz.searchKey(searchKey);
        for(ContentInfo content : list){
            String title = content.getTitle();
            String newTitle = title.replace(searchKey,"<b style='color:red;'>"+searchKey+"</b>");
            content.setTitle(newTitle);
        }

        return list;
    }

    /**
     * web页--模糊搜索--下拉页面异步加载
     * @param searchKey
     * @return
     */
    @RequestMapping(value = "/searchLoad",method = RequestMethod.GET)
    public List<ContentImageInfo> searchLoad(@RequestParam("searchKey") String searchKey,
                                       @RequestParam(name = "offset") Integer offset,
                                       @RequestParam(name = "limit") Integer limit){
        List<ContentImageInfo> list = contentBiz.searchLoad(searchKey, offset ,limit);
        for(ContentImageInfo content : list){
            String title = content.getTitle();
            String newTitle = title.replace(searchKey,"<b style='color:red;'>"+searchKey+"</b>");
            content.setTitle(newTitle);
        }

        return list;
    }

    /**
     * web--内容表和图集表一起查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentImageById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ContentImageInfo> getContentImageById(@PathVariable int id) {
        return contentBiz.getContentImageById(id);
    }

    /**
     * web--内容表和视频表一起查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentVideoById/{id}", method = RequestMethod.GET)
    public ContentInfo getContentVideoById(@PathVariable int id) {
        return contentBiz.getContentVideoById(id);
    }


    @RequestMapping(value = "/getContentPageForVideo", method = RequestMethod.POST)
    public TableResultResponse<ContentInfo> getContentPageForVideo(@RequestBody ContentInfo contentInfo) {
        return contentBiz.getContentPageForVideo(contentInfo);
    }

    /**
     * 添加专题
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addContentSubject", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> addContentSubject(@RequestBody ContentInfo entity) {
        ContentInfo info = contentBiz.addContentSubject(entity);
        ObjectRestResponse<ContentInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 编辑查询包含文章详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContent", method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContent(@RequestParam(name = "id") Integer id) {
        ObjectRestResponse<ContentInfo> resp = new ObjectRestResponse<>();
        ContentInfo contentInfo = contentBiz.getContent(id);

        ContentInfo contentInfo1 = contentBiz.getContentArticleLog(id);

        if(null!=contentInfo1){
            if(StringUtils.isNotEmpty(contentInfo1.getTitle())){
                contentInfo.setTitle(contentInfo1.getTitle());
            }
            if(StringUtils.isNotEmpty(contentInfo1.getContentDetail())){
                contentInfo.setContentDetail(contentInfo1.getContentDetail());
            }
        }
        //视频对象
        VideoDemand videoDemand = new VideoDemand();
        videoDemand.setId(contentInfo.getDid());
        videoDemand.setUrl(contentInfo.getVideo());
        contentInfo.setVideoDemand(videoDemand);
        contentInfo.setVideo(null);
        contentInfo.setDid(null);
        //频道选中
        getSelectChannel(id,contentInfo);
        //政法先锋子频道选中
        ActivityContentBind activityContentBind=new ActivityContentBind();
        activityContentBind.setContentId(id);
        List<ActivityContentBind> activityContentBinds = activityContentBindBiz.selectList(activityContentBind);
        List<Integer> childChannelIdList=new ArrayList<>(activityContentBinds.size());
        if(activityContentBinds!=null && activityContentBinds.size()>0){
            for (ActivityContentBind activityContentBind1 : activityContentBinds) {
                childChannelIdList.add(activityContentBind1.getActivityId());
            }
        }
        contentInfo.setChildChannelIdList(childChannelIdList);
        //标签选中
        getSelectTag(id, contentInfo);
        //编辑人.createId
        //记者选中
        List<KpiCountContentInfo> kpiCountContentInfoList = getKpiCountContentInfos(id);
        contentInfo.setKpiCountContentInfoList(kpiCountContentInfoList);
        //查询绑定内容
        //地理位置集合
        ContentLocationInfo contentLocationInfo=new ContentLocationInfo();
        contentLocationInfo.setCid(contentInfo.getId());
        List<LocationInfo> locationList=contentBiz.getContentList(contentLocationInfo);
        contentInfo.setLocationList(locationList);
        setDefaultValue(contentInfo);
        resp.setData(contentInfo);
        return resp;
    }

    //赋予默认值
    private void setDefaultValue(ContentInfo contentInfo) {
        if(contentInfo.getAppChannelIdList()==null||contentInfo.getAppChannelIdList().size()<=0){
            contentInfo.setAppChannelIdList(new ArrayList<>());
        }
        if(contentInfo.getPcChannelIdList()==null||contentInfo.getPcChannelIdList().size()<=0){
            contentInfo.setPcChannelIdList(new ArrayList<>());
        }
        if(contentInfo.getChildChannelIdList()==null||contentInfo.getChildChannelIdList().size()<=0){
            contentInfo.setChildChannelIdList(new ArrayList<>());
        }
        if(contentInfo.getTagNameList()==null||contentInfo.getTagNameList().size()<=0){
            contentInfo.setTagNameList(new ArrayList<>());
        }
        if(contentInfo.getUserList()==null||contentInfo.getUserList().size()<=0){
            contentInfo.setUserList(new ArrayList<>());
        }
        if(contentInfo.getLocationList()==null||contentInfo.getLocationList().size()<=0){
            contentInfo.setLocationList(new ArrayList<>());
        }
        if(StringUtils.isEmpty(contentInfo.getSource())){
            contentInfo.setSource("");
        }
        if(StringUtils.isEmpty(contentInfo.getContentDetail())){
            contentInfo.setContentDetail("");
        }
    }

    private List<KpiCountContentInfo> getKpiCountContentInfos(@RequestParam(name = "id") Integer id) {
        KpiCount kpiCount=new KpiCount();
        kpiCount.setTypeId(id);
        List<KpiCount> kpiCounts = kpiCountBiz.selectList(kpiCount);
        List<KpiCountContentInfo> kpiCountContentInfoList=new ArrayList<>(kpiCounts.size());
        if(kpiCounts!=null&&kpiCounts.size()>0){
            for (KpiCount count : kpiCounts) {
                KpiCountContentInfo kpiCountContentInfo=new KpiCountContentInfo();
//                kpiCountContentInfo.setId(count.getId());
                kpiCountContentInfo.setUid(count.getUid());
                kpiCountContentInfo.setArticleType(count.getArticleType());
                kpiCountContentInfoList.add(kpiCountContentInfo);
            }
        }
        return kpiCountContentInfoList;
    }

    private void getSelectChannel(@RequestParam(name = "id") Integer id, ContentInfo contentInfo) {
        List<ChannelInfo> channelInfos = channelContentBiz.selectListSelected(id);
        if(channelInfos!=null&&channelInfos.size()>0){
            List<Integer> appChannelList=new ArrayList<>(channelInfos.size());
            List<Integer> pcChannelList=new ArrayList<>(16);
            for (ChannelInfo channelInfo : channelInfos) {
                if(channelInfo.getCategory().equals(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key())){
                    appChannelList.add(channelInfo.getId());
                }else if (channelInfo.getCategory().equals(NewsEnumsConsts.ChannelOfCategory.INFO_PC.key())){
                    pcChannelList.add(channelInfo.getId());
                }
            }
            contentInfo.setAppChannelIdList(appChannelList);
            contentInfo.setPcChannelIdList(pcChannelList);
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDelete(@RequestBody List<Integer> ids,@RequestParam("ip") String ip,@RequestParam("uid") Integer uid) {
        Integer deleteCount = contentBiz.batchDelete(ids,ip,uid);
        ObjectRestResponse<Integer> restResponse = new ObjectRestResponse<>();
        if (deleteCount <= 0) {
            restResponse.setRel(true);
            restResponse.setData(0);
            restResponse.setMessage("刪除失败");
        }
        restResponse.setData(deleteCount);
        return restResponse;
    }

    /**
     * 查询专题包含子目录 频道+记者
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentSubject",method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContentSubject(@RequestParam("id") Integer id){
        ObjectRestResponse<ContentInfo> restResponse=new ObjectRestResponse<>();
        ContentInfo contentInfo = contentBiz.selectSubjectById(id);
        contentInfo.setCategory(null);
        contentInfo.setLongTitle(null);
        //查询频道绑定
        getSelectChannel(id,contentInfo);
        //查询标签
        getSelectTag(id,contentInfo);
        //记者选中
        List<KpiCountContentInfo> kpiCountContentInfoList = getKpiCountContentInfos(id);
        contentInfo.setKpiCountContentInfoList(kpiCountContentInfoList);
        //查询子目录
        SpecialCatalog specialCatalog = new SpecialCatalog();
        specialCatalog.setCid(id);
        List<SubjectCatalogInfo> specialCatalogs = specialCatalogBiz.selectSpecialCatalogList(specialCatalog);
        //封装目录关联内容
        if (specialCatalogs != null && specialCatalogs.size() > 0) {
            //子目录
            for (SubjectCatalogInfo catalogInfo : specialCatalogs) {
                ContentSpecial contentSpecial=new ContentSpecial();
                contentSpecial.setCatalogId(catalogInfo.getId());
                List<ContentInfo> contentInfoList = contentSpecialBiz.selectListSpecialContent(contentSpecial);
                catalogInfo.setContentInfoList(contentInfoList);
            }
        }
        contentInfo.setChildCatalogList(specialCatalogs);
        restResponse.setData(contentInfo);
        return restResponse;
    }

    /**
     * 查询内容信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentForBind", method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContentForBind(@RequestParam("id") Integer id) {
        ObjectRestResponse<ContentInfo> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(contentBiz.getContentForBind(id));
        return objectRestResponse;
    }

    /**
     * 视频列表的分页及查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getVideoPage", method = RequestMethod.POST)
    public TableResultResponse<VideoContentSelectInfo> getVideoPage(@RequestBody VideoSelectionInfo entity) {
        return contentBiz.getVideoPage(entity);
    }

    /**
     * get一个视频的信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getVideoContent", method = RequestMethod.GET)
    public ObjectRestResponse<VideoContentInfo> getVideoContent(@RequestParam("contentId") Integer contentId) {
        VideoContentInfo info = contentBiz.getVideoContent(contentId);
        ObjectRestResponse<VideoContentInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 修改一个视频的信息
     * @param
     * @return
     */
    @RequestMapping(value = "/updateVideoContent", method = RequestMethod.POST)
    public ObjectRestResponse<VideoContentInfo> updateVideoContent(@RequestBody VideoContentInfo entity) {
        VideoContentInfo info = contentBiz.updateVideoContent(entity);
        ObjectRestResponse<VideoContentInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    @RequestMapping(value = "/getContentNotCheckPage",method = RequestMethod.POST)
    public TableResultResponse<ContentInfo> getContentNotCheckPage(@RequestBody ContentInfo contentInfo) {
        return contentBiz.getContentNotCheckPage(contentInfo);
    }

    /**
     * 更新视频播放量
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateVideoViews", method = RequestMethod.POST)
    public ObjectRestResponse updateVideoViews(@RequestParam Integer id,
                                               @RequestParam Integer platform) {
        contentBiz.updateVideoViews(id, platform);
        return new ObjectRestResponse<>();
    }

    /**
     * 新增图集
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addContentAtlas", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> addContentAtlas(@RequestBody ContentInfo entity) {
        ContentInfo info = contentBiz.addContentAtlas(entity);
        ObjectRestResponse<ContentInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 更新图集
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateContentAtlas",method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> updateContentAtlas(@RequestBody ContentInfo entity){
        Integer update = contentBiz.updateContentAtlas(entity);
        ObjectRestResponse<ContentInfo> restResponse = new ObjectRestResponse<>();
        if (update <= 0) {
            restResponse.setMessage("更新失败");
            restResponse.setRel(true);
            return restResponse;
        }
        restResponse.setData(entity);
        return restResponse;
    }

    /**
     * 查询图集
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentAtlas",method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContentAtlas(@RequestParam("id") Integer id){
        ObjectRestResponse<ContentInfo> resp = new ObjectRestResponse<>();
        //查询
        ContentInfo contentInfo = contentBiz.getContent(id);
        //频道选中
        getSelectChannel(id, contentInfo);
        //标签选中
        getSelectTag(id, contentInfo);
        //记者选中
        List<KpiCountContentInfo> kpiCountContentInfoList = getKpiCountContentInfos(id);
        contentInfo.setKpiCountContentInfoList(kpiCountContentInfoList);
        //查询图集
        ContentImage contentImage = contentImageBiz.selectById(id);
        if(contentImage!=null){
            contentInfo.setDescType(contentImage.getDescType());
        }
        //图集组
        ContentImageGroup contentImageGroup = new ContentImageGroup();
        contentImageGroup.setCid(id);
        List<ContentImageGroup> contentImageGroups = contentImageGroupBiz.getImageGroupList(contentImageGroup);
        List<ImageInfo> imageInfoList=new ArrayList<>();
        for (ContentImageGroup imageGroup : contentImageGroups) {
            ImageInfo imageInfo=new ImageInfo();
            BeanUtils.copyProperties(imageGroup,imageInfo);
            imageInfoList.add(imageInfo);
        }
        contentInfo.setImageInfoList(imageInfoList);
        resp.setData(contentInfo);
        return resp;
    }

    private void getSelectTag(@RequestParam("id") Integer id, ContentInfo contentInfo) {
        List<VideoTag> videoTagList=videoTagBiz.getTagListByConentId(id);
        if(videoTagList!=null&&videoTagList.size()>0){
            List<String> tagNameList=new ArrayList<>(videoTagList.size());
            for (VideoTag videoTag : videoTagList) {
                tagNameList.add(videoTag.getName());
            }
            contentInfo.setTagNameList(tagNameList);
        }
    }

    /**
     * 新增活动
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addContentActivity",method = RequestMethod.POST)
    public ObjectRestResponse<ActivityInfo> addContentActivity(@RequestBody ActivityInfo entity){
        ActivityInfo info = contentBiz.addContentActivity(entity);
        ObjectRestResponse<ActivityInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 更新活动
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateContentActivity",method = RequestMethod.POST)
    public ObjectRestResponse<ActivityInfo> updateContentActivity(@RequestBody ActivityInfo entity){
        Integer update = contentBiz.updateContentActivity(entity);
        ObjectRestResponse<ActivityInfo> restResponse = new ObjectRestResponse<>();
        if (update <= 0) {
            restResponse.setMessage("更新失败");
            restResponse.setRel(true);
            return restResponse;
        }
        restResponse.setData(entity);
        return restResponse;
    }

    /**
     * 查询活动
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentActivity",method = RequestMethod.GET)
    public ObjectRestResponse<ActivityInfo> getContentActivity(@RequestParam("id") Integer id){
        ObjectRestResponse<ActivityInfo> resp = new ObjectRestResponse<>();
        //查询
        ContentInfo content = contentBiz.selectActivityById(id);
        ActivityInfo activityInfo=new ActivityInfo();
        BeanUtils.copyProperties(content,activityInfo);
        //频道选中
        List<ChannelInfo> channelInfos = channelContentBiz.selectListSelected(id);
        if(channelInfos!=null&&channelInfos.size()>0){
            List<Integer> appChannelList=new ArrayList<>(channelInfos.size());
            List<Integer> pcChannelList=new ArrayList<>(16);
            for (ChannelInfo channelInfo : channelInfos) {
                if(channelInfo.getCategory().equals(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key())){
                    appChannelList.add(channelInfo.getId());
                }
            }
            activityInfo.setAppChannelIdList(appChannelList);
        }
        resp.setData(activityInfo);
        return resp;
    }

    @RequestMapping(value = "/saveMogoLocation",method = RequestMethod.POST)
    public ObjectRestResponse saveMogoLocation(@RequestBody LocationInfo locationInfo){
        return contentBiz.saveMogoLocation(locationInfo);
    }

    /**
     * 新增VR
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addVRContent", method = RequestMethod.POST)
    public ObjectRestResponse<VRContentAddInfo> addVRContent(@RequestBody VRContentAddInfo entity) {
        VRContentAddInfo info = contentBiz.addVRContent(entity);
        ObjectRestResponse<VRContentAddInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 得到VR详情
     *
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getVrContent", method = RequestMethod.GET)
    public ObjectRestResponse<VRContentAddInfo> getVrContent(@RequestParam("contentId") Integer contentId) {
        VRContentAddInfo info = contentBiz.getVrContent(contentId);
        ObjectRestResponse<VRContentAddInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 编辑VR
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateVR", method = RequestMethod.POST)
    public ObjectRestResponse<VRContentAddInfo> updateVR(@RequestBody VRContentAddInfo entity) {
        VRContentAddInfo info = contentBiz.updateVR(entity);
        ObjectRestResponse<VRContentAddInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * VR的分页及查询
     * @param
     * @return
     */
    @RequestMapping(value = "/getVRPage", method = RequestMethod.POST)
    public TableResultResponse<VideoContentSelectInfo> getVRPage(@RequestBody VideoSelectionInfo entity) {
        return contentBiz.getVRPage(entity);
    }

    /**
     * 查询日志信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContentLog",method = RequestMethod.GET)
    public List<ContentArticleLogInfo> getContentLog(@RequestParam("id") Integer id){
        if(id==null){
            return null;
        }
        return contentBiz.getContentLog(id);
    }

    /**
     * 模糊查询文章视频等
     * @param params
     * @return
     */
    @RequestMapping(value = "/getContentPageForMessage",method = RequestMethod.GET)
    public TableResultResponse<ContentInfo> getContentPageForMessage(@RequestParam Map<String,Object> params){
        Integer targetType = Integer.valueOf(params.get("targetType").toString());
        List<Integer> notIds= new ArrayList<>(16);
        if(targetType.equals(NewsEnumsConsts.MessageOfTargetType.Video.getKey()) || targetType.equals(NewsEnumsConsts.MessageOfTargetType.Live.getKey())){
            notIds.add(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
            notIds.add(NewsEnumsConsts.ChannelOfCategory.VIDEO_APP.key());
        }else if(targetType.equals(NewsEnumsConsts.MessageOfTargetType.VR.getKey())){
            notIds.add(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
            notIds.add(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
        }else {
            notIds.add(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        }
        ContentInfo contentInfo=new ContentInfo();
        contentInfo.setNotInIds(notIds);
        contentInfo.setTitle(params.get("title").toString());
        contentInfo.setPage(Integer.valueOf(params.get("page").toString()));
        contentInfo.setLimit(Integer.valueOf(params.get("limit").toString()));
        contentInfo.setContentType(targetType);
        return contentBiz.getContentPageForMessage(contentInfo);
    }

    /**
     * 专题子目录搜索
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getContentPageForBind",method = RequestMethod.POST)
    public TableResultResponse<ContentInfo> getContentPageForBind(@RequestBody ContentInfo entity){
        return contentBiz.getContentPageForBind(entity);
    }

    /**
     * 审核通过与驳回
     * @param content
     * @return
     */
    @RequestMapping(value = "/checkPassContent",method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> checkPassContent(@RequestBody ContentInfo content){
        ObjectRestResponse<ContentInfo> objectRestResponse = new ObjectRestResponse<>();
        ContentInfo content1 = contentBiz.updateCheckPassContent(content);
        if(content1==null){
            objectRestResponse.setStatus(506);
            objectRestResponse.setMessage("审核失败");
            objectRestResponse.setRel(true);
            return objectRestResponse;
        }
        objectRestResponse.setData(content1);
        return objectRestResponse;
    }

    /**
     * 我的内容 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getMyContentPage", method = RequestMethod.POST)
    public TableResultResponse<MyContentInfo> getMyContentPage(@RequestBody MyContentInfo entity) {
        return contentBiz.getMyContentPage(entity);
    }

     /** 批量删除
     * @param contentListInfo
     * @return
     */
    @RequestMapping(value = "/batchDeleteByList",method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDeleteByList(@RequestBody ContentListInfo contentListInfo){
        ObjectRestResponse<Integer> objectRestResponse=new ObjectRestResponse<>();
        objectRestResponse.setData(contentBiz.batchDeleteByList(contentListInfo));
        return objectRestResponse;
    }


    @GetMapping("/addContentsListDataForEs")
    public Map<String ,Object> addContentsListDataForEs(@RequestParam Map<String ,Object> map){
        return contentBiz.addContentsListDataForEs(map);
    }

    @GetMapping("/listIds")
    public List<Integer> listIds(@RequestParam Integer start,@RequestParam Integer size){
        return contentBiz.selectListId(start,size);
    }

    /**
     * 查询内容详情
     * @param cid
     * @param cate
     * @param fontsize
     * @param ,  @RequestParam(name = "original",required = false) String original,  @RequestParam(name = "activity",required = false) String activity
     * @param devid
     * @return
     */
    @RequestMapping(value = "/getNewIndex",method = RequestMethod.GET)
    public  ContentDetailInfo getNewIndex(@RequestParam(name = "cid") Integer cid, @RequestParam(name = "cate") Integer cate, @RequestParam(name = "fontsize",required = false)  String fontsize,  @RequestParam(name = "devid", required=false) String devid,@RequestParam(name = "uid", required=false) Integer uid){
        //文章基本详情 1.确定是否发布,checkStatus,contentState,isPublish
        ContentInfo contentArticle = contentBiz.getNewIndex(cid);
        if(contentArticle==null){
            return null;
        }
        ContentDetailInfo contentDetailInfo = new ContentDetailInfo();
        BeanUtils.copyProperties(contentArticle,contentDetailInfo);
        contentDetailInfo.setCreateTime(DateHandler.dateToString(contentArticle.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        //视频信息
        List<Map<String,Object>> extent=new ArrayList<>(1);
        Map<String,Object> video=new HashMap<>(1);
        if(!(null==(contentArticle.getVideo()))){
            video.put("video",contentArticle.getVideo());
        }else {
            video.put("video","");
        }
        if(StringUtils.isNotEmpty(contentArticle.getVideo())){
            video.put("video_img",contentArticle.getVideoImage());
        }else {
            video.put("video_img","http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png");
        }
        extent.add(video);
        contentDetailInfo.setExtent(extent);
        //是否收藏
        if(uid!=null){
            Fav fav = new Fav();
            fav.setCid(cid);
            fav.setUid(uid);
            List<Fav> favs = favBiz.selectList(fav);
            if(favs!=null && favs.size()>0){
                contentDetailInfo.setIsFaved(1);
            }else {
                contentDetailInfo.setIsFaved(0);
            }
        }
        //评论
        List<Map<String, Object>> commentList = commentBiz.selectComment(cid, 1, 5, 0, 0, uid==null?0:uid);
        contentDetailInfo.setCommentList(commentList);
        //评论设置
        String value = settingBiz.getByName("comment_tourist");
        //1：先审后发，2：先发后审
        String comment_ops = settingBiz.getByName("comment_ops");
        Integer commentCount=0;
        if(StringUtils.isNotEmpty(comment_ops) && Integer.valueOf(comment_ops)==0){
            //评论总数
            commentCount = commentBiz.getCountForDetail(cid,0,uid,1);
        }else {
            commentCount = commentBiz.getCountForDetail(cid,0,uid,null);
        }
        contentDetailInfo.setCommentCount(commentCount);
        //分享
        Map<String,Object> share=getShareMap(cid,contentArticle);
        contentDetailInfo.setShare(share);
        //绑定内容//绑定集合
        List<ContentInfo> contentBindList = contentBroadcastBindBiz.getContentBroadBindByCid(cid);
        for (ContentInfo contentInfo : contentBindList) {
            contentInfo.setUrl(contentBiz.getDetailUrlSu(contentInfo.getId(),contentArticle.getContentType(),contentArticle.getCategory()));
        }
        contentDetailInfo.setBindList(contentBindList);
        //mongo中数据
        if(NewsEnumsConsts.ContentOfContentStyle.judgment.getKey().equals(contentDetailInfo.getContentStyle())
                ||NewsEnumsConsts.ContentOfContentStyle.notice.getKey().equals(contentDetailInfo.getContentStyle())){
            String sourceTile = contentBiz.getSiteContentDetail(cid);
            contentDetailInfo.setSourceTile(sourceTile);
        }
        //微信数据
        Map<String, Object> signPackage = getWeiXinShareMap(null);
        contentDetailInfo.setSignPackage(signPackage);
        return contentDetailInfo;
    }

    /**
     * 分享内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/getNewDetail",method = RequestMethod.GET)
    public ObjectRestResponse<ContentDetailInfo> getNewDetail(@RequestParam(name = "id") Integer id){
        //内容
        ContentInfo newIndex = contentBiz.getNewIndex(id);
        if(newIndex==null){
            Assert.isNull("ContentDetailInfo","未找到相应的内容");
        }
        ContentDetailInfo contentDetailInfo = new ContentDetailInfo();
        BeanUtils.copyProperties(newIndex,contentDetailInfo);
        contentDetailInfo.setCreateTime(DateHandler.dateToString(newIndex.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        String appUrl = contentBiz.getDetailUrlSu(id, newIndex.getContentType(), newIndex.getCategory());
        contentDetailInfo.setAppUrl(appUrl);
        //视频信息
        List<Map<String,Object>> extent=new ArrayList<>(1);
        Map<String,Object> video=new HashMap<>(1);
        video.put("video",newIndex.getVideo());
        if(StringUtils.isNotEmpty(newIndex.getVideo())){
            video.put("video_img",newIndex.getVideoImage());
        }else {
            video.put("video_img","http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png");
        }
        extent.add(video);
        contentDetailInfo.setExtent(extent);
        //分享
        Map<String, Object> share = getShareMap(id, newIndex);
        contentDetailInfo.setShare(share);
        //绑定内容
        List<ContentInfo> list = contentBroadcastBindBiz.getContentBindShareList(id);
        List<ContentDetailBindInfo> contentDetailBindInfoList = new ArrayList<>(5);
        if(list!=null && list.size()>0){
            for (ContentInfo contentInfo : list) {
                ContentDetailBindInfo contentDetailBindInfo=new ContentDetailBindInfo();
                BeanUtils.copyProperties(contentInfo,contentDetailBindInfo);
                contentDetailBindInfo.setCreateTime(DateHandler.dateToString(contentInfo.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                contentDetailBindInfo.setAppUrl(contentBiz.getDetailUrlSu(contentInfo.getId(),contentInfo.getContentType(),contentInfo.getCategory()));
                Map<String, String> param = new HashMap<>(1);
                param.put("locf","out");
                contentDetailBindInfo.setUrl(UrlAddParamUtil.getRqstUrl(contentBiz.getDetailUrlSu(contentInfo.getId(),contentInfo.getContentType(),contentInfo.getCategory()),param));
                contentDetailBindInfo.setThumbimg(contentInfo.getImage());
                contentDetailBindInfoList.add(contentDetailBindInfo);
            }
        }
        contentDetailInfo.setBindShareList(contentDetailBindInfoList);
        //判断活动//活动集合
        List<Map<String,Object>> actvitys=new ArrayList<>(2);
        Map<String,Object> map=new HashMap<>(2);
        map.put("ico","https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
        map.put("title","欲知后事如何？请看APP分解。");
        actvitys.add(map);
        contentDetailInfo.setActivitys(actvitys);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(contentDetailInfo);
        return objectRestResponse;
    }

    private Map<String, Object> getShareMap(Integer id, ContentInfo newIndex) {
        Map<String,Object> share=new HashMap<>(16);
        String title = newIndex.getTitle();
        String longTitle = newIndex.getLongTitle();
        share.put("title",title);
        if(StringUtils.isNotEmpty(longTitle)){
            if(longTitle.length()>40){
                share.put("desc",longTitle.substring(0,40));
            }else {
                share.put("desc",longTitle);
            }
        }else {
            if(title.length()>40){
                share.put("desc",title.substring(0,40));
            }else {
                share.put("desc",title);
            }
        }
        //locf'=>'out'
        Map<String, String> param = new HashMap<>(1);
        param.put("locf","out");
        share.put("link", UrlAddParamUtil.getRqstUrl(contentBiz.getShareUrlSu(id,newIndex.getContentType(),newIndex.getCategory()),param));
        share.put("imgUrl",StringUtils.isNotEmpty(newIndex.getImage())?newIndex.getImage():"http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png");
        return share;
    }

    @RequestMapping(value = "/getWeiXinShareMap",method = RequestMethod.GET)
    public Map<String, Object> getWeiXinShareMap(@RequestParam(value = "url") String url) {
        Long timestamp = System.currentTimeMillis();
        String nonceStr = UUIDUtils.generateShortUuid();
        String o = (String) redisTemplate.opsForValue().get(LiveCommonEnum.APP_WEIXIN_TICKET);
        String ticket = null;
        if(o != null){
            JSONObject object = JSON.parseObject(o);
            ticket = (String) object.get("ticket");
        }
        String params = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = StringHelper.sha1(params);
        Map<String, Object> signPackage = new HashMap<>(9);
        signPackage.put("appId", client_id);
        signPackage.put("nonceStr", nonceStr);
        signPackage.put("timestamp", timestamp);
        signPackage.put("signature", signature);
        return signPackage;
    }

    /** 视频详情页h5分享页接口
     * @param
     * @return
     */
    @RequestMapping(value = "/getVideoDetail",method = RequestMethod.POST)
    public ObjectRestResponse getVideoDetail(@RequestParam("id") Integer id){
        ObjectRestResponse objectRestResponse=new ObjectRestResponse<>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(16);
        Content content = contentBiz.selectById(id);
        //title
        String title = content.getTitle();
        if (title==null) {
            title = "";
        }
        Date createTime = content.getCreateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String类型的时间
        String createdate = sdf.format(createTime);
        //描述
        String desc = content.getIntroduction();
        //封面图
        String image = content.getImage();
        if (image==null){
            image = "https://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png";
        }
        //视频地址
        ContentVideo contentVideo = contentVideoBiz.selectById(id);
        String url = contentVideo.getUrl();

        //来源作者名称
        Integer sourceId = contentVideo.getSourceId();
        String name = "";
        String videoSourceImage = "https://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png";
        if (sourceId!=null){
            VideoSource videoSource = videoSourceBiz.selectById(sourceId);
            name = videoSource.getName();
            //来源作者图片
            videoSourceImage = videoSource.getImage();
        }

        //comment_list
        ArrayList<CommentWebInfo> comment_list = new ArrayList<>();
        List<Comment> comments = commentBiz.getCommentListByCid(id);
        if (comments!=null){
            for (Comment c:comments) {
                CommentWebInfo commentWebInfo = new CommentWebInfo();
                commentWebInfo.setContent(c.getContent());
                commentWebInfo.setCreate_time(sdf.format(c.getCreateTime()));
                Integer createUid =c.getCreateUid();
                if (createUid!=null){
                    BaseUser baseUser = baseUserBiz.selectById(c.getCreateUid());
                    commentWebInfo.setCreate_user(baseUser.getName());
                    if (baseUser.getHeadPortrait()==null){
                        commentWebInfo.setUser_image(LiveCommonEnum.URL_PREFIX+"/assets/web/img1/default-head-2x.png");
                    }
                    commentWebInfo.setUser_image(baseUser.getHeadPortrait());
                }else {
                    BaseUser baseUser = baseUserBiz.selectById(c.getCreateUid());
                    commentWebInfo.setUser_image(baseUser.getHeadPortrait());
                    commentWebInfo.setUser_image(LiveCommonEnum.URL_PREFIX+"/assets/web/img1/default-head-2x.png");
                }
                comment_list.add(commentWebInfo);
            }
        }
        //bind_list content_broadcast_bind 表
        ArrayList<ContentBroadcastBindWebInfo>  bind_list = new ArrayList<>();
        Map<String, String> param = new HashMap<>(1);
        param.put("locf","out");
        List<Integer> broadcastBindBindIds = contentBroadcastBindBiz.getBroadcastBindBindIds(id);
        if (broadcastBindBindIds!=null){
            for (Integer bind_id:broadcastBindBindIds) {
                ContentBroadcastBindWebInfo contentBroadcastBindWebInfo = new ContentBroadcastBindWebInfo();
                contentBroadcastBindWebInfo.setBind_id(bind_id);
                Content content1 = contentBiz.selectById(bind_id);
                contentBroadcastBindWebInfo.setTitle(content1.getTitle());
                contentBroadcastBindWebInfo.setType(content1.getContentType());
                contentBroadcastBindWebInfo.setCreate_time(sdf.format(content1.getCreateTime()));
                contentBroadcastBindWebInfo.setThumbimg(content1.getImage());
                contentBroadcastBindWebInfo.setUrl(UrlAddParamUtil.getRqstUrl(contentBiz.getShareUrlSu(bind_id,4,2),param));
                contentBroadcastBindWebInfo.setAppUrl(contentBiz.getDetailUrlSu(bind_id,4,2));
                ContentVideo contentVideo1 = contentVideoBiz.selectById(bind_id);
                String duration = contentVideo1.getDuration();
                contentBroadcastBindWebInfo.setDuration(duration);
                bind_list.add(contentBroadcastBindWebInfo);
            }
        }
        //activitys
        String ico = "https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png";
        String title2 = "欲知后事如何？请看APP分解。";
        ActivityWeb activityWeb = new ActivityWeb();
        activityWeb.setIco(ico);
        activityWeb.setTitle(title2);
        ArrayList<ActivityWeb> activityWebs = new ArrayList<>();
        activityWebs.add(activityWeb);

        //share
        ArrayList<ShareWeb> shareWebs = new ArrayList<>();
        ShareWeb shareWeb = new ShareWeb();
        shareWeb.setImg(image);
        shareWeb.setTitle(title);
        shareWeb.setSubtile(desc);
        shareWeb.setUrl(UrlAddParamUtil.getRqstUrl(contentBiz.getShareUrlSu(id,4,2),param));
        shareWebs.add(shareWeb);

        //封装数据
        stringObjectHashMap.put("title",title);
        stringObjectHashMap.put("create_time",createdate);
        stringObjectHashMap.put("desc",desc);
        stringObjectHashMap.put("image",image);
        stringObjectHashMap.put("video_url",url);
        stringObjectHashMap.put("source_name",name);
        stringObjectHashMap.put("source_image",videoSourceImage);
        stringObjectHashMap.put("comment_list",comment_list);
        stringObjectHashMap.put("bind_list",bind_list);
        stringObjectHashMap.put("activitys",activityWebs);
        stringObjectHashMap.put("share",shareWebs);

        objectRestResponse.setData(stringObjectHashMap);
        return objectRestResponse;
    }


    /** 视频专辑分享页接口
     * @param
     * @return
     */
    @RequestMapping(value = "/getVideoAlbum",method = RequestMethod.POST)
    public ObjectRestResponse getVideoAlbum(@RequestParam("id") Integer id) {
        ObjectRestResponse objectRestResponse=new ObjectRestResponse<>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(16);
        VideoAlbum videoAlbumById = videoAlbumBiz.getVideoAlbumById(id);
        if (videoAlbumById == null){
            objectRestResponse.setMessage("专辑不存在");
            objectRestResponse.setRel(true);
        }
        //title
        String title = videoAlbumById.getTitle();
        if (title==null) {
            title = "";
        }
        //bind_list
        ArrayList<ContentBroadcastBindWebInfo> bind_list = new ArrayList<>();
        List<VideoAlbumBindWebInfo> info = videoAlbumBindBiz.getInfo(id);
        if (info!=null){
            for (VideoAlbumBindWebInfo videoAlbumBindWebInfo:info) {
                Integer sourceId = videoAlbumBindWebInfo.getSourceId();
                ContentBroadcastBindWebInfo contentBroadcastBindWebInfo = new ContentBroadcastBindWebInfo();

                Map<String, String> param = new HashMap<>(1);
                param.put("locf","out");
                VideoSource videoSource = videoSourceBiz.selectById(sourceId==null?Integer.valueOf(2):sourceId);
                contentBroadcastBindWebInfo.setUrl(UrlAddParamUtil.getRqstUrl(contentBiz.getShareUrlSu(id,4,2),param));
                contentBroadcastBindWebInfo.setDuration(videoAlbumBindWebInfo.getDuration());
                contentBroadcastBindWebInfo.setType(videoAlbumBindWebInfo.getContentType());
                contentBroadcastBindWebInfo.setId(videoAlbumBindWebInfo.getId());
                contentBroadcastBindWebInfo.setTitle(videoAlbumBindWebInfo.getTitle());
                contentBroadcastBindWebInfo.setDuration(videoAlbumBindWebInfo.getDuration());
                contentBroadcastBindWebInfo.setSource_name(videoSource.getName());
                contentBroadcastBindWebInfo.setSource_img(videoSource.getImage());
                contentBroadcastBindWebInfo.setImage(videoAlbumBindWebInfo.getImage());
                contentBroadcastBindWebInfo.setName(videoSource.getName());
                bind_list.add(contentBroadcastBindWebInfo);
            }
        }
        //share
        ArrayList<ShareWeb> shareWebs = new ArrayList<>();
        ShareWeb shareWeb = new ShareWeb();
        shareWeb.setImg(videoAlbumById.getCoverImg());
        if (videoAlbumById.getCoverImg()==null){
            shareWeb.setImg("");
        }

        if (videoAlbumById.getSummary()!=null){
            shareWeb.setSubtile(videoAlbumById.getSummary());
        }else {
            shareWeb.setSubtile(title);
        }
        videoAlbumById.getSummary();
        shareWeb.setTitle(title);
        if (videoAlbumById.getSummary()!=null){
            shareWeb.setSubtile(videoAlbumById.getSummary());
        }
        Map<String, String> param = new HashMap<>(1);
        param.put("locf","out");
        shareWeb.setUrl(UrlAddParamUtil.getRqstUrl(LiveCommonEnum.URL_PREFIX+"public/specialDetailtest.html?cate="+2,param));
        shareWebs.add(shareWeb);
        //activitys
        String ico = "https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png";
        String title2 = "欲知后事如何？请看APP分解。";
        ActivityWeb activityWeb = new ActivityWeb();
        activityWeb.setIco(ico);
        activityWeb.setTitle(title2);
        ArrayList<ActivityWeb> activityWebs = new ArrayList<>();
        activityWebs.add(activityWeb);

        stringObjectHashMap.put("title",title);
        stringObjectHashMap.put("bind_list",bind_list);
        stringObjectHashMap.put("share",shareWebs);
        stringObjectHashMap.put("activitys",activityWebs);
        objectRestResponse.setData(stringObjectHashMap);

        return objectRestResponse;


    }

    /**
     * 更新浏览量
     * @param viewContent
     */
    @Transactional
    @RequestMapping(value = "/updateContentView",method = RequestMethod.PUT)
    public ObjectRestResponse updateContentView(@RequestBody ViewContent viewContent){
        Integer cid = viewContent.getCid();
        Content content = contentBiz.selectById(cid);
        String key=IS_VIEW+cid+"_"+(viewContent.getUid()!=null?viewContent.getUid():viewContent.getIp());
        Integer did=null;
        switch (content.getContentType()) {
            case 2:
                ContentArticle contentArticle = contentArticleBiz.selectById(cid);
                if(null!=contentArticle){
                    did=contentArticle.getDid();
                }
                break;
            case 4:
                ContentVideo contentVideo = contentVideoBiz.selectById(cid);
                if(null!=contentVideo){
                    did=contentVideo.getDid();
                }
                break;
            case 11:
                ContentVr contentVr = contentVrBiz.selectById(cid);
                if(null!=contentVr){
                    did=contentVr.getDid();
                }
                break;
        }
        viewContent.setType(content.getContentType());
        viewContent.setVid(did);
        viewContent.setLive_id(0);
        if(NewsEnumsConsts.ContentOfContentType.Video.getKey().equals(content.getContentType())||
                NewsEnumsConsts.ContentOfContentType.Live.getKey().equals(content.getContentType())||
                NewsEnumsConsts.ContentOfContentType.VR.getKey().equals(content.getContentType())){
            viewContentService.insertViewContent(viewContent);
            content.setViewCount(content.getViewCount()+1);
            contentBiz.updateSelectiveById(content);
        }else {
            if (!redisTemplate.hasKey(key)){
                viewContentService.insertViewContent(viewContent);
                redisTemplate.opsForValue().set(key,1,300, TimeUnit.SECONDS);
                content.setViewCount(content.getViewCount()+1);
                contentBiz.updateSelectiveById(content);
            }
        }
        return new ObjectRestResponse();
    }

    /**
     * 查询图集
     * @param cid
     * @param cate
     * @return
     */
    @RequestMapping(value = "/getContentImage",method = RequestMethod.GET)
    public ObjectRestResponse<ImageDetailInfo> getContentImage(@RequestParam("cid") Integer cid, @RequestParam("cate") Integer cate){
        //查询基本信息
        List<ContentImageInfo> contentImageList = contentBiz.getContentImageById(cid);
        if(contentImageList==null || contentImageList.size()<=0){
            return new ObjectRestResponse<>();
        }
        ImageDetailInfo imageDetailInfo = new ImageDetailInfo();
        imageDetailInfo.setContentImageList(contentImageList);
        //分享信息
        ContentInfo contentArticle = new ContentInfo();
        ContentImageInfo contentImageInfo = contentImageList.get(0);
        imageDetailInfo.setTitle(contentImageInfo.getTitle());
        BeanUtils.copyProperties(contentImageInfo,contentArticle);
        Map<String,Object> share=getShareMap(cid,contentArticle);
        imageDetailInfo.setShare(share);
        //微信数据
        Map<String, Object> signPackage = getWeiXinShareMap(null);
        imageDetailInfo.setSignPackage(signPackage);
        imageDetailInfo.setSourceType(contentImageInfo.getSourceType());
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(imageDetailInfo);
        return objectRestResponse;
    }

}