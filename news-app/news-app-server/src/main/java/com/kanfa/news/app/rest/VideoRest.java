package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.VideoAlbumBiz;
import com.kanfa.news.app.biz.app.BroadcastBiz;
import com.kanfa.news.app.feign.IVideoServiceFeign;
import com.kanfa.news.app.vo.video.VideoAlbumAppInfo;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("video")
public class VideoRest extends BaseRest {
    @Autowired
    private IVideoServiceFeign videoServiceFeign;

    @Autowired
    private BroadcastBiz broadcastBiz;

    @Autowired
    private VideoAlbumBiz videoAlbumBiz;

    /**
     * 视频首页接口还是之前的视频tab接口地址：<br>主要添加专辑列表 即：<code>video_album</code> 字段
     *
     * @param chanId 栏目ID
     * @param page   当前页
     * @param pcout  每页条数
     * @return
     */
    @RequestMapping(value = "/getContentList", method = RequestMethod.POST)
    public ObjectRestResponse getContentList(@RequestParam(value = "chanId") Integer chanId,
                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "pcount", defaultValue = "10") Integer pcout) {
        return videoServiceFeign.getContentList(chanId, page, pcout);
    }

    /**
     * 接口还是之前的视频tab接口地址：<br>
     * 添加渠道来源(即：source_channel);<br>
     * 绑定数据(即：bind);<br>
     * 播放量(即：views)<br>
     * 发布日期(即:create_time)
     *
     * @param cid  视频ID
     * @param cate 所属栏目分类
     * @return
     */
    @RequestMapping(value = "/getVideoDetail", method = RequestMethod.POST)
    public ObjectRestResponse getVideoDetail(@RequestParam(value = "cid") Integer cid,
                                             @RequestParam(value = "cate") Integer cate,
                                             @RequestParam(value = "uid",required = false) Integer uid) {
        try{
            return broadcastBiz.getVideoDetail(cate,cid,uid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 视频专辑
     *
     * @param id     专辑ID
     * @param userId 用户ID
     * @return
     */
    @RequestMapping(value = "/getalbum", method = RequestMethod.POST)
    public ObjectRestResponse<VideoAlbumAppInfo> getalbum(@RequestParam(value = "id") Integer id,
                                                          @RequestParam(value = "user_id",defaultValue = "") Integer userId,
                                                          @RequestParam(value = "album_type", defaultValue = "") Integer albumType) {
        this.appObjectResponse.setData(videoAlbumBiz.getAlbum(id, userId, albumType).getData());
        return appObjectResponse;
    }
}
