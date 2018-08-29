package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * 每日类目统计
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class CountCateInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String countDate;
    private String type;
    private Integer contentCount;

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getContentCount() {
        return contentCount;
    }

    public void setContentCount(Integer contentCount) {
        this.contentCount = contentCount;
    }
}


