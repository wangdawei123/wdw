package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/7/20 18:13
 */
public class ContentKpiInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cid;
    private String title;
    private String firstCheckTime;
    private Integer category;
    private Integer sourceType;
    private Integer createUid;
    private String createName;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstCheckTime() {
        return firstCheckTime;
    }

    public void setFirstCheckTime(String firstCheckTime) {
        this.firstCheckTime = firstCheckTime;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
