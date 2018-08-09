package com.kanfa.news.activity.mapper;

import com.kanfa.news.activity.entity.Setting;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 配置表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-19 18:15:53
 */
public interface SettingMapper extends Mapper<Setting> {

    Setting selectSettingByName(@Param("name") String name);
}
