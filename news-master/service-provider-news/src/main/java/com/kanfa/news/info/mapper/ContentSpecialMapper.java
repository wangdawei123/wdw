package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ContentSpecial;
import com.kanfa.news.info.vo.admin.app.AppSpecialContentInfo;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 内容附表-专题。一对多
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-03-23 15:08:24
 */
public interface ContentSpecialMapper extends Mapper<ContentSpecial> {

    List<ContentInfo> selectListSpecialContent(@Param("contentSpecial") ContentSpecial contentSpecial);

    List<ContentInfo> getIndexSpecialData(@Param("sid") Integer sid);

    Map selectSpecialContentListByCid(@Param("cid") Integer cid);

    List<Map> selectBindSpecialContentListByCid(@Param("entity") AppSpecialContentInfo entity);

    Integer addContentSpecial(ContentSpecial contentSpecial);
}
