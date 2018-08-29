package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.VideoAlbumBiz;
import com.kanfa.news.app.biz.VideoTypeBiz;
import com.kanfa.news.app.biz.app.BroadcastBiz;
import com.kanfa.news.app.feign.IVideoServiceFeign;
import com.kanfa.news.app.vo.video.VideoAlbumAppInfo;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("videotype")
public class VideoTypeRest extends BaseRest {
    @Autowired
    private VideoTypeBiz videoTypeBiz;

    /**
     * 新版视频顶部分类导航
     *
     * @param devID
     * @param uid
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse getList(@RequestParam(value = "devID") String devID,
                                      @RequestParam(value = "uid", required = false, defaultValue = "0") Integer uid) {
        try {
            return videoTypeBiz.getList(devID, uid);
        } catch (Exception e) {
            e.printStackTrace();
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 视频分类 保存用户自定义分类
     *
     * @param devID
     * @param uid
     * @param typeIds
     * @param hideIds
     * @return
     */
    @RequestMapping(value = "/updateVideoType", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse updateVideoType(@RequestParam(value = "devID") String devID,
                                              @RequestParam(value = "uid", required = false, defaultValue = "0") Integer uid,
                                              @RequestParam(value = "type_ids") String typeIds,
                                              @RequestParam(value = "hide_type_ids", required = false) String hideIds) {
        try {
            return videoTypeBiz.updateVideoType(devID, uid, typeIds, hideIds);
        } catch (Exception e) {
            e.printStackTrace();
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }
}
