package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.LiveTypeBiz;
import com.kanfa.news.info.entity.LiveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("liveType")
public class LiveTypeController extends BaseController<LiveTypeBiz, LiveType> {
    @Autowired
    private LiveTypeBiz liveTypeBiz;

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<HashMap> getPage(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return liveTypeBiz.page(query);
    }

}