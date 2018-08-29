package com.kanfa.news.admin.vo.kpicount;

import java.io.Serializable;
import java.util.Date;

/**
 * 机动即时部、全国采访部记者工作统计详情列表
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer typeId;
    private String title;
    private String isSpecial;
    private String pubUser;
    private String checkUser;
    private Date createTime;
    private Date firstCheckTime;
    private String articleTypeName;
    private String articleShapeName;
    private String workTypeName;
    private Float weight;
    private Integer authorNum;
    private Integer allPv =0;
    private Integer appPv =0;
    private Integer uidAppPv =0;
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public String getPubUser() {
        return pubUser;
    }

    public void setPubUser(String pubUser) {
        this.pubUser = pubUser;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFirstCheckTime() {
        return firstCheckTime;
    }

    public void setFirstCheckTime(Date firstCheckTime) {
        this.firstCheckTime = firstCheckTime;
    }

    public String getArticleTypeName() {
        return articleTypeName;
    }

    public void setArticleTypeName(String articleTypeName) {
        this.articleTypeName = articleTypeName;
    }

    public String getArticleShapeName() {
        return articleShapeName;
    }

    public void setArticleShapeName(String articleShapeName) {
        this.articleShapeName = articleShapeName;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getAuthorNum() {
        return authorNum;
    }

    public void setAuthorNum(Integer authorNum) {
        this.authorNum = authorNum;
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

    public Integer getUidAppPv() {
        return uidAppPv;
    }

    public void setUidAppPv(Integer uidAppPv) {
        this.uidAppPv = uidAppPv;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}


