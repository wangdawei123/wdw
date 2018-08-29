package com.kanfa.news.admin.vo.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/8/15 15:35
 */
public class VideoColumnShow implements Serializable {

    private Integer id;
    //栏目标题(必选)
    private String title;
    //栏目排序 column_order
    private Integer columnOrder;
    //导语 summary
    private String summary;
    //封面图(必选) cover_img
    private String coverImg;

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

    public Integer getColumnOrder() {
        return columnOrder;
    }

    public void setColumnOrder(Integer columnOrder) {
        this.columnOrder = columnOrder;
    }

    public String getSummary() {
        return StringUtils.isEmpty(summary) ? "" : summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
}
