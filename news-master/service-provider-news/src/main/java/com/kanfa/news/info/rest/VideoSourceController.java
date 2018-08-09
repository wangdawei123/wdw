package com.kanfa.news.info.rest;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.VideoSourceBiz;
import com.kanfa.news.info.entity.VideoSource;
import com.kanfa.news.info.vo.admin.video.VideoSourceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("videoSource")
public class VideoSourceController extends BaseController<VideoSourceBiz,VideoSource> {
    @Autowired
    private VideoSourceBiz videoSourceBiz;

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<VideoSourceInfo> getPage(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return videoSourceBiz.getPage(query);
    }
}