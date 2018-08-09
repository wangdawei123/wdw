package com.kanfa.news.app.vo.admin.kpicount;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频工作统计详情列表
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountVideoDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer videoId;
    private String title;
    private String isSpecial;
    private String name;
    private Date createTime;
    private String sourceTypeName;
    private String typeName;
    private String workName;
    private Integer contentCount;
    private Integer allPv =0;
    private Integer appPv =0;
    private Integer uv =0;
    private Integer pvLimit;
    private String isFinish;
    private String remarks;
    private String url;

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

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSourceTypeName() {
        return sourceTypeName;
    }

    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Integer getContentCount() {
        return contentCount;
    }

    public void setContentCount(Integer contentCount) {
        this.contentCount = contentCount;
    }

    public Integer getAllPv() {
        return allPv;
    }

    public void setAllPv(Integer allPv) {
        this.allPv = allPv;
    }

    public Integer getAppPv() {
        return appPv;
    }

    public void setAppPv(Integer appPv) {
        this.appPv = appPv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getPvLimit() {
        return pvLimit;
    }

    public void setPvLimit(Integer pvLimit) {
        this.pvLimit = pvLimit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


