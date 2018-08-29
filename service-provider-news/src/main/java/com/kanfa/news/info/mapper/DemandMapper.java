package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Demand;
import com.kanfa.news.info.vo.admin.my.DemandInfo;
import com.kanfa.news.info.vo.admin.my.MyDemandPageInfo;
import com.kanfa.news.info.vo.admin.video.VideoDemandInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-15 11:52:46
 */
public interface DemandMapper extends Mapper<Demand> {


    //视频库的分页显示
    List<VideoDemandInfo> getPage();

    //视频库的搜索功能
    List<VideoDemandInfo> getSearchPage(@Param("title")String title);

    //我的视频库 分页信息及搜索
    List<MyDemandPageInfo> getMyDemandPage(@RequestBody MyDemandPageInfo entity);

    /**
     * 新增视频
     * @param
     * @return
     */
    int  insertDemand(DemandInfo entity);

    /**
     * 查询所有视频的视频md5值
     * @param
     * @return
     */
    List<String> getAllVideoMD();


}
