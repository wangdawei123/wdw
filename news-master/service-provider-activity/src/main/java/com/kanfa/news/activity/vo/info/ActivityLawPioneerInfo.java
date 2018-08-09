package com.kanfa.news.activity.vo.info;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/3/27 19:34
 */
public class ActivityLawPioneerInfo implements Serializable {

    private Integer id;

    //政法活动id
    private Integer activityLawId;

    //关联的文章id
    private Integer cid;

    //标题
    private String title;

    //候选人状态   1 正常  0  删除
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityLawId() {
        return activityLawId;
    }

    public void setActivityLawId(Integer activityLawId) {
        this.activityLawId = activityLawId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
