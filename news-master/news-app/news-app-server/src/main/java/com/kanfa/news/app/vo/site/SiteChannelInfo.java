package com.kanfa.news.app.vo.site;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/4/3 16:14
 */
@Document(collection = "site_channel")
public class SiteChannelInfo implements Serializable {

    private String id;
    private String _created;
    private Integer is_check;
    private Integer stat;
    private Integer card_type;
    private String name;
    private String url;
    private Integer content_css;
    private List<Integer> app_channel;
    private Integer collect_time;
    private String site_id;
    private Integer channel_id;
    private Integer collect_type;
    private Date _modified;
    private String site_name;
    private Integer is_again_check;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_created() {
        return _created;
    }

    public void set_created(String _created) {
        this._created = _created;
    }

    public Integer getIs_check() {
        return is_check;
    }

    public void setIs_check(Integer is_check) {
        this.is_check = is_check;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getContent_css() {
        return content_css;
    }

    public void setContent_css(Integer content_css) {
        this.content_css = content_css;
    }

    public List<Integer> getApp_channel() {
        return app_channel;
    }

    public void setApp_channel(List<Integer> app_channel) {
        this.app_channel = app_channel;
    }

    public Integer getCollect_time() {
        return collect_time;
    }

    public void setCollect_time(Integer collect_time) {
        this.collect_time = collect_time;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public Integer getCollect_type() {
        return collect_type;
    }

    public void setCollect_type(Integer collect_type) {
        this.collect_type = collect_type;
    }

    public Date get_modified() {
        return _modified;
    }

    public void set_modified(Date _modified) {
        this._modified = _modified;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public Integer getIs_again_check() {
        return is_again_check;
    }

    public void setIs_again_check(Integer is_again_check) {
        this.is_again_check = is_again_check;
    }
}
