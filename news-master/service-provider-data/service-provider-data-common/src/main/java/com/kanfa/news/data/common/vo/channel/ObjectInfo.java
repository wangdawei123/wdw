package com.kanfa.news.data.common.vo.channel;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/2 17:22
 */
public class ObjectInfo implements Serializable {

    /**
     * 封装实体
     */
    private Object entity;

    /**
     *  类型
     */
    private Integer type;

    /**
     *  标题
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String title;

    /**
     * 存入list集合
     */
    private List<ChannelAssociateContent> list;

    /**
     * 存入list集合
     */
    private List<ChannelInfo> channelInfoList;

    /**
     * map
     * @return
     */
    private String name;

    /**
     * 对象集合
     */
    private List<CorpUserInfo> listObject;

    public List<ChannelInfo> getChannelInfoList() {
        return channelInfoList;
    }

    public void setChannelInfoList(List<ChannelInfo> channelInfoList) {
        this.channelInfoList = channelInfoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CorpUserInfo> getListObject() {
        return listObject;
    }

    public void setListObject(List<CorpUserInfo> listObject) {
        this.listObject = listObject;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public List<ChannelAssociateContent> getList() {
        return list;
    }

    public void setList(List<ChannelAssociateContent> list) {
        this.list = list;
    }
}
