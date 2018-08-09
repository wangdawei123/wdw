package com.kanfa.news.admin.rest;

import com.kanfa.news.admin.biz.BurstBiz;
import com.kanfa.news.admin.biz.BurstResourceBiz;
import com.kanfa.news.admin.entity.Burst;
import com.kanfa.news.admin.entity.BurstResource;
import com.kanfa.news.common.constant.news.BurstEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("burst-admin")
public class BurstController extends BaseController<BurstBiz, Burst> {
    @Autowired
    private BurstResourceBiz burstResourceBiz;

    @Value("${burst_video_oss.bucket}")
    private String bucket;
    @Value("${burst_video_oss.output}")
    private String output;
    @Value("${burst_video_oss.local}")
    private String local;

    @GetMapping("/ajaxBurstVideo")
    public ObjectRestResponse ajaxBurstVideo(@RequestParam Integer id, @RequestParam String contentmd5) {
        Assert.isTrue(id == null, BurstEnum.BURST_ID_NOT_NULL.value());
        Assert.hasText(contentmd5, BurstEnum.MD5_EEROR.value());

        BurstResource burstResource = new BurstResource();
        burstResource.setVideomd(contentmd5);
        Assert.isTrue(burstResourceBiz.selectOne(burstResource) == null, BurstEnum.VIDEO_EXISTENCE.value());
        return new ObjectRestResponse();
    }

    @GetMapping("/ajaxSaveVideo")
    public ObjectRestResponse ajaxBurstVideo(@RequestParam Integer id, @RequestParam String name,
                                             @RequestParam String title, @RequestParam String videomd) {
        Assert.isTrue(id == null, BurstEnum.BURST_ID_NOT_NULL.value());
        String url = "http://%s.oss-cn-%s.aliyuncs.com/%s%s";

        BurstResource burstResource = new BurstResource();
        burstResource.setBurstId(id);
        burstResource.setType(2);
        burstResource.setTitle(title);
        burstResource.setUrl(String.format(url, bucket, local, output, name));
        burstResource.setBurstSource(2);
        burstResource.setVideomd(videomd);
        burstResource.setCreateTime(new Date());
        this.burstResourceBiz.insertSelective(burstResource);
        Assert.isTrue(burstResource.getId() == null, BurstEnum.BURST_ID_NOT_NULL.value());
        ObjectRestResponse objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(burstResource.getId());
        return objectRestResponse;
    }

    @GetMapping("/ajaxUpdateVideo")
    public ObjectRestResponse ajaxBurstVideo(@RequestParam Integer id) {
        Assert.isTrue(id == null, BurstEnum.BURST_ID_NOT_NULL.value());
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        BurstResource resourceSearch = new BurstResource();
        resourceSearch.setId(id);
        resourceSearch.setUploadStatus(2);
        resourceSearch.setUpdateTime(new Date());
        try {
            this.burstResourceBiz.updateById(resourceSearch);
        } catch (Exception e) {
            objectRestResponse.setMessage("上传失败");
            objectRestResponse.setStatus(500);
            objectRestResponse.setRel(true);
        }
        return objectRestResponse;
    }
}