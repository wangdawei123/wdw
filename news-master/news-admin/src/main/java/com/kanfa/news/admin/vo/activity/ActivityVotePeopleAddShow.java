package com.kanfa.news.admin.vo.activity;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen
 * on 2018/4/12 17:38
 */
public class ActivityVotePeopleAddShow implements Serializable {
    private Integer id;
    //候选人姓名 name
    private String name;
    //简介 summary
    private String summary;
    //详情 detail
    private String detail;
    //预设票数 preset_vote
    private Integer presetVote;
    //预设评论数 preset_comment
    private Integer presetComment;
    //排序  order_number
    private Integer orderNumber;
    //候选人图片集合
    private List<String> image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPresetVote() {
        return presetVote;
    }

    public void setPresetVote(Integer presetVote) {
        this.presetVote = presetVote;
    }

    public Integer getPresetComment() {
        return presetComment;
    }

    public void setPresetComment(Integer presetComment) {
        this.presetComment = presetComment;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
}
