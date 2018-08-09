package com.kanfa.news.info.vo.site;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/4/2 19:23
 */
@Document(collection = "site")
public class SiteInfo implements Serializable{

    private String id;
    private String name;
    private String table_name;
    private Integer stat;
    private String _created;

    public String get_created() {
        return _created;
    }

    public void set_created(String _created) {
        this._created = _created;
    }

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

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }
}
