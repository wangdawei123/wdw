package com.kanfa.news.web.rpc.app;

import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
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
@RequestMapping("/web/index")
public class IndexAppRest {
    @Autowired
    private ILiveServiceFeign liveServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;


    @ResponseBody
    @RequestMapping(value = "/updateviews", method = RequestMethod.GET)
    public AppObjectResponse updateviews(@RequestParam(value = "id") Integer id ,
                                         @RequestParam(value = "type") Integer type,
                                         @RequestParam(value = "ispreview" ,defaultValue = "0") Integer ispreview,
                                         @RequestParam(value = "locf" ,defaultValue = "out") String locf) {
        AppObjectResponse response = new AppObjectResponse();
        if(id == null){
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            return response;
        }
        if(LiveCommonEnum.WEB_SUCCESS.equals(ispreview)){
            response.setStatus(AppCommonMessageEnum.OK.key());
            return response;
        }
        if(LiveCommonEnum.CONTENT_LIVE.equals(type) || LiveCommonEnum.CONTENT_LIVE_COURT.equals(type) || LiveCommonEnum.CONTENT_LIVE_COURT_H5.equals(type)){
            Integer i = liveServiceFeign.updateViews(id);
            if(LiveCommonEnum.CONSTANT_ZERO.equals(i)){
                response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
                response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            }else if(LiveCommonEnum.CONSTANT_ONE.equals(i)){
                response.setStatus(AppCommonMessageEnum.OK.key());
                response.setMessage(AppCommonMessageEnum.OK.value());
            }
        }else {
            Integer i = contentServiceFeign.updateViews(id);
            if(LiveCommonEnum.CONSTANT_ZERO.equals(i)){
                response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
                response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            }else if(LiveCommonEnum.CONSTANT_ONE.equals(i)){
                response.setStatus(AppCommonMessageEnum.OK.key());
                response.setMessage(AppCommonMessageEnum.OK.value());
            }
        }

        return response;
    }



}
