package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Live;
import com.kanfa.news.info.vo.admin.live.LiveAddInfo;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import com.kanfa.news.info.vo.admin.live.LivePageInfo;
import com.kanfa.news.info.vo.admin.live.LiveVideoBindInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 直播表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 15:15:25
 */
public interface LiveMapper extends Mapper<Live> {

    List<LiveInfo> getLiveList(@Param("liveInfo") LiveInfo liveInfo);

    //新增直播
    int addOneLive(LiveAddInfo entity);

    //根据uid type_id type 查出工作类型的集合
    List<Integer> getworkTypeList(@Param("uid") Integer uid,
                                  @Param("typeId") Integer typeId,
                                  @Param("type") Integer type);

    //编辑直播
    void updateLiveAddInfo(LiveAddInfo entity);


    //直播列表的分页及内容搜索
    List<LivePageInfo> getLiveSearchPage(LivePageInfo entity);

    List<LiveInfo> selectPriview(LiveInfo liveInfo);

    List<LiveInfo> selectPriviewList(LiveInfo liveInfo);


    List<LiveInfo> findByIds(@Param("cidsKey") List<Integer> cidsKey);


    List<Integer> selectFromtype(@Param("id") Integer id);

    List<LiveVideoBindInfo> selectBindList(@Param("id") Integer id);

    List<LiveVideoBindInfo> selectBindList2(@Param("id") Integer id);

    LiveInfo getLiveDetail(LiveInfo liveInfo);

    List<LiveInfo> selectSpecial(@Param("liveSpecialId") Integer liveSpecialId);

    int selectCountBy(LiveInfo liveInfo);

    List<LiveInfo> getFilterList(@Param("liveTypeId")Integer liveTypeId ,@Param("stat")Integer stat ,
                                 @Param("type")Integer type ,
                                 @Param("caseType")Integer caseType ,
                                 @Param("courtLevel")Integer courtLevel ,@Param("court")Integer court ,
                                 @Param("provinceId")Integer provinceId ,
                                 @Param("signtime")Date signtime);


    List<Live> getLiveForMessage(Map<String, Object> params);

    Long getLiveAllData();

    List<Integer> selectListId(@Param("start")Integer start, @Param("size")Integer size);
}
