package com.kanfa.news.admin.vo.live;

import com.kanfa.news.common.constant.app.LiveCommonEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/27 17:28
 */
public class LivePageInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    //标题
    private String title;
    //直播类型id live_type_id
    private String videoType;
    //状态 直播状态 0: 预告 1:直播中 2:回顾
    private Integer liveStatus;
    //添加人
    private String createUser;
    //添加时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //直播室参与用户 chatroom_allcount
    private Integer chatroomAllcount;
    //访问量 view_count
    private Integer viewCount;
    //直播上线下线状态 1:上线 0:下线
    private Integer isPublish;

    //点击url
    private String clickUrl;

    public String getClickUrl() {
        return LiveCommonEnum.URL_PREFIX+"web/live/index?id="+clickUrl+"&share=1";
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    private Integer liveTypeId;
    private Integer isDelete;
    private Integer isLock;
    private Integer checkState;

    public Integer getLiveTypeId() {
        return liveTypeId;
    }

    public void setLiveTypeId(Integer liveTypeId) {
        this.liveTypeId = liveTypeId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    private Integer page;
    private Integer limit;



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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public Integer getChatroomAllcount() {
        return chatroomAllcount;
    }

    public void setChatroomAllcount(Integer chatroomAllcount) {
        this.chatroomAllcount = chatroomAllcount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }
}
