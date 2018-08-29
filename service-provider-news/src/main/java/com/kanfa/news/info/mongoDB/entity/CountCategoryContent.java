package com.kanfa.news.info.mongodb.entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/4 17:37
 */
@Document(collection = "count_category_content")
public class CountCategoryContent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private ObjectId _id;
    private  Integer type;
    private  Integer content_count;
    private  Integer count_date;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getContent_count() {
        return content_count;
    }

    public void setContent_count(Integer content_count) {
        this.content_count = content_count;
    }

    public Integer getCount_date() {
        return count_date;
    }

    public void setCount_date(Integer count_date) {
        this.count_date = count_date;
    }
}
