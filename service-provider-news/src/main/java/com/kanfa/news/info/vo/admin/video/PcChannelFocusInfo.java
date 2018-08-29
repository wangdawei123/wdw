package com.kanfa.news.info.vo.admin.video;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/8/8 14:44
 */
public class PcChannelFocusInfo {
    private static final long serialVersionUID = 1L;
    //频道Id
    private Integer id;
    //频道名称
    private String name;

    private Integer focusCount;
    //创建时间
    private Date createTime;

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



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }
}
