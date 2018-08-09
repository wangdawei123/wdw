package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.LiveCourt;
import com.kanfa.news.info.vo.admin.live.LiveCourtInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-18 15:24:35
 */
public interface LiveCourtMapper extends Mapper<LiveCourt> {

    /**
     * //直播法院的分页显示
     * @return
     */
    List<LiveCourtInfo> getPage();

    /**
     * //直播法院的按法院名字的模糊查询
     * @param name
     * @return
     */
    List<LiveCourtInfo> getSearchPage(@Param("name")String name);


    /**
     * 查询一个返回LiveCourtInfo
     * @param id
     * @return
     */
    LiveCourtInfo selectOneLiveCourt(@Param("id")Integer id);


    List<LiveCourtInfo> getLevelCourtList(@Param("court_level") Integer court_level,
                                          @Param("province_id") Integer province_id,
                                          @Param("stat") Integer stat );
}
