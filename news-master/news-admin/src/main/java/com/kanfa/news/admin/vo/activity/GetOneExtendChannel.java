package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/16 13:56
 */
public class GetOneExtendChannel implements Serializable {
    private Integer id;
    private String name;
    //渠道号 channel_num
    private String channelNum;

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

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }
}
