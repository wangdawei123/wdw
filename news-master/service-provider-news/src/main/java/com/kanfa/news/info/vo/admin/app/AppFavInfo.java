package com.kanfa.news.info.vo.admin.app;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/3/19.
 */
public class AppFavInfo {
    private Integer fav_status;
    private Map share;

    public Integer getFav_status() {
        return fav_status;
    }

    public void setFav_status(Integer fav_status) {
        this.fav_status = fav_status;
    }

    public Map getShare() {
        return share;
    }

    public void setShare(Map share) {
        this.share = share;
    }
}
