package com.kanfa.news.web.vo.news;

import java.io.Serializable;

public class ViewContentInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer cid;
    private Integer vid;
    private Integer live_id;
    private Integer uid;
    private Integer type;
    private Integer request_source;
    private String user_agent;
    private Integer is_proxy;
    private String referer;
    private String ip;
    private String locf;
    private Integer create_date;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getLive_id() {
        return live_id;
    }

    public void setLive_id(Integer live_id) {
        this.live_id = live_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRequest_source() {
        return request_source;
    }

    public void setRequest_source(Integer request_source) {
        this.request_source = request_source;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public Integer getIs_proxy() {
        return is_proxy;
    }

    public void setIs_proxy(Integer is_proxy) {
        this.is_proxy = is_proxy;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocf() {
        return locf;
    }

    public void setLocf(String locf) {
        this.locf = locf;
    }

    public Integer getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Integer create_date) {
        this.create_date = create_date;
    }
}
