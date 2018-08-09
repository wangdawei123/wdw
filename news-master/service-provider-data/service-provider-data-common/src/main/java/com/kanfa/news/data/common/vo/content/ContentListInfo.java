package com.kanfa.news.data.common.vo.content;

import com.kanfa.news.data.common.vo.channel.ContentInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/2 17:22
 */
public class ContentListInfo implements Serializable {

    /**
     * 存入list集合
     */
    private List<ContentInfo> list;

    private String ip;
    private Integer uid;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<ContentInfo> getList() {
        return list;
    }

    public void setList(List<ContentInfo> list) {
        this.list = list;
    }
}
