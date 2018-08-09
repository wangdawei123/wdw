package com.kanfa.news.admin.vo.kpicount;

import java.io.Serializable;

/**
 * 视频详情关联内容
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountVideoDetailConnectContentInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer cid;
    private String title;
    private String type;
    private Integer allPv =0;
    private Integer appPv =0;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public Integer getAllPv() {
        return allPv;
    }

    public void setAllPv(Integer allPv) {
        this.allPv = allPv;
    }

    public Integer getAppPv() {
        return appPv;
    }

    public void setAppPv(Integer appPv) {
        this.appPv = appPv;
    }
}


