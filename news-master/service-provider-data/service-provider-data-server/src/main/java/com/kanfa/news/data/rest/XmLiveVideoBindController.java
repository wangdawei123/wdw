package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmLiveVideoBindBiz;
import com.kanfa.news.data.entity.XmLiveVideoBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("xmLiveVideoBind")
public class XmLiveVideoBindController extends BaseController<XmLiveVideoBindBiz,XmLiveVideoBind> {
    @Autowired
    private XmLiveVideoBindBiz xmLiveVideoBindBiz;

    /**
     * 通过cid得到绑定的xmLivevideobind集合
     * @param cid
     * @return
     */
    @RequestMapping(value = "getAllBinds",method = RequestMethod.GET)
    public List<XmLiveVideoBind> getAllBinds(@RequestParam("cid")Integer cid){
        return xmLiveVideoBindBiz.selectXmLiveVideoBinds(cid);
    }
}