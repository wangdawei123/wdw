package com.kanfa.news.admin.vo.live;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/4 14:03
 */
public class KpiTypeConfigIdName implements Serializable {
    private Integer id;
    private String Name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
