package com.kanfa.news.info.vo.admin.live;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/16 15:24
 *
 * 直播专题表
 */
public class LiveSpecialInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    //专题标题
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //直播类型的名字 专题类型
    private String liveTypeName;

    private Integer specialType;
    //添加人
    private String createUser;
    //创建时间
    private Date createTime;
    //是否上线 '专题状态 0:下线 1:上线',
    private Integer isPublish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLiveTypeName() {
        return StringUtils.isEmpty(liveTypeName) ? "" : liveTypeName;
    }

    public void setLiveTypeName(String liveTypeName) {
        this.liveTypeName = liveTypeName;
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

    public Integer getIsPublish() {
        return isPublish;
    }

    public Integer getSpecialType() {
        return specialType;
    }

    public void setSpecialType(Integer specialType) {
        this.specialType = specialType;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }
}
