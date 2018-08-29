package com.kanfa.news.admin.vo.adv;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/2/27 19:07
 */
public class ChannelOfAdvInfo implements Serializable {

    private String id;
    //频道名
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
