package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityBlueSkyImage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
public interface ActivityBlueSkyImageMapper extends Mapper<ActivityBlueSkyImage> {

    //查找sort的最大值通过blue_sky_people_id
    Integer selectMaxSort(@Param("blueSkyPeopleId") Integer blueSkyPeopleId);

    //通过blue_sky_people_id image的集合按sort排序
    List<String> getImageList(@Param("blueSkyPeopleId") Integer blueSkyPeopleId);


}
