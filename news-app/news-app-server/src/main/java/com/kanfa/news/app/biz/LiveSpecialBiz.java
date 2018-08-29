package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.LiveSpecial;
import com.kanfa.news.app.mapper.LiveMapper;
import com.kanfa.news.app.mapper.LiveSpecialMapper;
import com.kanfa.news.app.vo.admin.live.LiveInfo;
import com.kanfa.news.app.vo.admin.live.LiveSpecialInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.common.util.StringToDuration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 直播专题表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 15:20:16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveSpecialBiz extends BaseBiz<LiveSpecialMapper,LiveSpecial> {

    @Autowired
    private LiveSpecialMapper liveSpecialMapper;
    @Autowired
    private LiveMapper liveMapper;

    public TableResultResponse<LiveSpecialInfo> getPage(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<LiveSpecialInfo> list = liveSpecialMapper.getPage();
        return new TableResultResponse<>(result.getTotal(), list);
    }

    public TableResultResponse<LiveSpecialInfo> searchPage(Query query, String title){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<LiveSpecialInfo> searchlist = liveSpecialMapper.getSearchPage(title);
        return new TableResultResponse<>(result.getTotal(), searchlist);
    }

    public Map<String,Object> selectSpecialByType(Integer liveTypId){
        Map<String,Object> map = new HashMap<>(16);
        List<LiveInfo> liveInfos = liveSpecialMapper.selectSpecialByType(liveTypId);
        for(LiveInfo live :liveInfos){
            map.put("id",live.getId());
            map.put("title",live.getTitle());
            map.put("special_type",live.getSpecialType());
        }
        return map;
    }

    /**
     * 精彩回顾
     * @param liveTypId
     * @param limit
     * @return
     */
    public Map<String ,Object> selectSpecial(Integer liveTypId, Integer limit) {
        LiveInfo liveInfo = new LiveInfo();
        liveInfo.setLimit(limit);
        liveInfo.setLiveTypeId(liveTypId);

        Map<String,Object> onlineMap = new HashMap<>(16);
        List<LiveInfo> list = liveSpecialMapper.selectSpecialByliveTypId(liveInfo);
        for(LiveInfo info :list){
            Integer id = info.getId();
            onlineMap.put("title",info.getTitle());
            if(info.getSpecialType().equals(LiveCommonEnum.SSPECIAL_TYPE_REVIEW)){
                onlineMap.put("id",LiveCommonEnum.CONSTANT_TWO);
            }else if(info.getSpecialType().equals(LiveCommonEnum.SSPECIAL_TYPE_LIVE)){
                Integer count = LiveCommonEnum.CONSTANT_ZERO;
                if(liveTypId != null){
                    LiveInfo live = new LiveInfo();
                    live.setLiveTypeId(liveTypId);
                    count = liveMapper.selectCountBy(live);
                }
                onlineMap.put("subtitle","当前"+ count +"直播，进去看看。");
                onlineMap.put("id",LiveCommonEnum.CONSTANT_ONE);
            }

            List<Map<String ,Object>> listMap = new ArrayList<>();
            List<LiveInfo> specialList =  liveMapper.selectSpecial(id);
            for(int i = 0 ; i < specialList.size(); i++){
                Map<String,Object> specialMap = new HashMap<>(16);
                LiveInfo special = specialList.get(i);
                if(special.getLiveStatus().equals(LiveCommonEnum.LIVE_STATUS)){
                    //type值，9为跳转直播，4为视频
                    specialMap.put("type",LiveCommonEnum.TYPE_STATUS_NINE);
                }else{
                    specialMap.put("type",LiveCommonEnum.TYPE_STATUS_FOUR);
                }
                if(i == 0 && info.getSpecialType() != 1 && info.getSpecialType() != 2){
                    specialMap.put("image_type",LiveCommonEnum.IMAGE_TYPE_BIG);
                    specialMap.put("title_type",LiveCommonEnum.SSPECIAL_TYPE_REVIEW);
                    if(special.getLiveDuration() != null){
                        String s = StringToDuration.changeToFormat(special.getLiveDuration());
                        specialMap.put("duration",s);
                    }
                    if(StringUtils.isNotEmpty(special.getFlashObj()) && StringUtils.isNotEmpty(special.getSourceUrl())){
                        specialMap.put("jump_type", LiveCommonEnum.JUMP_TYPE_COURT);
                        specialMap.put("source_url",null);
                    }else{
                        specialMap.put("jump_type",LiveCommonEnum.JUMP_TYPE_LIVE);
                        specialMap.put("flash_obj",null);
                        specialMap.put("source_url",null);
                    }
                    specialMap.put("id",special.getId());
                    specialMap.put("title",special.getTitle());
                    specialMap.put("subtitle",special.getSubtitle());
                    specialMap.put("court_name",special.getCourtName());
                    specialMap.put("start_time",special.getStartTime());
                    specialMap.put("flash_obj",special.getFlashObj());
                    specialMap.put("nickname",special.getNickname());
                    specialMap.put("cover_img",special.getCoverImg());
                    specialMap.put("stat",special.getLiveStatus());
                    specialMap.put("live_type_id",special.getLiveTypeId());
                    continue;
                }
                if(info.getSpecialType().equals(LiveCommonEnum.SSPECIAL_TYPE_REVIEW) || info.getSpecialType().equals(LiveCommonEnum.SSPECIAL_TYPE_LIVE)){
                    specialMap.put("title_type",LiveCommonEnum.TITLE_TYPE_COURT);
                }else{
                    specialMap.put("title_type",LiveCommonEnum.SSPECIAL_TYPE_REVIEW);
                }
                if(special.getLiveDuration() != null){
                    String s = StringToDuration.changeToFormat(special.getLiveDuration());
                    specialMap.put("duration",s);
                }
                if(StringUtils.isNotEmpty(special.getFlashObj()) && StringUtils.isNotEmpty(special.getSourceUrl())){
                    specialMap.put("jump_type",LiveCommonEnum.JUMP_TYPE_COURT);
                }else{
                    //是直播内容
                    specialMap.put("jump_type",LiveCommonEnum.JUMP_TYPE_LIVE);
                }
                specialMap.put("image_type",LiveCommonEnum.IMAGE_TYPE_SMALL);
                specialMap.put("id",special.getId());
                specialMap.put("title",special.getTitle());
                specialMap.put("subtitle",special.getSubtitle());
                specialMap.put("court_name",special.getCourtName());
                specialMap.put("create_time",special.getCreateTime());
                specialMap.put("flash_obj",special.getFlashObj());
                specialMap.put("nickname",special.getNickname());
                specialMap.put("cover_img",special.getCoverImg());
                specialMap.put("stat",special.getLiveStatus());
                specialMap.put("live_type_id",special.getLiveTypeId());
                listMap.add(specialMap);
            }
            onlineMap.put("data",listMap);
        }
        return onlineMap;
    }
}