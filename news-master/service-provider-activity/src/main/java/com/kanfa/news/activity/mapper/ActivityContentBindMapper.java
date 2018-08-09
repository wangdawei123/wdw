package com.kanfa.news.activity.mapper;

import com.kanfa.news.activity.entity.ActivityContentBind;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
public interface ActivityContentBindMapper extends Mapper<ActivityContentBind> {


    List<ActivityContentBind> findByid(@Param("lawId") Integer lawId ,
                                       @Param("offset") Integer offset ,
                                       @Param("pcount")  Integer pcount);
	
}
