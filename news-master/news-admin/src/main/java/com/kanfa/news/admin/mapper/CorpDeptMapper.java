package com.kanfa.news.admin.mapper;

import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.admin.vo.adv.AdvInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 公司部门表
 * 
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-18 12:03:22
 */
public interface CorpDeptMapper extends Mapper<CorpDept> {
     List<Map<String,Object>> page(@Param("level1") Integer level1, @Param("level2") Integer level2);
}
