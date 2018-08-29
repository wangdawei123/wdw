package com.kanfa.news.admin.vo.video;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/3/9 9:52
 */
public class VideoChannelAddInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    //频道名称
    private String name;
    //评论量显示阈值
    private Integer commentThreshold;
    //阅读量初始值
    private Integer viewInitNum;
    //阅读量显示阈值
    private Integer viewThreshold;
    //热门标签显示阈值
    private Integer hotThreshold;
    //置顶数量
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

    public Integer getCommentThreshold() {
        return commentThreshold;
    }

    public void setCommentThreshold(Integer commentThreshold) {
        this.commentThreshold = commentThreshold;
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
