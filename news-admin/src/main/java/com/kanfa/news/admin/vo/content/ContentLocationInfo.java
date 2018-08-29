package com.kanfa.news.admin.vo.content;

import com.kanfa.news.admin.vo.channel.ChannelOfLocationInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/28 15:16
 */
public class ContentLocationInfo implements Serializable{

    private String id;
    private List<ChannelOfLocationInfo> channel;
    private Integer cid;
    private Integer stat;
    private List<LocationInfo> location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChannelOfLocationInfo> getChannel() {
        return channel;
    }

    public void setChannel(List<ChannelOfLocationInfo> channel) {
        this.channel = channel;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public List<LocationInfo> getLocation() {
        return location;
    }

    public void setLocation(List<LocationInfo> location) {
        this.location = location;
    }
}
