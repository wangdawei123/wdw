package com.kanfa.news.data.common.vo.channel;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/3/14 13:46
 */
public class CorpUserInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //用户名称
    private String name;

    //是否选中:  选中:1
    private Integer isSelect;

    //1在职 2 离职 3删除
    private Integer status;

    //类型:w文字p图片v视频
    private Integer type;

    //二级部门id
    private Integer level2Id;

    public Integer getLevel2Id() {
        return level2Id;
    }

    public void setLevel2Id(Integer level2Id) {
        this.level2Id = level2Id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
