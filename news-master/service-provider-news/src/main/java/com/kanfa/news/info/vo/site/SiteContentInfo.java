package com.kanfa.news.info.vo.site;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 站点抓取信息
 * @author Jiqc
 * @date 2018/4/3 15:21
 */
@Document(collection = "site_content")
public class SiteContentInfo  implements Serializable {

    private String id;
    private Date _created;
    private Integer stat;
    private Integer deleted;
    private String source;
    private String channel_id;
    private String channel_name;
    private String url;
    private String title;
    private Integer cid;
    private Date _modified;
    private String s_doc_id;
    private String site_id;
    private String hurl;
    private Date pub_date;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date get_created() {
        return _created;
    }

    public void set_created(Date _created) {
        this._created = _created;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date get_modified() {
        return _modified;
    }

    public void set_modified(Date _modified) {
        this._modified = _modified;
    }

    public String getS_doc_id() {
        return s_doc_id;
    }

    public void setS_doc_id(String s_doc_id) {
        this.s_doc_id = s_doc_id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getHurl() {
        return hurl;
    }

    public void setHurl(String hurl) {
        this.hurl = hurl;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
