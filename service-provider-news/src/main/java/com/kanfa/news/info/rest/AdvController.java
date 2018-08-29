package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.AdvBiz;
import com.kanfa.news.info.biz.AdvPositionBiz;
import com.kanfa.news.info.entity.Adv;
import com.kanfa.news.info.entity.AdvPosition;
import com.kanfa.news.info.vo.admin.adv.AdvInfo;
import com.kanfa.news.info.vo.admin.adv.AdvOnlineNumInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adv")
public class AdvController extends BaseController<AdvBiz,Adv> {

    @Autowired
    private AdvBiz advBiz;
    @Autowired
    private AdvPositionBiz advPositionBiz;

    /**
     * 新增广告
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/addAdv",method = RequestMethod.POST)
    public ObjectRestResponse<AdvInfo> addAdv(@RequestBody AdvInfo advInfo){
        advInfo = advBiz.addAdv(advInfo);
        ObjectRestResponse<AdvInfo> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(advInfo);
        return objectRestResponse;
    }

    /**
     * 分页
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/getAdvPage",method = RequestMethod.POST)
    public TableResultResponse<AdvInfo> getAdvPage(@RequestBody AdvInfo advInfo){
        return advBiz.getAdvPage(advInfo);
    }

    /**
     * 查询单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAdv/{id}",method = RequestMethod.GET)
    public ObjectRestResponse<AdvInfo> getAdv(@PathVariable("id") Integer id){
        Adv adv = advBiz.selectById(id);
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(adv,advInfo);
        advInfo.setContentType(null);
        advInfo.setAppAndroid(null);
        advInfo.setAppName(null);
        advInfo.setCreateTime(null);
        advInfo.setCreateUid(null);
        advInfo.setOrderNumber(null);
        advInfo.setIsDelete(null);
        AdvPosition advPosition = new AdvPosition();
        advPosition.setAdvertisementId(id);
        List<AdvPosition> advPositions = advPositionBiz.selectList(advPosition);
        if(advPositions!=null && advPositions.size()>0){
            List<String> postions=new ArrayList<>(advPositions.size());
            for (AdvPosition position : advPositions) {
                postions.add(position.getChannelId()+"-1");
            }
            advInfo.setPositionList(postions);
        }
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(advInfo);
        return objectRestResponse;
    }

    /**
     * 更新保存
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/updateAdv/{id}",method = RequestMethod.PUT)
    public ObjectRestResponse<AdvInfo> updateAdv(@RequestBody AdvInfo advInfo){
        advInfo=advBiz.updateAdv(advInfo);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(advInfo);
        return objectRestResponse;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAdv/{id}",method = RequestMethod.DELETE)
    public ObjectRestResponse<AdvInfo> deleteAdv(@PathVariable("id") Integer id){
        advBiz.deleteAdv(id);
        return new ObjectRestResponse<>();
    }

    /**
     * 上下线统计
     * @return
     */
    @RequestMapping(value = "/getOnlineNum",method = RequestMethod.GET)
    public ObjectRestResponse<AdvOnlineNumInfo> getOnlineNum(){
        AdvOnlineNumInfo advOnlineNumInfo=advBiz.getOnlineNum();
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        objectRestResponse.setData(advOnlineNumInfo);
        return objectRestResponse;
    }

    /**
     * 根据ids查询
     * @param oldIds
     * @return
     */
    @RequestMapping(value = "/getAdvInfoByIds",method = RequestMethod.GET)
    public List<Adv> getAdvInfoByIds(@RequestParam("oldIds") List<Integer> oldIds){
        return advBiz.getAdvInfoByIds(oldIds);
    }

    /**
     * 批量更新
     * @param advInfoList
     * @return
     */
    @RequestMapping(value = "/batchUpdate",method = RequestMethod.POST)
    public Integer batchUpdate(@RequestBody List<Adv> advInfoList) {
        return advBiz.batchUpdate(advInfoList);
    }
    /**
     *  app首页开屏广告
     */
    @RequestMapping(value = "/getStartUpAdv", method = RequestMethod.POST)
    public AppObjectResponse getStartUpAdv(){
        AppObjectResponse response = new AppObjectResponse();
        Adv adv = advBiz.getStartUpAdv();
        if(adv == null){
            response.setStatus(AppCommonMessageEnum.APP_ADV_NOTFOUND.key());
            response.setMessage(AppCommonMessageEnum.APP_ADV_NOTFOUND.value());
        }
        Map<String ,Object> data = new HashMap<>(12);
        // 广告pv统计 需要中间跳转
        if(adv.getUrl()==null || adv.getUrl().equals("")){
            data.put("url",advBiz.getCoutPvUrl(adv.getId()));
        }else {
            data.put("url",adv.getUrl());
        }
        data.put("keep",adv.getKeep());
        data.put("id",adv.getId());
        data.put("image",adv.getImage());

        response.setData(data);
        return response;
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortAdv",method = RequestMethod.POST)
    public ObjectRestResponse sortAdv(@RequestBody Map<String, Object> params){
        List<Integer> newIds = (List<Integer>) params.get("newIds");
        List<Integer> oldIds = (List<Integer>) params.get("oldIds");
        if (newIds == null || newIds.size() <= 0 || oldIds == null || oldIds.size() <= 0) {
            return getObjectRestResponse("请传入新旧id集合");
        }
        int count = newIds.size();
        for (int i = 0; i < oldIds.size(); i++) {
            if (!(oldIds.get(i).equals(newIds.get(i)))) {
                count = i;
                break;
            }
        }
        List<Adv> oldList = advBiz.getAdvInfoByIds(oldIds);
        //新的
        List<Adv> newList = advBiz.getAdvInfoByIds(newIds);
        List<Integer> sort = new ArrayList<>(oldList.size());
        if (oldList != null && oldList.size() > 0) {
            for (Adv advInfo : oldList) {
                sort.add(advInfo.getOrderNumber());
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            Adv advInfo = newList.get(i);
            advInfo.setId(newList.get(i).getId());
            advInfo.setOrderNumber(sort.get(i));
        }
        List<Adv> advInfoList = newList.subList(count, newList.size());
        ObjectRestResponse<Boolean> restResponse = new ObjectRestResponse<>();
        if (advInfoList != null && advInfoList.size() > 0) {
            Integer succNum = advBiz.batchUpdate(advInfoList);
            if (succNum <= 0) {
                restResponse.setData(true);
                restResponse.setMessage("排序失败");
            }
        }
        restResponse.setRel(false);
        return restResponse;
    }

    private ObjectRestResponse<AdvInfo> getObjectRestResponse(String msg) {
        ObjectRestResponse<AdvInfo> objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}