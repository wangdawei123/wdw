package com.kanfa.news.admin.vo.site;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/4/3 16:14
 */
public class SiteChannelEditResponseInfo implements Serializable {

    private String id;
    private String name;
    private String site_name;
    private Integer is_check;
    private Integer card_type;
    private Integer content_css;
    private List<Integer> app_channel;
    private Integer collect_time;
    private Integer collect_type;
    private Integer is_again_check;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIs_check() {
        return is_check;
    }

    public void setIs_check(Integer is_check) {
        this.is_check = is_check;
    }

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
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

    public Integer getCollect_type() {
        return collect_type;
    }

    public void setCollect_type(Integer collect_type) {
        this.collect_type = collect_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
