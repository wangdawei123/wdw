package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.Problem;
import com.kanfa.news.app.mapper.ProblemMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ExpertsBiz expertsBiz;


    public Integer delMyProblem(Integer uid , Integer id){
        Integer i = problemMapper.delMyProblem(uid,id);
        return i;
    }
}