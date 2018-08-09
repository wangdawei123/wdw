package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CommentBadwordsBiz;
import com.kanfa.news.info.biz.SettingBiz;
import com.kanfa.news.info.entity.CommentBadwords;
import com.kanfa.news.info.entity.Setting;
import com.kanfa.news.info.vo.admin.setting.SettingInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("setting")
public class SettingController extends BaseController<SettingBiz,Setting> {


    @Autowired
    private SettingBiz settingBiz;
    @Autowired
    private CommentBadwordsBiz commentBadwordsBiz;

    /**
     * 添加设置（全局）
     * @param setting
     * @return
     */
    @RequestMapping(value = "/addSetting", method = RequestMethod.POST)
    public ObjectRestResponse<Setting> addSetting(@RequestBody Setting setting){
        Setting setting1 = settingBiz.adminGet(setting.getName());
        if(setting1!=null){
            return getFailedObjectRestResponse("名称不能重复");
        }
        return getSuccessObjectRestResponse(settingBiz.addSetting(setting));
    }
    /**
     * 查询
     * @param name
     * @return
     */
    @RequestMapping(value = "/getSetting", method = RequestMethod.GET)
    public ObjectRestResponse<Setting> getSetting(@RequestParam String name){
        return getSuccessObjectRestResponse(settingBiz.adminGet(name));
    }

    /**
     * 更新配置
     * @param setting
     * @return
     */
    @RequestMapping(value = "/updateSetting", method = RequestMethod.PUT)
    public ObjectRestResponse<Setting> updateSetting(@RequestBody Setting setting){
        return getSuccessObjectRestResponse(settingBiz.updateSetting(setting));
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/pageSetting", method = RequestMethod.GET)
    public TableResultResponse<Setting> pageSetting(@RequestParam Map<String,Object> params){
        return settingBiz.pageSetting(params);
    }

    /**
     * 删除
     * @param name
     * @return
     */
    @RequestMapping(value = "/deleteSetting", method = RequestMethod.DELETE)
    public ObjectRestResponse<Setting> deleteSetting(@RequestParam("name") String name){
        settingBiz.deleteById(name);
        return getSuccessObjectRestResponse(new Setting());
    }

    /**
     * 获取设置信息
     * @return
     */
    @RequestMapping(value = "/getCommentSetting",method = RequestMethod.GET)
    public ObjectRestResponse<SettingInfo> getCommentSetting(){
        SettingInfo settingInfo = new SettingInfo();
        settingInfo.setValue(settingBiz.getByName("comment_ops"));
        List<CommentBadwords> commentBadwords = commentBadwordsBiz.selectListAll();
        if(commentBadwords!=null && commentBadwords.size()>0){
            settingInfo.setCommentBadwords(commentBadwords.get(0));
        }
        ObjectRestResponse<SettingInfo> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(settingInfo);
        return objectRestResponse;
    }

    /**
     * 修改批量（广告）
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateSettingForAdv", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> updateSettingForAdv(@RequestBody Map<String, String> params){
        Integer update = settingBiz.updateSettingForAdv(params);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(update);
        return objectRestResponse;
    }

    /**
     * 初始化编辑（广告）
     * @return
     */
    @RequestMapping(value = "/getSettingForAdv", method = RequestMethod.GET)
    public ObjectRestResponse<List<Setting>> getSettingForAdv(){
        List<Setting> list = new ArrayList<>(2);
        if(StringUtils.isNotEmpty(settingBiz.adminGet("adv_index").toString())){
            list.add(settingBiz.adminGet("adv_index"));
        }
        if(StringUtils.isNotEmpty(settingBiz.adminGet("adv_peroid").toString())){
            list.add(settingBiz.adminGet("adv_peroid"));
        }
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(list);
        return objectRestResponse;
    }

    private ObjectRestResponse getFailedObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
    private ObjectRestResponse getSuccessObjectRestResponse(Object data) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(data);
        return objectRestResponse;
    }
}