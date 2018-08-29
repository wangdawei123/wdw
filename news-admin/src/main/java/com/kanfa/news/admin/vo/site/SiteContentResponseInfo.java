package com.kanfa.news.admin.vo.site;

import java.io.Serializable;
import java.util.Date;

/**
 * 站点抓取信息
 * @author Jiqc
 * @date 2018/4/3 15:21
 */
public class SiteContentResponseInfo implements Serializable {

     private String id;

     private Integer stat;

     private Date _created;

     private String url;

     private String title;

     private Date _modified;

     private Date pub_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Date get_created() {
        return _created;
    }

    public void set_created(Date _created) {
        this._created = _created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date get_modified() {
        return _modified;
    }

    public void set_modified(Date _modified) {
        this._modified = _modified;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }
}
