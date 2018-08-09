package com.kanfa.news.search.client.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/2 17:22
 */
public class SubjectCatalogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //专题id
    private Integer cid;

    //目录标题
    private String title;

    //绑定文章的Id集合
    private List<Integer> contentIds;
    //绑定文章详情
    private List<ContentInfo> contentInfoList;

    public List<ContentInfo> getContentInfoList() {
        return contentInfoList;
    }

    public void setContentInfoList(List<ContentInfo> contentInfoList) {
        this.contentInfoList = contentInfoList;
    }

    public List<Integer> getContentIds() {
        return contentIds;
    }

    public void setContentIds(List<Integer> contentIds) {
        this.contentIds = contentIds;
    }

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
}
