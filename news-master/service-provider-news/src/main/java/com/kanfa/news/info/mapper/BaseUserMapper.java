package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.BaseUser;
import com.kanfa.news.info.vo.admin.baseuser.AdminUserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-05-16 13:53:49
 */
public interface BaseUserMapper extends Mapper<BaseUser> {

    List<AdminUserInfo> getAllBaseUser(@Param("baseUser") BaseUser baseUser);
}
