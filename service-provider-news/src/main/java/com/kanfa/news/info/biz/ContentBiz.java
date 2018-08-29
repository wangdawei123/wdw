package com.kanfa.news.info.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.constant.app.APPCommonEnum;
import com.kanfa.news.common.constant.app.AppCommonType;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.constant.web.WebCommonParams;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.*;
import com.kanfa.news.info.config.RequestConfiguration;
import com.kanfa.news.info.entity.*;
import com.kanfa.news.info.mapper.*;
import com.kanfa.news.info.mongodb.entity.ChannelInfoOfLog;
import com.kanfa.news.info.mongodb.entity.ContentArticleLog;
import com.kanfa.news.info.mongodb.entity.ContentArticleLogInfo;
import com.kanfa.news.info.mongodb.mapper.ContentDao;
import com.kanfa.news.info.mongodb.mapper.ViewContentDao;
import com.kanfa.news.info.vo.admin.app.AppSpecialContentInfo;
import com.kanfa.news.info.vo.admin.info.*;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountContentInfo;
import com.kanfa.news.info.vo.admin.my.MyContentInfo;
import com.kanfa.news.info.vo.admin.video.*;
import com.kanfa.news.info.vo.admin.vr.VRContentAddInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.kanfa.news.common.constant.news.NewsEnumsConsts.ContentOfContentType.VR;

/**
 * 内容表（含专题，文章，图集，视频类型）
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-05 14:23:28
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ContentBiz extends BaseBiz<ContentMapper, Content> {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ChannelContentMapper channelContentMapper;
    @Autowired
    private VideoTagBindMapper videoTagBindMapper;
    @Autowired
    private VideoTagMapper videoTagMapper;
    @Autowired
    private KpiCountMapper kpiCountMapper;
    @Autowired
    private ContentArticleMapper contentArticleMapper;
    @Autowired
    private CorpUserMapper corpUserMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private ContentVideoMapper contentVideoMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private ActivityRaffleMapper activityRaffleMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ChannelContentCardMapper channelContentCardMapper;
    @Autowired
    private ContentStandardMapper contentStandardMapper;
    @Autowired
    private VideoSourceMapper videoSourceMapper;
    @Autowired
    private VideoTypeMapper videoTypeMapper;
    @Autowired
    private ActivityContentBindMapper activityContentBindMapper;
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private ContentDao contentDao;
    @Autowired
    private SpecialCatalogMapper specialCatalogMapper;
    @Autowired
    private ContentSpecialMapper contentSpecialMapper;
    @Autowired
    private ContentImageMapper contentImageMapper;
    @Autowired
    private ContentImageGroupMapper contentImageGroupMapper;
    @Autowired
    private ContentBurstMapper contentBurstMapper;
    @Autowired
    private BurstMapper burstMapper;
    @Autowired
    private AdminLogMapper adminLogMapper;
    @Autowired
    private LiveMapper liveMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ViewContentDao viewContentDao;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private FavMapper favMapper;
    @Autowired
    private ContentBroadcastMapper contentBroadcastMapper;
    @Autowired
    private UserDeviceMapper userDeviceMapper;
    @Autowired
    private VideoColumnBindMapper videoColumnBindMapper;
    @Autowired
    private VideoTagBiz videoTagBiz;
    @Autowired
    private ContentVrMapper contentVrMapper;
    @Resource
    private RequestConfiguration requestConfiguration;
    private final long PUBTIME = 86400 * 7;
    private final Integer HOT_THRESHOT = 500;

    private final String CONTENT_REDIS_LIST_KEY = "content_redis_list";
    private final String LIVE_REDIS_LIST_KEY = "live_redis_list";

    /**
     * 获取多条
     *
     * @param
     * @return
     */
    public List<Map<String, Object>> getList(List<Integer> cidsKey) {
        List<Map<String, Object>> newList = new ArrayList();
        List<Content> list = contentMapper.getList(cidsKey);
        for (Content c : list) {
            Map<String, Object> cons = new HashMap<>(5);
            cons.put("id", c.getId());
            cons.put("type", c.getContentType());
            cons.put("title", c.getTitle());
            cons.put("cate", c.getCategory());
            cons.put("share_url", getDetailUrlSu(c.getId(), c.getContentType(), c.getCategory()));
            newList.add(cons);
        }
        return newList;
    }

    /**
     * 分页查询
     *
     * @param entity
     * @return
     */
    public TableResultResponse<ContentInfo> getPage(ContentInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<ContentInfo> list = contentMapper.getPage(entity);
        for (ContentInfo contentInfo : list) {
            contentInfo.setArticleUrl(getDetailUrlSu(contentInfo.getId(), contentInfo.getContentType(), contentInfo.getCategory()));
        }
        return new TableResultResponse<>(result.getTotal(), list);
    }


    /**
     * 将不同类型的资讯的内容补全
     *
     * @param contents
     * @return
     */
    public List<Map<String, Object>> getListTypeData(List<ContentInfo> contents) {
        //获取标签颜色字典
        String tag_color = AppCommonType.tag_color;
        JSONObject object = JSON.parseObject(tag_color);
        String value = object.getString("value");
        JSONObject valueObject = JSON.parseObject(value);
        //热门
        String contentJson = valueObject.getString("hot");
        JSONObject hotObject = JSON.parseObject(contentJson);
        String font_color = hotObject.getString("font_color");
        String border_color = hotObject.getString("border_color");
        //原创
        String original = valueObject.getString("original");
        JSONObject originalObject = JSON.parseObject(original);
        String font_color_original = originalObject.getString("font_color");
        String border_color_original = originalObject.getString("border_color");
        //缩略图样式
        String card_type = AppCommonType.card_type;
        JSONObject cardTypeObject = JSON.parseObject(card_type);
        String cardTypeValue = cardTypeObject.getString("value");
        JSONObject cardType = JSON.parseObject(cardTypeValue);

        //获取附近最大距离值
        Setting distance_value = settingMapper.selectSettingByName("distance_value");
        String kilometre = distance_value.getValue();

        List<Map<String, Object>> data = new ArrayList<>();
        for (ContentInfo content : contents) {
            Map<String, Object> map = new HashMap<>(5);
            //标签
            Map<String, Object> tags = getContentTags(content, null);
            map.put("tags", tags);
            //目前只针对文章下发热门和附近标签
            if (content.getContentType().equals(LiveCommonEnum.CONTENT_STYLE_ADV)) {
                //判断是否是热门
                if (content.getViewCount() >= HOT_THRESHOT && System.currentTimeMillis() - content.getPublish_time().getTime() < PUBTIME) {
                    Map<String, Object> hotMap = new LinkedHashMap<>(5);
                    hotMap.put("font_color", font_color);
                    hotMap.put("border_color", border_color);
                    hotMap.put("name", "热门");
                    map.put("other_tags", hotMap);
                }
            }
            List<Integer> typeList = new ArrayList<>();
            typeList.add(2);
            typeList.add(3);
            typeList.add(4);
            if (content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL) && typeList.contains(content.getContentType())) {
                Map<String, Object> originalMap = new LinkedHashMap<>(5);
                originalMap.put("font_color", font_color_original);
                originalMap.put("border_color", border_color_original);
                originalMap.put("name", "原创");
                map.put("tags", originalMap);
            }
            String cardTypeString = cardType.getString(content.getType() + "");
            map.put("card_type", cardTypeString);
            map.put("image_num", 0);
            map.put("duration", "");
            map.put("images", "");
            map.put("source", "");
            map.put("chanId", content.getChannelId());
            map.put("id", content.getId());
            map.put("title", content.getTitle());
            map.put("source_type", content.getSourceType());
            switch (content.getContentType()) {
                case 1:
                    //专题
                    break;
                case 2:
                    //文章
                    //文章缩略图   $chanId有值是首页频道列表，$chanId无值是首页搜索列表（文章只有无图和小图）
                    //首页搜索，优先选择小图模式，如果不是那么全部是无图模式
                    map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_NO);
                    map.put("image", "");
                    ChannelContentCard card = new ChannelContentCard();
                    card.setContentId(content.getId());
                    List<ChannelContentCard> cards = channelContentCardMapper.select(card);
                    if (cards.size() > LiveCommonEnum.CONSTANT_ZERO) {
                        for (int i = LiveCommonEnum.CONSTANT_ZERO; i < cards.size(); i++) {
                            if (cards.get(i).getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_SMALL)) {
                                map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_SMALL);
                                map.put("image", cards.get(i).getImage());
                                break;
                            }
                        }
                    }
                    //文章来源和作者
                    ContentArticle article = new ContentArticle();
                    article.setCid(content.getId());
                    article.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentArticle contentArticle = contentArticleMapper.selectOne(article);
                    if (contentArticle != null) {
                        map.put("source", contentArticle.getSource());
                        map.put("author", contentArticle.getAuthor());
                    } else {
                        map.put("source", "");
                        map.put("author", "");
                    }
                    // 将展现样式作为type类型来展示
                    if (content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_BOOK)) {
                        map.put("type", LiveCommonEnum.CONTENT_JUDGMENT);
                    } else if (content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_ADV)) {
                        map.put("type", LiveCommonEnum.CONTENT_POST);
                    }
                    break;
                case 3:
                    //图集
                    //图集个数
                    ContentImage image = new ContentImage();
                    image.setCid(content.getId());
                    image.setStat(LiveCommonEnum.NO_DEL_STATUS);
                    ContentImage contentImage = contentImageMapper.selectOne(image);
                    if (contentImage != null) {
                        map.put("image_num", contentImage.getNum());
                        List<String> imagelist = new ArrayList<>();
                        List<ContentImageGroup> images = contentImageGroupMapper.findImages(content.getId(), null);
                        for (ContentImageGroup group : images) {
                            imagelist.add(group.getImage());
                        }
                        map.put("images", imagelist);
                    } else {
                        map.put("image_num", LiveCommonEnum.CONSTANT_ZERO);
                    }
                    break;
                case 4:
                    //视频
                    //视频时长 源地址
                    ContentVideo video = new ContentVideo();
                    video.setCid(content.getId());
                    video.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentVideo contentVideo = contentVideoMapper.selectOne(video);
                    if (contentVideo != null) {
                        map.put("duration", contentVideo.getDuration());
                        map.put("video_source", contentVideo.getUrl());
                    } else {
                        map.put("duration", "00:00");
                        map.put("video_source", "");
                    }
                    break;
                case 9:
                    //直播
                    ContentBroadcast broadcast = new ContentBroadcast();
                    broadcast.setCid(content.getId());
                    broadcast.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentBroadcast contentBroadcast = contentBroadcastMapper.selectOne(broadcast);
                    if (contentBroadcast != null) {
                        map.put("broad_status", contentBroadcast.getBroadcastStatus());
                        map.put("duration", contentBroadcast.getReplayDuration());
                        if (contentBroadcast.getStartTime() != null) {
                            //现在的年份
                            int i = DateUtil.calendar.get(Calendar.YEAR);
                            //数据库年份
                            Integer startTime = contentBroadcast.getStartTime();
                            Date date = new Date(startTime);
                            Calendar now = Calendar.getInstance();
                            now.setTime(date);
                            int year = now.get(Calendar.YEAR);
                            if (i == year) {
                                SimpleDateFormat dfs = new SimpleDateFormat("MM月dd日 HH:mm");
                                map.put("start_time", dfs.format(date));
                            } else {
                                SimpleDateFormat dfs = new SimpleDateFormat("YY年MM月dd日 HH:mm");
                                map.put("start_time", dfs.format(date));
                            }
                        } else {
                            map.put("start_time", "");
                        }
                        //人数
                        map.put("partner_num", contentBroadcast.getPartnerNum());
                        // 加载默认图片
                        if (StringUtils.isEmpty(contentBroadcast.getVideoImg())) {
                            map.put("image", "http://kanfaimage.oss-cn-beijing.aliyuncs.com/5.png");
                        }
                    } else {
                        map.put("duration", "00:00");
                        map.put("broad_status", 0);
                        map.put("start_time", "");
                    }
                    break;
                case 11:
                    //VR
                    ContentVr vr = new ContentVr();
                    vr.setContentId(content.getId());
                    vr.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentVr contentVr = contentVrMapper.selectOne(vr);
                    if (contentVr != null) {
                        map.put("duration", contentVr.getDuration());
                        map.put("video_source", contentVr.getUrl());
                    } else {
                        map.put("duration", "00:00");
                        map.put("video_source", "");
                    }
                    break;
                case 12:
                    //问答
                    break;
                case 13:
                    //活动
                    break;
                case 25:
                    //政法
                    break;
                case 22:
                case 23:
                case 24:
                    map.put("broad_status", content.getCheckStatus() + LiveCommonEnum.CONSTANT_ONE);
                    map.put("duration", content.getDuration());
                    map.put("image", content.getCoverImg());
                    map.put("live_type_id", "live_type_id");
                    map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_FIVE);
                    break;
                default:
                    break;
            }
            //分享url
            String share_url = getShareUrlSu(content.getId(), content.getContentType(), content.getCategory());
            map.put("share_url", share_url);
            //详情url
            String url = getDetailUrlSu(content.getId(), content.getContentType(), null);
            if (content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL)) {
                map.put("url", url + "?original=1");
            }
            //几分钟前
            if (content.getPublish_time() != null) {
                map.put("pub_time", DateUtil.fromToday(content.getPublish_time()));
            } else {
                map.put("pub_time", "");
            }
            //阅读量等于初始值+真实值

            //评论量等于初始值+真实值
            Setting ops = settingMapper.selectSettingByName("comment_ops");
            String opsValue = ops.getValue();
            if (opsValue.equals(LiveCommonEnum.CONSTANT_ONE)) {
                map.put("comments", content.getCommentCheckedCount());
            } else {
                map.put("comments", content.getCommentCount());
            }
            // 评论量显示转换
            String viewDisplay = CommentViewUtil.getCommentViewDisplay("comment", content.getCommentCount());
            map.put("comments", viewDisplay);
            // 阅读量量显示转换
            String display = CommentViewUtil.getCommentViewDisplay("view", content.getViewCount());
            map.put("views", display);
            data.add(map);
        }
        return data;
    }


    public Map<String, Object> getContentTags(ContentInfo content, Integer channlId) {
        //获取标签颜色字典
        String tag_color = AppCommonType.tag_color;
        JSONObject object = JSON.parseObject(tag_color);
        String value = object.getString("value");
        JSONObject valueObject = JSON.parseObject(value);
        String contentJson = valueObject.getString("content");
        JSONObject hotObject = JSON.parseObject(contentJson);
        String font_color = hotObject.getString("font_color");
        String border_color = hotObject.getString("border_color");
        //内容类型
        Map<Integer, String> typeMap = AppCommonType.getTypeMap();
        //直播状态
        Map<Integer, String> broadcastTypeMap = AppCommonType.getBroadcastTypeMap();

        Map<String, Object> tags = new HashMap<>(9);
        tags.put("font_color", font_color);
        tags.put("border_color", border_color);

        if (content.getContentType().equals(LiveCommonEnum.CONTENT_NEWS)) {
            //首页列表
            if (channlId != null) {
                //置顶标签

            }
        } else if (content.getContentType().equals(LiveCommonEnum.CONTENT_BROADCAST)) {
            //直播状态
            ContentBroadcast broadcast = new ContentBroadcast();
            broadcast.setCid(content.getId());
            broadcast.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
            ContentBroadcast broad = contentBroadcastMapper.selectOne(broadcast);
            if (broad == null) {
                broad.setBroadcastStatus(LiveCommonEnum.PRIVIEW_STATUS1);
            }
            String s = broadcastTypeMap.get(broad.getBroadcastStatus());
            tags.put("name", s);
        } else if (content.getContentType().equals(LiveCommonEnum.CONTENT_LIVE) || content.getContentType().equals(LiveCommonEnum.CONTENT_LIVE_COURT) || content.getContentType().equals(LiveCommonEnum.CONTENT_LIVE_COURT_H5)) {
            tags.put("name", "直播");
        } else {
            if (!content.getContentType().equals(LiveCommonEnum.CONTENT_ALBUM) && !content.getContentType().equals(LiveCommonEnum.CONTENT_VIDEO)) {
                String s = typeMap.get(content.getContentType());
                tags.put("name", s);
            }
        }
        return tags;
    }


    public Map<String, Object> addContentsListData(Map<String, Object> map, ContentInfo content) {
        switch (content.getType()) {
            case 1:
                //专题
                map.put("card_type", LiveCommonEnum.CARD_TYPE_SPECAIL);
                break;
            case 2:
                //文章
                //文章缩略图   $chanId有值是首页频道列表，$chanId无值是首页搜索列表（文章只有无图和小图）
                if (content.getChannelId() != null) {
                    ChannelContentCard card = new ChannelContentCard();
                    card.setContentId(content.getId());
                    card.setChannelId(content.getChannelId());
                    ChannelContentCard contentCard = channelContentCardMapper.selectOne(card);
                    if (contentCard != null) {
                        if (contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_THREE) || contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_TEN)) {
                            map.put("image", "");
                            map.put("images", contentCard.getImage());
                        } else {
                            map.put("image", contentCard.getImage());
                            map.put("images", "");
                        }
                        if (contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_TEN)) {
                            contentCard.setCardType(LiveCommonEnum.CHANNEL_CARDTYPE_THREE);
                        }
                        map.put("card_type", contentCard.getCardType());
                    } else {
                        //万一没有传频道ID则展示无图或者小图
                        map.put("card_type", 0);
                        if (StringUtils.isNotEmpty(content.getImage())) {
                            map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_SMALL);
                        }
                    }
                }
                break;
            case 3:
                //图集
                //图集个数
                ContentImage image = new ContentImage();
                image.setCid(content.getId());
                image.setStat(LiveCommonEnum.NO_DEL_STATUS);
                ContentImage contentImage = contentImageMapper.selectOne(image);
                if (contentImage != null) {
                    map.put("image_num", contentImage.getNum());
                    List<ContentImageGroup> images = contentImageGroupMapper.findImages(content.getId(), LiveCommonEnum.CONSTANT_THREE);
                    if (images.size() < LiveCommonEnum.CONSTANT_THREE) {
                        //设置成单大图模式
                        map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_BIG);
                        map.put("images", "");
                    } else {
                        map.put("images", images);
                    }
                } else {
                    map.put("image_num", 0);
                }
                break;
            case 4:
                //视频
                //视频时长 源地址
                ContentVideo video = new ContentVideo();
                video.setCid(content.getId());
                video.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                ContentVideo contentVideo = contentVideoMapper.selectOne(video);
                if (contentVideo != null) {
                    map.put("duration", contentVideo.getDuration());
                    map.put("video_source", contentVideo.getUrl());
                } else {
                    map.put("duration", "00:00");
                    map.put("video_source", "");
                }
                break;
            case 9:
                //直播
                ContentBroadcast broadcast = new ContentBroadcast();
                broadcast.setCid(content.getId());
                broadcast.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                ContentBroadcast contentBroadcast = contentBroadcastMapper.selectOne(broadcast);
                if (contentBroadcast != null) {
                    map.put("live_stat", contentBroadcast.getBroadcastStatus());
                    map.put("duration", contentBroadcast.getReplayDuration());
                    if (contentBroadcast.getStartTime() != null) {
                        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date startTime = dfs.parse(contentBroadcast.getStartTime() + "");
                            map.put("preview_time", DateUtil.between(new Date(), startTime));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        map.put("preview_time", "");
                    }
                    //人数
                    map.put("video_num", CovertLiveNum.change(contentBroadcast.getPartnerNum()));
                    // 加载默认图片
                    if (StringUtils.isEmpty(contentBroadcast.getVideoImg())) {
                        map.put("image", "http://kanfaimage.oss-cn-beijing.aliyuncs.com/5.png");
                    }
                    if (contentBroadcast.getBroadcastStatus().equals(LiveCommonEnum.PRIVIEW_STATUS1)) {
                        map.put("video_source", contentBroadcast.getPreviewUrl());
                    } else if (contentBroadcast.getBroadcastStatus().equals(LiveCommonEnum.LIVE_STATUS1)) {
                        map.put("video_source", contentBroadcast.getLiveUrl());
                    } else if (contentBroadcast.getBroadcastStatus().equals(LiveCommonEnum.REVIEW_STATUS1)) {
                        map.put("video_source", contentBroadcast.getReviewUrl());
                    }
                } else {
                    map.put("duration", "00:00");
                    map.put("live_stat", 0);
                    map.put("preview_time", "");
                }
                break;
            case 11:
                //VR
                ContentVr vr = new ContentVr();
                vr.setContentId(content.getId());
                vr.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                ContentVr contentVr = contentVrMapper.selectOne(vr);
                if (contentVr != null) {
                    map.put("duration", contentVr.getDuration());
                    map.put("video_source", contentVr.getUrl());
                } else {
                    map.put("duration", "00:00");
                    map.put("video_source", "");
                }
                map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_FIVE);
                break;
            case 25:
                //政法
                ChannelContentCard card = new ChannelContentCard();
                card.setContentId(content.getId());
                card.setChannelId(content.getChannelId());
                ChannelContentCard contentCard = channelContentCardMapper.selectOne(card);
                if (contentCard != null) {
                    map.put("card_type", contentCard.getCardType());
                }
                break;
            default:
                break;
        }
        //几分钟前
        if (content.getPublish_time() != null) {
            map.put("pub_time", DateUtil.between(content.getPublish_time(), new Date()));
        } else {
            map.put("pub_time", "");
        }
        if (StringUtils.isNotEmpty(content.getCustomUrl())) {
            map.put("url", content.getCustomUrl());
        } else {
            map.put("url", getDetailUrlSu(content.getId(), content.getType(), content.getCategory()));
        }
        if (content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL) && content.getType().equals(LiveCommonEnum.SSPECIAL_TYPE_LIVE)) {
            map.put("url", content.getUrl() + "?original=1");
        }
        // 将展现样式作为type类型来展示
        if (content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_BOOK)) {
            map.put("type", LiveCommonEnum.CONTENT_JUDGMENT);
        } else if (content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_ADV)) {
            map.put("type", LiveCommonEnum.CONTENT_POST);
        }
        return map;
    }


    public Map<String, Object> addContentsListDataForEs(Map<String, Object> map) {
        Integer contentId = Integer.parseInt(map.get("id").toString());
//        Integer fromType = Integer.parseInt(map.get("fromType").toString());
        Integer channelId = Integer.parseInt(map.get("chan_id").toString());
        Integer channelContentType = Integer.parseInt(map.get("content_type").toString());
        ContentVideo contentVideo = null;
        switch (channelContentType) {
            case 1:
                //专题
                map.put("card_type", LiveCommonEnum.CARD_TYPE_SPECAIL);
                break;
            case 2:
                //文章
                //文章缩略图   $chanId有值是首页频道列表，$chanId无值是首页搜索列表（文章只有无图和小图）
                if (contentId != null) {
                    ChannelContentCard card = new ChannelContentCard();
                    card.setContentId(contentId);
                    card.setChannelId(channelId);
                    ChannelContentCard contentCard = channelContentCardMapper.selectOne(card);
                    if (contentCard != null) {
                        if (contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_THREE) || contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_TEN)) {
                            map.put("image", null);
                            try {
                                List<String> images = (List<String>) PHPSerializer.unserialize(contentCard.getImage().getBytes(), "utf-8");
                               /* List<Map<String,String>> imagesMap = new ArrayList<Map<String,String>>(null == images?0:images.size());
                                if(null != images){
                                    for (String image:images) {
                                        Map<String,String> imageMap = new HashMap<String,String>(1);
                                        imageMap.put("image",image);
                                        imagesMap.add(imageMap);
                                    }
                                }*/
                                map.put("images", images);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        } else {
                            map.put("image", contentCard.getImage());
                            map.put("images", null);
                        }
                        if (contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_TEN)) {
                            contentCard.setCardType(LiveCommonEnum.CHANNEL_CARDTYPE_THREE);
                        }
                        map.put("card_type", contentCard.getCardType());
                    } else {
                        //万一没有传频道ID则展示无图或者小图
                        map.put("card_type", 0);
                        if (null == map.get("image") || StringUtils.isNotEmpty(map.get("image").toString())) {
                            map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_SMALL);
                        }
                    }
                }
                break;
            case 3:
                //图集
                //图集个数
                ContentImage image = new ContentImage();
                image.setCid(contentId);
                image.setStat(LiveCommonEnum.NO_DEL_STATUS);
                ContentImage contentImage = contentImageMapper.selectOne(image);
                if (contentImage != null) {

                    List<ContentImageGroup> images = contentImageGroupMapper.findImages(contentId, image.getNum());
                    List<String> imagesList = new ArrayList<String>(images.size());
                    if (null != images) {

                        for (ContentImageGroup contentImageGroup : images) {
                            imagesList.add(contentImageGroup.getImage());
                        }

                    }
                    map.put("images", imagesList);
                    map.put("image_num", contentImage.getNum());
                    if (images.size() < LiveCommonEnum.CONSTANT_THREE) {

                        //设置成单大图模式
                        map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_BIG);

                    } else {
                        //设置成图集
                        map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_FOUR);
