package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IActiveAwardResultServiceFeign;
import com.kanfa.news.admin.vo.activity.ActiveAwardResultInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chen
 * on 2018/4/19 16:47
 */
@RestController
@RequestMapping("activeAwardResult")
public class ActiveAwardResultRest {

    @Autowired
    private IActiveAwardResultServiceFeign activeAwardResultServiceFeign;


    @RequestMapping(value = "/getActiveAwardResult",method = RequestMethod.GET)
    public ObjectRestResponse<List<ActiveAwardResultInfo>> getActiveAwardResult(@RequestParam("activityRaffleId")Integer activityRaffleId){
        List<ActiveAwardResultInfo> activeAwardResult = activeAwardResultServiceFeign.getActiveAwardResult(activityRaffleId);
        ObjectRestResponse<List<ActiveAwardResultInfo>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(activeAwardResult);
        return listObjectRestResponse;
    }
}
