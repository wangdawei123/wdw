package com.kanfa.news.app.vo.admin.live.courtinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kanfa on 2018/3/28.
 */
public class CourtInfo {
    private String name;
    private List<CourtContent> info = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<CourtContent> getInfo() {
        return info;
    }

    public void setInfo(List<CourtContent> info) {
        this.info = info;
    }
}
