package com.kanfa.news.admin.vo.site;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/4/3 16:14
 */
public class SiteChannelResponseInfo implements Serializable {

    private String id;
    private String name;
    private String site_id;


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

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }
}
