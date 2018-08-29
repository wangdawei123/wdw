package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.VideoType;
import com.kanfa.news.app.entity.VideoTypeCustom;
import com.kanfa.news.app.mapper.VideoTypeCustomMapper;
import com.kanfa.news.app.mapper.VideoTypeMapper;
import com.kanfa.news.app.vo.admin.video.VideoTypeInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VideoTypeBiz extends BaseBiz<VideoTypeMapper, VideoType> {
    @Autowired
    private VideoTypeMapper videoTypeMapper;

    @Autowired
    private VideoTypeCustomMapper videoTypeCustomMapper;

    public TableResultResponse<VideoTypeInfo> page(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoTypeInfo> list = videoTypeMapper.getPage();
        return new TableResultResponse<VideoTypeInfo>(result.getTotal(), list);
    }

    /**
     * 新版视频顶部分类导航查询
     *
     * @param devID
     * @param uid
     * @return
     */
    public ObjectRestResponse<Map<String, Object>> getList(String devID, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        Map result =new HashMap();
        List resultList=new ArrayList();
        VideoType videoType = new VideoType();
        videoType.setIsDelete(1);
        videoType.setIsPublish(1);
        //查询全部视频分类
        List<VideoType> allType = videoTypeMapper.select(videoType);
        if (allType == null || allType.size() == 0) {
            channelObjectRestResponse.setStatus(AppCommonMessageEnum.APP_NO_DATA.key());
            channelObjectRestResponse.setMessage(AppCommonMessageEnum.APP_NO_DATA.value());
            return channelObjectRestResponse;
        }
        //全部视频分类的id
        List<String> allIds = new ArrayList<>();
        for (VideoType data : allType) {
            allIds.add(data.getId().toString());
            Map videoTypeResult =new HashMap();
            videoTypeResult.put("id",data.getId());
            videoTypeResult.put("name",data.getName());
            videoTypeResult.put("is_default",0);
            videoTypeResult.put("top",false);
            resultList.add(videoTypeResult);
        }
        List<VideoTypeCustom> videoTypeCustomsList = null;
        if (uid != 0) {
            VideoTypeCustom videoTypeCustom = new VideoTypeCustom();
            videoTypeCustom.setUid(uid);
            videoTypeCustomsList = videoTypeCustomMapper.select(videoTypeCustom);
        } else if (devID != null) {
            VideoTypeCustom videoTypeCustom = new VideoTypeCustom();
            videoTypeCustom.setDevId(devID);
            videoTypeCustomsList = videoTypeCustomMapper.select(videoTypeCustom);
        }
        if (videoTypeCustomsList != null && videoTypeCustomsList.size() > 0) {
            videoTypeShowAndHide(videoTypeCustomsList,allType,allIds);
            channelObjectRestResponse.setData(result);
        }else{
            Map<String,Object> tuijian =new HashMap<>();
            tuijian.put("id","99999");
            tuijian.put("name","推荐");
            tuijian.put("is_default",1);
            tuijian.put("top",true);
            resultList.add(tuijian);
            result.put("show",resultList);
            channelObjectRestResponse.setData(result);
        }
        return channelObjectRestResponse;
    }

    /**
     * 整理用户的自定义视频分类
     * @param videoTypeCustomsList
     * @param allType
     * @param allIds
     * @return
     */
    public Map videoTypeShowAndHide(List<VideoTypeCustom> videoTypeCustomsList,List<VideoType> allType,List<String> allIds){
        Map<String,Object> result =new HashMap<String,Object>();
        List<Map> resultList=new ArrayList<Map>();
        List<Map> resultHideList=new ArrayList<Map>();
        //用户自定义的视频分类     showIds：视频分类ID      hideIds： 隐藏视频分类ID
        List<String> showIds = new ArrayList<>();
        List<String> hideIds = new ArrayList<>();
        //新增的频道
        List<String> newIds = new ArrayList<>();
        List<String> customIds = new ArrayList<>();
        if(videoTypeCustomsList.get(0).getTypeIds()!=null){
            showIds.addAll(Arrays.asList(videoTypeCustomsList.get(0).getTypeIds().split(",")));
            customIds.addAll(showIds);
        }
        if(videoTypeCustomsList.get(0).getHideTypeIds()!=null){
            hideIds.addAll(Arrays.asList(videoTypeCustomsList.get(0).getHideTypeIds().split(",")));
            customIds.addAll(hideIds);
        }
        //customIds 去重
        Set<String> customIdsSet =new HashSet(customIds);
        List<String> customIds2 = new ArrayList<>(customIdsSet);
        for(String data:allIds){
            if(!customIds2.contains(data)){
                newIds.add(data);
            }
        }
        //取交集 ，踢出下架的视频分类 类别
        showIds.retainAll(allIds);
        hideIds.retainAll(allIds);
        //封装返回数据
        resultList.addAll(videoTypeReturnMsg(showIds,allType));
        resultList.addAll(videoTypeReturnMsg(newIds,allType));
        Map<String,Object> tuijian =new HashMap<>();
        tuijian.put("id","99999");
        tuijian.put("name","推荐");
        tuijian.put("is_default",1);
        tuijian.put("top",true);
        resultList.add(tuijian);
        resultHideList.addAll(videoTypeReturnMsg(hideIds,allType));
        result.put("show",resultList);
        result.put("hide",resultHideList);
        return result;
    }

    /**
     * 封装返回数据
     * @param list
     * @param allType
     * @return
     */
    public List<Map>  videoTypeReturnMsg(List<String> list,List<VideoType> allType){
        List<Map> resultList=new ArrayList<Map>();
        for(String id:list){
            Map<String,Object> showMap=new HashMap<String,Object>();
            showMap.put("id",id);
            for(VideoType type:allType){
                if(type.getId().equals(id)){
                    showMap.put("name",type.getName());
                }
            }
            showMap.put("is_default",0);
            showMap.put("top",false);
            resultList.add(showMap);
        }
        return resultList;
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
    public ObjectRestResponse<Map<String, Object>> updateVideoType(String devID, Integer uid, String typeIds, String hideIds) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        List<VideoTypeCustom> videoTypeCustomsList = null;
        if (uid != 0) {
            VideoTypeCustom videoTypeCustom = new VideoTypeCustom();
            videoTypeCustom.setUid(uid);
            videoTypeCustomsList = videoTypeCustomMapper.select(videoTypeCustom);
        } else if (devID != null) {
            VideoTypeCustom videoTypeCustom = new VideoTypeCustom();
            videoTypeCustom.setDevId(devID);
            videoTypeCustomsList = videoTypeCustomMapper.select(videoTypeCustom);
        }else{
            channelObjectRestResponse.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
            channelObjectRestResponse.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            return channelObjectRestResponse;
        }
        if(videoTypeCustomsList!=null && videoTypeCustomsList.size()>0){
            VideoTypeCustom videoTypeCustom = videoTypeCustomsList.get(0);
            videoTypeCustom.setTypeIds(typeIds);
            videoTypeCustom.setHideTypeIds(hideIds);
            videoTypeCustom.setUpdateTime(new Date());
            Example example = new Example(VideoTypeCustom.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("uid",videoTypeCustom.getUid());
            criteria.andCondition("dev_id",videoTypeCustom.getDevId());
            videoTypeCustomMapper.updateByExample(videoTypeCustom,example);
        }else{
            VideoTypeCustom videoTypeCustom=new VideoTypeCustom();
            videoTypeCustom.setUid(uid);
            videoTypeCustom.setDevId(devID);
            videoTypeCustom.setTypeIds(typeIds);
            videoTypeCustom.setHideTypeIds(hideIds);
            videoTypeCustom.setCreateTime(new Date());
            videoTypeCustomMapper.insert(videoTypeCustom);
        }
        return channelObjectRestResponse;
    }
}