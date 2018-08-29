package com.kanfa.news.app.mongoDB.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/7 17:21
 */
@Document(collection = "count_channel_content")
public class CountChannelContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private ObjectId id;
    private Integer count_date;
    private Integer content_count;
    private Integer channel_id;
    private String channel_name;
    private Integer cate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getCount_date() {
        return count_date;
    }

    public void setCount_date(Integer count_date) {
        this.count_date = count_date;
    }

    public Integer getContent_count() {
        return content_count;
    }

    public void setContent_count(Integer content_count) {
        this.content_count = content_count;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public Integer getCate() {
        return cate;
    }

    public void setCate(Integer cate) {
        this.cate = cate;
    }
}
