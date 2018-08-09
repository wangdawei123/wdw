package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/7/30 15:34
 */
public class LiveUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer createUid;
    private String createTime;
    private Integer isDuty;
    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDuty() {
        return isDuty;
    }

    public void setIsDuty(Integer isDuty) {
        this.isDuty = isDuty;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
