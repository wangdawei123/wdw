package com.kanfa.news.admin.vo.pc;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/29 11:33
 */
public class ChannelShow implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;

    //名称
    private String name;
    //评论小于多少不显示 comment_threshold
    private String commentShowRule;
    //阅读量初始值 view_init_num
    private Integer viewInitNum;
    //阅读量小于多少不显示 view_threshold
    private Integer viewThreshold;
    //阅读量小于多少不显示热门标签 hot_threshold
    private Integer hotThreshold;
    //置顶数量 top_num
    private Integer topNum;


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

    public String getCommentShowRule() {
        return commentShowRule;
    }

    public void setCommentShowRule(String commentShowRule) {
        this.commentShowRule = commentShowRule;
    }

    public Integer getViewInitNum() {
        return viewInitNum;
    }

    public void setViewInitNum(Integer viewInitNum) {
        this.viewInitNum = viewInitNum;
    }

    public Integer getViewThreshold() {
        return viewThreshold;
    }

    public void setViewThreshold(Integer viewThreshold) {
        this.viewThreshold = viewThreshold;
    }

    public Integer getHotThreshold() {
        return hotThreshold;
    }

    public void setHotThreshold(Integer hotThreshold) {
        this.hotThreshold = hotThreshold;
    }

    public Integer getTopNum() {
        return topNum;
    }

    public void setTopNum(Integer topNum) {
        this.topNum = topNum;
    }
}
