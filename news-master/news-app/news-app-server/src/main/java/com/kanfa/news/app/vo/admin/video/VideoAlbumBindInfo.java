package com.kanfa.news.app.vo.admin.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/3/5 11:16
 *
 * 视频专辑与视频关联表
 *
 */
public class VideoAlbumBindInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //专辑类型标题
    private String title;
    //最后修改人
    private String updateUser;
    //最后更新日期
    private String updateTime;

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

    public String getUpdateTime() {
        return StringUtils.isEmpty(updateTime) ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return StringUtils.isEmpty(updateUser) ? "" : updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
