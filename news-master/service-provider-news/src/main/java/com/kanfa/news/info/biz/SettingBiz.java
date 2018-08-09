package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.Setting;
import com.kanfa.news.info.mapper.SettingMapper;
import com.kanfa.news.common.biz.BaseBiz;
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
        if(setting!=null){
            return setting.getValue();
        }
        return null;
    }

    public Setting adminGet(String name){
        Setting setting = settingMapper.selectSettingByName(name);
        return setting;
    }

    public String getByName(String name){
        Setting setting = settingMapper.selectSettingByName(name);
        if(setting!=null){
            return setting.getValue();
        }
        return null;
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

    /**
     * 批量修改（广告）
     * @param params
     * @return
     */
    public Integer updateSettingForAdv(Map<String, String> params) {
        Setting setting  = new Setting();
        setting.setName("adv_index");
        setting.setValue(params.get("adv_index"));
        setting.setDesc("设置首条广告在频道列表中出现的位置，从1开始。例如，1表示出现在第1位.");
        if(settingMapper.updateSettingByName(setting)<=0){
            settingMapper.insertSetting(setting);
        }
        setting.setName("adv_peroid");
        setting.setValue(params.get("adv_peroid"));
        setting.setDesc("设置每隔多少条内容出现下一条广告，0表示没有间隔。为了用户体验，建议不小于15.");
        if(settingMapper.updateSettingByName(setting)<=0){
            settingMapper.insertSetting(setting);
        }
        return params.size();
    }
}