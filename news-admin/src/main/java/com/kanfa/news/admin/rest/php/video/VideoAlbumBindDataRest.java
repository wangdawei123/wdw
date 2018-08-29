package com.kanfa.news.admin.rest.php.video;

import com.kanfa.news.admin.entity.VideoAlbumBind;
import com.kanfa.news.admin.feign.IVideoAlbumBindServiceFeign;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.data.client.feign.IvideoAlbumBindDataServiceFeign;
import com.kanfa.news.data.common.vo.video.XmVideoAlbumBind;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chen
 * on 2018/8/8 16:02
 */

@RestController
@RequestMapping("/xmVideoAlbumBind")
public class VideoAlbumBindDataRest {

    @Autowired
    private IvideoAlbumBindDataServiceFeign ivideoAlbumBindDataServiceFeign;
    @Autowired
    private IVideoAlbumBindServiceFeign iVideoAlbumBindServiceFeign;

    @RequestMapping(value = "addbind",method = RequestMethod.GET)
    public ObjectRestResponse addBind(@RequestParam("id")Integer id){
        List<VideoAlbumBind> allBind = iVideoAlbumBindServiceFeign.getAllBind(id);
        for (VideoAlbumBind videoAlbumBind:allBind) {
            iVideoAlbumBindServiceFeign.remove(videoAlbumBind.getVideoAlbumId(),videoAlbumBind.getVideoId());
        }
        List<XmVideoAlbumBind> allBinds = ivideoAlbumBindDataServiceFeign.getAllBinds(id);
        for (XmVideoAlbumBind xmVideoAlbumBind:allBinds) {
            VideoAlbumBind videoAlbumBind = new VideoAlbumBind();
            BeanUtils.copyProperties(xmVideoAlbumBind,videoAlbumBind);
            iVideoAlbumBindServiceFeign.add(videoAlbumBind);
        }
        String msg = "绑定成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;

    }

}
