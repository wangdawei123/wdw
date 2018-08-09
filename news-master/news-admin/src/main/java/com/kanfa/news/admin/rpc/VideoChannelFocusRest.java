package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ChannelFocus;
import com.kanfa.news.admin.feign.IChannelFocusServiceFeign;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/13 17:50
 */

@RestController
@RequestMapping("/videoChannelFocus")
public class VideoChannelFocusRest {

    @Autowired
    private IChannelFocusServiceFeign channelFocusServiceFeign;


    /**
     *  视频管理 焦点图 新增
     * @param entity
     * @return
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<ChannelFocus> add(@RequestBody ChannelFocus entity) {
        //初始化视频焦点图属性值
        entity.setCreateTime(new Date());
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.NOTPUBLISH.key());
        entity.setType(2);
        ObjectRestResponse<ChannelFocus> add = channelFocusServiceFeign.add(entity);
        add.setData(entity);
        return add;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ObjectRestResponse<ChannelFocus> update(@RequestBody ChannelFocus entity) {
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        entity.setType(2);
        return channelFocusServiceFeign.update(entity.getId(),entity);
    }

}
