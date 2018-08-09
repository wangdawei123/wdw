package com.kanfa.news.info.rest.app;

import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentBiz;
import com.kanfa.news.info.biz.app.BroadcastBiz;
import com.kanfa.news.info.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *  APP内容 模块
 *  Created by wdw on 2018/3/16.
 */
@RestController
@RequestMapping("special")
public class AppSpecialController extends BaseController<ContentBiz, Content> {
    @Autowired
    private ContentBiz contentBiz;

    /**
     * 专题h5页面接口，app内外使用
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getNewSpecial",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getNewSpecial(@RequestParam("cid") Integer cid ,@RequestParam(value = "cate") Integer cate ){
        try{
            return contentBiz.getNewSpecial(cid,cate);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }



}
