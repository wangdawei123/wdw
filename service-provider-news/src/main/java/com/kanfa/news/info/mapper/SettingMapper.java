package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Setting;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 配置表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-19 18:15:53
 */
public interface SettingMapper extends Mapper<Setting> {
    Setting selectSettingByName(@Param("name") String name);

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

    /**
     * 设置集合
     * @param params
     * @return
     */
    List<Setting> selectSetting(Map<String, Object> params);
}
