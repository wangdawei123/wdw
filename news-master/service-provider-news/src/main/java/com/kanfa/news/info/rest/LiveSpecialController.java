package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.LiveSpecialBiz;
import com.kanfa.news.info.entity.LiveSpecial;
import com.kanfa.news.info.vo.admin.live.LiveSpecialInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("liveSpecial")
public class LiveSpecialController extends BaseController<LiveSpecialBiz,LiveSpecial> {


    @Autowired
    private  LiveSpecialBiz liveSpecialBiz;


    //直播专题的分页显示
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<LiveSpecialInfo> getPage(@RequestParam Integer page,
                                                        @RequestParam Integer limit) {
        //查询列表数据
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return liveSpecialBiz.getPage(query);
    }



    //直播专题的按标题搜索
    @RequestMapping(value = "/getSearchPage",method =RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<LiveSpecialInfo> getSearchPage(@RequestParam Integer page,
                                                              @RequestParam Integer limit,
                                                              @RequestParam String title){
        //根据标题搜索
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return liveSpecialBiz.searchPage(query,title);
    }

}