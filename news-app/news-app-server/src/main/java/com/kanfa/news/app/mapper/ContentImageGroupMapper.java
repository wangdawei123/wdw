package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ContentImageGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 图集的图片表。一对多
 * 
 * @author ffy
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-13 18:26:57
 */
public interface ContentImageGroupMapper extends Mapper<ContentImageGroup> {

    /**
     * 查询内容图集列表
     * @return
     */
    List<ContentImageGroup> selectImageGroupList();

    List<ContentImageGroup> findImages(@Param("cid") Integer cid, @Param("pcount") Integer pcount);


    List<ContentImageGroup> selectImageGroupListByCid(Integer cid);

    Integer addContentImageGroup(ContentImageGroup contentImageGroup);



    /**
     *  查询图片集合
     * @param contentImageGroup
     * @return
     */
    List<ContentImageGroup> getImageGroupList(@Param("entity") ContentImageGroup contentImageGroup);
}
