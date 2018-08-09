package com.kanfa.news.admin.vo.site;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/4/2 19:23
 */
public class SiteInfo implements Serializable{

    private String id;
    private String name;
    private String table_name;
    private Integer stat;
    private Date _created;

    public Date get_created() {
        return _created;
    }

    public void set_created(Date _created) {
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
