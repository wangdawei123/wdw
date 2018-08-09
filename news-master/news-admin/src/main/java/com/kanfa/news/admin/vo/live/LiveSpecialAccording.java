package com.kanfa.news.admin.vo.live;


/**
 * Created by Chen
 * on 2018/3/30 14:52
 */
public class LiveSpecialAccording  implements java.io.Serializable{
    private Integer id;
    //专题标题
    private String title;

    private Integer liveTypeId;

    private Integer	specialType;

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

    public Integer getSpecialType() {
        return specialType;
    }

    public void setSpecialType(Integer specialType) {
        this.specialType = specialType;
    }
}