//                        map.put("imageNum",contentImage.getNum());
//                        map.put("images",images);
                    }
                } else {
                    map.put("image_num", 0);
                    map.put("images", new ArrayList());
                }
                break;
            case 4:
                //视频
                //视频时长 源地址
                ContentVideo video = new ContentVideo();
                video.setCid(contentId);
                video.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                contentVideo = contentVideoMapper.selectOne(video);
                if (contentVideo != null) {
                    map.put("duration", contentVideo.getDuration());
                    map.put("video_source", contentVideo.getUrl());
                } else {
                    map.put("duration", "00:00");
                    map.put("video_source", "");
                }
                break;

            case 11:
                //VR
                ContentVr vr = new ContentVr();
                vr.setContentId(contentId);
                vr.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                ContentVr contentVr = contentVrMapper.selectOne(vr);
                if (contentVr != null) {
                    map.put("duration", contentVr.getDuration());
                    map.put("video_source", contentVr.getUrl());
                } else {
                    map.put("duration", "00:00");
                    map.put("video_source", "");
                }
                map.put("card_type", LiveCommonEnum.CHANNEL_CARDTYPE_FIVE);
                break;
            default:
                break;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null != map.get("custom_url") && StringUtils.isNotEmpty(map.get("custom_url").toString())) {
            map.put("url", map.get("custom_url").toString());
        } else {
            map.put("url", getDetailUrlSu(contentId, Integer.parseInt(map.get("content_type").toString()), Integer.parseInt(map.get("cate").toString())));
        }
        if (map.get("source_type").toString().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL) && map.get("content_type").toString().equals(LiveCommonEnum.SSPECIAL_TYPE_LIVE)) {
            map.put("url", contentVideo.getUrl() + "?original=1");
        }
        // 将展现样式作为type类型来展示
        if (map.get("content_style").toString().equals(LiveCommonEnum.CONTENT_STYLE_BOOK)) {
            map.put("type", LiveCommonEnum.CONTENT_JUDGMENT);
        } else if (map.get("content_style").toString().equals(LiveCommonEnum.CONTENT_STYLE_ADV)) {
            map.put("type", LiveCommonEnum.CONTENT_POST);
        }
        return map;
    }

    public List<Map<String, Object>> getExtendsMyarticle(List<ContentInfo> list) {
        List<Map<String, Object>> newList = new ArrayList<>();
        String tag_color = AppCommonType.tag_color;
        String typeJson = AppCommonType.type;
        JSONObject object = JSON.parseObject(tag_color);
        String value = object.getString("value");
        JSONObject valueObject = JSON.parseObject(value);
        String contentJson = valueObject.getString("content");
        JSONObject contentObject = JSON.parseObject(contentJson);
        String font_color = contentObject.getString("font_color");
        String border_color = contentObject.getString("border_color");

        for (ContentInfo content : list) {
            Map<String, Object> data = new HashMap<>(16);
            data.put("id", content.getId());
            data.put("type", content.getContentType());
            data.put("title", content.getTitle());
            data.put("desc", content.getIntroduction());
            data.put("cate", content.getCategory());
            data.put("views", content.getViewCount());
            data.put("image", content.getImage());
            Map<String, Object> tags = new HashMap<>(6);
            List<Map<String, Object>> tagsList = new ArrayList<>();
            if (content.getContentType().equals(LiveCommonEnum.CONTENT_NEWS)) {
                if (StringUtils.isNotEmpty(content.getTag())) {
                    tags.put("name", content.getTag());
                    tags.put("font_color", font_color);
                    tags.put("border_color", border_color);
                }
            } else {
                if (content.getContentType().equals(LiveCommonEnum.CONTENT_BROADCAST)) {
                    ContentBroadcast contentBroadcast = contentBroadcastMapper.selectByCid(content.getId());
                    if (contentBroadcast != null) {
                    } else {
                        contentBroadcast.setBroadcastStatus(LiveCommonEnum.LIVE_STATUS);
                    }
                    data.put("broad_status", contentBroadcast.getBroadcastStatus());
                    data.put("duration", contentBroadcast.getReplayDuration());
                    if (StringUtils.isNotEmpty(content.getImage())) {
                        data.put("image", "http://kanfaimage.oss-cn-beijing.aliyuncs.com/5.png");
                    }
                } else {
                    Integer typ = content.getContentType();
                    JSONObject jsonObject = JSON.parseObject(typeJson);
                    String string = jsonObject.getString("value");
                    JSONObject json = JSON.parseObject(string);
                    String o = (String) json.get(typ + "");
                    tags.put("name", o);
                    tags.put("font_color", font_color);
                    tags.put("border_color", border_color);
                }
            }
            tagsList.add(tags);
            data.put("tags", tagsList);
            Integer contentType = content.getContentType();
            String card_type = AppCommonType.card_type;
            JSONObject jsonObject = JSON.parseObject(card_type);
            String string = jsonObject.getString("value");
            JSONObject json = JSON.parseObject(string);
            String o = (String) json.get(contentType);
            data.put("card_type", o);
            switch (contentType) {
                case 1:
                    //专题--下面if进行版本判断
                    if (true) {
                        data.put("url", "待定。");
                    }
                    break;
                case 2:
                    //文章
                    ChannelContentCard card = new ChannelContentCard();
                    card.setContentId(content.getId());
                    ChannelContentCard channelContentCard = channelContentCardMapper.selectOne(card);
                    Integer cardType = null;
                    if (channelContentCard != null) {
                        cardType = channelContentCard.getCardType();
                        if (cardType.equals(LiveCommonEnum.CHANNEL_CARDTYPE_THREE)) {
                            data.put("images", channelContentCard.getImage());
                        } else if (cardType.equals(LiveCommonEnum.CHANNEL_CARDTYPE_NO)) {
                            data.put("images", "");
                        } else if (cardType.equals(LiveCommonEnum.CHANNEL_CARDTYPE_SMALL)) {
                            data.put("images", channelContentCard.getImage());
                        } else if (cardType.equals(LiveCommonEnum.CHANNEL_CARDTYPE_BIG)) {
                            data.put("images", channelContentCard.getImage());
                        }
                    }
                    ContentArticle a = new ContentArticle();
                    a.setCid(content.getId());
                    ContentArticle contentArticle = contentArticleMapper.selectOne(a);
                    if (StringUtils.isEmpty(contentArticle.getSource())) {
                        data.put("src", "");
                    } else {
                        data.put("src", contentArticle.getSource());
                    }
                    data.put("card_type", cardType);
                    break;
                case 3:
                    //图集
                    //图集个数
                    ContentImage ci = new ContentImage();
                    ci.setCid(content.getId());
                    ci.setStat(LiveCommonEnum.NO_DEL_STATUS);
                    ContentImage contentImage = contentImageMapper.selectOne(ci);
                    data.put("image_num", contentImage.getNum());
                    break;
                case 4:
                    //视频
                    //视频时长
                    ContentVideo cv = new ContentVideo();
                    cv.setCid(content.getId());
                    cv.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentVideo contentVideo = contentVideoMapper.selectOne(cv);
                    data.put("duration", contentVideo.getDuration());
                    break;
                case 9:
                    //直播
                    break;
                case 11:
                    //VR
                    break;
                case 12:
                    //问答
                    break;
                default:
                    break;
            }
            String url = getDetailUrlSu(content.getId(), content.getContentType(), content.getCategory());
            data.put("share_url", url);
            newList.add(data);
        }
        return newList;
    }

    /**
     * 获取内容的分享落地页
     *
     * @param id
     * @param type
     * @param cate
     * @return
     */
    public String getShareUrlSu(Integer id, Integer type, Integer cate) {
        if (null == cate) {
            Content c = new Content();
            c.setId(id);
            Content content = contentMapper.selectOne(c);
            if (content != null) {
                cate = content.getCategory();
            } else {
                cate = 1;
            }
        }
        String url = null;
        switch (type) {
            case 1:
                //专题
                url = LiveCommonEnum.URL_PREFIX + "web/activity/special/index.html?category=" + cate + "&cid=" + id + "&share=1";
                break;
            case 2:
                //资讯   新分享页不需要share参数
                url = LiveCommonEnum.URL_PREFIX + "public/news/index.html?id=" + id;
                break;
            case 3:
                //图集
                url = LiveCommonEnum.URL_PREFIX + "web/share/image/index.html?id=" + id;
                break;
            case 4:
                //视频
                url = LiveCommonEnum.URL_PREFIX + "public/video/videoShare.html?id=" + id + "&type" + cate;
                break;
            case 9:
                //直播
                url = LiveCommonEnum.URL_PREFIX + "web/broadcast/index?cid=" + id + "&share=1";
                break;
            case 11:
                //VR
                url = LiveCommonEnum.URL_PREFIX + "http://a.app.qq.com/o/simple.jsp?pkgname=com.fawan.news";
                break;
            case 12:
                //问答
                url = LiveCommonEnum.URL_PREFIX + "web/problem/index/" + id + "/" + cate + "?share=1";
                break;
            case 22:
                //新直播
                url = LiveCommonEnum.URL_PREFIX + "public/live/index.html?id=" + id + "&share=1";
                break;
            case 23:
            case 24:
                url = LiveCommonEnum.URL_PREFIX + "web/live/index?id=" + id + "&share=1";
                break;
            default:
                break;
        }
        return url;
    }

    /**
     * //内容详情url
     *
     * @param id
     * @param type
     * @param cate
     * @return
     */
    public String getDetailUrlSu(Integer id, Integer type, Integer cate) {
        if (null == cate) {
            Content c = new Content();
            c.setId(id);
            Content content = contentMapper.selectOne(c);
            if (content != null) {
                cate = content.getCategory();
            } else {
                cate = 1;
            }
        }
        String url = null;
        switch (type.intValue()) {
            case 1:
                //专题
                url = LiveCommonEnum.URL_PREFIX + "public/specialDetailtest.html?cate=" + cate + "&cid=" + id;
                break;
            case 2:
                //资讯
                url = LiveCommonEnum.URL_PREFIX + "news/index/" + id + "/" + cate;
                break;
            case 3:
                //图集
                url = LiveCommonEnum.URL_PREFIX + "web/image/index/" + id + "/" + cate;
                break;
            case 4:
                //视频
                //url = LiveCommonEnum.URL_PREFIX + "web/video/index/" + id + "/" + cate;
                break;
            case 9:
                url = "#";
                break;
            case 11:
                //VR
                url = "#";
                break;
            case 12:
                //问答
                url = LiveCommonEnum.URL_PREFIX + "web/problem/index/" + id + "/" + cate;
                break;
            case 22:
                url = "#";
                break;
            case 24:
                url = "#";
                break;
            case 13:
                url = "#";
                break;
            default:
                break;
        }
        return url;
    }

    /**
     * 更新收藏数
     */
    public void updateFavs(Integer id, Integer type) {
        Long count = favMapper.selectCountNum(id, type);

        Content com = new Content();
        com.setId(id);
        Content content = contentMapper.selectByPrimaryKey(com);
        content.setCollectCount(new Long(count).intValue());
        contentMapper.updateByPrimaryKeySelective(content);
    }

    /**
     * 更新评论数
     */
    public void updateCommentCount(Integer id, Integer type) {
        Content content = contentMapper.selectByPrimaryKey(id);
        if (content != null) {
            if (LiveCommonEnum.CONSTANT_ONE.equals(type) && id.equals(LiveCommonEnum.EXTENSION_LIVE_ID)) {
                content.setCommentCount(content.getCommentCount() - LiveCommonEnum.CONSTANT_ONE);
            } else {
                //根据php代码，此处有问题--前台只显示已审核评论数，这个值无用
                content.setCommentCount(content.getCommentCount() + LiveCommonEnum.CONSTANT_ONE);
            }
            contentMapper.updateByPrimaryKeySelective(content);
        }
    }

    /**
     * 更新已审核评论数
     */
    public Integer updateCommentCheckedCount(Integer id) {
        Long count = commentMapper.selectCountnum(id);

        Content com = new Content();
        com.setId(id);
        Content content = contentMapper.selectByPrimaryKey(com);
        if (content != null) {
            content.setCommentCheckedCount(new Long(count).intValue());
            contentMapper.updateByPrimaryKeySelective(content);
        }
        return LiveCommonEnum.CONSTANT_ONE;
    }

    /**
     * web页--内容表和内容详情表一起查询
     *
     * @param id
     * @return
     */
    public ContentInfo getContentArticle(Integer id,Boolean flag) {
        ContentInfo info = contentMapper.getContentArticle(id);
        ContentInfo contentInfo = new ContentInfo();
        BeanUtils.copyProperties(info,contentInfo);
        contentInfo.setCreateTime(info.getCreateTime());
        //视频信息
        List<Map<String,Object>> extent=new ArrayList<>(1);
        Map<String,Object> video=new HashMap<>(1);
        if(!(null==(info.getVideo()))){
            video.put("video",info.getVideo());
        }else {
            video.put("video","");
        }
        if(StringUtils.isNotEmpty(info.getVideo())){
            video.put("video_img",info.getVideoImage());
        }else {
            video.put("video_img","http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png");
        }
        extent.add(video);
        contentInfo.setExtent(extent);

        if (info != null && flag ) {
           info.setViewCount(info.getViewCount() + 1);
            contentMapper.updateByviews(info);
        }

        List<VideoTag> videoTagList=videoTagBiz.getTagListByConentId(id);
        String tag = "";
        for(int i = 0 ; i < videoTagList.size() ; i++){
            if(i < videoTagList.size()-1){
                tag += videoTagList.get(i).getName() + ",";
            }else{
                tag += videoTagList.get(i).getName();
            }
        }
        contentInfo.setTag(tag);
        return contentInfo;
    }

    /**
     * 获取热门--web
     */
    public List<ContentInfo> hotgetlist(Integer contentType){
        List<ContentInfo> list = contentMapper.hotgetlist(contentType);
        List<ContentInfo> data = new ArrayList<>();
        for(ContentInfo content : list){
            if(content.getContentType().equals(WebCommonParams.CONTENT_TYPE_MAPS)){
                content.setImageNum(list.size());
            }else if(content.getContentType().equals(WebCommonParams.CONTENT_TYPE_VIDEO)){
                ContentVideo video = new ContentVideo();
                video.setCid(content.getId());
                video.setIsDelete(1);
                ContentVideo contentVideo = contentVideoMapper.selectOne(video);
                if(contentVideo != null){
                    content.setDurations(contentVideo.getDuration());
                }else{
                    content.setDurations("00:00");
                }
            }
            data.add(content);
        }
        return data;
    }

    /**
     * web页--模糊搜索
     *
     * @param searchKey
     * @return
     */
    public List<ContentInfo> searchKey(String searchKey) {
        return contentMapper.webSearch(searchKey);
    }

    /**
     * web页--模糊搜索--下拉页面异步加载
     *
     * @param searchKey
     * @return
     */
    public List<ContentImageInfo> searchLoad(String searchKey, Integer page, Integer limit) {
        return contentMapper.searchLoad(searchKey, page, limit);
    }


    /**
     * web页--内容表和图集表一起查询
     *
     * @param id
     * @return
     */
    public List<ContentImageInfo> getContentImageById(Integer id) {
        List<ContentImageInfo> info = contentMapper.getContentImageById(id);
        List<VideoTag> videoTagList=videoTagBiz.getTagListByConentId(id);
        String tag = "";
        for(int i = 0 ; i < videoTagList.size() ; i++){
            if(i < videoTagList.size()-1){
                tag += videoTagList.get(i).getName() + ",";
            }else{
                tag += videoTagList.get(i).getName();
            }
        }
        for(ContentImageInfo image: info){
            image.setTag(tag);
        }
        return info;
    }

    /**
     * web页--内容表和视频表一起查询
     *
     * @param id
     * @return
     */
    public ContentInfo getContentVideoById(Integer id ,Boolean flag) {
        ContentInfo info = contentMapper.getContentVideoById(id);
        if(flag){
            info.setViewCount(info.getViewCount() + 1);
            contentMapper.updateByviews(info);
        }
        List<VideoTag> videoTagList=videoTagBiz.getTagListByConentId(id);
        String tag = "";
        for(int i = 0 ; i < videoTagList.size() ; i++){
            if(i < videoTagList.size()-1){
                tag += videoTagList.get(i).getName() + ",";
            }else{
                tag += videoTagList.get(i).getName();
            }
        }
        info.setTag(tag);
        return info;
    }

    /**
     * 分页查询PC
     *
     * @param entity
     * @return
     */
    public List<ContentImageInfo> getPageentityPC(ContentImageInfo entity) {
        List<ContentImageInfo> list = contentMapper.getContentAll(entity);
        return list;
    }

    /**
     * 添加内容
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ContentInfo addContent(ContentInfo entity) {
        if (entity.getChannelIdList() != null) {
            entity.setChannels(entity.getChannelIdList().size());
        }
        contentMapper.addContent(entity);
        if (entity.getChannelIdList() != null && entity.getChannelIdList().size() > 0) {
            //添加频道绑定
            addBindChannel(entity);
        }
        //绑定政法先锋
        bindActivityContent(entity);
        //添加标签绑定
        contentBindTag(entity);
        //添加记者绑定
        contentBindKpi(entity);
        //文章详情
        if (StringUtils.isNotEmpty(entity.getContentDetail()) || entity.getDid() != null) {
            ContentArticle contentArticle = new ContentArticle();
            contentArticle.setCid(entity.getId());
            contentArticle.setContent(entity.getContentDetail());
            contentArticle.setSource(entity.getSource());
            contentArticle.setVideo(entity.getVideo());
            contentArticle.setVideoImg(entity.getVideoImage());
            contentArticle.setDid(entity.getDid());
            contentArticleMapper.insertSelective(contentArticle);
        }
        //绑定评论
        List<String> commentContentList = entity.getCommentContentList();
        bindComment(entity, commentContentList);
        //绑定样式
        bindCardType(entity);
        //添加内容地理位置
        saveContentLocation(entity);
        //爆料绑定
        bindBurst(entity.getBurstId(), entity.getId());
        //添加日志信息
        Long time = System.currentTimeMillis() / 1000;
        String action = "增加内容";
        addAdminLog(entity, time, action);
        //添加mongo日誌
        addMongoLog(entity, time);
        return entity;
    }


    //绑定爆料
    private void bindBurst(Integer burstId, Integer contentId) {
        if (burstId != null) {
            ContentBurst contentBurst = new ContentBurst();
            contentBurst.setBurstId(burstId);
            contentBurst.setContentId(contentId);
            contentBurst.setCreateTime(new Date());
            contentBurst.setStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
            contentBurstMapper.insertSelective(contentBurst);
        }
    }

    private Long addAdminLog(ContentInfo entity, Long time, String action) {
        AdminLog adminLog = new AdminLog();
        adminLog.setAction(action);
        adminLog.setActionUrl(entity.getTitle());
        adminLog.setContentId(entity.getId());
        adminLog.setCreateTime(time.intValue());
        adminLog.setCreateUid(entity.getCreateUid());
        adminLog.setType(entity.getContentType());
        adminLog.setIp(entity.getIp());
        adminLogMapper.insertSelective(adminLog);
        return time;
    }

    private void addMongoLog(ContentInfo entity, Long time) {
        ContentArticleLog contentArticleLog = new ContentArticleLog();
        contentArticleLog.setCid(entity.getId());
        contentArticleLog.setCreate_time(time.intValue());
        contentArticleLog.setEvent(NewsEnumsConsts.ContentArticleLogOfEvent.create.value());
        contentArticleLog.setUid(entity.getCreateUid());
        contentArticleLog.setIp(entity.getIp());
        ArrayList<Object> detailOfLogs = new ArrayList<>();
        Document document = new Document();
        document.put("field", "title");
        document.put("old", "");
        document.put("new", entity.getTitle());
        detailOfLogs.add(document);
        contentArticleLog.setDetail(detailOfLogs);
        contentDao.addContentAritcleLog(contentArticleLog);
    }

    private void bindCardType(ContentInfo entity) {
        if (entity.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key())) {
            ChannelContentCard channelContentCard = new ChannelContentCard();
            if (entity.getChannelIdList() != null && entity.getChannelIdList().size() > 0) {
                for (Integer integer : entity.getChannelIdList()) {
                    channelContentCard.setChannelId(integer);
                    channelContentCard.setContentId(entity.getId());
                    channelContentCard.setImage(entity.getImage());
                    channelContentCard.setCardType(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key());
                    channelContentCardMapper.insertSelective(channelContentCard);
                }
            }
        } else if (entity.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.BigImg.key())) {
            ChannelContentCard channelContentCard = new ChannelContentCard();
            if (entity.getChannelIdList() != null && entity.getChannelIdList().size() > 0) {
                for (Integer integer : entity.getChannelIdList()) {
                    channelContentCard.setChannelId(integer);
                    channelContentCard.setContentId(entity.getId());
                    channelContentCard.setImage(entity.getImage());
                    channelContentCard.setCardType(NewsEnumsConsts.ChannelContentOfCardType.BigImg.key());
                    channelContentCardMapper.insertSelective(channelContentCard);
                }
            }
        } else if (entity.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.ThreeImg.key())) {
            ChannelContentCard channelContentCard = new ChannelContentCard();
            if (entity.getChannelIdList() != null && entity.getChannelIdList().size() > 0) {
                for (Integer integer : entity.getChannelIdList()) {
                    channelContentCard.setChannelId(integer);
                    channelContentCard.setContentId(entity.getId());
                    String contentDetail = entity.getContentDetail();
                    List<String> imgStr = ImgUtils.getImgStr(contentDetail);
                    //判断有图的类型，三张
                    HashSet<String> set = new HashSet<>(16);
                    set.addAll(imgStr);
                    imgStr.clear();
                    imgStr.addAll(set);
                    byte[] serialize = PHPSerializer.serialize(imgStr);
                    channelContentCard.setImage(new String(serialize));
                    channelContentCard.setCardType(NewsEnumsConsts.ChannelContentOfCardType.ThreeImg.key());
                    channelContentCardMapper.insertSelective(channelContentCard);
                }
            }
        }
    }

    private void saveContentLocation(ContentInfo entity) {
        ContentLocationInfo contentLocationInfo = new ContentLocationInfo();
        contentLocationInfo.setCid(entity.getId());
        contentLocationInfo.setLocation(entity.getLocationList());
        contentLocationInfo.setStat(0);
        List<Integer> channelIds = new ArrayList<>(entity.getChannelIdList().size());
        for (Integer integer : entity.getChannelIdList()) {
            channelIds.add(integer);
        }
        List<Channel> channelByIds = channelMapper.getChannelByIds(channelIds);
        List<ChannelOfLocationInfo> locationInfos = new ArrayList<>(channelByIds.size());
        if (channelByIds != null && channelByIds.size() > 0) {
            for (Channel channel : channelByIds) {
                ChannelOfLocationInfo channelOfLocationInfo = new ChannelOfLocationInfo();
                channelOfLocationInfo.setId(channel.getId());
                channelOfLocationInfo.setTop(channel.getIsTop());
                channelOfLocationInfo.setStat(channel.getChannelStatus());
                locationInfos.add(channelOfLocationInfo);
            }
        }
        contentLocationInfo.setChannel(locationInfos);
        mongoTemplate.save(contentLocationInfo);
    }

    private void bindComment(ContentInfo entity, List<String> commentContentList) {
        if (commentContentList != null && commentContentList.size() > 0) {
            for (String content : commentContentList) {
                ContentStandard contentStandard = new ContentStandard();
                contentStandard.setCid(entity.getId());
                contentStandard.setContent(content);
                contentStandard.setCreateTime(new Date());
                contentStandard.setCreateUid(entity.getCreateUid());
                contentStandard.setStat(1);
                contentStandard.setType(1);
                contentStandardMapper.insertSelective(contentStandard);
            }
        }
    }

    private void bindActivityContent(ContentInfo entity) {
        if (entity.getChildChannelIdList() != null && entity.getChildChannelIdList().size() > 0) {
            for (Integer integer : entity.getChildChannelIdList()) {
                ActivityContentBind activityContentBind = new ActivityContentBind();
                activityContentBind.setActivityId(integer);
                activityContentBind.setContentId(entity.getId());
                activityContentBind.setIsDelete(1);
                activityContentBind.setTop(0);
                activityContentBind.setType(entity.getContentType());
                List<ActivityContentBind> activityContentBinds = activityContentBindMapper.selectAll();
                if (activityContentBinds != null && activityContentBinds.size() > 0) {
                    List<Integer> ids = new ArrayList<>(activityContentBinds.size());
                    for (ActivityContentBind contentBind : activityContentBinds) {
                        ids.add(contentBind.getOrderNumber());
                    }
                    activityContentBind.setOrderNumber(Collections.max(ids) + 1);
                } else {
                    activityContentBind.setOrderNumber(1);
                }
                activityContentBindMapper.insertSelective(activityContentBind);
            }
        }
    }

    /**
     * 更新   传入当前频道？
     *
     * @param contentInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer update(ContentInfo contentInfo) {
        Content content = new Content();
        BeanUtils.copyProperties(contentInfo, content);
        if (contentInfo.getChannelIdList() != null) {
            contentInfo.setChannels(contentInfo.getChannelIdList().size());
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(contentInfo.getIsCheck())){
            //是否为审核 0未审核
            content.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            //更新后进入审核
            content.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Show.getKey());
            content.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
        ContentInfo oldContent = contentMapper.getContent(contentInfo.getId());
        content.setTitle(null);
        int update = contentMapper.updateByPrimaryKeySelective(content);
        //绑定频道(使用原始信息)
        contentInfo.setCheckStatus(oldContent.getCheckStatus());
        contentInfo.setCardType(oldContent.getCardType());
        updateBindChannel(contentInfo);
        //绑定政法先锋
        if (contentInfo.getChildChannelIdList() != null && contentInfo.getChildChannelIdList().size() > 0) {
            ActivityContentBind activityContentBind = new ActivityContentBind();
            activityContentBind.setContentId(contentInfo.getId());
            activityContentBindMapper.delete(activityContentBind);
            bindActivityContent(contentInfo);
        }
        //添加标签绑定
        if (contentInfo.getTagNameList() != null && contentInfo.getTagNameList().size() > 0) {
            //删除
            videoTagBindMapper.deleteTagByCid(contentInfo.getId());
            contentBindTag(contentInfo);
        }
        //绑定记者
        updateBindKpiCount(contentInfo);
        //文章详情
        if (contentInfo.getContentType().equals(NewsEnumsConsts.ContentOfContentType.Article.getKey()) && (StringUtils.isNotEmpty(contentInfo.getContentDetail()) || contentInfo.getDid() != null)) {
            ContentArticle contentArticle = new ContentArticle();
            contentArticle.setCid(contentInfo.getId());
            //改存日志，审核后同步
//            contentArticle.setSource(contentInfo.getSource());
//            if (StringUtils.isNotEmpty(contentInfo.getContentDetail())) {
//                contentArticle.setContent(contentInfo.getContentDetail());
//            }
            if (contentInfo.getDid() != null) {
                contentArticle.setDid(contentInfo.getDid());
            }
            contentArticle.setVideo(contentInfo.getVideo());
            contentArticle.setVideoImg(contentInfo.getVideoImage());
            contentArticleMapper.updateByPrimaryKeySelective(contentArticle);
        }
        //更新地理位置
        if (contentInfo.getLocationList() != null && contentInfo.getLocationList().size() > 0) {
            ContentLocationInfo contentLocationInfo = new ContentLocationInfo();
            contentLocationInfo.setCid(contentInfo.getId());
            if (contentLocationInfo.getCid() != null) {
                contentDao.removeMogoLocation(contentLocationInfo);
            }
            saveContentLocation(contentInfo);
        }
        //添加系统日志
        String action = "修改内容";
        addAdminLog(contentInfo, System.currentTimeMillis() / 1000, action);
        //更新的日志
        addUpdateMongoLog(contentInfo, oldContent);
        return update;
    }

    /**
     * 添加更新的mongoLog
     *
     * @param contentInfo
     */
    private void addUpdateMongoLog(ContentInfo contentInfo, ContentInfo content) {
        //标题
        ArrayList<Object> detailOfLogs = new ArrayList<>();
        if (!(content.getTitle() != null && content.getTitle().equals(contentInfo.getTitle()))) {
            Document document = new Document();
            document.put("field", "title");
            document.put("old", content.getTitle());
            document.put("new", contentInfo.getTitle());
            detailOfLogs.add(document);
        }
        //详情
        if (!(content.getContentDetail() != null && MD5Util.md5(content.getContentDetail()).equals(MD5Util.md5(contentInfo.getContentDetail())))) {
            Document document = new Document();
            document.put("field", "content");
            document.put("old", content.getContentDetail());
            document.put("new", contentInfo.getContentDetail());
            detailOfLogs.add(document);
        }
        //来源
        if (!(content.getSource() != null && content.getSource().equals(contentInfo.getSource()))) {
            Document document = new Document();
            document.put("field", "source");
            document.put("old", content.getSource());
            document.put("new", contentInfo.getSource());
            detailOfLogs.add(document);
        }
        //频道
        List<ChannelInfo> channelInfos = channelContentMapper.selectListForLog(contentInfo.getId());
        if (channelInfos != null && channelInfos.size() > 0) {
            List<Integer> channelIds = new ArrayList<>(channelInfos.size());
            for (ChannelInfo channelInfo : channelInfos) {
                channelIds.add(channelInfo.getId());
            }
            if (!(channelInfos.containsAll(contentInfo.getChannelIdList()) && contentInfo.getChildChannelIdList().containsAll(channelInfos))) {
                List<Integer> channelIdList = contentInfo.getChannelIdList();
                List<Integer> ids = new ArrayList<>(channelIdList.size());
                for (Integer id : channelIdList) {
                    ids.add(id);
                }
                List<ChannelInfoOfLog> channelInfoList = channelMapper.getChannelByIdsForLog(ids);
                Document document = new Document();
                document.put("field", "channel");
                List<Document> documentList = new ArrayList<>(channelInfos.size());
                for (ChannelInfo channelInfo : channelInfos) {
                    Document document1 = new Document();
                    document1.put("id", channelInfo.getId());
                    document1.put("name", channelInfo.getName());
                    documentList.add(document1);
                }
                document.put("old", documentList);
                List<Document> documentListNew = new ArrayList<>(channelInfos.size());
                for (ChannelInfoOfLog channelInfoOfLog : channelInfoList) {
                    Document document1 = new Document();
                    document1.put("id", channelInfoOfLog.getId());
                    document1.put("name", channelInfoOfLog.getName());
                    documentListNew.add(document1);
                }
                document.put("new", documentListNew);
                detailOfLogs.add(document);
            }
        }
        if (detailOfLogs.size() > 0) {
            Long time = System.currentTimeMillis() / 1000;
            ContentArticleLog contentArticleLog = new ContentArticleLog();
            contentArticleLog.setCid(contentInfo.getId());
            contentArticleLog.setCreate_time(time.intValue());
            contentArticleLog.setEvent(NewsEnumsConsts.ContentArticleLogOfEvent.edit.value());
            contentArticleLog.setUid(contentInfo.getCreateUid());
            contentArticleLog.setDetail(detailOfLogs);
            contentArticleLog.setIp(contentInfo.getIp());
            contentDao.addContentAritcleLog(contentArticleLog);
        }
    }

    /**
     * 更新专题
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateContentSubject(ContentInfo entity) {
        Content content = new Content();
        BeanUtils.copyProperties(entity, content);
        if (entity.getChannelIdList() != null) {
            entity.setChannels(entity.getChannelIdList().size());
        }
        //更新后进入审核
        //content.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Show.getKey());
        //content.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        int update = contentMapper.updateByPrimaryKeySelective(content);
        //绑定频道
        updateBindChannel(entity);
        //添加标签绑定
        if (entity.getTagNameList() != null && entity.getTagNameList().size() > 0) {
            //删除
            videoTagBindMapper.deleteTagByCid(entity.getId());
            contentBindTag(entity);
        }
        //更新绑定记者
        updateBindKpiCount(entity);
        //专题更改子目录
        if (entity.getChildCatalogList() != null && entity.getChildCatalogList().size() > 0) {
            SpecialCatalog specialCatalog = new SpecialCatalog();
            specialCatalog.setCid(entity.getId());
            List<SpecialCatalog> select = specialCatalogMapper.select(specialCatalog);
            if (select != null && select.size() > 0) {
                List<Integer> ids = new ArrayList<>();
                for (SpecialCatalog catalog : select) {
                    ids.add(catalog.getId());
                    specialCatalogMapper.deleteByPrimaryKey(catalog.getId());
                }
                for (Integer id : ids) {
                    ContentSpecial contentSpecial = new ContentSpecial();
                    contentSpecial.setContentId(entity.getId());
                    contentSpecialMapper.delete(contentSpecial);
                }
            }
        }
        bindSubjectCatalog(entity, new Date());
        //更新地理位置
        contentDao.updateLocation(entity);
        //添加日志
        addAdminLog(entity, System.currentTimeMillis() / 1000, "修改专题");
        return update;
    }

    private void updateBindKpiCount(ContentInfo entity) {
        if (entity.getKpiCountContentInfoList() != null && entity.getKpiCountContentInfoList().size() > 0) {
            List<KpiCountContentInfo> kpiCountContentInfoList = entity.getKpiCountContentInfoList();
            //删除更新
            if (entity.getId() != null) {
                KpiCount kpicontent = new KpiCount();
                kpicontent.setTypeId(entity.getId());
                kpiCountMapper.delete(kpicontent);
            }
            contentBindKpi(entity);
        }
    }


    /**
     * 已回收列表
     *
     * @param query
     * @return
     */
    public TableResultResponse<RecycleBinInfo> selectDeletes(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<RecycleBinInfo> list = contentMapper.selectDeletes();
        return new TableResultResponse<RecycleBinInfo>(result.getTotal(), list);
    }

    /**
     * 按标题搜索已回收列表
     *
     * @param query
     * @return
     */
    public TableResultResponse<RecycleBinInfo> selectSearchDeletes(Query query, String title) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<RecycleBinInfo> list = contentMapper.selectSearchDeletes(title);
        return new TableResultResponse<RecycleBinInfo>(result.getTotal(), list);
    }


    /**
     * 已回收列表 的 清空回收站
     *
     * @param
     * @return
     */
    public void deleteAll() {
        contentMapper.deleteAll();

    }

    /**
     * 分页查询内容绑定视频
     *
     * @param contentInfo
     * @return
     */
    public TableResultResponse<ContentInfo> getContentPageForVideo(ContentInfo contentInfo) {
        Page<Object> result = PageHelper.startPage(contentInfo.getPage(), contentInfo.getLimit());
        List<ContentInfo> list = contentMapper.getContentPageForVideo(contentInfo);
        return new TableResultResponse<ContentInfo>(result.getTotal(), list);
    }

    private void updateBindChannel(ContentInfo contentInfo) {
        if (contentInfo.getChannelIdList() != null && contentInfo.getChannelIdList().size() > 0) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setContentId(contentInfo.getId());
            channelContentMapper.delete(channelContent);
            Integer isPublish = contentInfo.getIsPublish();
            if(NewsEnumsConsts.ContentOfContentType.Article.getKey().equals(contentInfo.getContentType())){
                //审核状态
                contentInfo.setIsPublish(contentInfo.getCheckStatus());
                //contentInfo.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
            }
            addBindChannel(contentInfo);
            contentInfo.setIsPublish(isPublish);
        }
    }

    private void updateBindChannelForActivity(ActivityInfo contentInfo) {
        if (contentInfo.getAppChannelIdList() != null && contentInfo.getAppChannelIdList().size() > 0) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setContentId(contentInfo.getId());
            channelContentMapper.delete(channelContent);
            addBindChannelForActivity(contentInfo, 1);
        }
    }

    private void addBindChannel(ContentInfo entity) {
        Integer cardType = entity.getCardType();
        List<Integer> pcChannelIdList = entity.getPcChannelIdList();
        for (Integer integer : entity.getChannelIdList()) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setContentId(entity.getId());
            channelContent.setChannelId(integer);
            channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
            if (pcChannelIdList.contains(integer)) {
                channelContent.setCardType(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key());
            } else {
                channelContent.setCardType(cardType);
            }
            if (NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key().equals(entity.getIsPublish())) {
                channelContent.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
                channelContent.setPublishTime(new Date());
                channelContent.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
            } else {
                channelContent.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.NOTPUBLISH.key());
            }
            channelContent.setRecommendWeight(entity.getRecommendWeight());
            channelContent.setType(entity.getContentType());
            channelContent.setFromType(NewsEnumsConsts.ChannelContentOfFromType.Content.key());
            Integer maxOrder = channelContentMapper.selectMaxOrderNumber();
            if (null != maxOrder) {
                channelContent.setOrderNumber(maxOrder + 1);
            } else {
                channelContent.setOrderNumber(1);
            }
            channelContentMapper.insertSelective(channelContent);
            if ((!NewsEnumsConsts.ChannelContentOfCardType.ThreeImg.key().equals(cardType)) &&
                    (!NewsEnumsConsts.ChannelContentOfCardType.FourImg.key().equals(cardType)) &&
                    (!NewsEnumsConsts.ContentOfContentType.Article.getKey().equals(entity.getContentType()))) {
                addChannelContentCard(integer, entity.getId(), channelContent.getCardType(), entity.getImage());
            }
        }
    }

    //添加绑定展示图
    private void addChannelContentCard(Integer channelId, Integer contentId, Integer cardType, String image) {
        ChannelContentCard channelContentCard = new ChannelContentCard();
        channelContentCard.setCardType(cardType);
        channelContentCard.setChannelId(channelId);
        channelContentCard.setContentId(contentId);
        channelContentCard.setImage(image);
        channelContentCardMapper.insertSelective(channelContentCard);
    }

    private void addBindChannelForActivity(ActivityInfo entity, Integer isPublish) {
        for (Integer integer : entity.getAppChannelIdList()) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setContentId(entity.getId());
            channelContent.setChannelId(integer);
            channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
            channelContent.setCardType(NewsEnumsConsts.ChannelContentOfCardType.VoteActivity.key());
            channelContent.setType(entity.getCardType());//13-h5,25-政法先锋
            if (NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key().equals(isPublish)) {
                channelContent.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
                channelContent.setPublishTime(new Date());
                channelContent.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
            } else {
                channelContent.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.NOTPUBLISH.key());
            }
            channelContent.setRecommendWeight(entity.getRecommendWeight());
            channelContent.setFromType(NewsEnumsConsts.ChannelContentOfFromType.Content.key());
            channelContentMapper.insertSelective(channelContent);
        }
    }

    /**
     * app内容模块 文章详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    public ObjectRestResponse<Map<String, Object>> getArticleDetail(String cate, String cid, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        ChannelContent chc = new ChannelContent();
        chc.setContentId(Integer.valueOf(cid));
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
        con.setId(Integer.valueOf(cid));
        con.setContentState(1);
        Content content = contentMapper.selectOne(con);
        Map<String, Object> result = fiterAppContent(content, uid);

        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * app内容模块 文章详情页接口
     */
    private Map<String, Object> fiterAppContent(Content data, Integer uid) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", data.getId());
        result.put("title", data.getTitle());
        result.put("uid", uid);
        Fav fav = new Fav();
        fav.setCid(data.getId());
        fav.setUid(uid);
        fav.setType(data.getContentType());
        Fav resultFav = favMapper.selectOne(fav);
        //是否收藏
        result.put("is_faved", resultFav == null ? 0 : 1);
        result.put("is_faved", resultFav == null ? 0 : 1);//是否收藏
        //获取评论设置
        Setting resultSet = settingMapper.selectSettingByName("comment_tourist");
        result.put("comment_tourist", resultSet != null ? true : false);
        Setting resultSet2 = settingMapper.selectSettingByName("comment_ops");
        result.put("posts_ops", Integer.parseInt(resultSet2.getValue()));
        //如果为先审后发，则用已审核的评论数来代替总评论数
        Comment comment = new Comment();
        comment.setCid(data.getId());
        comment.setType(0);
        comment.setStat(1);
        if (null != resultSet2 && resultSet2.getValue().equals("1")) {
            comment.setOps(1);
            if (uid != null) {
                comment.setCreateUid(uid);
            }
        }
        int commentNum = commentMapper.selectCount(comment);
        result.put("comment_allnum", commentNum);
        result.put("desc", data.getIntroduction());
        result.put("share_url", getShareUrl(CommonConstants.URL_PREFIX, data.getContentType(), data.getCategory(), data.getId()));
        result.put("image", data.getImage());
        if (null != data.getImage()) {
            result.put("share_image", "http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png?x-oss-process=image/resize,m_fill,w_748,h_400");
        }
        //bind content
        List<Map> bindContentByIds = contentMapper.getContentByIds(data.getId());
        if (bindContentByIds != null) {
            result.put("bind", contentMsgAdd(bindContentByIds));
        }
        List<ActivityRaffle> rafflesList = activityRaffleMapper.getNowActivityRaffle(new Date());
        if (null != rafflesList && rafflesList.size() > 0) {
            ActivityRaffle thisdata = rafflesList.get(0);
            Map thisMap = new HashMap();
            thisMap.put("activity_id", thisdata.getId());
            thisMap.put("title", thisdata.getTitle());
            thisMap.put("buoy", thisdata.getBuoy());
            String str = "http://localhost/web/activity/draw/detail.html?activity_id=" + thisdata.getId();
            thisMap.put("activity_url", str);
            result.put("activity", thisMap);
        }
        result.put("activity_static", 2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        result.put("title_time", sdf.format(data.getCreateTime()));

        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.parseInt(Long.toString(data.getCreateUid())));
        BaseUser adminUser1 = baseUserMapper.selectOne(baseUser);
        if (null != adminUser1) {
            Map thisUserMap = new HashMap();
            thisUserMap.put("nickname", adminUser1.getName());
            thisUserMap.put("image", adminUser1.getHeadPortrait());
            thisUserMap.put("id", adminUser1.getId());
            result.put("user", thisUserMap);

        }

        if (null != uid) {
            String reKey = "like_user_" + uid + "_type_0";
            String rekey2 = "like_total_0";
            result.put("is_liked", redisTemplate.opsForSet().isMember(reKey, data.getId()) == true ? 1 : 0);
            result.put("likes", (int) (redisTemplate.opsForHash().get(rekey2, data.getId()) == null ? 0 : redisTemplate.opsForHash().get(rekey2, data.getId())));
        }
        return result;
    }


    //将不同类型的资讯的内容补全
    public List<Map> contentMsgAdd(List<Map> contentList) {
        for (Map content : contentList) {
            //标签
            HashMap<Object, Object> tagMap = new HashMap<>(16);
            if ("2".equals(content.get("content_type"))) {
                String tag = content.get("tag").toString();
                tagMap.put("name", tag);
                tagMap.put("font_color", "#ce2524");
                tagMap.put("border_color", "#ce2524");
                content.put("tags", tagMap);
            } else if ("9".equals(content.get("content_type"))) {
                tagMap.put("name", "直播");
                tagMap.put("font_color", "#4D97DD");
                tagMap.put("border_color", "#4D97DD");
                content.put("tags", tagMap);
            }
            if ("1".equals(content.get("source_type"))) {
                if ("2".equals(content.get("content_type")) || "3".equals(content.get("content_type")) || "4".equals(content.get("content_type"))) {
                    tagMap.put("name", "直播");
                    tagMap.put("font_color", "#CD2525");
                    tagMap.put("border_color", "#CD2525");
                    content.put("tags", tagMap);
                }
            }
            int type = Integer.parseInt(content.get("content_type").toString());
            switch (type) {
                case 1:
                    break;
                case 2:
                    ContentArticle contentArticle = new ContentArticle();
                    contentArticle.setCid(Integer.valueOf(content.get("id").toString()));
                    contentArticle.setIsDelete(1);
                    //文章来源和作者
                    ContentArticle contentArticle1 = contentArticleMapper.selectOne(contentArticle);
                    if (contentArticle1 != null) {
                        content.put("source", contentArticle1.getSource());
                        content.put("author", contentArticle1.getAuthor());
                    }
                    if ("1".equals(content.get("content_style"))) {
                        content.put("type", CommonConstants.CONTENT_JUDGMENT);
                    }
                    if ("2".equals(content.get("content_style"))) {
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
                        List<ContentImageGroup> select = contentImageGroupMapper.select(contentImageGroup);
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
                    //视频时长 源地址
                    ContentVideo contentVideo1 = contentVideoMapper.selectOne(contentVideo);
                    if (contentVideo1 != null) {
                        content.put("duration", contentVideo1.getDuration());
                        content.put("video_source", contentVideo1.getUrl());
                    } else {
                        content.put("duration", "00:00");
                        content.put("video_source", "");
                    }
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


        }
        return contentList;

    }


    //分享落地页
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
            case 23://庭审h5
                url = URL_PREFIX + "web/live/index?id=" + id + "&share=1";
                break;
            //新直播
            case 22:
                url = URL_PREFIX + "public/live/index.html?id=" + id + "&share=1";
                break;
            default:
                break;
        }
        return url;
    }


    public void insertMongo(Map<String, Object> liveMap, Integer uid, String ip, HttpServletRequest request) {
        liveMap.put("uid", uid);
        liveMap.put("ip", ip);
        liveMap.put("request_source", LiveCommonEnum.CONSTANT_ONE);
        liveMap.put("user_agent", request.getHeader(requestConfiguration.getUserAgent()));
        liveMap.put("is_proxy", LiveCommonEnum.CONSTANT_ONE);
        liveMap.put("referer", request.getHeader(requestConfiguration.getReferer()));
        liveMap.put("create_date", new Date());
        mongoTemplate.save(liveMap);
    }

    public List<Map<String, String>> getWebActivityContent(@RequestParam(value = "1") Integer original, @RequestParam(value = "0") Integer activity) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, Map<String, String>> map = LiveCommonEnum.getActivity();
        if (LiveCommonEnum.CONSTANT_ONE.equals(original) && LiveCommonEnum.CONSTANT_ONE.equals(activity)) {
            for (Map.Entry m : map.entrySet()) {
                list.add((Map<String, String>) m.getValue());
            }
        } else {
            list.add(map.get("one"));
        }
        return list;
    }

    /**
     * add专题
     *
     * @param entity
     * @return
     */
    public ContentInfo addContentSubject(ContentInfo entity) {
        if (entity.getChannelIdList() != null) {
            entity.setChannels(entity.getChannelIdList().size());
        }
        Date date = new Date();
        contentMapper.addContentSubject(entity);
        if (entity.getChannelIdList() != null && entity.getChannelIdList().size() > 0) {
            //添加频道绑定
            addBindChannel(entity);
        }
        //添加标签绑定
        contentBindTag(entity);
        //添加记者绑定
        contentBindKpi(entity);
        //添加子目录
        bindSubjectCatalog(entity, date);
        //添加日志
        addAdminLog(entity, System.currentTimeMillis() / 1000, "增加内容");
        return entity;
    }

    private void bindSubjectCatalog(ContentInfo entity, Date date) {
        List<SubjectCatalogInfo> childCatalogList = entity.getChildCatalogList();
        for (SubjectCatalogInfo subjectCatalogInfo : childCatalogList) {
            SpecialCatalog specialCatalog = new SpecialCatalog();
            specialCatalog.setCid(entity.getId());
            specialCatalog.setCreateTime(date);
            specialCatalog.setUpdateTime(date);
            specialCatalog.setCreateUid(entity.getCreateUid());
            specialCatalog.setUpdateUid(entity.getCreateUid());
            specialCatalog.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
            specialCatalog.setTitle(subjectCatalogInfo.getTitle());
            specialCatalogMapper.addSpecialCatalog(specialCatalog);
            if (subjectCatalogInfo.getContentIds() != null && subjectCatalogInfo.getContentIds().size() > 0) {
                for (Integer cid : subjectCatalogInfo.getContentIds()) {
                    ContentSpecial contentSpecial = new ContentSpecial();
                    contentSpecial.setCatalogId(specialCatalog.getId());
                    contentSpecial.setContentId(entity.getId());
                    contentSpecial.setTargetCid(cid);
                    contentSpecial.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
                    contentSpecial.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
                    contentSpecial.setPublishTime(date);
                    //排序值
                    contentSpecialMapper.addContentSpecial(contentSpecial);
                }
            }
        }
    }

    private void contentBindKpi(ContentInfo entity) {
        List<KpiCountContentInfo> userList = entity.getKpiCountContentInfoList();
        if (userList != null && userList.size() > 0) {
            for (KpiCountContentInfo kpiCountInfo : userList) {
                if (kpiCountInfo.getUid() != null) {
                    KpiCount kpiCount = new KpiCount();
                    BeanUtils.copyProperties(kpiCountInfo, kpiCount);
                    kpiCount.setType(NewsEnumsConsts.KpiCountOfType.Cid.key());
                    kpiCount.setTypeId(entity.getId());
                    kpiCount.setArticleType(kpiCountInfo.getArticleType());
                    kpiCount.setCreateTime(new Date());
                    //权重规则
                    double weight = 1d / userList.size();
                    BigDecimal bigDecimal = BigDecimal.valueOf(weight).setScale(1, BigDecimal.ROUND_DOWN);
                    kpiCount.setWeight(bigDecimal);
                    CorpUser corpUser = corpUserMapper.selectByPrimaryKey(kpiCountInfo.getUid());
                    if (corpUser != null) {
                        kpiCount.setJobId(corpUser.getJobId());
                        kpiCount.setName(corpUser.getName());
                        kpiCount.setDepartmentId(corpUser.getLevel2Id());
                    }
                    kpiCountMapper.insertSelective(kpiCount);
                }
            }
        }
    }

    private void contentBindTag(ContentInfo entity) {
        if (entity.getTagNameList() != null && entity.getTagNameList().size() > 0) {
            VideoTagInfo videoTagInfo = new VideoTagInfo();
            videoTagInfo.setTagNameList(entity.getTagNameList());
            //匹配标签
            List<VideoTagInfo> tagList = videoTagMapper.selectTag(videoTagInfo);
            List<Integer> tagIds = new ArrayList<>(entity.getTagNameList().size());
            for (String name : entity.getTagNameList()) {
                Integer tagId = null;
                for (VideoTagInfo tagInfo : tagList) {
                    if (name.equals(tagInfo.getName())) {
//                        tagIds.add(tagInfo.getId());
                        tagId = tagInfo.getId();
                        break;
                    }
                }
                if (tagId != null) {
                    VideoTagBind videoTagBind = new VideoTagBind();
                    videoTagBind.setVideoId(entity.getId());
                    videoTagBind.setVideoTagId(tagId);
                    videoTagBindMapper.insertSelective(videoTagBind);
                } else {
                    VideoTag tag = new VideoTag();
                    tag.setName(name);
                    tag.setCreateTime(new Date());
                    tag.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
                    tag.setCreateUid(entity.getCreateUid());
                    videoTagMapper.addTag(tag);
                    VideoTagBind videoTagBind = new VideoTagBind();
                    videoTagBind.setVideoId(entity.getId());
                    videoTagBind.setVideoTagId(tag.getId());
                    videoTagBindMapper.insertSelective(videoTagBind);
                }
            }
        }
    }

    /**
     * 查询编辑的内容文章等信息
     *
     * @return
     */
    public ContentInfo getContent(Integer id) {
        return contentMapper.getContent(id);
    }

    /**
     * 咨询列表批量删除
     *
     * @param ids
     * @param ip
     * @param uid
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer batchDelete(List<Integer> ids, String ip, Integer uid) {
        int deleteCount = 0;
        for (Integer id : ids) {
            Content content1 = new Content();
            content1.setId(id);
            content1.setDeleteTime(new Date());
            content1.setDeleteUid(uid);
            content1.setContentState(NewsEnumsConsts.ContentOfContentState.DELETE.key());
            int flag = contentMapper.updateByPrimaryKeySelective(content1);
            if (flag > 0) {
                deleteCount++;
                //添加日志
                Content content = contentMapper.selectByPrimaryKey(id);
                ContentInfo contentInfo = new ContentInfo();
                BeanUtils.copyProperties(content, contentInfo);
                contentInfo.setIp(ip);
                contentInfo.setCreateUid(uid);
                contentInfo.setTitle(contentInfo.getTitle() + "[咨询列表删除]");
                addAdminLog(contentInfo, System.currentTimeMillis() / 1000, "内容删除");
                //删除频道绑定
                channelContentMapper.updateContentDeleteByContentId(id, 1);
            }
        }
        return deleteCount;
    }

    /**
     * 查询绑定内容
     *
     * @param entity
     * @return
     */
    public List<ContentInfo> getBroadContent(ContentInfo entity) {
        return contentMapper.getBroadContent(entity);
    }

    /**
     * 查询内容信息
     *
     * @param id
     * @return
     */
    public ContentInfo getContentForBind(Integer id) {
        return contentMapper.getContentForBind(id);
    }


    /**
     * 分页查询视频列表
     *
     * @param entity
     * @return
     */
    public TableResultResponse<VideoContentSelectInfo> getVideoPage(VideoSelectionInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置晒出条件
        //'审核状态（0待审核，1审核通过，2审核不通过）' check_status
        entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
        //contentState  '状态，1：正常，0：删除'
        entity.setContentState(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        entity.setIsCheck(0);
        entity.setCategory(2);
        List<VideoContentSelectInfo> list = contentMapper.getVideoPage(entity);

        return new TableResultResponse<>(result.getTotal(), list);
    }


    /**
     * 新增视频
     *
     * @param entity
     * @return
     */
    public VideoContentInfo addVideoContentInfo(VideoContentInfo entity) {
        entity.setContentType(4);
        contentMapper.addVideoContent(entity);
        //绑定app频道
        if (entity.getAppChannelIdList().contains(59)==false){
            entity.getAppChannelIdList().add(59);
        }
        if (entity.getAppChannelIdList() != null && entity.getAppChannelIdList().size() > 0) {
            for (Integer integer : entity.getAppChannelIdList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                channelContent.setChannelId(integer);
                channelContent.setCardType(5);
                //来源content表
                channelContent.setFromType(1);
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                channelContent.setIsPublish(0);
                //绑定推荐权重 recommend_weight kf_channel_content
                channelContent.setRecommendWeight(entity.getRecommendWeight());
                //设置排序值
                Integer integer1 = channelContentMapper.selectMaxOrderNumber();
                if (integer1 == null) {
                    channelContent.setOrderNumber(1);
                } else {
                    channelContent.setOrderNumber(integer1 + 1);
                }
                //1资讯（APP首页），2APP视频，3APPVR，4PC资讯
                channelContent.setCate(2);
                channelContent.setRecommendWeight(entity.getRecommendWeight());
                channelContentMapper.insertSelective(channelContent);
            }
        }
        // 绑定pc频道
        if (entity.getPcChannelIdList() != null && entity.getPcChannelIdList().size() > 0) {
            for (Integer integer : entity.getPcChannelIdList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                channelContent.setChannelId(integer);
                channelContent.setFromType(1);
                channelContent.setCardType(5);
                //设置排序值
                Integer integer1 = channelContentMapper.selectMaxOrderNumber();
                if (integer1 == null) {
                    channelContent.setOrderNumber(1);
                } else {
                    channelContent.setOrderNumber(integer1 + 1);
                }
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                channelContent.setIsPublish(0);
                //绑定推荐权重 recommend_weight kf_channel_content
                channelContent.setRecommendWeight(entity.getRecommendWeight());
                channelContent.setCate(2);
                channelContentMapper.insertSelective(channelContent);
            }
        }
        //添加标签绑定
        if (entity.getTagNameList() != null && entity.getTagNameList().size() > 0) {
            VideoTag videoTag = new VideoTag();
            for (String name : entity.getTagNameList()) {
                videoTag.setName(name);
                //匹配标签
                List<VideoTag> tagList = videoTagMapper.select(videoTag);
                Integer tagId = null;
                for (VideoTag tag : tagList) {
                    if (name.equals(tag.getName())) {
                        tagId = tag.getId();
                        VideoTagBind videoTagBind = new VideoTagBind();
                        videoTagBind.setVideoId(entity.getId());
                        videoTagBind.setVideoTagId(tagId);
                        videoTagBindMapper.insertSelective(videoTagBind);
                        break;
                    }
                }
                if (tagList.size() <= 0) {
                    VideoTag tag = new VideoTag();
                    tag.setName(name);
                    tag.setCreateTime(new Date());
                    tag.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
                    tag.setCreateUid(entity.getCreateUid());
                    videoTagMapper.addTag(tag);
                    VideoTagBind videoTagBind = new VideoTagBind();
                    videoTagBind.setVideoId(entity.getId());
                    videoTagBind.setVideoTagId(tag.getId());
                    videoTagBindMapper.insertSelective(videoTagBind);
                }
            }
        }
        //绑定视频库
        if (entity.getVideoDemand() != null) {
            VideoDemand videoDemand = entity.getVideoDemand();
            ContentVideo contentVideo = new ContentVideo();
            Demand demand = demandMapper.selectByPrimaryKey(videoDemand.getId());
            String url = "http://devvideotest.oss-cn-beijing.aliyuncs.com/outactmvp/" + demand.getName();
            contentVideo.setUrl(url);
            contentVideo.setDid(videoDemand.getId());
            contentVideo.setSourceId(entity.getSourceId());
            contentVideo.setSource(entity.getSource());
            //未删除 状态，1：正常，0：删除'
            contentVideo.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
            //绑定来源
            contentVideo.setSource(entity.getSource());
            contentVideo.setDuration(entity.getVideoDuration());
            contentVideo.setCid(entity.getId());
            if (entity.getSourceId() != null) {
                contentVideo.setSourceId(entity.getSourceId());
            }
            if (entity.getIsRecommend()!=null){
                contentVideo.setIsRecommend(entity.getIsRecommend());
            }
            contentVideoMapper.insertSelective(contentVideo);
        }
        //绑定评论
        List<String> commentContentList = entity.getCommentContentList();
        if (commentContentList != null && commentContentList.size() > 0) {
            for (String content : commentContentList) {
                ContentStandard contentStandard = new ContentStandard();
                contentStandard.setCid(entity.getId());
                contentStandard.setContent(content);
                contentStandard.setCreateTime(new Date());
                contentStandard.setCreateUid(entity.getCreateUid());
                contentStandard.setStat(1);
                contentStandard.setType(entity.getCategory());
                contentStandardMapper.insertSelective(contentStandard);
            }
        }
        //绑定videoColumnId
        if (entity.getVideoColumnId()!=null){
            VideoColumnBind videoColumnBind = new VideoColumnBind();
            videoColumnBind.setCid(entity.getId());
            videoColumnBind.setFromType(1);
            videoColumnBind.setVideoColumnId(entity.getVideoColumnId());
            videoColumnBind.setCreateTime(entity.getCreateTime());
            videoColumnBind.setCreateUid(entity.getCreateUid());
            videoColumnBind.setTitle(entity.getTitle());
            videoColumnBind.setUpdateUid(entity.getCreateUid());
            long time = entity.getCreateTime().getTime();
            videoColumnBind.setUpdateTime((int) time);
            videoColumnBindMapper.insertSelective(videoColumnBind);
        }
        // 绑定爆料
        bindBurst(entity.getBurstId(), entity.getId());
        return entity;
    }

    /**
     * 得到视频详尽信息
     *
     * @param
     * @return
     */
    public VideoContentInfo getVideoContent(Integer contentId) {
        VideoContentInfo videoContentInfo = new VideoContentInfo();
        //得到content表的信息
        Content content = contentMapper.selectByPrimaryKey(contentId);
        BeanUtils.copyProperties(content, videoContentInfo);
        //得到所绑定的频道信息
        List<Integer> appChannels = channelContentMapper.selectAppChannels(contentId);
        List<Integer> pcChannels = channelContentMapper.selectPcChannels(contentId);
        videoContentInfo.setAppChannelIdList(appChannels);
        videoContentInfo.setPcChannelIdList(pcChannels);
        //获得来源作者的信息
        ContentVideo contentVideo = contentVideoMapper.selectByPrimaryKey(contentId);
        if (contentVideo != null) {
            Integer sourceId = contentVideo.getSourceId();
            videoContentInfo.setSourceId(sourceId);
            Integer isRecommend = contentVideo.getIsRecommend();
            videoContentInfo.setIsRecommend(isRecommend);
        }
        //获得推荐权重的信息
        ChannelContent channelContent = new ChannelContent();
        channelContent.setContentId(contentId);
        List<ChannelContent> select = channelContentMapper.select(channelContent);
        for (ChannelContent c : select) {
            Integer recommendWeight = c.getRecommendWeight();
            videoContentInfo.setRecommendWeight(recommendWeight);
            break;
        }
        //获得栏目分类的信息
        VideoColumnBind videoColumnBind = new VideoColumnBind();
        videoColumnBind.setFromType(1);
        videoColumnBind.setCid(contentId);
        List<VideoColumnBind> select3 = videoColumnBindMapper.select(videoColumnBind);
        Integer videoColumnId;
        for (VideoColumnBind videoColumnBind1:select3) {
            videoColumnId = videoColumnBind1.getVideoColumnId();
            videoContentInfo.setVideoColumnId(videoColumnId);
            break;
        }
        //获得标签的信息
        List<VideoTagBind> select1 = videoTagBindMapper.seletByVideoId(contentId);
        List<String> tagNames = new ArrayList<>();
        for (VideoTagBind v : select1) {
            Integer videoTagBindId = v.getId();
            VideoTagBind videoTagBind2 = videoTagBindMapper.selectByPrimaryKey(videoTagBindId);
            Integer videoTagId = videoTagBind2.getVideoTagId();
            VideoTag videoTag = videoTagMapper.selectByPrimaryKey(videoTagId);
            String name = videoTag.getName();
            tagNames.add(name);
        }
        videoContentInfo.setTagNameList(tagNames);
        //获得视频地址和时长的信息
        //获得视频时长
        String duration = contentVideo.getDuration();
        String source = contentVideo.getSource();
        videoContentInfo.setVideoDuration(duration);
        videoContentInfo.setSource(source);
        //获得视频
        Integer did = contentVideo.getDid();
        VideoDemand videoDemand = new VideoDemand();
        videoDemand.setId(did);
        Demand demand = demandMapper.selectByPrimaryKey(did);
        String title = demand.getTitle();
        videoDemand.setTitle(title);
        String name = demand.getName();
        videoDemand.setUrl("http://devvideotest.oss-cn-beijing.aliyuncs.com/outactmvp/" + name);
        videoContentInfo.setVideoDemand(videoDemand);
        //获取绑定的评论
        ContentStandard contentStandard = new ContentStandard();
        contentStandard.setCid(contentId);
        List<ContentStandard> select2 = contentStandardMapper.select(contentStandard);
        ArrayList<String> commentContentList = new ArrayList<>();
        for (ContentStandard contentStandard1 : select2) {
            String content1 = contentStandard1.getContent();
            commentContentList.add(content1);
        }
        videoContentInfo.setCommentContentList(commentContentList);
        return videoContentInfo;
    }

    /**
     * 修改视频内容
     *
     * @param
     * @return
     */
    public VideoContentInfo updateVideoContent(VideoContentInfo entity) {
        //状态，1：正常，0：删除
        //entity.setContentState(1);
        //视频
        //entity.setCategory(2);
        //entity.setContentType(4);
        //'显示状态，0审核列不显示，1审核列表显示',
        entity.setIsCheck(1);
        //审核状态（0待审核，1审核通过，2审核不通过）
        entity.setCheckStatus(0);
        contentMapper.updateVideoContent(entity);
        Integer contentId = entity.getId();
        //绑定的频道更新
        //更新所绑定的频道信息
        List<Integer> appChannels = channelContentMapper.selectAppChannels(contentId);
        List<Integer> pcChannels = channelContentMapper.selectPcChannels(contentId);
        appChannels.add(59);
        for (Integer channelId : appChannels) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setChannelId(channelId);
            channelContent.setContentId(contentId);
            channelContentMapper.delete(channelContent);
        }
        for (Integer channelId : pcChannels) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setChannelId(channelId);
            channelContent.setContentId(contentId);
            channelContentMapper.delete(channelContent);
        }
        //绑定app频道
        if (entity.getAppChannelIdList().contains(59)==false){
            entity.getAppChannelIdList().add(59);
        }
        if (entity.getAppChannelIdList() != null && entity.getAppChannelIdList().size() > 0) {
            for (Integer integer : entity.getAppChannelIdList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                channelContent.setChannelId(integer);
                channelContent.setFromType(1);
                channelContent.setCardType(5);
                //设置排序值
                Integer integer1 = channelContentMapper.selectMaxOrderNumber();
                if (integer1 == null) {
                    channelContent.setOrderNumber(1);
                } else {
                    channelContent.setOrderNumber(integer1 + 1);
                }
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                channelContent.setIsPublish(0);
                //绑定推荐权重 recommend_weight kf_channel_content
                channelContent.setRecommendWeight(entity.getRecommendWeight());
                channelContent.setCate(2);
                channelContentMapper.insertSelective(channelContent);
            }
        }
        // 绑定pc频道
        if (entity.getPcChannelIdList() != null && entity.getPcChannelIdList().size() > 0) {
            for (Integer integer : entity.getPcChannelIdList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                channelContent.setChannelId(integer);
                channelContent.setFromType(1);
                channelContent.setCardType(5);
                //设置排序值
                Integer integer1 = channelContentMapper.selectMaxOrderNumber();
                if (integer1 == null) {
                    channelContent.setOrderNumber(1);
                } else {
                    channelContent.setOrderNumber(integer1 + 1);
                }
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                channelContent.setIsPublish(0);
                //绑定推荐权重 recommend_weight kf_channel_content
                channelContent.setRecommendWeight(entity.getRecommendWeight());
                channelContent.setCate(2);
                channelContentMapper.insertSelective(channelContent);
            }
        }
        //更新标签
        //删除标签的信息
        List<VideoTagBind> select1 = videoTagBindMapper.seletByVideoId(contentId);
        for (VideoTagBind vtb : select1) {
            videoTagBindMapper.delete(vtb);
        }
        //添加标签绑定
        if (entity.getTagNameList() != null && entity.getTagNameList().size() > 0) {
            VideoTag videoTag = new VideoTag();
            for (String name : entity.getTagNameList()) {
                videoTag.setName(name);
                //匹配标签
                List<VideoTag> tagList = videoTagMapper.select(videoTag);
                Integer tagId = null;
                for (VideoTag tag : tagList) {
                    if (name.equals(tag.getName())) {
                        tagId = tag.getId();
                        VideoTagBind videoTagBind = new VideoTagBind();
                        videoTagBind.setVideoId(entity.getId());
                        videoTagBind.setVideoTagId(tagId);
                        videoTagBindMapper.insertSelective(videoTagBind);
                        break;
                    }
                }
                if (tagList.size() <= 0) {
                    VideoTag tag = new VideoTag();
                    tag.setName(name);
                    tag.setCreateTime(new Date());
                    tag.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
                    tag.setCreateUid(entity.getCreateUid());
                    videoTagMapper.addTag(tag);
                    VideoTagBind videoTagBind = new VideoTagBind();
                    videoTagBind.setVideoId(entity.getId());
                    videoTagBind.setVideoTagId(tag.getId());
                    videoTagBindMapper.insertSelective(videoTagBind);
                }
            }
        }
        //更新视频地址 视频时长
        //重置
        ContentVideo contentVideo = contentVideoMapper.selectByPrimaryKey(contentId);
        if (contentVideo != null) {
            contentVideoMapper.delete(contentVideo);
        }
        if (entity.getVideoDemand() != null) {
            VideoDemand videoDemand = entity.getVideoDemand();
            ContentVideo contentVideo1 = new ContentVideo();
            Demand demand = demandMapper.selectByPrimaryKey(videoDemand.getId());
            String url = "http://devvideotest.oss-cn-beijing.aliyuncs.com/outactmvp/" + demand.getName();
            contentVideo1.setUrl(url);
            contentVideo1.setDid(videoDemand.getId());
            contentVideo1.setSourceId(entity.getSourceId());
            //未删除 状态，1：正常，0：删除'
            contentVideo1.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
            //绑定来源
            contentVideo1.setSource(entity.getSource());
            contentVideo1.setIsRecommend(entity.getIsRecommend());
            contentVideo1.setDuration(entity.getVideoDuration());
            contentVideo1.setCid(entity.getId());
            contentVideoMapper.insertSelective(contentVideo1);
        }
        //更新推荐权重 kf_channel_content recommend_weight
        ChannelContent channelContent = new ChannelContent();
        channelContent.setContentId(contentId);
        List<ChannelContent> select = channelContentMapper.select(channelContent);
        for (ChannelContent c : select) {
            if (!Objects.equals(c.getRecommendWeight(), entity.getRecommendWeight())) {
                c.setRecommendWeight(entity.getRecommendWeight());
            }
        }
        //更新栏目
        VideoColumnBind videoColumnBind = new VideoColumnBind();
        videoColumnBind.setFromType(1);
        videoColumnBind.setCid(contentId);
        List<VideoColumnBind> select3 = videoColumnBindMapper.select(videoColumnBind);
        for (VideoColumnBind videoColumnBind1:select3) {
            videoColumnBindMapper.delete(videoColumnBind1);
        }
        if (entity.getVideoColumnId()!=null){
            VideoColumnBind videoColumnBindNew = new VideoColumnBind();
            videoColumnBindNew.setCid(entity.getId());
            videoColumnBindNew.setFromType(1);
            videoColumnBindNew.setVideoColumnId(entity.getVideoColumnId());
            videoColumnBindNew.setCreateTime(entity.getUpdateTime());
            videoColumnBindNew.setCreateUid(entity.getUpdateUid());
            videoColumnBindNew.setTitle(entity.getTitle());
            videoColumnBindNew.setUpdateUid(entity.getUpdateUid());
            long time = entity.getUpdateTime().getTime();
            videoColumnBindNew.setUpdateTime((int) time);
            videoColumnBindMapper.insertSelective(videoColumnBindNew);
        }
        //更新评论
