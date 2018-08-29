package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.AppUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface AppUserMapper extends Mapper<AppUser> {


    List<AppUser> findByIds(@Param("uids") Set<Integer> uids);

    com.kanfa.news.app.vo.user.AppUser selectOneEntity(@Param("entity") com.kanfa.news.app.vo.user.AppUser u);

}