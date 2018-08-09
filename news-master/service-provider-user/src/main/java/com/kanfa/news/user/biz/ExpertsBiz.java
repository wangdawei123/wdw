package com.kanfa.news.user.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.user.entity.Experts;
import com.kanfa.news.user.mapper.ExpertsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-03 18:18:36
 */
@Service
public class ExpertsBiz extends BaseBiz<ExpertsMapper,Experts> {

    @Autowired
    private ExpertsMapper expertsMapper;

    public Map<String ,Integer> findExpert(Integer id){
        Map<String ,Integer> map = new HashMap<>(1);
        Experts ex = new Experts();
        ex.setId(id);
        ex.setStat(1);
        Experts experts = expertsMapper.selectOne(ex);
        if(experts != null){
            map.put("id",experts.getId());
            return map;
        }
        return map;
    }
}