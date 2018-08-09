package com.kanfa.news.data.mapper;

import com.kanfa.news.data.entity.XmLiveVideoBind;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 直播关联内容表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-08 10:28:46
 */
public interface XmLiveVideoBindMapper extends Mapper<XmLiveVideoBind> {

    List<XmLiveVideoBind> selectXmLiveVideoBindsByCid(@Param("cid")Integer cid);
	
}
