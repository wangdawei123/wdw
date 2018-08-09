package com.kanfa.news.app.vo.admin.live;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/3/18 15:21
 */
public class LiveCourtInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    //法院Logo
    private  String avatar;
    //法院名称
    private String name;
    //法院级别  1:最高法院 2:高级法院 3:中级法院 4:基层法院
    private Integer courtLevel;
    //法院级别中文
    private String courtLevelCh;
    //直播次数
    private Integer liveCount;
    //操作人
    private String updateUser;
    //操作时间
    private String updateTime;

    //sheng province_id
    private Integer provinceId;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getCourtLevelCh() {
        return courtLevelCh;
    }

    public String getUpdateUser() {
        return StringUtils.isEmpty(updateUser) ? null : updateUser;
    }

    public void setCourtLevelCh(String courtLevelCh) {
        this.courtLevelCh = courtLevelCh;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(Integer liveCount) {
        this.liveCount = liveCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourtLevel() {
        return courtLevel;
    }

    public void setCourtLevel(Integer courtLevel) {
        this.courtLevel = courtLevel;
    }
}
