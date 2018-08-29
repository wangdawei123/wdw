package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.VideoTagBiz;
import com.kanfa.news.info.entity.VideoTag;
import com.kanfa.news.info.vo.admin.video.VideoTagInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("videoTag")
public class VideoTagController extends BaseController<VideoTagBiz,VideoTag> {
    @Autowired
    private  VideoTagBiz videoTagBiz;

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<VideoTagInfo> getPage(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return videoTagBiz.page(query);
    }

    @RequestMapping(value = "/getSearchPage",method =RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<VideoTagInfo> getSearchPage(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam String text){
        //根据标题搜索
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return videoTagBiz.searchPage(query,text);
    }

    /**
     * 根据内容Id查询对应标签
     * @param videoId
     * @return
     */
    @RequestMapping(value = "/videoTag/getTagListByConentId",method = RequestMethod.GET)
    public ObjectRestResponse<List<VideoTag>> getTagListByConentId(@RequestParam("videoId") Integer videoId){
        List<VideoTag> list=videoTagBiz.getTagListByConentId(videoId);
        ObjectRestResponse<List<VideoTag>> restResponse=new ObjectRestResponse<>();
        restResponse.setData(list);
        return restResponse;
    }
}