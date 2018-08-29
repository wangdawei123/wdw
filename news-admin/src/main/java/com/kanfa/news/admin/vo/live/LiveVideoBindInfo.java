package com.kanfa.news.admin.vo.live;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/28 10:52
 */
public class LiveVideoBindInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    //标题
    private String title;
    //类别 同用 from_type  1：content表视频，2：live表直播
    private Integer fromType;
    private String fromTypeCh;


    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public String getFromTypeCh() {
        return fromTypeCh;
    }

    public void setFromTypeCh(String fromTypeCh) {
        this.fromTypeCh = fromTypeCh;
    }

    //最后编辑
    private String updateUser;
    //更新时间
    private Date updateTime;

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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
