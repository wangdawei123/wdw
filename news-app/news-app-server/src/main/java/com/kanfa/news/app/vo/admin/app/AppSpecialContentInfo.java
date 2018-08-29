package com.kanfa.news.app.vo.admin.app;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/3/19.
 */
public class AppSpecialContentInfo {
    private Integer cid;
    private List<String> ids;
    private Integer catalogId;

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
