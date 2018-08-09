package com.kanfa.news.user.mapper;

import com.kanfa.news.user.entity.Problem;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 话题帖子内容
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-02 14:55:56
 */
public interface ProblemMapper extends Mapper<Problem> {

    Integer delMyProblem(@Param("uid") Integer uid, @Param("id") Integer id);


    List<Problem> selectProblem(@Param("offset") Integer offset ,@Param("pcount") Integer pcount ,@Param("id") Integer id);

    void updateRead(@Param("uid") Integer uid);

    Long getCount(Problem p);
}
