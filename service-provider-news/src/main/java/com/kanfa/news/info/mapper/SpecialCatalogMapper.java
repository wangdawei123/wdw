package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.SpecialCatalog;
import com.kanfa.news.info.vo.admin.app.AppSpecialContentInfo;
import com.kanfa.news.info.vo.admin.info.SubjectCatalogInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 专题-目录表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 15:36:06
 */
public interface SpecialCatalogMapper extends Mapper<SpecialCatalog> {

    List<SubjectCatalogInfo> selectSpecialCatalogList(@Param("specialCatalog") SpecialCatalog specialCatalog);

    Integer addSpecialCatalog(SpecialCatalog specialCatalog);

    Integer deleteSpecialCatalog(@Param("specialCatalog") SpecialCatalog specialCatalog);

    List<Map> getSpecialCatalog(@Param("id") Integer id);

    List<Map> selectBindSpecialContentListByCid(@Param("entity") AppSpecialContentInfo entity);
}
