package com.kanfa.news.app.vo.admin.kpicount;

import java.io.Serializable;

/**
 * 运维导出数据
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class OperationExportDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private Integer typeId;
    private String title;
    private String articleType;
    private String ctype;
    private Integer cid;
    private Integer views;
    private String sourceType;
    private String wtype;
    private String name;
    private String createTime;
    private Integer count;
    private Integer mePv;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMePv() {
        return mePv;
    }

    public void setMePv(Integer mePv) {
        this.mePv = mePv;
    }
}


