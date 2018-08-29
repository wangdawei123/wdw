package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.vo.setting.SettingInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 全局设置
 * @author Jiqc
 * @date 2018/4/23 13:42
 */
@FeignClient(name = "service-provider-news")
public interface ISettingServiceFeign {

    /**
     * 添加设置（全局）
     * @param settingInfo
     * @return
     */
    @RequestMapping(value = "/setting/addSetting", method = RequestMethod.POST)
    ObjectRestResponse<SettingInfo> addSetting(@RequestBody SettingInfo settingInfo);

    /**
     * 查询
     * @param name
     * @return
     */
    @RequestMapping(value = "/setting/getSetting", method = RequestMethod.GET)
    ObjectRestResponse<SettingInfo> getSetting(@RequestParam("name") String name);

    /**
     * 更新配置
     * @param settingInfo
     * @return
     */
    @RequestMapping(value = "/setting/updateSetting", method = RequestMethod.PUT)
    ObjectRestResponse<SettingInfo> updateSetting(@RequestBody SettingInfo settingInfo);

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/setting/pageSetting", method = RequestMethod.GET)
    TableResultResponse<SettingInfo> pageSetting(@RequestParam Map<String, Object> params);

    /**
     * 删除
     * @param name
     * @return
     */
    @RequestMapping(value = "/setting/deleteSetting", method = RequestMethod.DELETE)
    ObjectRestResponse<SettingInfo> deleteSetting(@RequestParam("name") String name);

    /**
     * 修改批量（广告）
     * @param params
     * @return
     */
    @RequestMapping(value = "/setting/updateSettingForAdv", method = RequestMethod.POST)
    ObjectRestResponse<Integer> updateSettingForAdv(@RequestBody Map<String, String> params);

    /**
     * 初始化编辑（广告）
     * @return
     */
    @RequestMapping(value = "/setting/getSettingForAdv", method = RequestMethod.GET)
    ObjectRestResponse<List<SettingInfo>> getSettingForAdv();
}
