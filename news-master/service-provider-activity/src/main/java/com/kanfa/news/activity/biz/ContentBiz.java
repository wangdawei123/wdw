package com.kanfa.news.activity.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.activity.entity.*;
import com.kanfa.news.activity.mapper.*;
import com.kanfa.news.activity.vo.info.ContentInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.AppCommonType;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 内容表（含专题，文章，图集，视频类型）
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-05 14:23:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentBiz extends BaseBiz<ContentMapper, Content> {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentArticleMapper contentArticleMapper;
    @Autowired
    private ContentVideoMapper contentVideoMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private ChannelContentCardMapper channelContentCardMapper;
    @Autowired
    private ContentImageMapper contentImageMapper;
    @Autowired
    private ContentImageGroupMapper contentImageGroupMapper;
    @Autowired
    private ContentBroadcastMapper contentBroadcastMapper;
    @Autowired
    private ContentVrMapper contentVrMapper;

    private final long PUBTIME = 86400 * 7;
    private final Integer HOT_THRESHOT = 500;




    /**
     *  将不同类型的资讯的内容补全
     * @param contents
     * @return
     */
    public List<Map<String ,Object>> getListTypeData(List<ContentInfo> contents){
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

        List<Map<String ,Object>> data = new ArrayList<>();
        for(ContentInfo content : contents){
            Map<String ,Object> map = new HashMap<>(5);
            //标签
            Map<String ,Object> tags = getContentTags(content ,null);
            map.put("tags",tags);
            //目前只针对文章下发热门和附近标签
            if(content.getContentType().equals(LiveCommonEnum.CONTENT_STYLE_ADV)){
                //判断是否是热门
                if(content.getViewCount() >= HOT_THRESHOT && System.currentTimeMillis()-content.getPublish_time().getTime() < PUBTIME){
                    Map<String ,Object> hotMap = new LinkedHashMap<>(5);
                    hotMap.put("font_color" ,font_color);
                    hotMap.put("border_color" ,border_color);
                    hotMap.put("name" ,"热门");
                    map.put("other_tags",hotMap);
                }
            }
            List<Integer> typeList = new ArrayList<>();
            typeList.add(2);
            typeList.add(3);
            typeList.add(4);
            if(content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL) && typeList.contains(content.getContentType())){
                Map<String ,Object> originalMap = new LinkedHashMap<>(5);
                originalMap.put("font_color" ,font_color_original);
                originalMap.put("border_color" ,border_color_original);
                originalMap.put("name" ,"原创");
                map.put("tags",originalMap);
            }
            String cardTypeString = cardType.getString(content.getType() + "");
            map.put("card_type",cardTypeString);
            map.put("image_num",0);
            map.put("duration","");
            map.put("images","");
            map.put("source","");
            map.put("chanId",content.getChannelId());
            map.put("id",content.getId());
            map.put("title",content.getTitle());
            map.put("source_type",content.getSourceType());
            switch (content.getContentType()){
                case 1:
                    //专题
                    break;
                case 2:
                    //文章
                    //文章缩略图   $chanId有值是首页频道列表，$chanId无值是首页搜索列表（文章只有无图和小图）
                    //首页搜索，优先选择小图模式，如果不是那么全部是无图模式
                    map.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_NO);
                    map.put("image","");
                    ChannelContentCard card = new ChannelContentCard();
                    card.setContentId(content.getId());
                    List<ChannelContentCard> cards = channelContentCardMapper.select(card);
                    if(cards.size() > LiveCommonEnum.CONSTANT_ZERO){
                        for(int i = LiveCommonEnum.CONSTANT_ZERO ; i < cards.size() ; i++){
                            if(cards.get(i).getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_SMALL)){
                                map.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_SMALL);
                                map.put("image",cards.get(i).getImage());
                                break;
                            }
                        }
                    }
                    //文章来源和作者
                    ContentArticle article = new ContentArticle();
                    article.setCid(content.getId());
                    article.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentArticle contentArticle = contentArticleMapper.selectOne(article);
                    if(contentArticle != null){
                        map.put("source",contentArticle.getSource());
                        map.put("author",contentArticle.getAuthor());
                    }else{
                        map.put("source","");
                        map.put("author","");
                    }
                    // 将展现样式作为type类型来展示
                    if(content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_BOOK)){
                        map.put("type",LiveCommonEnum.CONTENT_JUDGMENT);
                    }else if(content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_ADV)){
                        map.put("type",LiveCommonEnum.CONTENT_POST);
                    }
                    break;
                case 3:
                    //图集
                    //图集个数
                    ContentImage image = new ContentImage();
                    image.setCid(content.getId());
                    image.setStat(LiveCommonEnum.NO_DEL_STATUS);
                    ContentImage contentImage = contentImageMapper.selectOne(image);
                    if(contentImage != null){
                        map.put("image_num",contentImage.getNum());
                        List<String> imagelist = new ArrayList<>();
                        List<ContentImageGroup> images = contentMapper.findImages(content.getId(),null);
                        for(ContentImageGroup group :images){
                            imagelist.add(group.getImage());
                        }
                        map.put("images",imagelist);
                    }else{
                        map.put("image_num",LiveCommonEnum.CONSTANT_ZERO);
                    }
                    break;
                case 4:
                    //视频
                    //视频时长 源地址
                    ContentVideo video = new ContentVideo();
                    video.setCid(content.getId());
                    video.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentVideo contentVideo = contentVideoMapper.selectOne(video);
                    if(contentVideo != null){
                        map.put("duration",contentVideo.getDuration());
                        map.put("video_source",contentVideo.getUrl());
                    }else{
                        map.put("duration","00:00");
                        map.put("video_source","");
                    }
                    break;
                case 9:
                    //直播
                    ContentBroadcast broadcast = new ContentBroadcast();
                    broadcast.setCid(content.getId());
                    broadcast.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentBroadcast contentBroadcast = contentBroadcastMapper.selectOne(broadcast);
                    if(contentBroadcast != null){
                        map.put("broad_status",contentBroadcast.getBroadcastStatus());
                        map.put("duration",contentBroadcast.getReplayDuration());
                        if(contentBroadcast.getStartTime() != null){
                            //现在的年份
                            int i = DateUtil.calendar.get(Calendar.YEAR);
                            //数据库年份
                            Integer startTime = contentBroadcast.getStartTime();
                            Date date = new Date(startTime);
                            Calendar now = Calendar.getInstance();
                            now.setTime(date);
                            int year = now.get(Calendar.YEAR);
                            if(i == year){
                                SimpleDateFormat dfs = new SimpleDateFormat("MM月dd日 HH:mm");
                                map.put("start_time",dfs.format(date));
                            }else{
                                SimpleDateFormat dfs = new SimpleDateFormat("YY年MM月dd日 HH:mm");
                                map.put("start_time",dfs.format(date));
                            }
                        }else{
                            map.put("start_time","");
                        }
                        //人数
                        map.put("partner_num",contentBroadcast.getPartnerNum());
                        // 加载默认图片
                        if(StringUtils.isEmpty(contentBroadcast.getVideoImg())){
                            map.put("image","http://kanfaimage.oss-cn-beijing.aliyuncs.com/5.png");
                        }
                    }else{
                        map.put("duration","00:00");
                        map.put("broad_status",0);
                        map.put("start_time","");
                    }
                    break;
                case 11:
                    //VR
                    ContentVr vr = new ContentVr();
                    vr.setContentId(content.getId());
                    vr.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                    ContentVr contentVr = contentVrMapper.selectOne(vr);
                    if(contentVr != null){
                        map.put("duration",contentVr.getDuration());
                        map.put("video_source",contentVr.getUrl());
                    }else{
                        map.put("duration","00:00");
                        map.put("video_source","");
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
                    map.put("broad_status",content.getCheckStatus()+LiveCommonEnum.CONSTANT_ONE);
                    map.put("duration",content.getDuration());
                    map.put("image",content.getCoverImg());
                    map.put("live_type_id","live_type_id");
                    map.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_FIVE);
                    break;
                    default:
                        break;
            }
            //分享url
            String share_url = getShareUrlSu(content.getId() ,content.getContentType() ,content.getCategory());
            map.put("share_url",share_url);
            //详情url
            String url = getDetailUrlSu(content.getId() ,content.getContentType() ,null);
            if(content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL)){
                map.put("url",url + "?original=1");
            }
            //几分钟前
            if(content.getPublish_time() != null){
                map.put("pub_time",DateUtil.fromToday(content.getPublish_time()));
            }else{
                map.put("pub_time","");
            }
            //阅读量等于初始值+真实值

            //评论量等于初始值+真实值
            Setting ops = settingMapper.selectSettingByName("comment_ops");
            String opsValue = ops.getValue();
            if(opsValue.equals(LiveCommonEnum.CONSTANT_ONE)){
                map.put("comments",content.getCommentCheckedCount());
            }else{
                map.put("comments",content.getCommentCount());
            }
            // 评论量显示转换
            String viewDisplay = CommentViewUtil.getCommentViewDisplay("comment", content.getCommentCount());
            map.put("comments",viewDisplay);
            // 阅读量量显示转换
            String display = CommentViewUtil.getCommentViewDisplay("view", content.getViewCount());
            map.put("views",display);
            data.add(map);
        }
        return data;
    }


    public Map<String ,Object> getContentTags(ContentInfo content ,Integer channlId){
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

        Map<String ,Object> tags = new HashMap<>(9);
        tags.put("font_color", font_color);
        tags.put("border_color", border_color);

        if(content.getContentType().equals(LiveCommonEnum.CONTENT_NEWS)){
            //首页列表
            if(channlId != null){
                //置顶标签

            }
        }else if(content.getContentType().equals(LiveCommonEnum.CONTENT_BROADCAST)){
            //直播状态
            ContentBroadcast broadcast = new ContentBroadcast();
            broadcast.setCid(content.getId());
            broadcast.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
            ContentBroadcast broad = contentBroadcastMapper.selectOne(broadcast);
            if(broad == null){
                broad.setBroadcastStatus(LiveCommonEnum.PRIVIEW_STATUS1);
            }
            String s = broadcastTypeMap.get(broad.getBroadcastStatus());
            tags.put("name", s);
        }else if(content.getContentType().equals(LiveCommonEnum.CONTENT_LIVE) || content.getContentType().equals(LiveCommonEnum.CONTENT_LIVE_COURT) || content.getContentType().equals(LiveCommonEnum.CONTENT_LIVE_COURT_H5) ){
            tags.put("name", "直播");
        }else{
            if(!content.getContentType().equals(LiveCommonEnum.CONTENT_ALBUM) && !content.getContentType().equals(LiveCommonEnum.CONTENT_VIDEO)){
                String s = typeMap.get(content.getContentType());
                tags.put("name", s);
            }
        }
        return tags;
    }



    public Map<String ,Object> addContentsListData(Map<String ,Object> map ,ContentInfo content){
        switch (content.getType()){
            case 1:
                //专题
                map.put("card_type",LiveCommonEnum.CARD_TYPE_SPECAIL);
                break;
            case 2:
                //文章
                //文章缩略图   $chanId有值是首页频道列表，$chanId无值是首页搜索列表（文章只有无图和小图）
                if(content.getChannelId() != null){
                    ChannelContentCard card = new ChannelContentCard();
                    card.setContentId(content.getId());
                    card.setChannelId(content.getChannelId());
                    ChannelContentCard contentCard = channelContentCardMapper.selectOne(card);
                    if(contentCard != null){
                        if(contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_THREE) || contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_TEN)){
                            map.put("image","");
                            map.put("images",contentCard.getImage());
                        }else{
                            map.put("image",contentCard.getImage());
                            map.put("images","");
                        }
                        if(contentCard.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_TEN)){
                            contentCard.setCardType(LiveCommonEnum.CHANNEL_CARDTYPE_THREE);
                        }
                        map.put("card_type",contentCard.getCardType());
                    }else{
                        //万一没有传频道ID则展示无图或者小图
                        map.put("card_type",0);
                        if(StringUtils.isNotEmpty(content.getImage())){
                            map.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_SMALL);
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
                if(contentImage != null){
                    map.put("image_num",contentImage.getNum());
                    List<ContentImageGroup> images = contentMapper.findImages(content.getId() ,LiveCommonEnum.CONSTANT_THREE);
                    if(images.size() < LiveCommonEnum.CONSTANT_THREE){
                        //设置成单大图模式
                        map.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_BIG);
                        map.put("images","");
                    }else{
                        map.put("images",images);
                    }
                }else{
                    map.put("image_num",0);
                }
                break;
            case 4:
                //视频
                //视频时长 源地址
                ContentVideo video = new ContentVideo();
                video.setCid(content.getId());
                video.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                ContentVideo contentVideo = contentVideoMapper.selectOne(video);
                if(contentVideo != null){
                    map.put("duration",contentVideo.getDuration());
                    map.put("video_source",contentVideo.getUrl());
                }else{
                    map.put("duration","00:00");
                    map.put("video_source","");
                }
                break;
            case 9:
                //直播
                ContentBroadcast broadcast = new ContentBroadcast();
                broadcast.setCid(content.getId());
                broadcast.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                ContentBroadcast contentBroadcast = contentBroadcastMapper.selectOne(broadcast);
                if(contentBroadcast != null){
                    map.put("live_stat",contentBroadcast.getBroadcastStatus());
                    map.put("duration",contentBroadcast.getReplayDuration());
                    if(contentBroadcast.getStartTime() != null){
                        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try{
                            Date startTime = dfs.parse(contentBroadcast.getStartTime() + "");
                            map.put("preview_time",DateUtil.between(new Date() ,startTime));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        map.put("preview_time","");
                    }
                    //人数
                    map.put("video_num",CovertLiveNum.change(contentBroadcast.getPartnerNum()));
                    // 加载默认图片
                    if(StringUtils.isEmpty(contentBroadcast.getVideoImg())){
                        map.put("image","http://kanfaimage.oss-cn-beijing.aliyuncs.com/5.png");
                    }
                    if(contentBroadcast.getBroadcastStatus().equals(LiveCommonEnum.PRIVIEW_STATUS1)){
                        map.put("video_source",contentBroadcast.getPreviewUrl());
                    }else  if(contentBroadcast.getBroadcastStatus().equals(LiveCommonEnum.LIVE_STATUS1)) {
                        map.put("video_source", contentBroadcast.getLiveUrl());
                    }else if(contentBroadcast.getBroadcastStatus().equals(LiveCommonEnum.REVIEW_STATUS1)){
                        map.put("video_source",contentBroadcast.getReviewUrl());
                    }
                }else{
                    map.put("duration","00:00");
                    map.put("live_stat",0);
                    map.put("preview_time","");
                }
                break;
            case 11:
                //VR
                ContentVr vr = new ContentVr();
                vr.setContentId(content.getId());
                vr.setIsDelete(LiveCommonEnum.NO_DEL_STATUS);
                ContentVr contentVr = contentVrMapper.selectOne(vr);
                if(contentVr != null){
                    map.put("duration",contentVr.getDuration());
                    map.put("video_source",contentVr.getUrl());
                }else{
                    map.put("duration","00:00");
                    map.put("video_source","");
                }
                map.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_FIVE);
                break;
            case 25:
                //政法
                ChannelContentCard card = new ChannelContentCard();
                card.setContentId(content.getId());
                card.setChannelId(content.getChannelId());
                ChannelContentCard contentCard = channelContentCardMapper.selectOne(card);
                if(contentCard != null){
                    map.put("card_type",contentCard.getCardType());
                }
                break;
                default:
                    break;
        }
        //几分钟前
        if(content.getPublish_time() != null){
            map.put("pub_time",DateUtil.between( content.getPublish_time() ,new Date()));
        }else{
            map.put("pub_time","");
        }
        if(StringUtils.isNotEmpty(content.getCustomUrl())){
            map.put("url",content.getCustomUrl());
        }else{
            map.put("url",getDetailUrlSu(content.getId() ,content.getType() ,content.getCategory()));
        }
        if(content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL) && content.getType().equals(LiveCommonEnum.SSPECIAL_TYPE_LIVE)){
            map.put("url",content.getUrl()+"?original=1");
        }
        // 将展现样式作为type类型来展示
        if(content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_BOOK)){
            map.put("type",LiveCommonEnum.CONTENT_JUDGMENT);
        }else if(content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_ADV)) {
            map.put("type", LiveCommonEnum.CONTENT_POST);
        }
        return map;
    }


    /**
     * 获取内容的分享落地页
     * @param id
     * @param type
     * @param cate
     * @return
     */
    public String getShareUrlSu(Integer id , Integer type , Integer cate){
        if(null == cate ){
            Content c = new Content();
            c.setId(id);
            Content content = contentMapper.selectOne(c);
            if(content != null){
                cate = content.getCategory();
            }else{
                cate = 1;
            }
        }
        String url = null;
        switch (type){
            case 1:
                //专题
                url = LiveCommonEnum.URL_PREFIX + "web/activity/special/index.html?category="+cate+"&cid="+id +"&share=1";
                break;
            case 2:
                //资讯   新分享页不需要share参数
                url = LiveCommonEnum.URL_PREFIX + "public/news/index.html?id="+id;
                break;
            case 3:
                //图集
                url = LiveCommonEnum.URL_PREFIX + "web/share/image/index.html?id="+id;
                break;
            case 4:
                //视频
                url = LiveCommonEnum.URL_PREFIX + "web/share/video/index.html?id="+id;
                break;
            case 9:
                //直播
                url = LiveCommonEnum.URL_PREFIX + "web/broadcast/index?cid=" +id +"&share=1";
                break;
            case 11:
                //VR
                url = LiveCommonEnum.URL_PREFIX + "http://a.app.qq.com/o/simple.jsp?pkgname=com.fawan.news";
                break;
            case 12:
                //问答
                url = LiveCommonEnum.URL_PREFIX + "web/problem/index/"+id+"/"+cate +"?share=1";
                break;
            case 22:
                //新直播
                url = LiveCommonEnum.URL_PREFIX + "web/activity/live/index.html?id=" + id +"&share=1";
                break;
            case 23:
            case 24:
                url = LiveCommonEnum.URL_PREFIX + "web/live/index?id=" + id +"&share=1";
                break;
            default:
                break;
        }
        return url;
    }

    /**
     * //内容详情url
     * @param id
     * @param type
     * @param cate
     * @return
     */
    public String getDetailUrlSu(Integer id , Integer type , Integer cate){
        if(null == cate ){
            Content c = new Content();
            c.setId(id);
            Content content = contentMapper.selectOne(c);
            if(content != null){
                cate = content.getCategory();
            }else{
                cate = 1;
            }
        }
        String url = null;
        switch (type){
            case 1:
                //专题
                url = LiveCommonEnum.URL_PREFIX + "web/activity/special/index.html?category="+cate+"&cid="+id;
                break;
            case 2:
                //资讯
                url = LiveCommonEnum.URL_PREFIX + "web/news/index/"+id+"/"+cate;
                break;
            case 3:
                //图集
                url = LiveCommonEnum.URL_PREFIX + "web/image/index/"+id+"/"+cate;
                break;
            case 4:
                //直播
                url = LiveCommonEnum.URL_PREFIX + "web/video/index/"+id+"/"+cate;
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
                url = LiveCommonEnum.URL_PREFIX + "web/problem/index/"+id+"/"+cate;
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




}