package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.LiveSpecial;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import com.kanfa.news.info.vo.admin.live.LiveSpecialInfo;
import com.kanfa.news.info.vo.admin.video.VideoDemandInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 直播专题表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 15:20:16
 */
public interface LiveSpecialMapper extends Mapper<LiveSpecial> {

    //直播专题的分页显示
    List<LiveSpecialInfo> getPage();

    //直播专题的按标题搜索功能
    List<LiveSpecialInfo> getSearchPage(@Param("title")String title);

    List<LiveInfo> selectSpecialByliveTypId(LiveInfo info);

    List<LiveInfo> selectSpecialByType(@Param("liveTypeId") Integer liveTypeId);

}
