package com.kanfa.news.web.rpc.app;

import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.IContentServiceFeign;
import com.kanfa.news.web.feign.ILiveServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kanfa on 2018/6/15.
 */

@Controller
@RequestMapping("/web/content")
public class ContentRest {
    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private ILiveServiceFeign liveServiceFeign;

    /**
     * 更新播放量接口
     */
    @ResponseBody
    @RequestMapping(value = "/videoView", method = RequestMethod.POST)
    public AppObjectResponse videoView(@RequestParam(value = "id") Integer id,
                            @RequestParam(value = "type") Integer type,
                            @RequestParam(value = "fromApp" ,defaultValue = "0") Integer fromApp) {
        AppObjectResponse response = new AppObjectResponse();

        if(id == null && type == null){
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }
        Integer i = 0 ;
        if(LiveCommonEnum.LIVE_TYPE_ONLINE.equals(type)){
            ObjectRestResponse res = contentServiceFeign.updateVideoViews(id ,fromApp);
            int status = res.getStatus();
            if(LiveCommonEnum.STATUS_SUCCESS.equals(status)){
                i = 1 ;
            }
        }else{
            i = liveServiceFeign.updatePlayViews(id, fromApp);
        }
        if(LiveCommonEnum.CONSTANT_ONE.equals(i)){
            response.setMessage(AppCommonMessageEnum.OK.value());
            response.setStatus(AppCommonMessageEnum.OK.key());
        }else{
            response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
        }
        return response;
    }



}
