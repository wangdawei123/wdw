package com.kanfa.news.info.vo.admin.info;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/28 15:16
 */
@Document(collection = "site_content_detail")
public class ContentLocationInfo implements Serializable{

    private String id;
    private List<ChannelOfLocationInfo> channel;
    private Integer cid;
    private Integer stat;
    private String source_title;
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

    public String getSource_title() {
        return source_title;
    }

    public void setSource_title(String source_title) {
        this.source_title = source_title;
    }
}
