package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.DemandBiz;
import com.kanfa.news.info.entity.Demand;
import com.kanfa.news.info.vo.admin.my.DemandInfo;
import com.kanfa.news.info.vo.admin.my.MyDemandPageInfo;
import com.kanfa.news.info.vo.admin.video.VideoDemandInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("demand")
public class DemandController extends BaseController<DemandBiz,Demand> {

    @Autowired
    private DemandBiz demandBiz;


    //视频库的分页显示
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<VideoDemandInfo> getPage(@RequestParam Integer page,
                                                        @RequestParam Integer limit) {
        //查询列表数据
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return demandBiz.getPage(query);
    }


    @RequestMapping(value = "/getSearchPage",method =RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<VideoDemandInfo> getSearchPage(@RequestParam Integer page,
                                                              @RequestParam Integer limit,
                                                              @RequestParam String title){
        //根据标题搜索
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return demandBiz.searchPage(query,title);
    }

    @RequestMapping(value = "/getMyDemandPage",method = RequestMethod.POST)
    public TableResultResponse<MyDemandPageInfo> getMyDemandPage(@RequestBody MyDemandPageInfo entity ){
        return  demandBiz.getMyDemandPage(entity);
    }


    @RequestMapping(value = "/insertDemand",method = RequestMethod.POST)
    public Integer insertDemand(@RequestBody DemandInfo demandInfo ){
       return demandBiz.insertDemand(demandInfo);
    }

    @RequestMapping(value = "/getAllVideoMD",method = RequestMethod.GET)
    public List<String> getAllVideoMd(){
        return  demandBiz.getAllVideoMD();
    }


    @RequestMapping(value = "/getDemandId",method = RequestMethod.GET)
    public Demand getDemandId(@RequestParam("name")String name){
        Demand demand = new Demand();
        demand.setName(name);
        return demandBiz.selectOne(demand);
    }

    @RequestMapping(value = "getDemandByMedid",method = RequestMethod.GET)
    public Demand getDemandByMedid(@RequestParam("medid")String medid){
        Demand demand = new Demand();
        demand.setMedid(medid);
        return demandBiz.selectOne(demand);
    }

}