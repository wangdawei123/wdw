package com.kanfa.news.user.vo.app;

import java.util.*;

/**
 * @author Jezy
 */
public class AppUser {
    private int id;
    private Date create_time;
    private String gender;
    private String image;
    private String nickname;
    private String sessionid;
    private Map<String, Object> qq = new HashMap<>(2);
    private Map<String, Object> weibo = new HashMap<>(2);
    private Map<String, Object> weixin = new HashMap<>(2);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Map<String, Object> getQq() {
        return qq;
    }

    public void setQq(Map<String, Object> qq) {
        this.qq = qq;
    }

    public Map<String, Object> getWeibo() {
        return weibo;
    }

    public void setWeibo(Map<String, Object> weibo) {
        this.weibo = weibo;
    }

    public Map<String, Object> getWeixin() {
        return weixin;
    }

    public void setWeixin(Map<String, Object> weixin) {
        this.weixin = weixin;
    }
}
