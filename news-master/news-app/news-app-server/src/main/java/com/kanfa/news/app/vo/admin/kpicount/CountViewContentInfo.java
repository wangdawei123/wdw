package com.kanfa.news.app.vo.admin.kpicount;

import java.io.Serializable;

/**
 * 网站访问量数据统计
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class CountViewContentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String countDate;
    private String title ;
    private String type;
    private String createName;
    private String author;
    private String src;
    private String tag;
    private String channel;
    private Integer pv;
    private Integer uv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }
}


