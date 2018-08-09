package com.kanfa.news.app.vo.admin.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/2/28 17:30
 * 视频专辑列表
 */
public class VideoAlbumInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    //专辑类型标题
    private String title;
    //专辑类型 1为普通 2为推荐 默认为1
    private Integer albumType;
    //专辑排序
    private Integer albumOrder;
    //添加人
    private String createUser;
    //创建时间
    private Date createTime;
    //是否上线(1:上线；0:下线)
    private Integer isPublish;

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public Integer getAlbumType() {return albumType;
    }

    public void setAlbumType(Integer albumType) {this.albumType = albumType;
    }

    public Integer getAlbumOrder() {
        return albumOrder;
    }

    public void setAlbumOrder(Integer albumOrder) {
        this.albumOrder = albumOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }
}
