package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;
import java.util.Date;

/**
 * 直播工作统计详情列表
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountLiveDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer liveId;
    private String title;
    private String isSpecial;
    private String name;
    private Date firstCheckTime;
    private String liveTypeName;
    private String workName;
    private Integer allPv =0;
    private Integer appPv =0;
    private Integer uv =0;
    private Integer pvLimit;
    private String isFinish;
    private String remarks;
    private String fromRemark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
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

    public Date getFirstCheckTime() {
        return firstCheckTime;
    }

    public void setFirstCheckTime(Date firstCheckTime) {
        this.firstCheckTime = firstCheckTime;
    }

    public String getLiveTypeName() {
        return liveTypeName;
    }

    public void setLiveTypeName(String liveTypeName) {
        this.liveTypeName = liveTypeName;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
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

    public Integer getPvLimit() {
        return pvLimit;
    }

    public void setPvLimit(Integer pvLimit) {
        this.pvLimit = pvLimit;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFromRemark() {
        return fromRemark;
    }

    public void setFromRemark(String fromRemark) {
        this.fromRemark = fromRemark;
    }
}


