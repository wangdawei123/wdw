package com.kanfa.news.info.vo.admin.info;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/20 16:12
 */
public class SpecialCatalogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //专题id
    private Integer cid;

    //目录标题
    private String title;

    //标题排序
    private Integer orderNumber;

    //标题排序
    private List<ContentInfo> contentInfoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<ContentInfo> getContentInfoList() {
        return contentInfoList;
    }

    public void setContentInfoList(List<ContentInfo> contentInfoList) {
        this.contentInfoList = contentInfoList;
    }
}
