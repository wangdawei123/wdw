package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Demand;
import com.kanfa.news.admin.entity.VideoType;
import com.kanfa.news.admin.feign.IDemandServiceFeign;
import com.kanfa.news.admin.feign.IVideoDemandServiceFeign;
import com.kanfa.news.admin.vo.my.DemandInfo;
import com.kanfa.news.admin.vo.my.MyDemandPageInfo;
import com.kanfa.news.admin.vo.video.VideoDemandInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/15 14:19
 */

@RestController
@RequestMapping("demand")
public class DemandRest {

    @Autowired
    private IVideoDemandServiceFeign videoDemandServiceFeign;
    @Autowired
    private IDemandServiceFeign demandServiceFeign;

    /**
     * 分页查询视频库
     * @param page,limit
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<VideoDemandInfo> get(@RequestParam Integer page,
                                                    @RequestParam Integer limit) {
        return videoDemandServiceFeign.getPage(page, limit);
    }

    /**
     * 按标题搜索
     * @param page,limit,title
     * @return
     */
    @RequestMapping(value = "/getSearchPage", method = RequestMethod.GET)
    public TableResultResponse<VideoDemandInfo> getSearchPage(@RequestParam Integer page,
                                                             @RequestParam Integer limit,
                                                             @RequestParam String title) {
        return videoDemandServiceFeign.getSearchPage(page, limit, title);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<Demand> remove(@PathVariable("id") int id) {
        return videoDemandServiceFeign.remove(id);
    }

    /**
     * 我的视频库 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getMyDemandPage",method = RequestMethod.POST)
    public TableResultResponse<MyDemandPageInfo> getMyDemandPage(@RequestBody MyDemandPageInfo entity){
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return  videoDemandServiceFeign.getMyDemandPage(entity);
    }


    /**
     * 上传视频
     * @param
     * @return
     */
    @RequestMapping(value = "/insertDemand",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Integer>> insertDemand(@RequestBody DemandInfo demandInfo){
        demandInfo.setCreateTime(new Date());
        demandInfo.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        Integer integer = demandServiceFeign.insertDemand(demandInfo);
        ObjectRestResponse<Map<String,Integer>> stringObjectRestResponse = new ObjectRestResponse<>();
        Map<String, Integer> stringIntegerMap = new HashMap<>(16);
        stringIntegerMap.put("id",integer);
        stringObjectRestResponse.setData(stringIntegerMap);
        return stringObjectRestResponse;
    }


    /**
     * 检查视频是否存在
     * @param
     * @return
     */
    @RequestMapping(value = "/checkedVideo",method = RequestMethod.GET)
    public ObjectRestResponse checkedVideo(@RequestParam("videoMd") String videoMd){
        ObjectRestResponse<Object> objectObjectRestResponse = new ObjectRestResponse<>();
        //获得数据中所有视频的Md值
        List<String> allViedeoMD = demandServiceFeign.getAllViedeoMD();
        if (allViedeoMD.contains(videoMd)){
            objectObjectRestResponse.setStatus(500);
            objectObjectRestResponse.setRel(true);
            objectObjectRestResponse.setMessage("该视频已经上传");
            return  objectObjectRestResponse;
        }else {
            objectObjectRestResponse.setMessage("该视频可以上传");
            objectObjectRestResponse.setStatus(200);
            objectObjectRestResponse.setRel(false);
            return objectObjectRestResponse;
        }
    }

    /**
     * 上传成功
     * @param
     * @return
     */
    @RequestMapping(value = "/uploadSuccess",method = RequestMethod.GET)
    public ObjectRestResponse uploadSuccess(@RequestParam("id")Integer id){
        ObjectRestResponse<Demand> demandObjectRestResponse = demandServiceFeign.get(id);
        Demand data = demandObjectRestResponse.getData();
        //1上传失败 2上传成功 3 正在转码 4 转码成功
        data.setStatus("2");
        return demandServiceFeign.update(id,data);
    }


    /**
     * 上传失败
     * @param
     * @return
     */
    @RequestMapping(value = "/uploadFailure",method = RequestMethod.GET)
    public ObjectRestResponse uploadFailure(@RequestParam("id")Integer id){
        ObjectRestResponse<Demand> demandObjectRestResponse = demandServiceFeign.get(id);
        Demand data = demandObjectRestResponse.getData();
        //1上传失败 2上传成功 3 正在转码 4 转码成功
        data.setStatus("1");
        return demandServiceFeign.update(id,data);
    }


}
