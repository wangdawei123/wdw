package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.AppUser;
import com.kanfa.news.app.mapper.AppUserMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 前台用户
 *
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AppUserBiz extends BaseBiz<AppUserMapper, AppUser> {

    @Autowired
    private AppUserMapper userMapper;
    @Autowired
    private ChatroomUserBiz chatroomUserBiz;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<Map<String ,Object>> findByIds(Set<Integer> uids){
        List<Map<String ,Object>> newList = new ArrayList();
        List<AppUser> users = userMapper.findByIds(uids);
        for(AppUser u : users){
            Map<String ,Object> cons = new HashMap<>(5);
            cons.put(u.getId()+"",u);
            newList.add(cons);
        }
        return newList;
    }

    /**
     * 修改用户信息
     * @param
     */
    public Integer updateone(Integer uid ,String name ,String gender){
        AppUser appUser = userMapper.selectByPrimaryKey(uid);
        if(appUser != null){
            //更新融云昵称
            chatroomUserBiz.refreshNickName(uid ,name);
        }

        AppUser u = new AppUser();
        u.setNickname(name);
        u.setId(uid);
        u.setGender(gender);
        int i = userMapper.updateByPrimaryKeySelective(u);
        return i;
    }


    public com.kanfa.news.app.vo.user.AppUser findByNickname(String name){
        com.kanfa.news.app.vo.user.AppUser u = new com.kanfa.news.app.vo.user.AppUser();
        u.setNickname(name);
        u.setIsDelete(1+"");
        return userMapper.selectOneEntity(u);
    }

    public Map<String ,Integer> getapiuser(Integer id){
        Map<String ,Integer> map = new HashMap<>(1);
        AppUser ua = new AppUser();
        ua.setIsDelete(1);
        ua.setId(id);
        AppUser user = userMapper.selectOne(ua);
        if(user != null){
            map.put("id",user.getId());
            return map;
        }
        return map;

    }



    /**
     * 查找用户信息从redis中
     * @param sessionId
     */
    public Object findRedisUser(String sessionId) {
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.USER_SESSIONID + sessionId);
        return o;
    }

}