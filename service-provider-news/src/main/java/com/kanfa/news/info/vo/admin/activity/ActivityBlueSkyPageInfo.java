package com.kanfa.news.info.vo.admin.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/17 16:55
 */
public class ActivityBlueSkyPageInfo  implements Serializable{
    //id
    private Integer id;
    //姓名 name
    private  String name;
    //投票总数 vote_total
    private Integer voteTotal;
    //评论总数 comment_total
    private Integer commentTotal;
    //创建时间 create_time
    private Date createTime;


    //是否删除 is_delete
    private Integer isDelete;
    private Integer limit;
    private Integer page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVoteTotal() {
        return voteTotal;
    }

    public void setVoteTotal(Integer voteTotal) {
        this.voteTotal = voteTotal;
    }

    public Integer getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(Integer commentTotal) {
        this.commentTotal = commentTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
