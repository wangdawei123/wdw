package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.LiveVideoBind;
import com.kanfa.news.info.vo.admin.live.LiveVideoBindInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 直播关联内容表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-28 10:48:58
 */
public interface LiveVideoBindMapper extends Mapper<LiveVideoBind> {

    //直播列表的关联内容(from_type为2 来源直播表)
    List<LiveVideoBindInfo> getLiveBind(@Param("liveId")Integer liveId);
    //直播列表的关联内容(from_type为1 来源content内容表)
    List<LiveVideoBindInfo> getContentBind(@Param("liveId")Integer liveId);

    //直播的搜索
    List<LiveVideoBindInfo> getLiveSearchPage(@Param("liveId")Integer liveId,
                                              @Param("title")String title);
	//视频的搜索
    List<LiveVideoBindInfo> getVideoSearchPage(@Param("liveId")Integer liveId,
                                               @Param("title")String title);

    //查询直播关联内容表中的sort的最大值
    Integer getMaxSort();

//    /**
//     * 通过cid bindId 查出 id
//     * @param
//     * @param
//     * @return
//     */
//    LiveVideoBind getLiveVideoBindId(@Param("cid")Integer cid,
//                               @Param("bindId") Integer bindId);


    //查询绑定内容
    List<LiveVideoBind>  getLiveVideoBind(@Param("ids") List<Integer> ids,@Param("contentId") Integer contentId);
}
