package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.VideoColumn;
import com.kanfa.news.app.entity.VideoColumnBind;
import com.kanfa.news.app.mapper.VideoColumnMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频栏目表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-07-30 16:34:09
 */
@Service
public class VideoColumnBiz extends BaseBiz<VideoColumnMapper,VideoColumn> {

    @Autowired
    private VideoColumnMapper videoColumnMapper;

    public List<Map<String,Object>> getColumnInfo(){

        List<Map<String,Object>> data = new ArrayList<>();

        List<VideoColumn> list = videoColumnMapper.findData();
        if(list != null && list.size() > 0){
            for(VideoColumn videoColumn : list){
                Map<String,Object> map = new HashMap<>(16);
                if(videoColumn.getId() == 1){
                    map.put("is_law" , 1);
                    map.put("id",videoColumn.getId());
                    map.put("title",videoColumn.getTitle());
                    map.put("cover_img",videoColumn.getCoverImg());
                    data.add(map);
                    continue;
                }
                map.put("id",videoColumn.getId());
                map.put("title",videoColumn.getTitle());
                map.put("cover_img",videoColumn.getCoverImg());
                VideoColumnBind videoColumnBind = videoColumnMapper.findBindData(videoColumn.getId());
                if (videoColumnBind != null) {
                        map.put("cid" , videoColumnBind.getCid());
                        map.put("content_title" , videoColumnBind.getTitle());
                        if(videoColumnBind.getFromType() == 1){
                            map.put("type" , 4);
                        }else {
                            map.put("type" , 22);
                        }
                }else {
                    System.out.println("----else-===");
                }
                data.add(map);
            }
        }
        return data;
    }
}