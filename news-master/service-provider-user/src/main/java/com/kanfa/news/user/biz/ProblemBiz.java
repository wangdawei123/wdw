package com.kanfa.news.user.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.util.DateUtil;
import com.kanfa.news.user.entity.Problem;
import com.kanfa.news.user.mapper.ProblemMapper;
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
    @Autowired
    private UserBiz userBiz;


    public Integer delMyProblem(Integer uid , Integer id){
        Integer i = problemMapper.delMyProblem(uid,id);
        return i;
    }

    public void updateRead(Integer id){
        problemMapper.updateRead(id);
    }



    public List<Map<String,Object>> getmyposts(Integer page , Integer pcount , Integer id){
        Integer offset = (page-LiveCommonEnum.CONSTANT_ONE)*pcount;
        List<Problem> list = problemMapper.selectProblem(offset,pcount , id);
        List<Map<String,Object>> listMap = new ArrayList();
        if(list.size()> LiveCommonEnum.CONSTANT_ZERO){
            for(int i = 0 ; i < list.size() ; i++){
                Map<String,Object> map = new HashMap<>(16);
                map.put("id",list.get(i).getId());
                map.put("cid",list.get(i).getCid());
                map.put("content",list.get(i).getContent());
                if(list.get(i).getOps().equals(LiveCommonEnum.OPS_TRUE)){
                    map.put("reason",list.get(i).getReason());
                }
                Map<String ,Integer> experts  = expertsBiz.findExpert(list.get(i).getCreateUid());
                map.put("experts",experts);
                map.put("create_uid",list.get(i).getCreateUid());
                Map<String ,Integer> users  = userBiz.getapiuser(list.get(i).getCreateUid());
                map.put("user",users);
                //控制时间显示
                //问答正文页
                map.put("content_url","web/problem/index/"+ list.get(i).getCid() +"/1");
                String createTime = DateUtil.fromToday(list.get(i).getCreateTime());
                String updateTime = DateUtil.fromToday(list.get(i).getUpdateTime());
                map.put("create_time",createTime);
                map.put("update_time",updateTime);
                listMap.add(map);
            }
            return listMap;
        }else{
            return listMap;
        }
    }
}