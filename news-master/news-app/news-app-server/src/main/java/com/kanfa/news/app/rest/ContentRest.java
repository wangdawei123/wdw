package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.ContentBiz;
import com.kanfa.news.app.biz.LiveBiz;
import com.kanfa.news.app.vo.admin.video.VideoContentInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("content")
public class ContentRest extends BaseRest {

    private final static Integer VIDEO = 1;
    private final static Integer LIVE = 2;

    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private LiveBiz liveBiz;

    /**
     * 更新播放量接口
     *
     * @param id      文章、视频或直播id
     * @param type    1为视频 2为直播
     * @param fromApp app端添加此参数：1
     * @return
     */
    @RequestMapping("videoView")
    public ObjectRestResponse videoView(@RequestParam("id") Integer id,
                                        @RequestParam("type") Integer type,
                                        @RequestParam("fromApp") Integer fromApp) {
        if (type.equals(VIDEO)) {
            contentBiz.updateVideoViews(id, fromApp);
            ObjectRestResponse<VideoContentInfo> response = new ObjectRestResponse<>();
            return new ObjectRestResponse<>();
        }else{
            liveBiz.updatePlayViews(id, fromApp);
            ObjectRestResponse<VideoContentInfo> response = new ObjectRestResponse<>();
            return new ObjectRestResponse<>();
        }
    }
}
