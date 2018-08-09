package com.kanfa.news.app.vo.admin.info;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/3/20 16:15
 */
public class ContentSpecialInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //内容ID
    private Integer contentId;

    //关联的内容ID
    private Integer targetCid;

    //发布状态。1：发布，0：未发布
    private Integer isPublish;

    //状态，1：正常，0：删除
    private Integer isDelete;

    //排序
    private Integer orderNumber;

    //专题目录id
    private Integer catalogId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getTargetCid() {
        return targetCid;
    }

    public void setTargetCid(Integer targetCid) {
        this.targetCid = targetCid;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }
}
