package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.SpecialCatalog;
import com.kanfa.news.app.vo.admin.info.SubjectCatalogInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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
}
