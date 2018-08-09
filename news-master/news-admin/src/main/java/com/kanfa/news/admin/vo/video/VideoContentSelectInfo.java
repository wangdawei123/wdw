package com.kanfa.news.admin.vo.video;

import com.kanfa.news.common.constant.app.LiveCommonEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/20 18:52
 */
public class VideoContentSelectInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //标题
    private String title;
    //视频类型
    private Integer contentType;
    //视频类型的中文
    private String contentTypeCh;
    //添加人
    private String createUser;
    //最后编辑
    private String updateUser;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //访问量
    private  Integer viewCount;
    //是否发布
    private Integer isPublish;
    //置顶
    private Integer isTop;
    //点击url
    private String clickUrl;

    //视频点击的url
    public String getClickUrl() {
        return LiveCommonEnum.URL_PREFIX+"public/video/videoShare.html?id=" + clickUrl + "&type=" + 4;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
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

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getContentTypeCh() {
        return contentTypeCh;
    }

    public void setContentTypeCh(String contentTypeCh) {
        this.contentTypeCh = contentTypeCh;
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

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}
