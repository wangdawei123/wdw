package com.kanfa.news.data.common.vo.channel;

import com.kanfa.news.data.common.vo.content.ImageInfo;
import com.kanfa.news.data.common.vo.content.LocationInfo;
import com.kanfa.news.data.common.vo.content.SubjectCatalogInfo;
import com.kanfa.news.data.common.vo.kpi.KpiCountContentInfo;
import com.kanfa.news.data.common.vo.video.VideoDemand;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/5 15:58
 */
public class ContentInfo implements Serializable{

    private Integer id;

    /**
     * 栏目，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
     */
    private Integer category;

    private Integer channelId;
    /**
     * 存入list集合
     */
    private List<Integer> channelIdList;
    /**
     * app频道集合
     */
    private List<Integer> appChannelIdList;
    /**
     * pc频道集合
     */
    private List<Integer> pcChannelIdList;

    /**
     * 法证先锋子频道集合
     */
    private List<Integer> childChannelIdList;

    /**
     * 存入tagId集合
     */
    private List<Integer> tagIdList;

    /**
     * 排除的Id
     */
    private List<Integer> notInIds;

    /**
     * 存入记者Id集合
     */
    private List<Integer> reporterIdList;

    /**
     * 存入记者类型id集合
     */
    private List<Integer> reporterTypeList;

    /**
     * 记者
     */
    private List<KpiContentInfo> userList;

    /**
     * 记者统计
     */
    private List<KpiCountContentInfo> kpiCountContentInfoList;

    /**
     * 评论内容集合
     */
    private List<String> commentContentList;


    /**
     * 0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
     */
    private Integer cardType;

    //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
    private Integer contentType;

    private String contentTypeName;

    //是否发布
    private Integer isPublish;

    //是否置顶
    private Integer isTop;

    //标题
    private String title;

    //关键字
    private String keyword;

    //关键字
    private String articleUrl;

    //跳转链接
    private String customUrl;

    //搜索关键字类型 1-标题，2-文章id，3-创建人
    private Integer searchType;

    //长标题
    private String longTitle;

    //导语
    private String introduction;

    //图片路径
    private String image;

    //专题的封面图
    private String coverImg;

    /**
     * 标签名称集合
     */
    private List<String> tagNameList;

    private Integer sourceType;

    //来源
    private String source;

    //查看数量
    private Integer viewCount;

    //推荐权重
    private Integer recommendWeight;

    //0 在审核列表不显示　１显示
    private Integer isCheck;

    //审核状态（0待审核，1审核通过，2审核不通过）
    private Integer checkStatus;

    //uid记者编辑人
    private List<Integer> uId;

    private Integer isLegal;

    //视频时长
    private Integer duration;

    private String video;

    //视频对象
    private VideoDemand videoDemand;

    private List<LocationInfo> locationList;

    //视频底图
    private String videoImage;

    private Integer videoType;

    //起始日期
    private Date beginTime;

    //截止日期
    private Date endTime;

    //创建人Id
    private Integer createUid;

    //创建时间
    private Date createTime;

    //最后修改人Id
    private Integer updateUid;

    //更新时间
    private Date updateTime;

    //状态，1：正常，0：删除
    private Integer contentState;

    //添加人
    private String createUser;

    //最后编辑人
    private String updateUser;

    private Integer page;

    private Integer limit;

    //文章详情
    private String contentDetail;
    //视频库资源id
    private Integer did;

    /**
     * 子目录集合
     */
    private List<SubjectCatalogInfo> childCatalogList;

    /**
     * 图集
     */
    //图集集合
    private List<ImageInfo> imageInfoList;

    //是否使用导语来做图片介绍，1：是，0：否
    private Integer descType;

    /**
     * 爆料绑定使用Id
     */
    private Integer burstId;

    /**
     * 客户端的ip
     */
    private String ip;

    //内容来源.1：content表，2：live表
    private Integer fromType;

    public VideoDemand getVideoDemand() {
        return videoDemand;
    }

    public void setVideoDemand(VideoDemand videoDemand) {
        this.videoDemand = videoDemand;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getBurstId() {
        return burstId;
    }

    public void setBurstId(Integer burstId) {
        this.burstId = burstId;
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }

    public Integer getDescType() {
        return descType;
    }

    public void setDescType(Integer descType) {
        this.descType = descType;
    }

    public List<SubjectCatalogInfo> getChildCatalogList() {
        return childCatalogList;
    }

    public void setChildCatalogList(List<SubjectCatalogInfo> childCatalogList) {
        this.childCatalogList = childCatalogList;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public List<LocationInfo> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationInfo> locationList) {
        this.locationList = locationList;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public List<Integer> getChildChannelIdList() {
        return childChannelIdList;
    }

    public void setChildChannelIdList(List<Integer> childChannelIdList) {
        this.childChannelIdList = childChannelIdList;
    }

    public List<String> getCommentContentList() {
        return commentContentList;
    }

    public void setCommentContentList(List<String> commentContentList) {
        this.commentContentList = commentContentList;
    }

    public List<Integer> getNotInIds() {
        return notInIds;
    }

    public void setNotInIds(List<Integer> notInIds) {
        this.notInIds = notInIds;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public List<Integer> getAppChannelIdList() {
        return appChannelIdList;
    }

    public void setAppChannelIdList(List<Integer> appChannelIdList) {
        this.appChannelIdList = appChannelIdList;
    }

    public List<Integer> getPcChannelIdList() {
        return pcChannelIdList;
    }

    public void setPcChannelIdList(List<Integer> pcChannelIdList) {
        this.pcChannelIdList = pcChannelIdList;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public List<KpiCountContentInfo> getKpiCountContentInfoList() {
        return kpiCountContentInfoList;
    }

    public void setKpiCountContentInfoList(List<KpiCountContentInfo> kpiCountContentInfoList) {
        this.kpiCountContentInfoList = kpiCountContentInfoList;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<KpiContentInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<KpiContentInfo> userList) {
        this.userList = userList;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getRecommendWeight() {
        return recommendWeight;
    }

    public void setRecommendWeight(Integer recommendWeight) {
        this.recommendWeight = recommendWeight;
    }

    public List<Integer> getuId() {
        return uId;
    }

    public void setuId(List<Integer> uId) {
        this.uId = uId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Integer isLegal) {
        this.isLegal = isLegal;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }

    public List<Integer> getReporterIdList() {
        return reporterIdList;
    }

    public void setReporterIdList(List<Integer> reporterIdList) {
        this.reporterIdList = reporterIdList;
    }

    public List<Integer> getReporterTypeList() {
        return reporterTypeList;
    }

    public void setReporterTypeList(List<Integer> reporterTypeList) {
        this.reporterTypeList = reporterTypeList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return StringUtils.isEmpty(updateUser) ? "" : updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public List<Integer> getChannelIdList() {
        return channelIdList;
    }

    public void setChannelIdList(List<Integer> channelIdList) {
        this.channelIdList = channelIdList;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getContentState() {
        return contentState;
    }

    public void setContentState(Integer contentState) {
        this.contentState = contentState;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }
}
