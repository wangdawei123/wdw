package com.kanfa.news.web.rest;

import com.kanfa.news.common.constant.WebCommonMessageEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.IActivityServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ffy
 */
@RestController
@RequestMapping("/Activitymicrovideo")
public class ActivityRest{
    @Autowired
    private IActivityServiceFeign activityServiceFeign;



    /**
     *  app -青春微普法活动
     */
    @RequestMapping(value = "/microvideoIndex", method = RequestMethod.POST)
    public ObjectRestResponse microvideoIndex(@RequestParam(value = "activity_id") Integer activity_id ,
                                        @RequestParam(value = "is_weixin",defaultValue = "0") Integer is_weixin) {
        ObjectRestResponse response = new ObjectRestResponse();
        if(activity_id == null){
            response.setStatus(WebCommonMessageEnum.ACTIVITY_NOT_EXIST.key());
            response.setMessage(WebCommonMessageEnum.ACTIVITY_NOT_EXIST.value());
            return response;
        }else{
            return activityServiceFeign.microvideoIndex(activity_id, is_weixin);
        }
    }


}
