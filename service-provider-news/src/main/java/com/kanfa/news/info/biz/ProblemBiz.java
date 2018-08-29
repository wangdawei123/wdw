package com.kanfa.news.info.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.info.entity.Problem;
import com.kanfa.news.info.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 话题帖子内容
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-02 14:55:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProblemBiz extends BaseBiz<ProblemMapper,Problem> {

    @Autowired
    private ProblemMapper problemMapper;




    public Integer delMyProblem(Integer uid , Integer id){
        Integer i = problemMapper.delMyProblem(uid,id);
        return i;
    }





}