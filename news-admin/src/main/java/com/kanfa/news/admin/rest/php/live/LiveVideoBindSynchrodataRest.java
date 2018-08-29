package com.kanfa.news.admin.rest.php.live;

import com.kanfa.news.admin.entity.LiveVideoBind;
import com.kanfa.news.admin.feign.ILiveVideoBindServiceFeign;
import com.kanfa.news.admin.vo.live.LiveVideoBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.data.client.feign.IliveVideoBindSynDataServiceFeign;
import com.kanfa.news.data.common.vo.live.XmLiveVideoBind;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chen
 * on 2018/8/8 11:05
 */
@RestController
@RequestMapping("/liveVideoBindData")
public class LiveVideoBindSynchrodataRest {

    @Autowired
    private IliveVideoBindSynDataServiceFeign iliveVideoBindSynDataServiceFeign;
    @Autowired
    private ILiveVideoBindServiceFeign iLiveVideoBindServiceFeign;
    /**
     * 通过id查找再新增
     * @param id
     * @return
     */
    @RequestMapping(value = "addBind",method = RequestMethod.GET)
    public ObjectRestResponse addBind(@RequestParam("id")Integer id){
        //逻辑先删除再新增
        List<LiveVideoBindInfo> bind = iLiveVideoBindServiceFeign.getBind(id);
        for (LiveVideoBindInfo liveVideoBindInfo:bind) {
            Integer id1 = liveVideoBindInfo.getId();
            Integer liveVideoBindId = iLiveVideoBindServiceFeign.getLiveVideoBindId(id, id1);
            iLiveVideoBindServiceFeign.delete(liveVideoBindId);
        }
        //通过cid查表
        List<XmLiveVideoBind> allBinds = iliveVideoBindSynDataServiceFeign.getAllBinds(id);
        for (XmLiveVideoBind xmLiveVideoBind:allBinds) {
            LiveVideoBind liveVideoBind = new LiveVideoBind();
            BeanUtils.copyProperties(xmLiveVideoBind, liveVideoBind);
            iLiveVideoBindServiceFeign.add(liveVideoBind);
        }
        String msg = "新增成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }


}
