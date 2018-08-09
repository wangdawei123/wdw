package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.ISettingServiceFeign;
import com.kanfa.news.admin.vo.setting.SettingInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/4/23 11:36
 */
@RestController
@RequestMapping("/setting")
public class SettingRest extends BaseRPC{

    @Autowired
    private ISettingServiceFeign settingServiceFeign;

    /**
     * 添加设置
     * @param settingInfo
     * @return
     */
    @RequestMapping(value = "/addSetting", method = RequestMethod.POST)
    public ObjectRestResponse<SettingInfo> addSetting(@RequestBody SettingInfo settingInfo) {
        if(StringUtils.isEmpty(settingInfo.getName())){
           return getObjectRestResponse("名称不能为空");
        }else if(StringUtils.isEmpty(settingInfo.getValue())){
            return getObjectRestResponse("值不能为空");
        }else if(StringUtils.isEmpty(settingInfo.getDesc())){
            return getObjectRestResponse("描述不能为空");
        }
        return settingServiceFeign.addSetting(settingInfo);
    }

    /**
     * 查询单个设置
     * @param name
     * @return
     */
    @RequestMapping(value = "/getSetting", method = RequestMethod.GET)
    public ObjectRestResponse<SettingInfo> getSetting(@RequestParam String name) {
        if(StringUtils.isEmpty(name)){
           return getObjectRestResponse("名称不能为空");
        }
        return settingServiceFeign.getSetting(name);
    }
    /**
     * 更新配置
     * @param settingInfo
     * @return
     */
    @RequestMapping(value = "/updateSetting", method = RequestMethod.PUT)
    public ObjectRestResponse<SettingInfo> updateSetting(@RequestBody SettingInfo settingInfo) {
        if(StringUtils.isEmpty(settingInfo.getName())){
           return getObjectRestResponse("名称不能为空");
        }
        return settingServiceFeign.updateSetting(settingInfo);
    }

    /**
     * 分页展示
     * @param params
     * @return
     */
    @RequestMapping(value = "/pageSetting", method = RequestMethod.GET)
    public TableResultResponse<SettingInfo> pageSetting(@RequestParam Map<String,Object> params) {
        return settingServiceFeign.pageSetting(params);
    }

    /**
     * 删除
     * @param name
     * @return
     */
    @RequestMapping(value = "/deleteSetting", method = RequestMethod.DELETE)
    public ObjectRestResponse<SettingInfo> deleteSetting(@RequestParam String name) {
        if(StringUtils.isEmpty(name)){
            return getObjectRestResponse("名称不能为空");
        }
        return settingServiceFeign.deleteSetting(name);
    }

    /**
     * 初始化编辑（广告）
     * @return
     */
    @RequestMapping(value = "/getSettingForAdv", method = RequestMethod.GET)
    public ObjectRestResponse<Map<String,String>> getSettingForAdv() {
        ObjectRestResponse<List<SettingInfo>> settingForAdv = settingServiceFeign.getSettingForAdv();
        List<SettingInfo> data = settingForAdv.getData();
        Map<String,String> map = new HashMap<>(2);
        if(data != null && data.size()>0){
            data.forEach(settingInfo->map.put(settingInfo.getName(),settingInfo.getValue()));
        }
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(map);
        return objectRestResponse;
    }

    /**
     * 修改批量（广告）
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateSettingForAdv", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> updateSettingForAdv(@RequestBody Map<String,String> params) {
        if(params==null || params.size()<=0 || params.get("adv_index")==null || params.get("adv_peroid")==null){
            return getObjectRestResponse("设置数值不能为空");
        }
        return settingServiceFeign.updateSettingForAdv(params);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
