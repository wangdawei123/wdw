package com.kanfa.news.info.vo.admin.app;


/**
 * Created by user on 2018/3/19.
 */
public class AppBurstInfo {
    private  String title ;
    private String burst_type;
    private Integer stat;
    private Integer cid;
    private String time;

    private String url;
    private Integer burst_id;
    private Integer content_style;
    private Integer type;
    private String notice;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBurst_type() {
        return burst_type;
    }

    public void setBurst_type(String burst_type) {
        this.burst_type = burst_type;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getBurst_id() {
        return burst_id;
    }

    public void setBurst_id(Integer burst_id) {
        this.burst_id = burst_id;
    }

    public Integer getContent_style() {
        return content_style;
    }

    public void setContent_style(Integer content_style) {
        this.content_style = content_style;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
