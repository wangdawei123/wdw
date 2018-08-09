package com.kanfa.news.info.vo.admin.adv;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/2/27 19:07
 */
public class AdvOnlineNumInfo implements Serializable {

    private Integer online;
    private Integer notOnline;

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getNotOnline() {
        return notOnline;
    }

    public void setNotOnline(Integer notOnline) {
        this.notOnline = notOnline;
    }
}
