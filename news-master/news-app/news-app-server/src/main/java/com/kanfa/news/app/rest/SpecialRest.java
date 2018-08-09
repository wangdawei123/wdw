package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.app.BroadcastBiz;
import com.kanfa.news.app.feign.IAppContentServiceFeign;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wdw
 */
@RestController
@RequestMapping("web/Special")
public class SpecialRest extends BaseRest {
    @Autowired
    private BroadcastBiz broadcastBiz;

    /**
     * 新版专题接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getNewSpecial", method = RequestMethod.POST)
    public ObjectRestResponse getNewSpecial( @RequestParam("cate") Integer cate,
                                       @RequestParam("cid") Integer cid) {
        try{
            return broadcastBiz.getNewSpecial(cate,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }


}
