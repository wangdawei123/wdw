package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.VideoTypeBiz;
import com.kanfa.news.info.entity.VideoType;
import com.kanfa.news.info.vo.admin.video.VideoTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("videoType")
public class VideoTypeController extends BaseController<VideoTypeBiz,VideoType> {
   @Autowired VideoTypeBiz videoTypeBiz;

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<VideoTypeInfo> getPage(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return videoTypeBiz.page(query);
    }
}