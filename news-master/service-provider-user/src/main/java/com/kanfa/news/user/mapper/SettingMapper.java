package com.kanfa.news.user.mapper;

import com.kanfa.news.user.entity.Setting;
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


    public Setting selectSettingByName(@Param("name") String name);

    /**
     * 更新设置
     * @param setting
     * @return
     */
    Integer updateSettingByName(Setting setting);

    /**
     * 新增设置
     * @param setting
     * @return
     */
    Integer insertSetting(Setting setting);

}
