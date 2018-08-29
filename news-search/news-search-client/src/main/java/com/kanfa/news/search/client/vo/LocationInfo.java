package com.kanfa.news.search.client.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 地理位置
 * @author Jiqc
 * @date 2018/3/27 11:37
 */
public class LocationInfo implements Serializable{

    private String id;
    private String name;
    private String business;
    private String city;
    private String cityid;
    private String distict;
    private List<Float> point;
    private String uid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getDistict() {
        return distict;
    }

    public void setDistict(String distict) {
        this.distict = distict;
    }

    public List<Float> getPoint() {
        return point;
    }

    public void setPoint(List<Float> point) {
        this.point = point;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
