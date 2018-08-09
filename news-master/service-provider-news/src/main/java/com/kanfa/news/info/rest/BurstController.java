package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.BurstBiz;
import com.kanfa.news.info.biz.BurstResourceBiz;
import com.kanfa.news.info.biz.ContentBurstBiz;
import com.kanfa.news.info.entity.Burst;
import com.kanfa.news.info.entity.BurstResource;
import com.kanfa.news.info.entity.ContentBurst;
import com.kanfa.news.info.vo.admin.burst.BurstInfo;
import com.kanfa.news.info.vo.admin.burst.BurstResourceInfo;
import com.kanfa.news.info.vo.admin.burst.BurstUpdateInfo;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("burst")
public class BurstController extends BaseController<BurstBiz,Burst> {

    @Autowired
    private BurstBiz burstBiz;
    @Autowired
    private BurstResourceBiz burstResourceBiz;
    @Autowired
    private ContentBurstBiz contentBurstBiz;

    /**
     * 爆料分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/burstPage",method = RequestMethod.GET)
    public TableResultResponse<BurstInfo> burstPage(@RequestParam Map<String, Object> params){
        return  burstBiz.burstPage(params);
    }

    /**
     * 获取爆料信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getBurstInfoById",method = RequestMethod.GET)
    ObjectRestResponse<BurstInfo> getBurstInfoById(@RequestParam("id") Integer id){
        BurstInfo burstInfo = burstBiz.getBurstInfoById(id);
        //基本信息，资源信息：图片视频，关联内容
        if(burstInfo!=null){
            //图片视频
            BurstResource entity=new BurstResource();
            entity.setBurstId(burstInfo.getId());
            entity.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
            entity.setUploadStatus(NewsEnumsConsts.BurstResourceOfUploadStatus.Success.key());
            List<BurstResource> burstResources = burstResourceBiz.selectList(entity);
            //资源信息
            int size = burstResources.size();
            List<BurstResourceInfo> listRsrContent=new ArrayList<>(size);
            List<String> burstImageList = new ArrayList<>(size);
            List<BurstResourceInfo> burstVideoList = new ArrayList<>(size);
            for (BurstResource burstResource : burstResources) {
                BurstResourceInfo burstResourceInfo=new BurstResourceInfo();
                burstResourceInfo.setId(burstResource.getId());
                burstResourceInfo.setType(burstResource.getType());
                burstResourceInfo.setUrl(burstResource.getUrl());
                burstResourceInfo.setTitle(burstResource.getTitle());
                if(burstResource.getBurstSource().equals(NewsEnumsConsts.BurstResourceOfBurstSource.User.key())){
                    listRsrContent.add(burstResourceInfo);
                }else if(burstResource.getBurstSource().equals(NewsEnumsConsts.BurstResourceOfBurstSource.CorpUser.key())){
                    if(burstResource.getType().equals(NewsEnumsConsts.BurstResourceOfType.Image.key())){
                        burstImageList.add(burstResource.getUrl());
                    }else if (burstResource.getType().equals(NewsEnumsConsts.BurstResourceOfType.Video.key())){
                        burstVideoList.add(burstResourceInfo);
                    }
                }
            }
            burstInfo.setListRsrContent(listRsrContent);
            burstInfo.setBurstImageList(burstImageList);
            burstInfo.setBurstVideoList(burstVideoList);
            //内容信息
            ContentBurst contentBurst=new ContentBurst();
            contentBurst.setBurstId(burstInfo.getId());
            contentBurst.setStatus(1);
            List<ContentInfo> contentBursts = contentBurstBiz.selectContentByBurst(contentBurst);
            burstInfo.setBindContent(contentBursts);
            ObjectRestResponse<BurstInfo> burstInfoObjectRestResponse = new ObjectRestResponse<>();
            burstInfoObjectRestResponse.setData(burstInfo);
            return burstInfoObjectRestResponse;
        }
        return new ObjectRestResponse<BurstInfo>();
    }

    /**
     * 更新爆料
     * @param burstUpdateInfo
     * @return
     */
    @RequestMapping(value = "/updateBurst",method = RequestMethod.POST)
    public ObjectRestResponse<BurstUpdateInfo> updateBurst(@RequestBody BurstUpdateInfo burstUpdateInfo){
        ObjectRestResponse<BurstUpdateInfo> objectRestResponse=new ObjectRestResponse<>();
        BurstUpdateInfo burstUpdateInfo1 = burstBiz.updateBurst(burstUpdateInfo);
        if(burstUpdateInfo1==null){
            objectRestResponse.setRel(true);
            objectRestResponse.setMessage("更新失败");
            objectRestResponse.setStatus(506);
            return objectRestResponse;
        }
        objectRestResponse.setData(burstUpdateInfo1);
        return objectRestResponse;
    }
}