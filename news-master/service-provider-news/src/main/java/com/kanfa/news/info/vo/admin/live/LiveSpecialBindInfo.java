package com.kanfa.news.info.vo.admin.live;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/18 13:24
 */
public class LiveSpecialBindInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    //liveSpcialId
    private Integer liveSpecialId;

    //标题
    private String title;
    //最后编辑人
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
        return StringUtils.isEmpty(updateUser) ? "" : updateUser;
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



    public Integer getLiveSpecialId() {
        return liveSpecialId;
    }

    public void setLiveSpecialId(Integer liveSpecialId) {
        this.liveSpecialId = liveSpecialId;
    }
}
