package com.kanfa.news.data.mapper;

import com.kanfa.news.data.entity.XmLive;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 直播表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-07 11:13:56
 */
public interface XmLiveMapper extends Mapper<XmLive> {



    //根据id查询live
    XmLive selectLiveById(@Param("id")Integer id);


    //根据uid type_id type 查出工作类型的集合
    List<Integer> getworkTypeList(@Param("uid") Integer uid,
                                  @Param("typeId") Integer typeId,
                                  @Param("type") Integer type);
}
