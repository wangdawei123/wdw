package com.kanfa.news.admin.vo.content;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/3/29 11:12
 */
public class ContentResponseInfo implements Serializable {
    private Integer id;
    //标题
    private String title;

    //直播类型id
    private Integer liveTypeId;

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

    public Integer getLiveTypeId() {
        return liveTypeId;
    }

    public void setLiveTypeId(Integer liveTypeId) {
        this.liveTypeId = liveTypeId;
    }
}
