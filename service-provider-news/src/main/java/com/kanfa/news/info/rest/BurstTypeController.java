package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.BurstTypeBiz;
import com.kanfa.news.info.entity.BurstType;
import com.kanfa.news.info.vo.admin.burst.BurstTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("burstType")
public class BurstTypeController extends BaseController<BurstTypeBiz,BurstType> {

    @Autowired
    private BurstTypeBiz burstTypeBiz;

    @RequestMapping(value = "/burstTypePage",method = RequestMethod.GET)
    public TableResultResponse<BurstTypeInfo> burstTypePage(@RequestParam Map<String, Object> params){
        return burstTypeBiz.burstTypePage(params);
    }
    /**
     * 查询单个
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getBurstType",method = RequestMethod.POST)
    public List<BurstType> getBurstType(@RequestBody BurstType entity){
        entity.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        entity.setId(null);
        return burstTypeBiz.selectList(entity);
    }

    /**
     * 查询类型集合
     * @param burstType
     * @return
     */
    @RequestMapping(value = "/burstTypeListAll",method = RequestMethod.POST)
    public ObjectRestResponse<List<BurstType>> burstTypeListAll(@RequestBody BurstType burstType){
        List<BurstType> burstTypes = burstTypeBiz.burstTypeListAll(burstType);
        ObjectRestResponse<List<BurstType>> objectRestResponse = new ObjectRestResponse<List<BurstType>>();
        objectRestResponse.setData(burstTypes);
        return objectRestResponse;
    }
}