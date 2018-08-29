package com.kanfa.news.admin.rest.php.live;

import com.kanfa.news.admin.entity.LiveSpecialBind;
import com.kanfa.news.admin.feign.ILiveSpecialBindServiceFeign;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.data.client.feign.IliveSpecialBindSynDataService;
import com.kanfa.news.data.common.vo.live.XmLiveSpecialBind;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chen
 * on 2018/8/8 12:07
 */
@RestController
@RequestMapping("/liveSpecialBindData")
public class LiveSpecialBindDateRest {

    @Autowired
    private IliveSpecialBindSynDataService iliveSpecialBindSynDataService;
    @Autowired
    private ILiveSpecialBindServiceFeign iLiveSpecialBindServiceFeign;

    @RequestMapping(value = "/addBind",method = RequestMethod.GET)
    public ObjectRestResponse addBind(@RequestParam("id")Integer id){
        //逻辑先查库删除新增
        List<LiveSpecialBind> allBinds = iLiveSpecialBindServiceFeign.getAllBinds(id);
        for (LiveSpecialBind liveSpecialBind:allBinds) {
            Integer liveId = liveSpecialBind.getLiveId();
            iLiveSpecialBindServiceFeign.delete(liveId);
        }
        //查xm
        List<XmLiveSpecialBind> allBinds1 = iliveSpecialBindSynDataService.getAllBinds(id);
        for (XmLiveSpecialBind xmLiveSpecialBind:allBinds1) {
            LiveSpecialBind liveSpecialBind = new LiveSpecialBind();
            BeanUtils.copyProperties(xmLiveSpecialBind, liveSpecialBind);
            iLiveSpecialBindServiceFeign.add(liveSpecialBind);
        }
        String msg = "新增成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }


}
