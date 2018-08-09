package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 *  工作类型
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountWorkTypeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String workTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }
}