//        ContentStandard contentStandard = new ContentStandard();
//        contentStandard.setCid(contentId);
//        List<ContentStandard> select2 = contentStandardMapper.select(contentStandard);
//        for (ContentStandard contentStandard1:select2) {
//            contentStandardMapper.delete(contentStandard1);
//        }
//        List<String> commentContentList = entity.getCommentContentList();
//        if(commentContentList!=null&&commentContentList.size()>0){
//            for (String content : commentContentList) {
//                ContentStandard contentStandard2=new ContentStandard();
//                contentStandard2.setCid(entity.getId());
//                contentStandard2.setContent(content);
//                contentStandard2.setCreateTime(new Date());
//                contentStandard2.setCreateUid(entity.getCreateUid());
//                contentStandard2.setStat(1);
//                contentStandard2.setType(1);
//                contentStandardMapper.insertSelective(contentStandard2);
//            }
//        }
        return entity;
    }

    /**
     * 查询审核内容
     *
     * @param contentInfo
     * @return
     */
    public TableResultResponse<ContentInfo> getContentNotCheckPage(ContentInfo contentInfo) {
        Page<Object> result = PageHelper.startPage(contentInfo.getPage(), contentInfo.getLimit());
        List<ContentInfo> list = contentMapper.getContentNotCheckPage(contentInfo);
        return new TableResultResponse<ContentInfo>(result.getTotal(), list);
    }

    /**
     * 查询对应的内ids
     *
     * @param ids
     * @return
     */
    public List<ContentInfo> selectContentByIds(List<Integer> ids, Integer checkStatus) {
        return contentMapper.selectContentByIds(ids, checkStatus);
    }


    public void updateVideoViews(Integer id, Integer platform) {
        // 如果未指定文章id
        Content content = this.contentMapper.selectByPrimaryKey(id);
        Integer uid = 0;
        Integer did = 0;
        if (content.getContentType().equals(Integer.valueOf(2))) {
            did = this.contentArticleMapper.selectByPrimaryKey(id).getDid();
        } else if (content.getContentType().equals(Integer.valueOf(4))) {
            did = this.contentVideoMapper.selectByPrimaryKey(id).getDid();
        } else if (content.getContentType().equals(Integer.valueOf(11))) {
        }

        if (platform.equals(APPCommonEnum.PLATFORM_IOS)) {
            content.setAppvideoView(content.getAppvideoView() + Integer.valueOf(1));
        } else {
        }
        mongoTemplate.save(content);
        content.setVideoView(content.getVideoView() + Integer.valueOf(1));
        if (contentMapper.updateByPrimaryKeySelective(content) < 1) {
            Assert.isTrue(contentMapper.updateByPrimaryKeySelective(content) < 1, "新增播放量失败");
        }

    }

    /**
     * 添加图集
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ContentInfo addContentAtlas(ContentInfo entity) {
        if (entity.getChannelIdList() != null) {
            entity.setChannels(entity.getChannelIdList().size());
        }
        contentMapper.addContentAtlas(entity);
        if (entity.getChannelIdList() != null && entity.getChannelIdList().size() > 0) {
            //添加频道绑定
            addBindChannel(entity);
        }
        //添加标签绑定
        contentBindTag(entity);
        //添加记者绑定
        contentBindKpi(entity);
        //绑定爆料
        bindBurst(entity.getBurstId(), entity.getId());
        //绑定评论
        List<String> commentContentList = entity.getCommentContentList();
        bindComment(entity, commentContentList);
        //绑定图集
        List<ImageInfo> imageInfoList = entity.getImageInfoList();
        ContentImage contentImage = new ContentImage();
        contentImage.setCid(entity.getId());
        contentImage.setDescType(entity.getDescType());
        contentImage.setStat(1);
        contentImage.setNum(imageInfoList.size());
        contentImageMapper.insertSelective(contentImage);
        for (ImageInfo imageInfo : imageInfoList) {
            ContentImageGroup contentImageGroup = new ContentImageGroup();
            BeanUtils.copyProperties(imageInfo, contentImageGroup);
            contentImageGroup.setCid(entity.getId());
            contentImageGroupMapper.addContentImageGroup(contentImageGroup);
        }
        //添加日志
        addAdminLog(entity, System.currentTimeMillis() / 1000, "增加图集");
        return entity;
    }

    /**
     * 更新图集
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateContentAtlas(ContentInfo entity) {
        Content content = new Content();
        BeanUtils.copyProperties(entity, content);
        if (entity.getChannelIdList() != null) {
            entity.setChannels(entity.getChannelIdList().size());
        }
        //更新后进入审核
        //content.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Show.getKey());
        //content.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        int update = contentMapper.updateByPrimaryKeySelective(content);
        //绑定频道
        updateBindChannel(entity);
        //添加标签绑定
        if (entity.getTagNameList() != null && entity.getTagNameList().size() > 0) {
            //删除
            int flag = videoTagBindMapper.deleteTagByCid(entity.getId());
            contentBindTag(entity);
        }
        //更新绑定记者
        updateBindKpiCount(entity);
        //图集更新图片组
        List<ImageInfo> imageInfoList = entity.getImageInfoList();
        if (imageInfoList != null && imageInfoList.size() > 0) {
            ContentImage contentImage = new ContentImage();
            contentImage.setCid(entity.getId());
            contentImage.setDescType(entity.getDescType());
            contentImage.setNum(imageInfoList.size());
            contentImageMapper.updateByPrimaryKeySelective(contentImage);
            //删除图组
            ContentImageGroup contentImageGroup = new ContentImageGroup();
            contentImageGroup.setCid(entity.getId());
            contentImageGroupMapper.delete(contentImageGroup);
            for (int i = imageInfoList.size() - 1; i >= 0; i--) {
                ContentImageGroup contentImageGroup1 = new ContentImageGroup();
                BeanUtils.copyProperties(imageInfoList.get(i), contentImageGroup1);
                contentImageGroup1.setCid(entity.getId());
                contentImageGroupMapper.addContentImageGroup(contentImageGroup1);
            }
        }
        addAdminLog(entity, System.currentTimeMillis() / 1000, "修改图集");
        return update;
    }

    /**
     * 新增活动
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ActivityInfo addContentActivity(ActivityInfo entity) {
        if (entity.getAppChannelIdList() != null) {
            entity.setChannels(entity.getAppChannelIdList().size());
        }
        contentMapper.addContentActivity(entity);
        if (entity.getAppChannelIdList() != null && entity.getAppChannelIdList().size() > 0) {
            //添加频道绑定
            addBindChannelForActivity(entity, 0);
        }
        //添加日志
        ContentInfo contentInfo = new ContentInfo();
        BeanUtils.copyProperties(entity, contentInfo);
        addAdminLog(contentInfo, System.currentTimeMillis() / 1000, "增加活动");
        return entity;
    }

    /**
     * 更新活动
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateContentActivity(ActivityInfo entity) {
        Content content = new Content();
        BeanUtils.copyProperties(entity, content);
        if (entity.getAppChannelIdList() != null) {
            entity.setChannels(entity.getAppChannelIdList().size());
        }
        //更新后进入审核
        //content.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Show.getKey());
        //content.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        int update = contentMapper.updateByPrimaryKeySelective(content);
        //绑定频道
        updateBindChannelForActivity(entity);
        //添加日志
        ContentInfo contentInfo = new ContentInfo();
        BeanUtils.copyProperties(entity, contentInfo);
        addAdminLog(contentInfo, System.currentTimeMillis() / 1000, "修改活动");
        return update;
    }


    /**
     * 测试mogo存储
     *
     * @param locationInfo
     * @return
     */
    public ObjectRestResponse saveMogoLocation(LocationInfo locationInfo) {
        //contentDao.removeMogoLocation(locationInfo);
//        Document document = new Document();
//        document.append("new","1234");
//        mongoTemplate.save(document,"content_article_log");
        return new ObjectRestResponse();
    }

    /**
     * mogo查询对应的地理位置
     *
     * @param contentLocationInfo
     * @return
     */
    public List<LocationInfo> getContentList(ContentLocationInfo contentLocationInfo) {
        ContentLocationInfo contentLocation = contentDao.findContentLocation(contentLocationInfo);
        return contentLocation.getLocation();
    }

    /**
     * 查询专题包含推荐权重
     *
     * @param id
     * @return
     */
    public ContentInfo selectSubjectById(Integer id) {
        return contentMapper.selectSubjectById(id);
    }

    /**
     * 新增VR
     *
     * @param entity
     * @return
     */
    public VRContentAddInfo addVRContent(VRContentAddInfo entity) {
        //初始化属性
        //审核列表显示
        entity.setCheckStatus(0);
        entity.setIsCheck(1);
        //未删除
        entity.setContentState(1);
        entity.setCategory(entity.getCategory());
        entity.setContentType(VR.getKey());
        if (entity.getIntroduction() == null) {
            entity.setIntroduction("");
        }
        contentMapper.addVRContent(entity);
        //绑定频道
        //app频道
        if (entity.getCategory()==1){
            if (entity.getAppChannelList().size() > 0 && entity.getAppChannelList() != null) {
                for (Integer integer : entity.getAppChannelList()) {
                    ChannelContent channelContent = new ChannelContent();
                    channelContent.setContentId(entity.getId());
                    //来源于1：content表
                    channelContent.setFromType(1);
                    //vr
                    channelContent.setCate(1);
                    //设置排序值
                    Integer integer1 = channelMapper.selectMaxOrderNumber(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
                    if (integer1 == null) {
                        channelContent.setOrderNumber(1);
                    } else {
                        channelContent.setOrderNumber(integer1 + 1);
                    }
                    channelContent.setType(VR.getKey());
                    channelContent.setCardType(5);
                    channelContent.setChannelId(integer);
                    channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                    channelContent.setIsPublish(0);
                    channelContentMapper.insertSelective(channelContent);
                }
            }
        }
        //vr频道
        if (entity.getVrChannelList().size() > 0 && entity.getVrChannelList() != null) {
            for (Integer integer : entity.getVrChannelList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                //来源于1：content表
                channelContent.setFromType(1);
                //vr
                channelContent.setCate(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
                //设置排序值
                Integer integer1 = channelMapper.selectMaxOrderNumber(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
                if (integer1 == null) {
                    channelContent.setOrderNumber(1);
                } else {
                    channelContent.setOrderNumber(integer1 + 1);
                }
                channelContent.setCardType(5);
                channelContent.setType(VR.getKey());
                channelContent.setChannelId(integer);
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                channelContent.setIsPublish(0);
                channelContentMapper.insertSelective(channelContent);
            }
        }
        //绑定VR作者
        List<KpiCountContentInfo> userList = entity.getKpiCountList();
        if (userList != null && userList.size() > 0) {
            for (KpiCountContentInfo kpiCountInfo : userList) {
                KpiCount kpiCount = new KpiCount();
                CorpUser corpUser = corpUserMapper.selectByPrimaryKey(kpiCountInfo.getUid());
                kpiCount.setIsNumPass(1);
                kpiCount.setArticleType(0);
                kpiCount.setName(corpUser.getName());
                kpiCount.setUid(corpUser.getId());
                //设置为VR
                kpiCount.setType(VR.getKey());
                kpiCount.setTypeId(entity.getId());
                kpiCount.setCreateTime(new Date());
                kpiCount.setWeight(kpiCountInfo.getWeight());
                kpiCount.setJobId(corpUser.getJobId());
                kpiCount.setDepartmentId(corpUser.getLevel2Id());
                kpiCount.setRemarks(kpiCountInfo.getRemarks());
                kpiCountMapper.insertSelective(kpiCount);
            }
        }
        //绑定视频
        VideoDemand videoDemand = entity.getVideoDemand();
        if (videoDemand != null) {
            ContentVr contentVr = new ContentVr();
            contentVr.setDid(videoDemand.getId());
            contentVr.setContentId(entity.getId());
            contentVr.setIsDelete(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
            contentVr.setUrl(videoDemand.getUrl());
            contentVr.setDuration(entity.getVideoDuration());
            contentVrMapper.insertSelective(contentVr);
        }
        return entity;
    }


    /**
     * 得到VR详情
     * @param contentId
     * @return
     */
    public VRContentAddInfo getVrContent(Integer contentId) {
        VRContentAddInfo vrContentAddInfo = new VRContentAddInfo();
        //得到content表中的信息
        Content content = contentMapper.selectByPrimaryKey(contentId);
        BeanUtils.copyProperties(content, vrContentAddInfo);
        //得到绑定的vr频道
        List<Integer> integers = channelContentMapper.selectVrChannels(contentId);
        vrContentAddInfo.setVrChannelList(integers);
        //得到绑定的app频道
        List<Integer> integers1 = channelContentMapper.selectAppChannelsForVr(contentId);
        vrContentAddInfo.setAppChannelList(integers1);
        //得到绑定的Vr作者信息
        List<KpiCountContentInfo> kpiCountContentInfos = new ArrayList<>();
        KpiCount kpiCount = new KpiCount();
        kpiCount.setType(VR.getKey());
        kpiCount.setTypeId(contentId);
        List<KpiCount> kpiCounts = kpiCountMapper.select(kpiCount);
        for (KpiCount k : kpiCounts) {
            KpiCountContentInfo kpiCountContentInfo = new KpiCountContentInfo();
            kpiCountContentInfo.setUid(k.getUid());
            String remarks = k.getRemarks();
            kpiCountContentInfo.setRemarks(remarks);
            BigDecimal weight = k.getWeight();
            kpiCountContentInfo.setWeight(weight);
            kpiCountContentInfos.add(kpiCountContentInfo);
            vrContentAddInfo.setKpiCountList(kpiCountContentInfos);
        }
        //获得视频地址和时间
        VideoDemand videoDemand = new VideoDemand();
        ContentVr contentVr = new ContentVr();
        contentVr.setContentId(contentId);
        ContentVr contentVr1 = contentVrMapper.selectOne(contentVr);
        if (contentVr1 != null) {
            Integer did = contentVr1.getDid();
            videoDemand.setId(did);
            Demand demand = demandMapper.selectByPrimaryKey(contentVr1.getDid());
            String title = demand.getTitle();
            videoDemand.setTitle(title);
            String name = demand.getName();
            videoDemand.setUrl("http://devvideotest.oss-cn-beijing.aliyuncs.com/outactmvp/" + name);
            vrContentAddInfo.setVideoDuration(contentVr1.getDuration());
        } else {
            videoDemand.setId(null);
            videoDemand.setUrl("");
            videoDemand.setTitle("");
            vrContentAddInfo.setVideoDuration("");
        }
        vrContentAddInfo.setVideoDemand(videoDemand);
        //剔除重复元素
        if (vrContentAddInfo.getKpiCountList() != null) {
            HashSet hashSet = new HashSet(vrContentAddInfo.getKpiCountList());
            vrContentAddInfo.getKpiCountList().clear();
            vrContentAddInfo.getKpiCountList().addAll(hashSet);
        }
        return vrContentAddInfo;
    }

    /**
     * VR的编辑
     * @param entity
     * @return
     */
    public VRContentAddInfo updateVR(VRContentAddInfo entity) {
        entity.setCheckStatus(0);
        entity.setIsCheck(1);
        contentMapper.updateVRContent(entity);
        Integer contentId = entity.getId();
        //更新绑定的app频道
        List<Integer> appChannels = channelContentMapper.selectVrChannels(contentId);
        //更新绑定的Vr频道
        List<Integer> vrChannels = channelContentMapper.selectVrChannels(contentId);
        //聚合
        vrChannels.addAll(appChannels);
        for (Integer channelId : vrChannels) {
            ChannelContent channelContent = new ChannelContent();
            channelContent.setChannelId(channelId);
            channelContent.setContentId(contentId);
            channelContentMapper.delete(channelContent);
        }
        if (entity.getAppChannelList().size() > 0 && entity.getAppChannelList() != null) {
            for (Integer integer : entity.getAppChannelList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                //来源于1：content表
                channelContent.setFromType(1);
                //vr
                channelContent.setCate(1);
                //设置排序值
                Integer integer1 = channelMapper.selectMaxOrderNumber(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
                if (integer1 == null) {
                    channelContent.setOrderNumber(1);
                } else {
                    channelContent.setOrderNumber(integer1 + 1);
                }
                channelContent.setType(VR.getKey());
                channelContent.setCardType(5);
                channelContent.setChannelId(integer);
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                channelContent.setIsPublish(0);
                channelContentMapper.insertSelective(channelContent);
            }
        }

        if (entity.getVrChannelList().size() > 0 && entity.getVrChannelList() != null) {
            for (Integer integer : entity.getVrChannelList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                //来源于1：content表
                channelContent.setFromType(1);
                //vr
                channelContent.setCate(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
                //设置排序值
                Integer integer1 = channelMapper.selectMaxOrderNumber(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
                if (integer1 == null) {
                    channelContent.setOrderNumber(1);
                } else {
                    channelContent.setOrderNumber(integer1 + 1);
                }
                channelContent.setType(VR.getKey());
                channelContent.setCardType(5);
                channelContent.setChannelId(integer);
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                channelContent.setIsPublish(0);
                channelContentMapper.insertSelective(channelContent);
            }

        }
        //更新Vr作者
        //删除绑定信息作者
        KpiCount kpiCount = new KpiCount();
        kpiCount.setType(VR.getKey());
        kpiCount.setTypeId(contentId);
        List<KpiCount> kpiCounts = kpiCountMapper.select(kpiCount);
        for (KpiCount kpiCount1 : kpiCounts) {
            kpiCountMapper.delete(kpiCount1);
        }
        List<KpiCountContentInfo> userList = entity.getKpiCountList();
        if (userList != null && userList.size() > 0) {
            for (KpiCountContentInfo kpiCountInfo : userList) {
                KpiCount kpiCount1 = new KpiCount();
                CorpUser corpUser = corpUserMapper.selectByPrimaryKey(kpiCountInfo.getUid());
                kpiCount1.setIsNumPass(1);
                kpiCount1.setArticleType(0);
                kpiCount1.setName(corpUser.getName());
                kpiCount1.setUid(corpUser.getId());
                //设置为VR
                kpiCount1.setType(VR.getKey());
                kpiCount1.setTypeId(entity.getId());
                kpiCount1.setCreateTime(new Date());
                kpiCount1.setWeight(kpiCountInfo.getWeight());
                kpiCount1.setJobId(corpUser.getJobId());
                kpiCount1.setDepartmentId(corpUser.getLevel2Id());
                kpiCount1.setRemarks(kpiCountInfo.getRemarks());
                kpiCountMapper.insertSelective(kpiCount1);
            }
        }
        //更新绑定的视频
        ContentVr contentVr = contentVrMapper.selectByPrimaryKey(contentId);
        if (contentVr != null) {
            contentVrMapper.delete(contentVr);
        }
        VideoDemand videoDemand = entity.getVideoDemand();
        if (videoDemand != null) {
            ContentVr contentVr2 = new ContentVr();
            contentVr2.setDid(videoDemand.getId());
            contentVr2.setContentId(entity.getId());
            contentVr2.setIsDelete(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
            contentVr2.setUrl(videoDemand.getUrl());
            contentVr2.setDuration(entity.getVideoDuration());
            contentVrMapper.insertSelective(contentVr2);
        }
        return entity;
    }

    /**
     * 分页查询Vr列表
     *
     * @param entity
     * @return
     */
    public TableResultResponse<VideoContentSelectInfo> getVRPage(VideoSelectionInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置晒出条件
        //审核页不出现
        entity.setIsCheck(0);
        //'审核状态（0待审核，1审核通过，2审核不通过）' check_status
        entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
        //contentState  '状态，1：正常，0：删除'
        entity.setContentState(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.VR.getKey());
        //3 vr
        entity.setCategory(3);
        List<VideoContentSelectInfo> list = contentMapper.getVideoPage(entity);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 查询活动
     *
     * @param id
     * @return
     */
    public ContentInfo selectActivityById(Integer id) {
        return contentMapper.selectActivityById(id);
    }

    /**
     * 查询日志
     *
     * @param id
     * @return
     */
    public List<ContentArticleLogInfo> getContentLog(Integer id) {
        ContentArticleLog contentArticleLog = new ContentArticleLog();
        contentArticleLog.setCid(id);
        List<ContentArticleLog> list = contentDao.getContentLog(contentArticleLog);
        List<ContentArticleLogInfo> listLogInfo = new ArrayList<>(list.size());
        for (ContentArticleLog articleLog : list) {
            ContentArticleLogInfo contentArticleLogInfo = new ContentArticleLogInfo();
            if (articleLog.getEvent().equals(NewsEnumsConsts.ContentArticleLogOfEvent.create.value())) {
                contentArticleLogInfo.setEvent(NewsEnumsConsts.ContentArticleLogOfEvent.create.desc());
                //详情
                List<Object> detail = articleLog.getDetail();
                String title = null;
                for (Object object : detail) {
                    Map entry = (Map) object;
                    title = entry.get("new").toString();
                }
                contentArticleLogInfo.setDetail("标题做出了修改,新值为【" + title + "】;");
            } else if (articleLog.getEvent().equals(NewsEnumsConsts.ContentArticleLogOfEvent.edit.value())) {
                contentArticleLogInfo.setEvent(NewsEnumsConsts.ContentArticleLogOfEvent.edit.desc());
                //详情
                List<Object> detail = articleLog.getDetail();
                String content = "";
                for (Object object : detail) {
                    Map entry = (Map) object;
                    if (entry.get("field").equals("title")) {
                        content += "标题做出了修改,新值为【" + entry.get("new").toString() + "】;";
                    } else if (entry.get("field").equals("source")) {
                        content += "来源做出了修改,新值为【" + entry.get("new").toString() + "】;";
                    } else if (entry.get("field").equals("content")) {
                        content += "文章详情做出了修改;";
                    } else if (entry.get("field").equals("channel")) {
                        List<Object> old = (List<Object>) entry.get("old");
                        List<Object> aNew = (List<Object>) entry.get("new");
                        StringBuffer stringBuffer = new StringBuffer();
                        for (Object oldObject : old) {
                            Map oldChannel = (Map) oldObject;
                            stringBuffer.append(oldChannel.get("name") + "|");
                        }
                        content += "频道做出了修改,原值为【" + stringBuffer.substring(0, stringBuffer.length() - 1) + "】";
                        StringBuffer stringBuffer1 = new StringBuffer();
                        for (Object newObject : aNew) {
                            Map newChannel = (Map) newObject;
                            stringBuffer1.append(newChannel.get("name") + "|");
                        }
                        content += ",新值为【" + stringBuffer1.substring(0, stringBuffer.length() - 1) + "】;";
                    }
                }
                contentArticleLogInfo.setDetail(content);
            } else if (articleLog.getEvent().equals(NewsEnumsConsts.ContentArticleLogOfEvent.check.value())) {
                List<Object> detail = articleLog.getDetail();
                String content = "";
                for (Object object : detail) {
                    Map entry = (Map) object;
                    if (entry.get("field").equals("check")) {//审核状态做出了修改,原值为【待审核】,新值为【审核通过】
                        content += "审核状态做出了修改,原值为【" + entry.get("old").toString() + "】,新值为【" + entry.get("old").toString() + "】";
                    }
                }
                contentArticleLogInfo.setDetail(content);
                contentArticleLogInfo.setEvent(NewsEnumsConsts.ContentArticleLogOfEvent.check.desc());
            }
            contentArticleLogInfo.setIp(articleLog.getIp());
            try {
                Date date = DateHandler.longToDate(Long.valueOf(articleLog.getCreate_time() * 1000l), "yyyy-MM-dd HH:mm:ss");
                contentArticleLogInfo.setCreateTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            AdminUser adminUser = adminUserMapper.selectByPrimaryKey(articleLog.getUid());
            if (adminUser != null) {
                contentArticleLogInfo.setUser(adminUser.getNickname());
            }
            if (StringUtils.isEmpty(contentArticleLogInfo.getIp())) {
                contentArticleLogInfo.setIp("");
            }
            listLogInfo.add(contentArticleLogInfo);
        }
        return listLogInfo;
    }

    /**
     * 模糊查询（消息推送）
     *
     * @param contentInfo
     * @return
     */
    public TableResultResponse<ContentInfo> getContentPageForMessage(ContentInfo contentInfo) {
        Page<ContentInfo> page = PageHelper.startPage(contentInfo.getPage(), contentInfo.getLimit());
        List<ContentInfo> contentInfoList = contentMapper.getContentPageForMessage(contentInfo);
        return new TableResultResponse<>(page.getTotal(), contentInfoList);
    }

    /**
     * 专题子目录搜索
     *
     * @param entity
     * @return
     */
    public TableResultResponse<ContentInfo> getContentPageForBind(ContentInfo entity) {
        Page<ContentInfo> page = PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<ContentInfo> contentInfoList = contentMapper.getContentPageForBind(entity);
        return new TableResultResponse<>(page.getTotal(), contentInfoList);
    }

    /**
     * 审核通过与驳回
     *
     * @param contentInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ContentInfo updateCheckPassContent(ContentInfo contentInfo) {
        Long time = System.currentTimeMillis() / 1000;
        //文章类型
        Integer type = null;
        Integer oldCheck = null;
        if (NewsEnumsConsts.ChannelContentOfFromType.Content.key().equals(contentInfo.getFromType())) {
            Content content = new Content();
            BeanUtils.copyProperties(contentInfo, content);
            //修改审核状态
            // 1.搜索源内容
            Date date = new Date();
            Content oldContent = contentMapper.selectByPrimaryKey(content.getId());
            if (oldContent == null) {
                Assert.notNull(contentInfo.getId(), "内容不存在");
            }
            if (content.getCheckStatus() == 1) {
                // 2.判断第一次，修改状态，时间等（所有）
                content.setCheckTime(time.intValue());
                //更新发布状态,设置第一次审核，
                if (oldContent.getFirstCheckTime() == null && content.getCheckStatus() == 1) {
                    content.setFirstCheckTime(date);
                }
                content.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
                // 3.同步日志信息（文章）
                if (oldContent.getContentType().equals(NewsEnumsConsts.ContentOfContentType.Article.getKey())) {
                    ContentArticleLog contentArticleLog = new ContentArticleLog();
                    contentArticleLog.setCid(content.getId());
                    List<ContentArticleLog> contentLog = contentDao.getContentLog(contentArticleLog);
                    if (contentLog != null && contentLog.size() > 0) {
                        for (ContentArticleLog articleLog : contentLog) {
                            if (articleLog.getEvent().equals(NewsEnumsConsts.ContentArticleLogOfEvent.edit.value())) {
                                if (articleLog.getDetail() != null && articleLog.getDetail().size() > 0) {
                                    List<Object> detail = articleLog.getDetail();
                                    for (Object object : detail) {
                                        Map entry = (Map) object;
                                        if (entry.get("field").equals("title")) {
                                            content.setTitle(entry.get("new").toString());
                                        } else if (entry.get("field").equals("source")) {
                                            ContentArticle contentArticle = new ContentArticle();
                                            contentArticle.setSource(entry.get("new").toString());
                                            contentArticle.setCid(content.getId());
                                            contentArticleMapper.updateByPrimaryKeySelective(contentArticle);
                                        } else if (entry.get("field").equals("content")) {
                                            ContentArticle contentArticle = new ContentArticle();
                                            contentArticle.setContent(entry.get("new").toString());
                                            contentArticle.setCid(content.getId());
                                            contentArticleMapper.updateByPrimaryKeySelective(contentArticle);
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
            type = content.getContentType();
            contentInfo.setTitle(content.getTitle());
            oldCheck = content.getCheckStatus();
            contentMapper.updateByPrimaryKeySelective(content);
            //添加redis
            redisTemplate.opsForSet().add(CONTENT_REDIS_LIST_KEY, contentInfo.getId());
        } else if (NewsEnumsConsts.ChannelContentOfFromType.Live.key().equals(contentInfo.getFromType())) {
            Live live = liveMapper.selectByPrimaryKey(contentInfo.getId());
            if (live == null || live.getIsDelete().equals(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key())) {
                Assert.notNull(live.getId(), "直播不能为空");
            }
            live.setCheckState(contentInfo.getCheckStatus());
            if (contentInfo.getCheckStatus().equals(NewsEnumsConsts.ContentOfCheckStatus.PASS.key())) {
                live.setCheckTime(time.intValue());
            }
            type = NewsEnumsConsts.MessageOfTargetType.NewLive.getKey();
            contentInfo.setTitle(live.getTitle());
            oldCheck = live.getCheckState();
            liveMapper.updateByPrimaryKeySelective(live);
            //添加redis
            redisTemplate.opsForSet().add(LIVE_REDIS_LIST_KEY, contentInfo.getId());
        }
        Date date = new Date();
        //添加日志
        AdminLog adminLog = new AdminLog();
        adminLog.setIp(contentInfo.getIp());
        adminLog.setType(type);
        adminLog.setCreateUid(contentInfo.getUpdateUid());
        adminLog.setCreateTime(time.intValue());
        adminLog.setContentId(contentInfo.getId());
        if (StringUtils.isNotEmpty(contentInfo.getTitle())) {
            adminLog.setActionUrl(contentInfo.getTitle());
        } else {
            adminLog.setActionUrl("");
        }
        if (contentInfo.getCheckStatus().equals(NewsEnumsConsts.ContentOfCheckStatus.PASS.key())) {
            // 4.发布所有频道绑定内容（所有）
            ChannelContent entity = new ChannelContent();
            entity.setFromType(contentInfo.getFromType());
            entity.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
            entity.setContentId(contentInfo.getId());
            List<ChannelContent> select = channelContentMapper.select(entity);
            if (select != null && select.size() > 0) {
                for (ChannelContent channelContent : select) {
                    channelContent.setPublishTime(date);
                    channelContent.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
                    channelContent.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
                    channelContent.setCheckUid(contentInfo.getUpdateUid());
                    //修改地理位置状态
                    Update update = new Update();
                    update.set("stat", 1);
                    update.set("channel.$.stat", 1);
                    contentDao.updateSiteContentDetail(contentInfo, update, channelContent.getChannelId());
                    channelContentMapper.updateByPrimaryKeySelective(channelContent);
                }
            }
            adminLog.setAction("审核通过");
        } else {
            adminLog.setAction("审核不通过");
        }
        adminLogMapper.insertSelective(adminLog);
        //mongo审核日志
        addMongoLog(contentInfo, time, oldCheck);
        //kpi 修改
        updateKpiCount(contentInfo, type, date);
        //修改爆料状态
        if (NewsEnumsConsts.ChannelContentOfFromType.Content.key().equals(contentInfo.getFromType())) {
            ContentBurst contentBurst = new ContentBurst();
            contentBurst.setContentId(contentInfo.getId());
            List<ContentBurst> select = contentBurstMapper.select(contentBurst);
            Integer uid = null;
            if (null != select && select.size() > 0) {
                ContentBurst contentBurst1 = select.get(0);
                contentBurst1.setStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
                contentBurst1.setUpdateTime(date);
                contentBurstMapper.updateByPrimaryKeySelective(contentBurst1);
                //修改爆料绑定数量
                Burst burst = burstMapper.selectByPrimaryKey(contentBurst1.getBurstId());
                uid = burst.getUserId();
                burst.setBindNum(burst.getBindNum() + 1);
                burst.setStatus(NewsEnumsConsts.BurstOfStatus.Finished.key());
                burstMapper.updateByPrimaryKeySelective(burst);
            }
            //推送push
            UserDevice userDevice = new UserDevice();
            userDevice.setUid(uid);
            userDevice.setStatus(1);
            List<UserDevice> select1 = userDeviceMapper.select(userDevice);
        }
        return contentInfo;
    }

    private void addMongoLog(ContentInfo contentInfo, Long time, Integer oldCheck) {
        ContentArticleLog contentArticleLog = new ContentArticleLog();
        contentArticleLog.setCid(contentInfo.getId());
        contentArticleLog.setIp(contentInfo.getIp());
        contentArticleLog.setUid(contentInfo.getUpdateUid());
        contentArticleLog.setEvent(NewsEnumsConsts.ContentArticleLogOfEvent.check.value());
        contentArticleLog.setCreate_time(time.intValue());
        ArrayList<Object> detailOfLogs = new ArrayList<>();
        Document document = new Document();
        document.put("field", "check");
        document.put("old", NewsEnumsConsts.ContentOfCheckStatus.getStatus(oldCheck));
        document.put("new", NewsEnumsConsts.ContentOfCheckStatus.getStatus(contentInfo.getCheckStatus()));
        detailOfLogs.add(document);
        contentArticleLog.setDetail(detailOfLogs);
        contentDao.addContentAritcleLog(contentArticleLog);
    }

    private void updateKpiCount(ContentInfo contentInfo, Integer type, Date date) {
        if (NewsEnumsConsts.MessageOfTargetType.Article.getKey().equals(type)
                || NewsEnumsConsts.MessageOfTargetType.Atlas.getKey().equals(type)
                || NewsEnumsConsts.MessageOfTargetType.Video.getKey().equals(type)) {
            KpiCount kpiCount = new KpiCount();
            kpiCount.setTypeId(contentInfo.getId());
            kpiCount.setFirstCheckTime(null);
            List<KpiCount> select = kpiCountMapper.select(kpiCount);
            if (select != null && select.size() > 0) {
                for (KpiCount kpiCount1 : select) {
                    kpiCount1.setFirstCheckTime(date);
                    kpiCount1.setFirstCheckId(contentInfo.getUpdateUid());
                    kpiCountMapper.updateByPrimaryKeySelective(kpiCount1);
                }
            }
            if (NewsEnumsConsts.MessageOfTargetType.Article.getKey().equals(type)) {
                ContentArticle contentArticle = contentArticleMapper.selectByPrimaryKey(contentInfo.getId());
                if (contentArticle != null) {
                    kpiCount.setTypeId(contentArticle.getDid());
                    kpiCount.setFirstCheckTime(null);
                    List<KpiCount> articleKpi = kpiCountMapper.select(kpiCount);
                    if (articleKpi != null && articleKpi.size() > 0) {
                        for (KpiCount count : articleKpi) {
                            count.setFirstCheckId(contentInfo.getUpdateUid());
                            count.setFirstCheckTime(date);
                            kpiCountMapper.updateByPrimaryKeySelective(count);
                        }
                    }
                }
            } else if (NewsEnumsConsts.MessageOfTargetType.Video.getKey().equals(type)) {
                ContentVideo contentVideo = contentVideoMapper.selectByPrimaryKey(contentInfo.getId());
                if (contentVideo != null) {
                    kpiCount.setTypeId(contentVideo.getDid());
                    kpiCount.setFirstCheckTime(null);
                    List<KpiCount> articleKpi = kpiCountMapper.select(kpiCount);
                    if (articleKpi != null && articleKpi.size() > 0) {
                        for (KpiCount count : articleKpi) {
                            count.setFirstCheckId(contentInfo.getUpdateUid());
                            count.setFirstCheckTime(date);
                            kpiCountMapper.updateByPrimaryKeySelective(count);
                        }
                    }
                }
            }
        }
    }

    /**
     * 我的内容 分页及搜索
     *
     * @param
     * @return
     */
    public TableResultResponse<MyContentInfo> getMyContentPage(MyContentInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        entity.setIsCheck(0);
        entity.setContentState(1);
        List<MyContentInfo> list = contentMapper.getMyContentPage(entity);
        return new TableResultResponse<MyContentInfo>(result.getTotal(), list);
    }

    /* * 批量删除内容区分来源
     * @param contentListInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer batchDeleteByList(ContentListInfo contentListInfo) {
        Integer deleteCount = 0;
        if (contentListInfo.getList() != null && contentListInfo.getList().size() > 0) {
            Integer uid = contentListInfo.getUid();
            String ip = contentListInfo.getIp();
            for (ContentInfo contentInfo : contentListInfo.getList()) {
                Integer id = contentInfo.getId();
                Integer fromType = contentInfo.getFromType();
                if (NewsEnumsConsts.ChannelContentOfFromType.Content.key().equals(fromType)) {
                    Content content1 = new Content();
                    content1.setId(id);
                    content1.setDeleteTime(new Date());
                    content1.setDeleteUid(uid);
                    content1.setContentState(NewsEnumsConsts.ContentOfContentState.DELETE.key());
                    int flag = contentMapper.updateByPrimaryKeySelective(content1);
                    if (flag > 0) {
                        deleteCount++;
                        //添加日志
                        contentInfo.setIp(ip);
                        contentInfo.setCreateUid(uid);
                        addAdminLog(contentInfo, System.currentTimeMillis() / 1000, "内容删除");
                        //删除频道绑定
                        channelContentMapper.updateContentDeleteByContentId(id, fromType);
                    }
                } else if (NewsEnumsConsts.ChannelContentOfFromType.Live.key().equals(fromType)) {
                    Live live = new Live();
                    live.setId(contentInfo.getId());
                    live.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
                    live.setUpdateTime(new Date());
                    live.setUpdateUid(uid);
                    int succFlag = liveMapper.updateByPrimaryKeySelective(live);
                    if (succFlag > 0) {
                        deleteCount++;
                        contentInfo.setIp(ip);
                        contentInfo.setCreateUid(uid);
                        addAdminLog(contentInfo, System.currentTimeMillis() / 1000, "删除内容");
                        //删除频道绑定
                        channelContentMapper.updateContentDeleteByContentId(id, fromType);
                    }
                }
            }
        }
        return deleteCount;
    }

    public List<Integer> selectListId(Integer start, Integer size) {
        return contentMapper.selectListId(start, size);
    }

    /**
     * h5详情页
     *
     * @param cid
     * @return
     */
    public ContentInfo getNewIndex(Integer cid) {
        return contentMapper.getNewIndex(cid);
    }

    /**
     * 查询判决书或公告
     *
     * @param cid
     * @return
     */
    public String getSiteContentDetail(Integer cid) {
        ContentLocationInfo contentLocationInfo = new ContentLocationInfo();
        contentLocationInfo.setCid(cid);
        ContentLocationInfo contentLocation = contentDao.findContentLocation(contentLocationInfo);
        if (contentLocation != null) {
            return contentLocation.getSource_title();
        }
        return null;
    }

    /**
     * app内容模块 专题详情页接口数据查询
     *
     * @param cate
     * @param cid
     * @return
     */
    public ObjectRestResponse<Map<String, Object>> getNewSpecial(Integer cid, Integer cate) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        Map result = new HashMap(16);
        Content com = new Content();
        com.setId(cid);
        Content content = contentMapper.selectByPrimaryKey(com);
        if (content != null) {
            result.put("title", content.getTitle());
            Map imageMap = new HashMap<>();
            imageMap.put("image", content.getCoverImg());
            result.put("special_img", imageMap);
            Map activityMap = new HashMap<>();
            activityMap.put("ico", "https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
            activityMap.put("title", "欲知后事如何？请看APP分解。");
            result.put("activitys", activityMap);

            Map shareUrlMap = new HashMap<>();
            shareUrlMap.put("title", content.getTitle());
            shareUrlMap.put("url", "/web/activity/special/index.html?cate=" + content.getCategory() + "&cid=" + content.getId() + "&share=1");
            shareUrlMap.put("desc", content.getIntroduction());
            result.put("share_url", shareUrlMap);

            Fav fav = new Fav();
            fav.setCid(content.getId());
//            if(uid!=null){
//                fav.setUid(uid);
//            }
            fav.setType(content.getContentType());
            Fav resultFav = favMapper.selectOne(fav);
            result.put("is_faved", resultFav == null ? 0 : 1);

            result.put("desc", content.getIntroduction());
        }
        List<Map> specialCatalog = specialCatalogMapper.getSpecialCatalog(cid);
        if (specialCatalog != null) {
            List catalogList = new ArrayList();
            for (Map data : specialCatalog) {
                String target_cid = data.get("target_cid").toString();
                Integer catelogid = Integer.valueOf(data.get("id").toString());
                AppSpecialContentInfo appSpecialContentInfo = new AppSpecialContentInfo();
                appSpecialContentInfo.setCid(cid);
                appSpecialContentInfo.setCatalogId(catelogid);
                if (target_cid != null) {
                    appSpecialContentInfo.setIds(Arrays.asList(target_cid.split(",")));
                }
//                //set测试数据
//                appSpecialContentInfo.setCid(37524);
//                appSpecialContentInfo.setIds(Arrays.asList("37667,38761,37563".split(",")));

                List<Map> maps = specialCatalogMapper.selectBindSpecialContentListByCid(appSpecialContentInfo);

                //缩略图样式
                String card_type = AppCommonType.card_type;
                JSONObject cardTypeObject = JSON.parseObject(card_type);
                String cardTypeValue = cardTypeObject.getString("value");
                JSONObject cardType = JSON.parseObject(cardTypeValue);

                //获取标签颜色字典
                String tag_color = AppCommonType.tag_color;
                JSONObject object = JSON.parseObject(tag_color);
                String value = object.getString("value");
                JSONObject valueObject = JSON.parseObject(value);
                //内容类型标签  original
                JSONObject contentJson = valueObject.getJSONObject("original");
                contentJson.fluentPut("name", "原创");
                for (Map specialContent : maps) {
                    String type = specialContent.get("type").toString();
                    String id = specialContent.get("id").toString();
                    String category = specialContent.get("category").toString();
                    String cardTypeString = cardType.getString(type);
                    specialContent.put("card_type", cardTypeString);
                    specialContent.put("tags", contentJson);
                    //分享url
                    String share_url = getShareUrlSu(Integer.valueOf(id), Integer.valueOf(type), Integer.valueOf(category));
                    specialContent.put("share_url", share_url);
                    List imagesList = new ArrayList();
                    specialContent.put("images", imagesList);
                }
                Map specialCatalogMap = new HashMap(16);
                specialCatalogMap.put("id", data.get("id").toString());
                specialCatalogMap.put("name", data.get("title").toString());
                specialCatalogMap.put("specials", maps != null ? maps : null);
                catalogList.add(specialCatalogMap);
            }
            result.put("catalog", catalogList);
        }
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * 获取详情（日志中的）
     *
     * @param id
     * @return
     */
    public ContentInfo getContentArticleLog(Integer id) {
        ContentArticleLog contentArticle = new ContentArticleLog();
        contentArticle.setCid(id);
        List<ContentArticleLog> contentArticleLogs = contentDao.getContentLog(contentArticle);
        if (contentArticleLogs != null && contentArticleLogs.size() > 0) {
            ContentArticleLog contentArticleLog = contentArticleLogs.get(0);
            ContentInfo contentInfo = new ContentInfo();
            if (contentArticleLog.getEvent().equals(NewsEnumsConsts.ContentArticleLogOfEvent.edit.value())) {
                //详情
                List<Object> detail = contentArticleLog.getDetail();
                String content = "";
                for (Object object : detail) {
                    Map entry = (Map) object;
                    if (entry.get("field").equals("title")) {
                        contentInfo.setTitle(entry.get("new").toString());
                    } else if (entry.get("field").equals("content")) {
                        contentInfo.setContentDetail(entry.get("new").toString());
                    }
                }
                return contentInfo;
            }
        }
        return null;
    }

}