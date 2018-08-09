package com.kanfa.news.activity.mapper;

import com.kanfa.news.activity.entity.ContentImageGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 图集的图片表。一对多
 * 
 * @author ffy
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-13 18:26:57
 */
public interface ContentImageGroupMapper extends Mapper<ContentImageGroup> {


}
