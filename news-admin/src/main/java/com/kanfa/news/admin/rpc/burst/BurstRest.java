package com.kanfa.news.admin.rpc.burst;

import com.kanfa.news.admin.entity.Burst;
import com.kanfa.news.admin.entity.BurstResource;
import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.entity.burst.BurstType;
import com.kanfa.news.admin.feign.IChannelServiceFeign;
import com.kanfa.news.admin.feign.burst.IBurstServiceFeign;
import com.kanfa.news.admin.vo.burst.BurstInfo;
import com.kanfa.news.admin.vo.burst.BurstResourceInfo;
import com.kanfa.news.admin.vo.burst.BurstUpdateInfo;
import com.kanfa.news.admin.vo.channel.ChannelAssociateContent;
import com.kanfa.news.admin.vo.channel.ChannelInfo;
import com.kanfa.news.admin.vo.channel.ObjectInfo;
import com.kanfa.news.common.constant.news.BurstEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/2/27 13:46
 */
@RestController
@RequestMapping("burst")
public class BurstRest extends BaseRPC {
    @Autowired
    private IBurstServiceFeign burstServiceFeign;

    /**
     * 列表参数：分页信息，手机号或id，受理状态
     *
     * @return
     */
    @RequestMapping(value = "/burstTypeList", method = RequestMethod.GET)
    public TableResultResponse<BurstInfo> burstTypeList(@RequestParam Map<String, Object> params) {
        return burstServiceFeign.burstPage(params);
    }

    /**
     * 获取爆料详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<BurstInfo> burstTypeList(@PathVariable("id") Integer id) {
        if (id == null) {
            return getObjectRestResponse("id不能为空");
        }
        return burstServiceFeign.getBurstInfoById(id);
    }

    /**
     * 添加
     *
     * @param burstUpdateInfo
     * @return
     */
    @RequestMapping(value = "/updateBurst", method = RequestMethod.POST)
    public ObjectRestResponse<BurstUpdateInfo> updateBurst(@RequestBody BurstUpdateInfo burstUpdateInfo) {
        if (burstUpdateInfo.getId() == null) {
            return getObjectRestResponse("id不能为空");
        }
        List<String> burstImageList = burstUpdateInfo.getBurstImageList();
        List<BurstResourceInfo> burstVideoList = burstUpdateInfo.getBurstVideoList();
        List<BurstResourceInfo> burstResourceInfoList = new ArrayList<>(burstImageList.size() + burstVideoList.size());
        if (burstImageList != null) {
            for (String url : burstImageList) {
                BurstResourceInfo burstResourceInfo = new BurstResourceInfo();
                burstResourceInfo.setType(NewsEnumsConsts.BurstResourceOfType.Image.key());
                burstResourceInfo.setUrl(url);
                burstResourceInfoList.add(burstResourceInfo);
            }
        }
        if (burstVideoList != null) {
            for (BurstResourceInfo burstResourceInfo : burstVideoList) {
                burstResourceInfo.setType(NewsEnumsConsts.BurstResourceOfType.Video.key());
                burstResourceInfoList.add(burstResourceInfo);
            }
        }
        burstUpdateInfo.setBurstResourceInfoList(burstResourceInfoList);
        burstUpdateInfo.setAdminId(Integer.valueOf(getCurrentUserId()));
        return burstServiceFeign.updateBurst(burstUpdateInfo);
    }

    /**
     * 删除爆料资源
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteResource/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse deleteResource(@PathVariable("id") Integer id) {
        if (id == null) {
            return getObjectRestResponse("id不能为空");
        }
        BurstResource burstResource = new BurstResource();
        burstResource.setId(id);
        burstResource.setUpdateTime(new Date());
        burstResource.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
        return burstServiceFeign.updateBurstResource(id, burstResource);
    }

    /**
     * 拒绝
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/refuse/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse refuseBurst(@PathVariable("id") Integer id) {
        if (id == null) {
            return getObjectRestResponse("id不能为空");
        }
        Burst burst = new Burst();
        burst.setId(id);
        burst.setAdminId(Integer.valueOf(getCurrentUserId()));
        burst.setStatus(NewsEnumsConsts.BurstOfStatus.Refuse.key());
        burst.setUpdateTime(new Date());
        return burstServiceFeign.update(id, burst);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }
}
