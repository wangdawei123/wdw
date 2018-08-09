package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/7/30 15:34
 */
public class ContentUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer category;
    private Integer createUid;
    private Integer sourceType;
    private String firstCheckTime;
    private Integer isDuty;
    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getFirstCheckTime() {
        return firstCheckTime;
    }

    public void setFirstCheckTime(String firstCheckTime) {
        this.firstCheckTime = firstCheckTime;
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
