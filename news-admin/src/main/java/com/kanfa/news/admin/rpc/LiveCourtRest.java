package com.kanfa.news.admin.rpc;

import com.alibaba.druid.util.StringUtils;
import com.kanfa.news.admin.entity.LiveCourt;
import com.kanfa.news.admin.entity.Province;
import com.kanfa.news.admin.feign.ILiveCourtServiceFeign;
import com.kanfa.news.admin.feign.IProvinceServiceFeign;
import com.kanfa.news.admin.vo.live.LiveCourtInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/18 16:05
 */
@RestController
@RequestMapping("liveCourt")
public class LiveCourtRest {

    @Autowired
    private ILiveCourtServiceFeign liveCourtServiceFeign;

    /**
     * 分页显示
     * @param
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public TableResultResponse<LiveCourtInfo> get(@RequestParam Integer page,
                                                  @RequestParam Integer limit) {

        return liveCourtServiceFeign.list(page,limit);
    }

    /**
     * 按名字搜索法院
     * @param
     * @return
     */
    @RequestMapping(value = "/getSearchPage", method = RequestMethod.GET)
    public TableResultResponse<LiveCourtInfo> getSearchPage(@RequestParam Integer page,
                                                             @RequestParam Integer limit,
                                                             @RequestParam String name) {
        return liveCourtServiceFeign.searchList(page, limit, name);
    }


    /**
     * 新增直播法院
     * @param entity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<LiveCourt> add(@RequestBody LiveCourt entity) {
        //初始化LiveCourt的属性
        if (StringUtils.isEmpty(entity.getName())) {
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            objectRestResponse.setRel(true);
            objectRestResponse.setMessage("法院名称不能为空");
            return objectRestResponse;
        }
        if (StringUtils.isEmpty(entity.getAvatar())){
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            objectRestResponse.setRel(true);
            objectRestResponse.setMessage("法院logo不能为空");
            return objectRestResponse;
        }
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveCourtServiceFeign.addLiveCourt(entity);
    }



    //编辑
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<LiveCourt> update(@PathVariable("id") Integer id,@RequestBody LiveCourt entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveCourtServiceFeign.update(id, entity);
    }

    //得到一个直播法院的信息
    @RequestMapping(value = "/selectOne", method = RequestMethod.GET)
    public ObjectRestResponse<LiveCourtInfo> get(@RequestParam Integer id) {
        LiveCourtInfo liveCourtInfo = liveCourtServiceFeign.selectOne(id);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(liveCourtInfo);
        return objectRestResponse;
    }




}
