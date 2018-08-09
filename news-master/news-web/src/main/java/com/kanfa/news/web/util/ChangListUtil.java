package com.kanfa.news.web.util;

import com.kanfa.news.web.vo.channel.ContentImageInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kanfa on 2018/3/22.
 */
public class ChangListUtil {

    /**当类型为图集
     *
     */
    static final int CONTENT_TYPE_MAPS = 3;

    /**
     *
     用于在上面的方法中处理数据重复的方法
     */
    public static List<ContentImageInfo> changeList(List<ContentImageInfo> rows){
        //创建一个新的集合，放ContentImageInfo
        HashMap<Integer,ContentImageInfo> map = new HashMap<>(16);

        Iterator<ContentImageInfo> it = rows.iterator();
        while (it.hasNext()) {
            ContentImageInfo c = it.next();
            if(c.getContentType() == CONTENT_TYPE_MAPS){
                if(map.containsKey(c.getId())){
                    ContentImageInfo contentImageInfo = map.get(c.getId());
                    contentImageInfo.getImageList().add(c.getImageGroup());
                    it.remove();
                }else{
                    map.put(c.getId(),c);
                    c.getImageList().add(c.getImageGroup());
                }
            }
        }
        return rows;
    }
}
