package com.kanfa.news.admin.vo.live;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/3/16 10:36
 */
public class LiveInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //
    private String title;

    private Integer page;

    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
