package com.kanfa.news.user.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.user.entity.Setting;
import com.kanfa.news.user.mapper.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配置表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-19 18:15:53
 */
@Service
public class SettingBiz extends BaseBiz<SettingMapper,Setting> {

    @Autowired
    private SettingMapper settingMapper;

    public Setting adminGet(String name){
        Setting setting = settingMapper.selectSettingByName(name);
        return setting;
    }

    public String getByName(String name){
        Setting setting = settingMapper.selectSettingByName(name);
        return setting.getValue();
    }

}