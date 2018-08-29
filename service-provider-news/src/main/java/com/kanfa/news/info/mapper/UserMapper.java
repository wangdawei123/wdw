package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface UserMapper extends Mapper<User> {
    List<User> selectMemberByGroupId(@Param("groupId") int groupId);

    List<User> selectLeaderByGroupId(@Param("groupId") int groupId);


}