package com.kanfa.news.web.rpc.app;

import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.web.feign.IContentServiceFeign;
import com.kanfa.news.web.feign.ILiveServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/web/live")
public class LiveRest{

    @Autowired
    private ILiveServiceFeign liveServiceFeign;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private IContentServiceFeign contentServiceFeign;
    /**
     * 微博登陆的id
     */
    @Value("${pc.weixin.client_id}")
    private String client_id;
    /**
     * 微博登陆的密钥
     */
    @Value("${pc.weixin.client_secret}")
    private String client_secret;

    /**
     * 庭审直播详情页
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(@RequestParam(value = "id") Integer liveId , HttpServletResponse response ,Model model){
        Map<String, Object> liveDetail = liveServiceFeign.getDetailData(liveId);
        if(liveDetail == null || liveDetail.get("live_id") == null  ){
            return "redirect:../../web/redirect/test";
        }
        //微信分享
        String desc = "";
        if(liveDetail.get("live_type_id").equals(LiveCommonEnum.LIVE_TYPE_COURT)){
            if(liveDetail.get("case_info") != null && ((String)liveDetail.get("case_info")).length() > LiveCommonEnum.CONSTANT_ZERO){
                if(((String)liveDetail.get("case_info")).length()<40){
                    desc = (String)liveDetail.get("case_info");
                }
                desc = ((String)liveDetail.get("case_info")).substring(0,40);
            }
        }else{
            if(liveDetail.get("live_content") != null && ((String)liveDetail.get("live_content")).length() > LiveCommonEnum.CONSTANT_ZERO){
                if(((String)liveDetail.get("live_content")).length()<40){
                    desc = (String)liveDetail.get("live_content");
                }
                desc = ((String)liveDetail.get("live_content")).substring(0,40);
            }
        }
        HashMap<String, String> share = (HashMap<String, String>) liveDetail.get("share");
        String image ;
        if(share.get("img").equals("")){
            image = LiveCommonEnum.DEFAULT_IMAGE;
        }else {
            image = share.get("img");
        }
        HashMap<String, Object> newShare = new HashMap<>(8);
        newShare.put("title" , liveDetail.get("title"));
        newShare.put("desc" , desc);
        newShare.put("link" , request.getRequestURL().toString()+"?"+request.getQueryString()+"&locf=out");
        newShare.put("imgUrl" , image);
        if(liveDetail.get("live_type_id").equals(LiveCommonEnum.LIVE_TYPE_COURT)){

        }
        HashMap<String, Object> data = new HashMap<>(8);
        data.put("signPackage" ,contentServiceFeign.getSignPackage(share.get("url")));
        data.put("share" , newShare);
        data.put("obj" , liveDetail);
        data.put("params" , liveId);
        data.put("nowtime" , new Date());
        data.put("tag" , liveDetail.get("title"));
        model.addAttribute("data",data);
        return  "live/index";
    }


    /**
     * 直播详情页
     */
    @ResponseBody
    @RequestMapping(value = "/getLiveDetail",method = RequestMethod.POST)
    public  AppObjectResponse getLiveDetail(@RequestParam(value = "id") Integer liveId,
                                            @RequestParam(value = "websessionid" ,required = false) String sessionid){
        AppObjectResponse response = new AppObjectResponse();
        if(liveId == null){
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_ILLEGALITY.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_ILLEGALITY.value());
        }else{
            response = liveServiceFeign.getWebLiveDetail(liveId ,sessionid);
            HashMap<String, Object> data = (HashMap<String, Object>)response.getData();
            Object live_id = data.get("live_id");
            if(live_id == null){
                response.setStatus(AppCommonMessageEnum.APP_NO_DATA.key());
                response.setMessage(AppCommonMessageEnum.APP_NO_DATA.value());
            }
        }
        return  response;

    }



}
