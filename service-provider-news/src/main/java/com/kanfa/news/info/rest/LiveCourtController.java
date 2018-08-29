package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.LiveCourtBiz;
import com.kanfa.news.info.biz.ProvinceBiz;
import com.kanfa.news.info.entity.LiveCourt;
import com.kanfa.news.info.entity.Province;
import com.kanfa.news.info.vo.admin.live.LiveCourtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("liveCourt")
public class LiveCourtController extends BaseController<LiveCourtBiz,LiveCourt> {

    @Autowired
    private LiveCourtBiz liveCourtBiz;
    @Autowired
    private ProvinceBiz provinceBiz;

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<LiveCourtInfo> getPage(@RequestParam Map<String, Object> params) {
        //查询视频专辑列表数据
        Query query = new Query(params);
        return liveCourtBiz.page(query);
    }

    @RequestMapping(value = "/getSearchPage",method =RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<LiveCourtInfo> getSearchPage(@RequestParam Integer page,
                                                            @RequestParam Integer limit,
                                                            @RequestParam String name){
        //根据标题搜索
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return liveCourtBiz.getSearchPage(query,name);
    }


    @RequestMapping(value = "/selectOne",method =RequestMethod.GET )
    @ResponseBody
    public LiveCourtInfo selectOne(@RequestParam Integer id){
        return liveCourtBiz.selectOne(id);
    }


    @RequestMapping(value = "/addLiveCourt",method =RequestMethod.POST )
    @ResponseBody
    public ObjectRestResponse addLiveCourt(@RequestBody LiveCourt entity){
        // 状态 1:正常;0:已删除
        entity.setStat(1);
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        //传省份Id获取对应的名字
        Province province = provinceBiz.selectById(entity.getProvinceId());
        entity.setProvinceName(province.getName());
        liveCourtBiz.insertSelective(entity);
        ObjectRestResponse<Object> objectObjectRestResponse = new ObjectRestResponse<>();
        objectObjectRestResponse.setStatus(200);
        objectObjectRestResponse.setRel(false);
        return objectObjectRestResponse;
    }
}