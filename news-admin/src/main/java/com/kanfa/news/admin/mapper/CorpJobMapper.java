package com.kanfa.news.admin.mapper;

import com.kanfa.news.admin.entity.CorpJob;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 公司岗位表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-06-11 20:09:29
 */
public interface CorpJobMapper extends Mapper<CorpJob> {
    /**
     * 公司岗位分页查询接口
     *
     * @param level1    一级部门ID
     * @param level2    二级部门ID
     * @param corpJobId 岗位ID
     * @param stat      岗位状态
     * @return
     */
    List<Map<String, Object>> search(@Param("level1") Integer level1, @Param("level2") Integer level2, @Param("corpJobId") Integer corpJobId, @Param("stat") Integer stat);

    /**
     * 获取岗位select信息
     *
     * @return
     */
    List<CorpJob> getAll();
}
