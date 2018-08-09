package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * 机动即时部、全国采访部记者工作统计PV统计
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiTypeIdInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer typeId;
    private String name;
    private Integer uid;
    private Integer weight;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}


