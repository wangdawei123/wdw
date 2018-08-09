package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.LiveSpecialBind;
import com.kanfa.news.info.vo.admin.live.LiveSpecialBindInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 专题与直播关联表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 15:43:19
 */
public interface LiveSpecialBindMapper extends Mapper<LiveSpecialBind> {

    //直播专题下的关联内容的分页显示
    List<LiveSpecialBindInfo> getPage(@Param("liveSpecialId")Integer liveSpecialId);

    //直播专题-->关联内容-->按标题搜索
    List<LiveSpecialBindInfo> getSearchPage(@Param("liveSpecialId")Integer liveSpecialId,
                                            @Param("title")String title);


    //查询绑定内容
    List<LiveSpecialBind>  getLiveSpecialBind(@Param("ids") List<Integer> ids, @Param("contentId") Integer contentId);

    //根据liveSpecialId查找最大的排序值
    Integer getMaxSort(@Param("liveSpecialId")Integer liveSpecialId);
}
