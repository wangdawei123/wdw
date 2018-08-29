package com.kanfa.news.info.vo.site;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/4/2 19:23
 */
public class SiteResponseInfo implements Serializable{

    private String id;
    private String name;

    private List<SiteChannelResponseInfo> channelList;

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

    public List<SiteChannelResponseInfo> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<SiteChannelResponseInfo> channelList) {
        this.channelList = channelList;
    }
}
