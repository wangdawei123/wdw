package com.kanfa.news.activity.mapper;

import com.kanfa.news.activity.entity.ActivityBlueSky;
import com.kanfa.news.activity.vo.info.ActivityBlueSkyInfo;
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
public interface ActivityBlueSkyMapper extends Mapper<ActivityBlueSky> {


    List<ActivityBlueSkyInfo> selectById(@Param("id") Integer id);


    List<ActivityBlueSkyInfo> selectABSById(@Param("id") Integer id);

}
