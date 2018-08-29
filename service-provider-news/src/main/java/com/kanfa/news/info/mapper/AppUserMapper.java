package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.AppUser;
import com.kanfa.news.info.vo.admin.appuser.AppUserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AppUserMapper extends Mapper<AppUser> {


    List<AppUser> findByIds(@Param("uids") Set<Integer> uids);

    List<AppUserInfo> getPage(Map<String, Object> params);

    AppUserInfo getAppUser(@Param("id") Integer id);

    Integer getTodayUser();
}