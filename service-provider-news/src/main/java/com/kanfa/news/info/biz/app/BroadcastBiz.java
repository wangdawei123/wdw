package com.kanfa.news.info.biz.app;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.constant.UserConstant;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.DateUtil;
import com.kanfa.news.info.entity.*;
import com.kanfa.news.info.mapper.*;
import com.kanfa.news.info.vo.admin.app.AppBurstInfo;
import com.kanfa.news.info.vo.admin.app.AppContent;
import com.kanfa.news.info.vo.admin.app.AppFavInfo;
import com.kanfa.news.info.vo.admin.app.AppSpecialContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * app内容 直播详情页接口
 *
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-3-20 14:23:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BroadcastBiz extends BaseBiz<ContentMapper, Content> {
    @Autowired
    private ChannelContentMapper channelContentMapper;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private FavMapper favMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private ActivityRaffleMapper activityRaffleMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private ContentBroadcastMapper contentBroadcastMapper;
    @Autowired
    private ChannelFocusMapper channelFocusMapper;
    @Autowired
    private ContentImageGroupMapper contentImageGroupMapper;
    @Autowired
    private BurstMapper burstMapper;
    @Autowired
    private BurstResourceMapper burstResourceMapper;
    @Autowired
    private ContentSpecialMapper contentSpecialMapper;
    @Autowired
    private VideoAlbumMapper videoAlbumMapper;
    @Autowired
    private CommentMapper commentMapper ;
    @Autowired
    private RedisTemplate redisTemplate;



    /**
     * app内容模块 直播详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getBroadcastDetail(Integer cate ,Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //从session中获取用户ID
        Integer uid = null;
        ChannelContent chc = new ChannelContent();
        chc.setContentId(cid);
        chc.setIsDelete(1);
        chc.setFromType(1);
        chc.setIsPublish(1);
        List<ChannelContent> chlist = channelContentMapper.select(chc);
        if (null == chlist || chlist.size() == 0) {
            //已取消发布
            channelObjectRestResponse.setMessage("已取消发布");
            return channelObjectRestResponse;
        }
        Content con = new Content();
        con.setId(cid);
        con.setContentState(1); //状态，1：正常，0：删除
        con.setCheckStatus(1); //审核状态（0待审核，1审核通过，2审核不通过）
        Content content = contentMapper.selectOne(con);
        AppContent result = getBroadcastResult(content, uid);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 直播详情页接口返回-组装
     * data  内容数据对象
     * uid  用户ID
     */
    private AppContent getBroadcastResult(Content data, Integer uid) {
        AppContent appContent = new AppContent();
        Map<String, Object> result = new HashMap<>(16);
        appContent.setId(data.getId());
        appContent.setTitle(data.getTitle());
        Fav fav = new Fav();
        fav.setCid(data.getId());
        fav.setUid(uid);
        fav.setType(data.getContentType());
        Fav resultFav = favMapper.selectOne(fav);
        appContent.setIs_faved(resultFav == null ? 0 : 1);
        //获取评论设置
        Setting resultSet = settingMapper.selectSettingByName("comment_tourist");
        appContent.setComment_tourist(resultSet != null ? true : false);
        Setting resultSet2 = settingMapper.selectSettingByName("comment_ops");
        appContent.setPosts_ops(Integer.parseInt(resultSet2.getValue()));
        //如果为先审后发，则用已审核的评论数来代替总评论数
        if (null != resultSet2 && "1".equals(resultSet2.getValue())) {
            appContent.setComments(data.getCommentCheckedCount());
        }
        appContent.setDesc(data.getIntroduction());
        appContent.setShare_url(getShareUrl(CommonConstants.URL_PREFIX, data.getContentType(), data.getCategory(), data.getId()));
        appContent.setImage(data.getImage());
        if (null != data.getImage()) {
            appContent.setShare_image("http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png?x-oss-process=image/resize,m_fill,w_748,h_400");
        }
        List<ActivityRaffle> rafflesList = activityRaffleMapper.getNowActivityRaffle(new Date());
        if (null != rafflesList && rafflesList.size() > 0) {
            ActivityRaffle thisdata = rafflesList.get(0);
            Map thisMap = new HashMap(16);
            thisMap.put("activity_id", thisdata.getId());
            thisMap.put("buoy", thisdata.getBuoy());
            thisMap.put("title", thisdata.getTitle());
            String str = "http://localhost/web/activity/draw/detail.html?activity_id=" + thisdata.getId();
            thisMap.put("activity_url", str);
            appContent.setActivity(thisMap);
        }
        ContentBroadcast contentBroadcast = new ContentBroadcast();
        contentBroadcast.setCid(data.getId());
        ContentBroadcast contentBroadcast1 = contentBroadcastMapper.selectOne(contentBroadcast);
        if(contentBroadcast1!=null){
            result.put("video_img", contentBroadcast1.getVideoImg());
            result.put("status", contentBroadcast1.getBroadcastStatus());
            result.put("review", contentBroadcast1.getReviewUrl());
            // set extend
            appContent.setExtend(result);
        }

        List<Map> contentByIds = contentMapper.getContentByIds(data.getId());
        if(contentByIds!=null){
            List<Map> binds = new ArrayList<>();
            for (Map contentdata : contentByIds) {
                Map<String, Object> bindMap = new HashMap<>(16);
                bindMap.put("title", contentdata.get("title"));
                bindMap.put("image", contentdata.get("image"));
                bindMap.put("pub_time", DateUtil.between(new Date(), (Date) contentdata.get("update_time")));
                bindMap.put("source", contentdata.get("source_type"));
                bindMap.put("broad_status", contentdata.get("broadcast_status"));
                bindMap.put("duration", contentdata.get("replay_duration"));
                bindMap.put("splited", 1);
                binds.add(bindMap);
            }
            //set bind
            appContent.setBind(binds);
        }
        appContent.setActivity_static(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        appContent.setCreate_time(sdf.format(data.getCreateTime()));

        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.parseInt(Long.toString(data.getCreateUid())));
        BaseUser adminUser1 = baseUserMapper.selectOne(baseUser);
        if (null != adminUser1) {
            Map thisUserMap = new HashMap();
            thisUserMap.put("nickname", adminUser1.getName());
            thisUserMap.put("image", adminUser1.getHeadPortrait());
            thisUserMap.put("id", adminUser1.getId());
            appContent.setUser(thisUserMap);

        }

        if (null != uid) {
            String reKey = "like_user_" + uid + "_type_0";
            String rekey2 = "like_total_0";
            appContent.setIs_liked(redisTemplate.opsForSet().isMember(reKey, data.getId()) == true ? 1 : 0);
            appContent.setLikes((int) redisTemplate.opsForHash().get(rekey2, data.getId()));
        }
        return appContent;
    }

    /**
     * app内容模块 -获取频道的内容列表
     */
    public ObjectRestResponse<Map<String, Object>> getContentList(Integer chanId ,Integer page,Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        Map params = new HashMap(16);
        params.put("chan_id", chanId);
        PageHelper.startPage(page,pcount);
        //查询频道的内容信息
        List<Map> pageContentListByChannelId = channelContentMapper.getPageContentListByChannelId(params);
        for(Map data:pageContentListByChannelId){
            String url=getShareUrl(CommonConstants.URL_PREFIX, Integer.valueOf(data.get("type").toString()), Integer.valueOf(data.get("category").toString()), Integer.valueOf(data.get("id").toString()));
            data.put("url",url);
        }
        ChannelFocus focus = new ChannelFocus();
        focus.setChannelId(chanId);
        focus.setIsDelete(1);
        List<ChannelFocus> focusList = channelFocusMapper.select(focus);
        Map result = new HashMap(16);
        result.put("content", pageContentListByChannelId);
        result.put("focus", focusList);
        //查询获取所有上线专辑
        List<Map> videoAlbum1 = videoAlbumMapper.getVideoAlbum();
        result.put("video_album",videoAlbum1);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 图集详情页接口
     * @param cate
     * @param cid
     * @return
     */
    public ObjectRestResponse<Map<String, Object>> getImageDetail(Integer cate ,Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //从session中获取用户ID
        Integer uid = null;
        ChannelContent chc = new ChannelContent();
        chc.setContentId(cid);
        chc.setIsDelete(1);
        chc.setFromType(1);
        chc.setIsPublish(1);
        List<ChannelContent> chlist = channelContentMapper.select(chc);
        if (null == chlist || chlist.size() == 0) {
            //已取消发布
            channelObjectRestResponse.setMessage("已取消发布");
            return channelObjectRestResponse;
        }
        Content con = new Content();
        con.setId(cid);
        con.setContentState(1);
        Content content = contentMapper.selectOne(con);
        AppContent result = getImageDetailResult(content, uid,chlist);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 图集详情页接口返回-组装
     * data  内容数据对象
     * uid  用户ID
     */
    private AppContent getImageDetailResult(Content data, Integer uid,List<ChannelContent> chlist) {
        System.out.println("start now!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        AppContent appContent = new AppContent();
        Map<String, Object> result = new HashMap<>(16);
        if(data.getScoreTaskId()>0){
            appContent.setScore("");
        }
//        Comment comment =new Comment();
//        comment.setCid(data.getId());
//        comment.setOps(1);
//        comment.setStat(1);
//        List<Comment> select = commentMapper.select(comment);
//        if(select !=null && select.size()>0){
//            appContent.setComment(select);
//        }
        appContent.setId(data.getId());
        appContent.setTitle(data.getTitle());
        Fav fav = new Fav();
        fav.setCid(data.getId());
        fav.setUid(uid);
        fav.setType(data.getContentType());
        Fav resultFav = favMapper.selectOne(fav);
        appContent.setIs_faved(resultFav == null ? 0 : 1);
        //获取评论设置
        Setting resultSet = settingMapper.selectSettingByName("comment_tourist");
        appContent.setComment_tourist(resultSet != null ? true : false);
        Setting resultSet2 = settingMapper.selectSettingByName("comment_ops");
        appContent.setPosts_ops(Integer.parseInt(resultSet2.getValue()));
        //如果为先审后发，则用已审核的评论数来代替总评论数
        if (null != resultSet2 && resultSet2.getValue().equals("1")) {
            appContent.setComments(data.getCommentCheckedCount());
        }
        appContent.setDesc(data.getIntroduction());
        appContent.setShare_url(getShareUrl(CommonConstants.URL_PREFIX, data.getContentType(), data.getCategory(), data.getId()));
        appContent.setImage(data.getImage());
        if (null != data.getImage()) {
            appContent.setShare_image("http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png?x-oss-process=image/resize,m_fill,w_748,h_400");
        }
        List<ActivityRaffle> rafflesList = activityRaffleMapper.getNowActivityRaffle(new Date());
        if (null != rafflesList && rafflesList.size() > 0) {
            ActivityRaffle thisdata = rafflesList.get(0);
            Map thisMap = new HashMap(16);
            thisMap.put("activity_id", thisdata.getId());
            thisMap.put("title", thisdata.getTitle());
            thisMap.put("buoy", thisdata.getBuoy());
            String str = "http://localhost/web/activity/draw/detail.html?activity_id=" + thisdata.getId();
            thisMap.put("activity_url", str);
            appContent.setActivity(thisMap);
        }
        //set extend
        List<ContentImageGroup> contentImageGroupReslut = contentImageGroupMapper.selectImageGroupListByCid(data.getId());
        // set extend
         if(contentImageGroupReslut!=null && contentImageGroupReslut.size()>0){
             appContent.setExtend(contentImageGroupReslut);
         }
        appContent.setActivity_static(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        appContent.setCreate_time(sdf.format(data.getCreateTime()));

        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.parseInt(Long.toString(data.getCreateUid())));
        BaseUser adminUser1 = baseUserMapper.selectOne(baseUser);
        if (null != adminUser1) {
            Map thisUserMap = new HashMap();
            thisUserMap.put("nickname", adminUser1.getName());
            thisUserMap.put("image", adminUser1.getHeadPortrait());
            thisUserMap.put("id", adminUser1.getId());
            appContent.setUser(thisUserMap);

        }

        if (null != uid) {
            String reKey = "like_user_" + uid + "_type_0";
            String rekey2 = "like_total_0";
            appContent.setIs_liked(redisTemplate.opsForSet().isMember(reKey, data.getId()) == true ? 1 : 0);
            appContent.setLikes((int) redisTemplate.opsForHash().get(rekey2, data.getId()));
        }
        return appContent;
    }

    /**
     * app内容模块 图集详情页接口
     * @param keyword
     * @param page
     * @param pcount
     * @return
     */
    public ObjectRestResponse<Map<String, Object>> getSearchList(String keyword,Integer page ,Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //从session中获取用户ID
        Integer uid = null;
        Map paramsMap = new HashMap(16);
        paramsMap.put("keyWord", keyword);
        Page<Map> result = PageHelper.startPage(page, pcount);
        List<Map> searchListByKeyWord = contentMapper.getSearchListByKeyWord(paramsMap);
        Map returnMap=new HashMap(16);
        returnMap.put("bind",searchListByKeyWord);
        channelObjectRestResponse.setData(returnMap);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 专题详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getSpecialDetail( Integer cate,Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //从session中获取用户ID
        Integer uid = null;
        ChannelContent chc = new ChannelContent();
        chc.setContentId(cid);
        chc.setIsDelete(1);
        chc.setFromType(1);
        chc.setIsPublish(1);
        List<ChannelContent> chlist = channelContentMapper.select(chc);
        if (null == chlist || chlist.size() == 0) {
            //已取消发布
            channelObjectRestResponse.setMessage("已取消发布");
            return channelObjectRestResponse;
        }
        Content con = new Content();
        con.setId(cid);
        con.setContentState(1);
        Content content = contentMapper.selectOne(con);
        AppContent result = getSpecialDetailResult(content, uid);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 直播详情页接口返回-组装
     * data  内容数据对象
     * uid  用户ID
     */
    private AppContent getSpecialDetailResult(Content data, Integer uid) {
        AppContent appContent = new AppContent();
        Map<String, Object> result = new HashMap<>(16);
        appContent.setId(data.getId());
        appContent.setTitle(data.getTitle());
        Fav fav = new Fav();
        fav.setCid(data.getId());
        fav.setUid(uid);
        fav.setType(data.getContentType());
        Fav resultFav = favMapper.selectOne(fav);
        appContent.setIs_faved(resultFav == null ? 0 : 1);
        //获取评论设置
        Setting resultSet = settingMapper.selectSettingByName("comment_tourist");
        appContent.setComment_tourist(resultSet != null ? true : false);
        Setting resultSet2 = settingMapper.selectSettingByName("comment_ops");
        appContent.setPosts_ops(Integer.parseInt(resultSet2.getValue()));
        //如果为先审后发，则用已审核的评论数来代替总评论数
        if (null != resultSet2 && "1".equals(resultSet2.getValue())) {
            appContent.setComments(data.getCommentCheckedCount());
        }
        appContent.setDesc(data.getIntroduction());
        appContent.setShare_url(getShareUrl(CommonConstants.URL_PREFIX, data.getContentType(), data.getCategory(), data.getId()));
        appContent.setImage(data.getImage());
        if (null != data.getImage()) {
            appContent.setShare_image("http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png?x-oss-process=image/resize,m_fill,w_748,h_400");
        }
        appContent.setActivity_static(2);
        //set news array
        List<Map> contentSpecialById = contentMapper.getContentSpecialDetailById(data.getId());
        if (contentSpecialById != null && contentSpecialById.size() > 0) {
            appContent.setNews(contentSpecialById);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        appContent.setCreate_time(sdf.format(data.getCreateTime()));

        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.parseInt(Long.toString(data.getCreateUid())));
        BaseUser adminUser1 = baseUserMapper.selectOne(baseUser);
        if (null != adminUser1) {
            Map thisUserMap = new HashMap();
            thisUserMap.put("nickname", adminUser1.getName());
            thisUserMap.put("image", adminUser1.getHeadPortrait());
            thisUserMap.put("id", adminUser1.getId());
            appContent.setUser(thisUserMap);

        }

        if (null != uid) {
            String reKey = "like_user_" + uid + "_type_0";
            String rekey2 = "like_total_0";
            appContent.setIs_liked(redisTemplate.opsForSet().isMember(reKey, data.getId()) == true ? 1 : 0);
            appContent.setLikes((int) redisTemplate.opsForHash().get(rekey2, data.getId()));
        }
        return appContent;
    }

    /**
     * app内容模块 视频详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getVideoDetail( Integer cate,Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //从session中获取用户ID
        Integer uid = null;
        ChannelContent chc = new ChannelContent();
        chc.setContentId(cid);
        chc.setIsDelete(1);
        chc.setFromType(1);
        chc.setIsPublish(1);
        List<ChannelContent> chlist = channelContentMapper.select(chc);
        if (null == chlist || chlist.size() == 0) {
            //已取消发布
            channelObjectRestResponse.setMessage("已取消发布");
            return channelObjectRestResponse;
        }
        Content con = new Content();
        con.setId(cid);
        con.setContentState(1);
        Content content = contentMapper.selectOne(con);
        AppContent result = getVideoDetailResult(content, uid);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 图集详情页接口返回-组装
     * data  内容数据对象
     * uid  用户ID
     */
    private AppContent getVideoDetailResult(Content data, Integer uid) {
        AppContent appContent = new AppContent();
        Map<String, Object> result = new HashMap<>(16);
        appContent.setId(data.getId());
        appContent.setTitle(data.getTitle());
        Fav fav = new Fav();
        fav.setCid(data.getId());
        fav.setUid(uid);
        fav.setType(data.getContentType());
        Fav resultFav = favMapper.selectOne(fav);
        appContent.setIs_faved(resultFav == null ? 0 : 1);
        //获取评论设置
        Setting resultSet = settingMapper.selectSettingByName("comment_tourist");
        appContent.setComment_tourist(resultSet != null ? true : false);
        Setting resultSet2 = settingMapper.selectSettingByName("comment_ops");
        appContent.setPosts_ops(Integer.parseInt(resultSet2.getValue()));
        //如果为先审后发，则用已审核的评论数来代替总评论数
        if (null != resultSet2 && "1".equals(resultSet2.getValue())) {
            appContent.setComments(data.getCommentCheckedCount());
        }
        appContent.setDesc(data.getIntroduction());
        appContent.setShare_url(getShareUrl(CommonConstants.URL_PREFIX, data.getContentType(), data.getCategory(), data.getId()));
        appContent.setImage(data.getImage());
        if (null != data.getImage()) {
            appContent.setShare_image("http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png?x-oss-process=image/resize,m_fill,w_748,h_400");
        }
        List<ActivityRaffle> rafflesList = activityRaffleMapper.getNowActivityRaffle(new Date());
        if (null != rafflesList && rafflesList.size() > 0) {
            ActivityRaffle thisdata = rafflesList.get(0);
            Map thisMap = new HashMap(16);
            thisMap.put("activity_id", thisdata.getId());
            thisMap.put("title", thisdata.getTitle());
            thisMap.put("buoy", thisdata.getBuoy());
            String str = "http://localhost/web/activity/draw/detail.html?activity_id=" + thisdata.getId();
            thisMap.put("activity_url", str);
            appContent.setActivity(thisMap);
        }
        //set extend
        List<ContentImageGroup> contentImageGroupReslut = contentImageGroupMapper.selectImageGroupListByCid(data.getId());
        // set extend
        appContent.setExtend(contentImageGroupReslut);
        appContent.setActivity_static(2);
        //set binds
        List<Map> contentVideoDetailById = contentMapper.getContentVideoDetailById(data.getId());
        appContent.setBind(contentVideoDetailById);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        appContent.setCreate_time(sdf.format(data.getCreateTime()));


        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.parseInt(Long.toString(data.getCreateUid())));
        BaseUser adminUser1 = baseUserMapper.selectOne(baseUser);
        if (null != adminUser1) {
            Map thisUserMap = new HashMap();
            thisUserMap.put("nickname", adminUser1.getName());
            thisUserMap.put("image", adminUser1.getHeadPortrait());
            thisUserMap.put("id", adminUser1.getId());
            appContent.setUser(thisUserMap);
        }

        if (null != uid) {
            String reKey = "like_user_" + uid + "_type_0";
            String rekey2 = "like_total_0";
            appContent.setIs_liked(redisTemplate.opsForSet().isMember(reKey, data.getId()) == true ? 1 : 0);
            appContent.setLikes((int) redisTemplate.opsForHash().get(rekey2, data.getId()));
        }
        return appContent;
    }

    /**
     * APP内容-VR详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getVrDetail(Integer cate,Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //从session中获取用户ID
        Integer uid = null;
        ChannelContent chc = new ChannelContent();
        chc.setContentId(cid);
        chc.setIsDelete(1);
        chc.setFromType(1);
        chc.setIsPublish(1);
        List<ChannelContent> chlist = channelContentMapper.select(chc);
        if (null == chlist || chlist.size() == 0) {
            //已取消发布
            channelObjectRestResponse.setMessage("已取消发布");
            return channelObjectRestResponse;
        }
        Content con = new Content();
        con.setId(cid);
        con.setContentState(1); //状态，1：正常，0：删除
        con.setCheckStatus(1); //审核状态（0待审核，1审核通过，2审核不通过）
        Content content = contentMapper.selectOne(con);
        AppContent result = getBroadcastResult(content, uid);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }
    /**
     * app内容模块 直播详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getNewSpecial(Integer cate,Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //从session中获取用户ID
        Integer uid = 2;
        ChannelContent chc = new ChannelContent();
        chc.setContentId(cid);
        chc.setIsDelete(1);
        chc.setFromType(1);
        chc.setIsPublish(1);
        List<ChannelContent> chlist = channelContentMapper.select(chc);
        if (null == chlist || chlist.size() == 0) {
            //已取消发布
            channelObjectRestResponse.setMessage("已取消发布");
            return channelObjectRestResponse;
        }
        Content con = new Content();
        con.setId(cid);
        //状态，1：正常，0：删除
        con.setContentState(1);
        //审核状态（0待审核，1审核通过，2审核不通过）
        con.setCheckStatus(1);
        Content content = contentMapper.selectOne(con);
        Map result = fiterNewSpecial(content, uid);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 文章详情页接口
     */
    private Map<String, Object> fiterNewSpecial(Content data, Integer uid) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("id", data.getId());
        result.put("title", data.getTitle());
        result.put("uid", uid);
        Fav fav = new Fav();
        fav.setCid(data.getId());
        fav.setUid(uid);
        fav.setType(data.getContentType());
        Fav resultFav = favMapper.selectOne(fav);
        result.put("is_faved", resultFav == null ? 0 : 1);//是否收藏
        result.put("'desc'", data.getIntroduction());
        result.put("share_url", getShareUrl(CommonConstants.URL_PREFIX, data.getContentType(), data.getCategory(), data.getId()));
        result.put("''image''", data.getImage());
        if (null != data.getImage()) {
            result.put("share_image", "http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png?x-oss-process=image/resize,m_fill,w_748,h_400");
        }
        List specialsList=new ArrayList();
        //获取目录和绑定的内容
        Map specialMap = contentSpecialMapper.selectSpecialContentListByCid(data.getId());
        if(specialMap==null || specialMap.get("target_cid")==null ){
            return result;
        }else{
            String targetCids = specialMap.get("target_cid").toString();
            String catalogId = specialMap.get("id").toString();
            String[] targetCidsList = targetCids.split(",");
            AppSpecialContentInfo appSpecialContentInfo=new AppSpecialContentInfo();
            appSpecialContentInfo.setCid(data.getId());
            appSpecialContentInfo.setCatalogId(Integer.valueOf(catalogId));
            appSpecialContentInfo.setIds(Arrays.asList(targetCidsList));
            List<Map> maps = contentSpecialMapper.selectBindSpecialContentListByCid(appSpecialContentInfo);
            result.put("specials",maps);
        }
        return result;
    }

    /**
     * app内容模块 单篇内容收藏状态接口
     */
    public ObjectRestResponse<Map<String, Object>> getOneFavStatus(Integer uid,Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        AppFavInfo appFav = new AppFavInfo();
        Fav fav = new Fav();
        fav.setCid(cid);
        fav.setUid(uid);
        fav.setType(0);
        Fav favResult = favMapper.selectOne(fav);
        //set fav_status
        if (null != favResult) {
            appFav.setFav_status(1);
        }

        Content con = new Content();
        con.setId(cid);
        Content content = contentMapper.selectOne(con);
        Map<Object, Object> shareMap = new HashMap<>(16);
        if (null != content) {
            shareMap.put("title", content.getTitle());
            shareMap.put("desc", content.getIntroduction());
            shareMap.put("image", content.getImage() != null ? content.getImage() : "http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png");
            shareMap.put("url", "");
            appFav.setShare(shareMap);
        }
        channelObjectRestResponse.setData(appFav);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 爆料列表API接口
     */
    public ObjectRestResponse<Map<String, Object>> getBurstList(Integer page ,Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        List resultList = new LinkedList();
        //从session中获取用户ID
        Integer uid = null;
        Page<Map> result = PageHelper.startPage(page, pcount);
        List<Map> burstList = burstMapper.selectBurstListByUid(uid);
        if (burstList != null && burstList.size() > 0) {
            for (Map burstMap : burstList) {
                AppBurstInfo appBurstInfo = new AppBurstInfo();
                appBurstInfo.setBurst_id(Integer.valueOf(burstMap.get("id") != null ? burstMap.get("id").toString() : null));
                appBurstInfo.setTitle(burstMap.get("remark") != null ? burstMap.get("remark").toString() : null);
                appBurstInfo.setCid(Integer.valueOf(burstMap.get("content_id") != null ? burstMap.get("content_id").toString() : null));
                appBurstInfo.setBurst_type("发布线索");
                if (burstMap.get("status") != null) {
                    Integer stat = Integer.valueOf(burstMap.get("status").toString());
                    appBurstInfo.setStat(stat);
                    if (stat == 3) {
                        appBurstInfo.setNotice("您好，感谢您对看法新闻的关注，但您所提供的内容不适合看法新闻，您可以重新提交，谢谢。");
                    }
                    if (stat == 2) {
                        if (burstMap.get("bind_num") != null) {
                            Integer bindNum = Integer.valueOf(burstMap.get("bind_num").toString());
                            if (bindNum > 1) {
                                appBurstInfo.setContent_style(2);
                            } else {
                                appBurstInfo.setContent_style(1);
                                appBurstInfo.setCid(burstMap.get("content_id") != null ? Integer.valueOf(burstMap.get("content_id").toString()) : null);
                                appBurstInfo.setType(burstMap.get("content_type") != null ? Integer.valueOf(burstMap.get("content_type").toString()) : null);
                            }
                        }
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                appBurstInfo.setTime(sdf.format(burstMap.get("create_time")));
                resultList.add(appBurstInfo);
            }

        }
        HashMap returnMap = new HashMap(16);
        returnMap.put("burst_list", resultList);
        channelObjectRestResponse.setData(returnMap);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 新增爆料接口接口
     */
    public ObjectRestResponse<Map<String, Object>> addBurst(Integer user_id,String user_name,
                                                            String address,String point,
                                                            String remark,String content,
                                                            String images,String videos) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        List resultList = new LinkedList();
        Burst burst = new Burst();
        burst.setUserName(user_name);
        burst.setUserId(user_id);
        burst.setAddDesc(address);
        burst.setPoint(point);
        burst.setRemark(remark);
        burst.setContent(content);
        //save burst
        burstMapper.insertBurstSql(burst);
        //save burstResource image type
        if(null!=images){
            String[] imagesArray=images.split(",");
            for(String data:imagesArray){
                BurstResource burstResource=new BurstResource();
                burstResource.setBurstId(burst.getId());
                burstResource.setIsDelete(1);
                burstResource.setCreateTime(new Date());
                burstResource.setUrl(data);
                burstResource.setType(1);//图片类型为1
                burstResourceMapper.insert(burstResource);
            }
        }
        //save burstResource video type
        if(null!=videos){
            String[] videosArray=videos.split(",");
            for(String data:videosArray){
                BurstResource burstResource=new BurstResource();
                burstResource.setBurstId(burst.getId());
                burstResource.setIsDelete(1);
                burstResource.setCreateTime(new Date());
                burstResource.setUrl(data);
                burstResource.setType(2);//视频类型为2
                burstResourceMapper.insert(burstResource);
            }
        }
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 爆料绑定内容列表接口
     */
    public ObjectRestResponse<Map<String, Object>> getBindNews(Integer burst_id,Integer page, Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        List resultList = new LinkedList();
        PageHelper.startPage(page,pcount);
        List<Map> bindNewsByBurstId = contentMapper.getBindNewsByBurstId(burst_id);
        channelObjectRestResponse.setData(bindNewsByBurstId);
        return channelObjectRestResponse;
    }

    /**
     * 分享落地页
     *
     * @param URL_PREFIX
     * @param type
     * @param cate
     * @param id
     * @return
     */
    private String getShareUrl(String URL_PREFIX, Integer type, Integer cate, Integer id) {
        String url = "";
        switch (type) {
            case 1://专题
                url = URL_PREFIX + "web/activity/special/index.html?cate=" + cate + "&cid=" + id + "&share=1";
                break;
            case 2://资讯 新分享页不需要share参数
                url = URL_PREFIX + "web/share/news/index.html?id=" + id;
                break;
            case 3://图集
                url = URL_PREFIX + "web/share/image/index.html?id=" + id;
                break;
            case 4://视频
                url = URL_PREFIX + "web/share/video/index.html?id=" + id;
                break;
            case 9: //直播
                //   $url = '#';
                url = URL_PREFIX + "web/broadcast/index?cid=" + id + "&share=1";
                break;
            case 11://vr
                url = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fawan.news";
                break;
            case 12://问答
                url = URL_PREFIX + "web/problem/index/" + id + "/" + cate + "?share=1";
                break;
            case 22://新直播
                url = URL_PREFIX + "web/activity/live/index.html?id=" + id + "&share=1";
                break;
            case 23://庭审h5
                url = URL_PREFIX + "web/live/index?id=" + id + "&share=1";
                break;
            default:
                break;
        }
        return url;
    }

}