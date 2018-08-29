package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IAdvServiceFeign;
import com.kanfa.news.admin.vo.adv.AdvInfo;
import com.kanfa.news.admin.vo.adv.AdvOnlineNumInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.DateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/5/30 11:10
 */
@RestController
@RequestMapping("/adv")
public class AdvRest extends BaseRPC{

    @Autowired
    private IAdvServiceFeign advServiceFeign;

    /**
     * 添加广告
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/addAdv", method = RequestMethod.POST)
    public ObjectRestResponse<AdvInfo> addAdv(@RequestBody AdvInfo advInfo) {
        if (StringUtils.isEmpty(advInfo.getName())){
            return getObjectRestResponse("广告名称不能为空");
        }else if (advInfo.getName().length()>20){
            return getObjectRestResponse("广告名称不能超过20字");
        }
        if(advInfo.getStartTime()==null || advInfo.getEndTime()==null || DateHandler.isAfterForDay(advInfo.getStartTime(),advInfo.getEndTime())){
            return getObjectRestResponse("广告的上线时间的不能为空，同时开始不能大于结束");
        }
        if(advInfo.getType()==null){
            return getObjectRestResponse("广告的类型不能为空");
        }
        if(StringUtils.isEmpty(advInfo.getUrl())){
            return getObjectRestResponse("广告的链接不能为空");
        }
        if (!NewsEnumsConsts.AdvOfType.StartUpPage.key().equals(advInfo.getType())){
            if (StringUtils.isEmpty(advInfo.getTitle())){
                return getObjectRestResponse("广告标题不能为空");
            }
            if (StringUtils.isEmpty(advInfo.getDescription())){
                return getObjectRestResponse("广告描述不能为空");
            }
            if(advInfo.getPositionList() == null || advInfo.getPositionList().size() <= 0){
                return getObjectRestResponse("广告至少有一个位置");
            }
        }else{
            if(advInfo.getKeep()==null){
                return getObjectRestResponse("广告停留时间不能为空");
            }
        }
        advInfo.setCreateTime(new Date());
        advInfo.setCreateUid(Integer.valueOf(getCurrentUserId()));
        advInfo.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        return advServiceFeign.addAdv(advInfo);
    }

    /**
     * 分页
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/getAdvPage", method = RequestMethod.POST)
    public TableResultResponse<AdvInfo> getAdvPage(@RequestBody AdvInfo advInfo) {
        if(advInfo.getPage()==null){
            advInfo.setPage(1);
        }
        if(advInfo.getLimit()==null){
            advInfo.setLimit(10);
        }
        advInfo.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        return advServiceFeign.getAdvPage(advInfo);
    }

    /**
     * 编辑初始化
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAdv", method = RequestMethod.GET)
    public ObjectRestResponse<AdvInfo> getAdv(@RequestParam Integer id) {
        return advServiceFeign.getAdv(id);
    }

    /**
     * 编辑保存
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/updateAdv", method = RequestMethod.PUT)
    public ObjectRestResponse<AdvInfo> updateAdv(@RequestBody AdvInfo advInfo) {
        if(advInfo.getId()==null){
            return getObjectRestResponse("广告id不能为空");
        }
        if (!NewsEnumsConsts.AdvOfType.StartUpPage.key().equals(advInfo.getType())){
            if (StringUtils.isEmpty(advInfo.getTitle())){
                return getObjectRestResponse("广告标题不能为空");
            }
            if (StringUtils.isEmpty(advInfo.getDescription())){
                return getObjectRestResponse("广告描述不能为空");
            }
            if(advInfo.getPositionList() == null || advInfo.getPositionList().size() <= 0){
                return getObjectRestResponse("广告至少有一个位置");
            }
        }else{
            if(advInfo.getKeep()==null||advInfo.getKeep().equals(0)){
                return getObjectRestResponse("广告停留时间不能为空");
            }
        }
        advInfo.setViews(null);
        return advServiceFeign.updateAdv(advInfo.getId(),advInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAdv/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<AdvInfo> deleteAdv(@PathVariable("id") Integer id) {
        if(id==null){
            return getObjectRestResponse("广告id不能为空");
        }
        return advServiceFeign.deleteAdv(id);
    }

    /**
     * 上下线统计
     * @return
     */
    @RequestMapping(value = "/getOnlineNum", method = RequestMethod.GET)
    public ObjectRestResponse<AdvOnlineNumInfo> getOnlineNum() {
        return advServiceFeign.getOnlineNum();
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortAdv", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse sortAdv( @RequestBody Map<String, Object> params) {
        return advServiceFeign.sortAdv(params);
    }

    private ObjectRestResponse<AdvInfo> getObjectRestResponse(String msg) {
        ObjectRestResponse<AdvInfo> objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
