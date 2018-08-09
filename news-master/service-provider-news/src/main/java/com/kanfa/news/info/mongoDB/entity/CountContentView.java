package com.kanfa.news.info.mongodb.entity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/4 17:37
 */
@Document(collection = "count_content_view")
public class CountContentView implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private ObjectId id;
    private String title;
    private Integer pub_date;
    private Integer count_date;
    private Integer source;
    private Integer cid;
    private Integer type;
    private String create_name;
    private List<Channel> channel;

    private Integer pv;
    private Integer uv;
    private Integer proxy_count;
    private Integer app_count;
    private Integer pc_count;
    private Integer m_count;
    private String src;
    private List<Author> author;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPub_date() {
        return pub_date;
    }

    public void setPub_date(Integer pub_date) {
        this.pub_date = pub_date;
    }

    public Integer getCount_date() {
        return count_date;
    }

    public void setCount_date(Integer count_date) {
        this.count_date = count_date;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public List<Channel> getChannel() {
        return channel;
    }

    public void setChannel(List<Channel> channel) {
        this.channel = channel;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getProxy_count() {
        return proxy_count;
    }

    public void setProxy_count(Integer proxy_count) {
        this.proxy_count = proxy_count;
    }

    public Integer getApp_count() {
        return app_count;
    }

    public void setApp_count(Integer app_count) {
        this.app_count = app_count;
    }

    public Integer getPc_count() {
        return pc_count;
    }

    public void setPc_count(Integer pc_count) {
        this.pc_count = pc_count;
    }

    public Integer getM_count() {
        return m_count;
    }

    public void setM_count(Integer m_count) {
        this.m_count = m_count;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
}
