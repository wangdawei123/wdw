package com.kanfa.news.info.vo.admin.live.Utils;

import com.kanfa.news.info.vo.admin.live.changemap.LiveStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kanfa on 2018/4/1.
 */
public class ChangeMapUtil {

    public static List<LiveStatus> changemap(Map<Integer , String> map){
        List<LiveStatus> list = new ArrayList<>();
        for(Map.Entry<Integer , String> m : map.entrySet()){
            LiveStatus live = new LiveStatus();
            live.setId(m.getKey());
            live.setName(m.getValue());
            list.add(live);
        }
        return list;
    }
}
