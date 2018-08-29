package com.kanfa.news.info.vo.admin.video;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/8/15 11:16
 */
public class VideoColumnPageInfo implements Serializable{
    //栏目ID
    private Integer id;
    //栏目标题
    private String title;
    //栏目排序 column_order
    private Integer columnOrder;
    //添加人
    private String createUser;
    //创建时间
    private Date createTime;

    private Integer page;
    private Integer limit;


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

    public Integer getColumnOrder() {
        return columnOrder;
    }

    public void setColumnOrder(Integer columnOrder) {
        this.columnOrder = columnOrder;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
