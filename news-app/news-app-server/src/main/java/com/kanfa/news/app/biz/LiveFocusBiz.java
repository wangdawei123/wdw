package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.LiveFocus;
import com.kanfa.news.app.mapper.LiveFocusMapper;
import com.kanfa.news.app.vo.admin.live.LiveFocusInfo;
import com.kanfa.news.app.vo.admin.live.LiveInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 直播焦点图表
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-24 11:23:12
 * @date 2018-03-26 18:07:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveFocusBiz extends BaseBiz<LiveFocusMapper,LiveFocus> {

    @Autowired
    private LiveFocusMapper liveFocusMapper;

    /**
     * 分页查询直播焦点图
     * @param entity
     * @return
     */
    public TableResultResponse<LiveFocusInfo> getPage(LiveFocusInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置晒出条件
        //状态(0:已删除;1:正常)'
        entity.setStat(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        //contentState  '状态，1：正常，0：删除'
        List<LiveFocusInfo> list = liveFocusMapper.getPage(entity);
        return new TableResultResponse<>(result.getTotal(), list);
    }

     /** 轮播图分页查找
     * @param liveInfo
     * @return
     */
    public List<Map<String ,Object>> selectFocus(LiveInfo liveInfo) {
        List<Map<String ,Object>> listMap = new ArrayList<>();
        List<LiveInfo> list = liveFocusMapper.selectFocus(liveInfo);
        if(list.size() > LiveCommonEnum.CONSTANT_ZERO){
            for(LiveInfo live : list){
                Map<String,Object> focusMap = new HashMap<>(10);
                if("".equals(live.getFlashObj()) && "".equals(live.getSourceUrl())){
                    focusMap.put("jump_type", LiveCommonEnum.FOCUS_JUMP_TYPE_H5);
                }else{
                    focusMap.put("jump_type",LiveCommonEnum.FOCUS_JUMP_TYPE_APP);
                }
                focusMap.put("image_type",LiveCommonEnum.IMAGE_TYPE_BIG);
                focusMap.put("flash_obj",live.getFlashObj());
                focusMap.put("title",live.getTitle());
                focusMap.put("image",live.getImage());
                focusMap.put("url",live.getUrl());
                focusMap.put("jump",live.getJump());
                focusMap.put("live_id",live.getLiveId());
                focusMap.put("live_type_id",live.getLiveTypeId());
                listMap.add(focusMap);
            }
        }
        return listMap;
    }

}