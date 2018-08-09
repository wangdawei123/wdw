package com.kanfa.news.admin.vo.live;

/**
 * Created by Chen
 * on 2018/4/2 11:26
 */
public class LiveFocusShow {
    private Integer id;
    private String title;
    private Integer liveTypeId;
    private String image;
    private Integer sort;
    private String url;
    private Integer jump;
    private Integer liveId;


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getJump() {
        return jump;
    }

    public void setJump(Integer jump) {
        this.jump = jump;
    }

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }
}
