package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.Setting;
import com.kanfa.news.app.mapper.SettingMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 配置表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-19 18:15:53
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SettingBiz extends BaseBiz<SettingMapper,Setting> {

    @Autowired
    private SettingMapper settingMapper;

    public String selectByname(String name){
        Setting setting = settingMapper.selectSettingByName(name);
        return setting.getValue();
    }

    public Setting adminGet(String name){
        Setting setting = settingMapper.selectSettingByName(name);
        return setting;
    }

    public String getByName(String name){
        Setting setting = settingMapper.selectSettingByName(name);
        return setting.getValue();
    }

   /* public String selectBynameTo(String name ,@RequestParam(defaultValue = "false") Boolean flag){

        Setting setting = settingMapper.selectSettingByName(name);
        return flag?setting.getValue():setting;
    }*/

    /**
     * 添加全局设置
     * @param setting
     * @return
     */
   public Setting addSetting(Setting setting) {
       if(settingMapper.insertSetting(setting)<=0){
           Assert.isTrue(false,"添加设置失败");
       }
       return setting;
   }

    /**
     * 更新配置
     * @param setting
     * @return
     */
    public Setting updateSetting(Setting setting) {
        if(settingMapper.updateSettingByName(setting)<=0){
//            Assert.isTrue(false,"更新设置失败,名称不能修改,如需修改请新增一个。");
            settingMapper.insertSetting(setting);
        }
        return setting;
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    public TableResultResponse<Setting> pageSetting(Map<String,Object> params) {
        Query query = new Query(params);
        Page<Setting> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Setting> list = settingMapper.selectSetting(params);
        return new TableResultResponse<>(result.getTotal(),list);
    }
}