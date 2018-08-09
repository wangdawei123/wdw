package com.kanfa.news.app.biz.app;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.biz.ContentBiz;
import com.kanfa.news.app.entity.*;
import com.kanfa.news.app.mapper.*;
import com.kanfa.news.app.vo.admin.app.AppBurstInfo;
import com.kanfa.news.app.vo.admin.app.AppContent;
import com.kanfa.news.app.vo.admin.app.AppFavInfo;
import com.kanfa.news.app.vo.admin.app.AppSpecialContentInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.DateUtil;
import com.kanfa.news.common.util.PHPSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
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
    private ChannelMapper channelMapper;
    @Autowired
    private ColumnNewMapper columnNewMapper;

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
    private CommentMapper commentMapper;

    @Autowired
    private ContentArticleMapper contentArticleMapper;
    @Autowired
    private ContentImageMapper contentImageMapper;
    @Autowired
    private ContentVideoMapper contentVideoMapper;
    @Autowired
    private ContentVrMapper contentVrMapper;

    @Autowired
    private ContentBiz contentBiz;

    @Autowired
    private RedisTemplate redisTemplate;

    private String newsWebUrl = "http://java-web.vrcdkj.cn";

    private Integer dayNum = 7;

    private Integer CARD_TYPE_TJ = 2; //推荐频道

    //---------新版新增
    private Integer CARD_TYPE_SPECAIL = 12; //新版专题
    private Integer CARD_TYPE_COLUMS_JRKF = 13; // 今日看法专栏
    private Integer CARD_TYPE_TOP = 14;         //置顶显示样式
    private Integer CARD_TYPE_COLUMS_KFZL = 15; //看法专栏
    private Integer CARD_TYPE_COLUMS_KFDJ = 16; //看法独家
    private Integer CARD_TYPE_FOCUS = 17;  //看法焦点图


    /**
     * app内容模块 直播详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getBroadcastDetail(Integer cate, Integer cid, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
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
        if (null != uid) {
            fav.setUid(uid);
        }
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
        if (contentBroadcast1 != null) {
            result.put("video_img", contentBroadcast1.getVideoImg());
            result.put("status", contentBroadcast1.getBroadcastStatus());
            result.put("review", contentBroadcast1.getReviewUrl());
            // set extend
            appContent.setExtend(result);
        }

        List<Map> contentByIds = contentMapper.getContentByIds(data.getId());
        if (contentByIds != null) {
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
    public ObjectRestResponse<Map<String, Object>> getContentList(Integer chanId, Integer page, Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        Map params = new HashMap(16);
        params.put("chan_id", chanId);
        PageHelper.startPage(page, pcount);
        //查询频道的内容信息
        List<Map> pageContentListByChannelId = channelContentMapper.getPageContentListByChannelId(params);
        //查询频道信息
        Channel channel = new Channel();
        channel.setId(chanId);
        Channel channel1 = channelMapper.selectByPrimaryKey(channel);
        Integer hot_threshold = channel1.getHotThreshold();
        //将不同类型的资讯的内容补全
        pageContentListByChannelId = contentMsgAdd(pageContentListByChannelId, hot_threshold, dayNum);

        for (Map data : pageContentListByChannelId) {
//            String url=getShareUrl(CommonConstants.URL_PREFIX, Integer.valueOf(data.get("type").toString()), Integer.valueOf(data.get("category").toString()), Integer.valueOf(data.get("id").toString()));
            String url = contentBiz.getDetailUrlSu(Integer.valueOf(data.get("id").toString()), Integer.valueOf(data.get("type").toString()), Integer.valueOf(data.get("category").toString()));
//            String url=newsWebUrl+"/detail/"+data.get("id").toString()+"/"+data.get("type").toString();
            data.put("url", url);
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
        result.put("video_album", videoAlbum1);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 -New获取频道的内容列表
     * 首页改版 ，添加推荐栏目
     */
    public ObjectRestResponse<Map<String, Object>> getNewContentList(Integer chanId, Long signtime, String device_imei, String channel, Integer refresh, String order, String maxid, Integer page, Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        Map result = new HashMap(16);
        System.out.println("首页改版频道内容查询接口开始.......");
        Map params = new HashMap(16);
        params.put("chan_id", chanId);
        params.put("signtime", signtime);
        params.put("order", order);
        PageHelper.startPage(page, 20);
        //查询频道的内容信息
        List<Map> pageContentListByChannelId = channelContentMapper.getNewPageContentListByChannelId(params);
        if (pageContentListByChannelId == null || pageContentListByChannelId.size() == 0) {
            result.put("list", null);
            channelObjectRestResponse.setData(result);
            return channelObjectRestResponse;
        }
        //查询频道信息
        Channel channelparam = new Channel();
        channelparam.setId(chanId);
        Channel channel1 = channelMapper.selectByPrimaryKey(channelparam);
        Integer hot_threshold = channel1.getHotThreshold();
        //将不同类型的资讯的内容补全
        pageContentListByChannelId = contentMsgAdd(pageContentListByChannelId, hot_threshold, dayNum);
        for (Map data : pageContentListByChannelId) {
            int from_type = data.get("from_type") != null ? Integer.valueOf(data.get("from_type").toString()) : 1;
            if(from_type==1){
                String url = contentBiz.getDetailUrlSu(Integer.valueOf(data.get("id").toString()), Integer.valueOf(data.get("type").toString()), Integer.valueOf(data.get("category").toString()));
                data.put("url", url);
            }
            //关联查询 存在别名重复的情况   所以判断from_type=2 的视频的数据 做转换
            if (from_type == 2) {
                data.put("id", data.get("live_id"));
                data.put("title", data.get("title"));
                data.put("image", data.get("live_image"));
                data.put("views", data.get("live_views"));
            }
        }
        result.put("signtime", signtime != null ? signtime : System.currentTimeMillis());
        result.put("list", pageContentListByChannelId);
        if(refresh!=null && refresh==1){
            result.put("refresh_content", getRefreshCententCount(chanId, maxid));
        }
        // 查看所有新闻的时候  order = ""空串  or "new"
        if (page == 1 && chanId != null && chanId.equals(CARD_TYPE_TJ) && "".equals(order)) {
            result.put("list", operationReturnMapForHomePage(pageContentListByChannelId));
            channelObjectRestResponse.setData(result);
            return channelObjectRestResponse;
        }
        result.put("list", pageContentListByChannelId);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * 首页改版 处理返回结果数据
     *
     * @return
     */
    public List<Map> operationReturnMapForHomePage(List<Map> pageContentListByChannelId) {
        List<Map> result = new LinkedList<>();
        List<Map> top_arr = new ArrayList();
        List<Map> weight_arr = new ArrayList();
        List<Map> other_arr = new ArrayList();
        //独家看法 读取最新6条文章
        List<Map> djresult = new LinkedList<>();
        //看法专栏 读取最新10条直播
        List<Map> liveresult = new LinkedList<>();
        for (Map data : pageContentListByChannelId) {
            int top = data.get("top") != null ? Integer.valueOf(data.get("top").toString()) : 0;
            int recommend_weight = data.get("recommend_weight") != null ? Integer.valueOf(data.get("recommend_weight").toString()) : 60;
            int type = data.get("type") != null ? Integer.valueOf(data.get("type").toString()) : 0;
            int from_type = data.get("from_type") != null ? Integer.valueOf(data.get("from_type").toString()) : 0;
            if (top > 0) {
                top_arr.add(data);
            } else if (recommend_weight > 60) {
                weight_arr.add(data);
            } else {
                other_arr.add(data);
            }
            if (type == 1 && djresult.size() < 6) {
                djresult.add(data);
            }
            if (from_type == 2 && djresult.size() < 10) {
                liveresult.add(data);
            }
        }
        List<ColumnNew> columnNews = homePageAddChannelMsg();
        if (columnNews != null && columnNews.size() > 0) {
            for (ColumnNew columnNew : columnNews) {
                //今日看法
                if (1 == columnNew.getId()) {
                    Map kfMap = new HashMap();
                    kfMap.put("id", columnNew.getId());
                    kfMap.put("title", columnNew.getName());
                    kfMap.put("desc", columnNew.getDescription());
                    kfMap.put("image", columnNew.getImage());
                    kfMap.put("card_type", CARD_TYPE_COLUMS_JRKF);
                    if (top_arr != null) {
                        if (top_arr.size() > 2) {
                            kfMap.put("content", top_arr.subList(0, 2));
                        }
                        if (top_arr.size() <= 2) {
                            kfMap.put("content", top_arr.subList(0, top_arr.size()));
                        }
                    }
                    kfMap.put("splited", 1);
                    kfMap.put("type", -1);
                    result.add(kfMap);
                }
            }
        }
        if (top_arr != null && top_arr.size() > 3) {
            result.addAll(top_arr.subList(2, top_arr.size()));
        }
        if (columnNews != null && columnNews.size() > 0) {
            for (ColumnNew columnNew : columnNews) {
                //推荐直播
                if (2 == columnNew.getId()) {
                    result.get(result.size() - 1).put("splited", 1);
                    Map kfMap = new HashMap();
                    kfMap.put("id", columnNew.getId());
                    kfMap.put("title", columnNew.getName());
                    kfMap.put("desc", columnNew.getDescription());
                    kfMap.put("image", columnNew.getImage());
                    result.add(kfMap);
                }
            }
        }
        if (weight_arr != null && weight_arr.size() >= 3) {
            result.addAll(weight_arr.subList(0, 3));
        }
        if (columnNews != null && columnNews.size() > 0) {
            for (ColumnNew columnNew : columnNews) {
                //看法专栏
                if (3 == columnNew.getId()) {
                    result.get(result.size() - 1).put("splited", 1);
                    Map kfMap = new HashMap();
                    kfMap.put("id", columnNew.getId());
                    kfMap.put("title", columnNew.getName());
                    kfMap.put("desc", columnNew.getDescription());
                    kfMap.put("image", columnNew.getImage());
                    kfMap.put("card_type", CARD_TYPE_COLUMS_KFZL);
                    //读取最新的直播内容10条
                    kfMap.put("content", liveresult);
                    kfMap.put("splited", 1);
                    kfMap.put("type", -1);
                    result.add(kfMap);
                }
            }
        }
        if (weight_arr != null && weight_arr.size() > 3) {
            result.addAll(weight_arr.subList(3, weight_arr.size()));
        }
        if (other_arr != null) {
            if (other_arr.size() > 0 && other_arr.size() <= 5) {
                result.addAll(other_arr.subList(0, weight_arr.size()));
            }
            if (other_arr.size() > 5) {
                result.addAll(other_arr.subList(0, 5));
            }
        }
        if (columnNews != null && columnNews.size() > 0) {
            for (ColumnNew columnNew : columnNews) {
                //看法独家
                if (5 == columnNew.getId()) {
                    result.get(result.size() - 1).put("splited", 1);
                    Map kfMap = new HashMap();
                    kfMap.put("id", columnNew.getId());
                    kfMap.put("title", columnNew.getName());
                    kfMap.put("desc", columnNew.getDescription());
                    kfMap.put("image", columnNew.getImage());
                    kfMap.put("card_type", CARD_TYPE_COLUMS_KFDJ);
                    //读取最新6条文章带大图
                    kfMap.put("content", djresult);
                    kfMap.put("splited", 1);
                    kfMap.put("type", -1);
                    result.add(kfMap);
                }
            }
        }
        if (other_arr != null && other_arr.size() > 5) {
            result.addAll(other_arr.subList(5, other_arr.size()));
        }
        return result;

    }


    /**
     * 首页改版 添加推荐栏目   获取所有栏目数据
     *
     * @return
     */
    public List<ColumnNew> homePageAddChannelMsg() {
        ColumnNew columnNew = new ColumnNew();
        columnNew.setHide(0);
        columnNew.setIsDelete(0);
        List<ColumnNew> select = columnNewMapper.select(columnNew);
        return select;
    }

    /**
     * 首页改版new
     * 获取刷新的内容条数
     *
     * @param chanId
     * @param maxid
     * @return
     */
    public String getRefreshCententCount(Integer chanId, String maxid) {
        if (chanId == null || maxid == null) {
            return "没有新的推荐内容";
        }
        Map params = new HashMap(16);
        params.put("chan_id", chanId);
        params.put("maxId", maxid);
        //查询频道刷新的内容信息条数
        List<Map> newContentCountByChannelId = channelContentMapper.getNewContentCountByChannelId(params);
        if (newContentCountByChannelId != null && newContentCountByChannelId.size() > 0) {
            return "刷新了" + newContentCountByChannelId.size() + "条新内容";
        } else {
            return "没有新的推荐内容";
        }
    }


    //将不同类型的资讯的内容补全
    public List<Map> contentMsgAdd(List<Map> contentList, Integer hotThreshold, Integer dayNum) {
        for (Map content : contentList) {
            if(content.get("from_type")!=null && content.get("from_type").toString().equals("1")){
                //标签
                HashMap<Object, Object> tagMap = new HashMap<>(16);

                if ("2".equals(content.get("type").toString())) {
                    String tag = content.get("tag").toString();
                    tagMap.put("name", tag);
                    tagMap.put("font_color", "#ce2524");
                    tagMap.put("border_color", "#ce2524");
                    content.put("tags", tagMap);

                    Date publicTime = (Date) content.get("publish_time");
                    if (content.get("views") != null
                            && hotThreshold <= Integer.valueOf(content.get("views").toString())
                            && (System.currentTimeMillis() - publicTime.getTime()) < 1000 * 86400 * dayNum) {
                        HashMap<Object, Object> tagMap2 = new HashMap<>(16);
                        tagMap2.put("name", "热门");
                        tagMap2.put("font_color", "#cc2424");
                        tagMap2.put("border_color", "#fff3f3");
                        content.put("other_tags", tagMap2);
                    }
                } else if ("9".equals(content.get("type").toString())) {
                    tagMap.put("name", "直播");
                    tagMap.put("font_color", "#4D97DD");
                    tagMap.put("border_color", "#4D97DD");
                    content.put("tags", tagMap);
                }
                if ("1".equals(content.get("source_type").toString())) {
                    if ("2".equals(content.get("type").toString()) || "3".equals(content.get("type").toString()) || "4".equals(content.get("type").toString())) {
                        tagMap.put("name", "原创");
                        tagMap.put("font_color", "#CD2525");
                        tagMap.put("border_color", "#CD2525");
                        content.put("tags", tagMap);
                    }
                }
                List imagesInit = new ArrayList(16);
                content.put("images", imagesInit);
                int type = Integer.parseInt(content.get("type") == null ? "0" : content.get("type").toString());
                switch (type) {
                    case 1:
                        break;
                    case 2:
                        try {
                            if (content.get("cardType") != null && content.get("cardImage") != null) {
                                Integer cardType = Integer.valueOf(content.get("cardType").toString());
                                String cardImage = content.get("cardImage").toString();
                                if (cardType != null && (cardType == 3 || cardType == 10)) {
                                    List cardImageList = (List<String>) PHPSerializer.unserialize(cardImage.getBytes(), "utf-8");
                                    content.put("images", cardImageList);
                                }
                            }

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }


//                    ContentArticle contentArticle = new ContentArticle();
//                    contentArticle.setCid(Integer.valueOf(content.get("id").toString()));
//                    contentArticle.setIsDelete(1);
//                    //文章来源和作者
//                    ContentArticle contentArticle1 = contentArticleMapper.selectOne(contentArticle);
//                    if (contentArticle1 != null) {
//                        content.put("source", contentArticle1.getSource());
//                        content.put("author", contentArticle1.getAuthor());
//                    }
                        if ("1".equals(content.get("content_style").toString())) {
                            content.put("type", CommonConstants.CONTENT_JUDGMENT);
                        }
                        if ("2".equals(content.get("content_style").toString())) {
                            content.put("type", CommonConstants.CONTENT_POST);
                        }
                        break;
                    //图集
                    case 3:
                        ContentImage contentImage = new ContentImage();
                        contentImage.setCid(Integer.valueOf(content.get("id").toString()));
                        contentImage.setStat(1);
                        //图集个数
                        ContentImage contentImage1 = contentImageMapper.selectOne(contentImage);
                        if (contentImage1 != null) {
                            content.put("image_num", contentImage1.getNum());
                            ContentImageGroup contentImageGroup = new ContentImageGroup();
                            contentImageGroup.setCid(Integer.valueOf(content.get("id").toString()));
                            List<ContentImageGroup> select = contentImageGroupMapper.getImageGroupList(contentImageGroup);

                            if (select != null && select.size() > 0) {
                                List images = new ArrayList(16);
                                for (ContentImageGroup imageGroup : select) {
                                    images.add(imageGroup.getImage());
                                }
                                content.put("images", images);
                            }

                        } else {
                            content.put("image_num", 0);
                        }
                        break;
                    //视频
                    case 4:
                        ContentVideo contentVideo = new ContentVideo();
                        contentVideo.setCid(Integer.valueOf(content.get("id").toString()));
                        contentVideo.setIsDelete(1);
                    /*//视频时长 源地址
                    ContentVideo contentVideo1 = contentVideoMapper.selectOne(contentVideo);
                    if (contentVideo1 != null) {
                        content.put("duration", contentVideo1.getDuration());
                        content.put("video_source", contentVideo1.getUrl());
                    } else {
                        content.put("duration", "00:00");
                        content.put("video_source", "");
                    }*/
                        break;
                    //直播
                    case 9:
                        ContentBroadcast contentBroadcast = new ContentBroadcast();
                        contentBroadcast.setCid(Integer.valueOf(content.get("id").toString()));
                        contentBroadcast.setIsDelete(1);
                        ContentBroadcast contentBroadcast1 = contentBroadcastMapper.selectOne(contentBroadcast);
                        if (contentBroadcast1 != null) {
                            content.put("broad_status", contentBroadcast1.getBroadcastStatus());
                            content.put("duration", contentBroadcast1.getReplayDuration());
                            content.put("partner_num", contentBroadcast1.getPartnerNum());
                        } else {
                            content.put("broad_status", 0);
                            content.put("duration", "00:00");
                            content.put("dustart_timeration", "");
                        }
                        break;
                    //VR
                    case 11:
                        ContentVr contentVr = new ContentVr();
                        contentVr.setContentId(Integer.valueOf(content.get("id").toString()));
                        contentVr.setIsDelete(1);
                        ContentVr contentVr1 = contentVrMapper.selectOne(contentVr);
                        if (contentVr1 != null) {
                            content.put("duration", contentVr1.getDuration());
                            content.put("video_source", contentVr1.getUrl());
                        } else {
                            content.put("duration", "00:00");
                            content.put("video_source", "");
                        }
                        break;
                    case 12:
                        break;
                    //活动
                    case 13:
                        break;
                    case 25:
                        break;
                    case CommonConstants.CONTENT_LIVE:
                    case CommonConstants.CONTENT_LIVE_COURT:
                    case CommonConstants.CONTENT_LIVE_COURT_H5:
                        content.put("broad_status", Integer.valueOf(content.get("content_state").toString()) + 1);
                        content.put("card_type", 5);
                        break;
                    default:
                        break;
                }
                //布的新闻的时间  如“刚刚”、“xx分钟前”、“xx小时前”、“x天前”
                if (null != content.get("publish_time")) {
                    content.put("publish_time", DateUtil.fromTodayFormat((Date) content.get("publish_time")));
                }
            }


        }
        return contentList;

    }

    /**
     * app内容模块 图集详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    public ObjectRestResponse<Map<String, Object>> getImageDetail(Integer cate, Integer cid, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
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
        AppContent result = getImageDetailResult(content, uid, chlist);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 图集详情页接口返回-组装
     * data  内容数据对象
     * uid  用户ID
     */
    private AppContent getImageDetailResult(Content data, Integer uid, List<ChannelContent> chlist) {
        System.out.println("start now!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        AppContent appContent = new AppContent();
        Map<String, Object> result = new HashMap<>(16);
        if (data.getScoreTaskId() > 0) {
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
        if (uid != null) {
            fav.setUid(uid);
        }
        fav.setType(data.getContentType());
        Fav resultFav = favMapper.selectOne(fav);
        appContent.setIs_faved(resultFav == null ? 0 : 1);
        //获取评论设置
        Setting resultSet = settingMapper.selectSettingByName("comment_tourist");
        appContent.setComment_tourist(resultSet != null ? true : false);
        Setting resultSet2 = settingMapper.selectSettingByName("comment_ops");
        appContent.setPosts_ops(Integer.parseInt(resultSet2.getValue()));
        //如果为先审后发，则用已审核的评论数来代替总评论数
        if (null != resultSet2 && resultSet2.getValue().equals(String.valueOf(LiveCommonEnum.COMMENT_SHEN))) {
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
        ContentImage contentImage = new ContentImage();
        contentImage.setCid(data.getId());
        ContentImage contentImage1 = contentImageMapper.selectOne(contentImage);

        // set extend
        if (contentImageGroupReslut != null && contentImageGroupReslut.size() > 0) {
            if (contentImage1 != null && "1".equals(contentImage1.getDescType())) {
                for (ContentImageGroup imageGroup : contentImageGroupReslut) {
                    imageGroup.setDesc(data.getIntroduction());
                }
            }
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
            Object likes = redisTemplate.opsForHash().get(rekey2, data.getId());
            appContent.setLikes(likes != null ? (int) likes : 0);
        }
        return appContent;
    }

    /**
     * app内容模块 图集详情页接口
     *
     * @param keyword
     * @param page
     * @param pcount
     * @return
     */
    public ObjectRestResponse<Map<String, Object>> getSearchList(String keyword, Integer page, Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        Map paramsMap = new HashMap(16);
        paramsMap.put("keyWord", keyword);
        Page<Map> result = PageHelper.startPage(page, pcount);
        List<Map> searchListByKeyWord = contentMapper.getSearchListByKeyWord(paramsMap);
        Map returnMap = new HashMap(16);
        returnMap.put("bind", searchListByKeyWord);
        channelObjectRestResponse.setData(returnMap);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 专题详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getSpecialDetail(Integer cate, Integer cid, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
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
        if (uid != null) {
            fav.setUid(uid);
        }
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
    public ObjectRestResponse<Map<String, Object>> getVideoDetail(Integer cate, Integer cid, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
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
        if (uid != null) {
            fav.setUid(uid);
        }
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
        String url = contentBiz.getDetailUrlSu(data.getId(), data.getContentType(), data.getCategory());
        appContent.setShare_url(url);
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
        ContentVideo contentVideoReslut = contentVideoMapper.selectByPrimaryKey(data.getId());
        // set extend
        appContent.setExtend(contentVideoReslut);
        appContent.setActivity_static(2);
        //set binds
//        List<Map> contentVideoDetailById = contentMapper.getContentVideoDetailById(data.getId());
        List<Map> contentVideoDetailById = contentMapper.getContentBroadcastBindById(data.getId());
        appContent.setBind(contentVideoDetailById);


        SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日");
        appContent.setCreate_time(sdf.format(data.getCreateTime()) + " 发布");

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
     * 关联数据获取
     * 视频的相关内容（推荐，绑定）
     *
     * @param cid
     * @return
     */
    public List<Map> getVideoBind(Integer cid) {


        return null;
    }

    /**
     * APP内容-VR详情页接口
     */
    public ObjectRestResponse<Map<String, Object>> getVrDetail(Integer cate, Integer cid, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
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
    public ObjectRestResponse<Map<String, Object>> getNewSpecial(Integer cate, Integer cid) {
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
        List specialsList = new ArrayList();
        //获取目录和绑定的内容
        Map specialMap = contentSpecialMapper.selectSpecialContentListByCid(data.getId());
        if (specialMap == null || specialMap.get("target_cid") == null) {
            return result;
        } else {
            String targetCids = specialMap.get("target_cid").toString();
            String catalogId = specialMap.get("id").toString();
            String[] targetCidsList = targetCids.split(",");
            AppSpecialContentInfo appSpecialContentInfo = new AppSpecialContentInfo();
            appSpecialContentInfo.setCid(data.getId());
            appSpecialContentInfo.setCatalogId(Integer.valueOf(catalogId));
            appSpecialContentInfo.setIds(Arrays.asList(targetCidsList));
            List<Map> maps = contentSpecialMapper.selectBindSpecialContentListByCid(appSpecialContentInfo);
            result.put("specials", maps);
        }
        return result;
    }

    /**
     * app内容模块 单篇内容收藏状态接口
     */
    public ObjectRestResponse<Map<String, Object>> getOneFavStatus(Integer uid, Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        AppFavInfo appFav = new AppFavInfo();
        Fav fav = new Fav();
        fav.setCid(cid);
        fav.setUid(uid);
        fav.setType(0);
        Fav favResult = favMapper.selectOne(fav);
        //set fav_status  default 0
        appFav.setFav_status(0);
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
    public ObjectRestResponse<Map<String, Object>> getBurstList(Integer page, Integer pcount) {
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
    public ObjectRestResponse<Map<String, Object>> addBurst(Integer user_id, String user_name,
                                                            String address, String point,
                                                            String remark, String content,
                                                            String images, String videos) {
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
        if (null != images) {
            String[] imagesArray = images.split(",");
            for (String data : imagesArray) {
                BurstResource burstResource = new BurstResource();
                burstResource.setBurstId(burst.getId());
                burstResource.setIsDelete(1);
                burstResource.setCreateTime(new Date());
                burstResource.setUrl(data);
                burstResource.setType(1);//图片类型为1
                burstResourceMapper.insert(burstResource);
            }
        }
        //save burstResource video type
        if (null != videos) {
            String[] videosArray = videos.split(",");
            for (String data : videosArray) {
                BurstResource burstResource = new BurstResource();
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
    public ObjectRestResponse<Map<String, Object>> getBindNews(Integer burst_id, Integer page, Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        List resultList = new LinkedList();
        PageHelper.startPage(page, pcount);
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