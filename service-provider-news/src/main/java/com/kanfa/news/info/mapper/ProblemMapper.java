package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Problem;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 话题帖子内容
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-02 14:55:56
 */
public interface ProblemMapper extends Mapper<Problem> {

    Integer delMyProblem(@Param("uid") Integer uid ,@Param("id") Integer id);
	
}
