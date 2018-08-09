package com.kanfa.news.info.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.util.StringToDuration;
import com.kanfa.news.info.biz.app.AppLiveBroadcastBiz;
import com.kanfa.news.info.entity.Content;
import com.kanfa.news.info.entity.ContentBroadcast;
import com.kanfa.news.info.entity.Fav;
import com.kanfa.news.info.entity.Live;
import com.kanfa.news.info.mapper.ContentBroadcastMapper;
import com.kanfa.news.info.mapper.ContentMapper;
import com.kanfa.news.info.mapper.FavMapper;
import com.kanfa.news.info.mapper.LiveMapper;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


/**
 * 用户对内容的收藏
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FavBiz extends BaseBiz<FavMapper,Fav> {
    @Autowired
    private FavMapper favMapper;
    @Autowired
    private  LiveBiz liveBiz;
    @Autowired
    private LiveMapper liveMapper;
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private AppLiveBroadcastBiz appLiveBroadcastBiz;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentBroadcastMapper contentBroadcastMapper;

    /**
     * 获取我的收藏
     * @return
     */
    public List<Map<String, Object>> getListType(Integer page ,Integer pcount ,Integer uid ,Integer type){
        Map<String ,Object> liveInfo = new HashMap<>(5);
        liveInfo.put("offset",(page-1)*pcount);
        liveInfo.put("pcount", pcount);
        liveInfo.put("uid", uid);
        liveInfo.put("type", type);
        List<Fav> list = favMapper.getListByUid(liveInfo);
        if(list.size() == LiveCommonEnum.CONSTANT_ZERO){
            return null;
        }
        List<Integer> ids = new ArrayList<>();
        for(Fav f : list){
            ids.add(f.getCid());
        }
        List<ContentInfo> contentInfos = contentMapper.selectByIds(ids);
        List<Map<String, Object>> mapList = contentBiz.getExtendsMyarticle(contentInfos);
        return mapList;
    }


    public List<Map<String ,Object>> getListMultType(Integer page ,Integer pcount ,Integer uid ,Integer[] types){
        List<Map<String ,Object>> newlist = new ArrayList<>();
        Map<String ,Object> liveInfo = new HashMap<>(5);
        liveInfo.put("offset",(page-LiveCommonEnum.CONSTANT_ONE)*pcount);
        liveInfo.put("pcount", pcount);
        liveInfo.put("uid", uid);
        liveInfo.put("types", types);

        List<Fav> list = favMapper.getListByUid(liveInfo);
        if(list.size() == LiveCommonEnum.CONSTANT_ZERO){
            return null;
        }
        for(Fav fav :list){
            Map<String ,Object> data = new HashMap<>(16);
            if(fav.getType().equals(LiveCommonEnum.CONTENT_BROADCAST)){
                Content content = contentBiz.selectById(fav.getCid());
                data.put("id",content.getId());
                data.put("type",content.getContentType());
                data.put("title",content.getTitle());
                data.put("desc",content.getIntroduction());
                data.put("cate",content.getCategory());
                data.put("views",content.getViewCount());
                data.put("image",content.getImage());
                ContentBroadcast contentBroadcast = contentBroadcastMapper.selectByCid(content.getId());
                if(contentBroadcast != null){
                }else{
                    contentBroadcast.setBroadcastStatus(LiveCommonEnum.LIVE_STATUS);
                }
                data.put("broad_status",contentBroadcast.getBroadcastStatus());
                data.put("duration",contentBroadcast.getReplayDuration());
                if(content.getImage()== null){
                    data.put("image","http://kanfaimage.oss-cn-beijing.aliyuncs.com/5.png");
                }
            }else{
                Live live = liveMapper.selectByPrimaryKey(fav.getCid());
                data.put("id",live.getId());
                data.put("type",LiveCommonEnum.CONTENT_LIVE);
                data.put("title",live.getTitle());
                data.put("desc",live.getTitle());
                data.put("cate",LiveCommonEnum.CATE_APP_PORTAL);
                data.put("views",live.getViewCount());
                data.put("image",live.getCoverImg());
                data.put("broad_status",live.getLiveStatus()+LiveCommonEnum.CONSTANT_ONE);
                data.put("live_type_id",live.getLiveTypeId());
                if(live.getLiveStatus().equals(LiveCommonEnum.LIVE_TYPE_COURT)){
                    data.put("duration", StringToDuration.changeToFormat(live.getLiveDuration()));
                }else{
                    data.put("duration", "");
                }
            }
            data.put("tags","");
            data.put("card_type",LiveCommonEnum.CONTENT_LOCAL_LIFE);
            data.put("share_url","#");
            newlist.add(data);
        }
        return newlist;
    }

    public Integer delFav(Integer cid , Integer uid ,Integer type){
        Integer isLive = LiveCommonEnum.CONSTANT_ONE;
        if(!type.equals(LiveCommonEnum.CONTENT_LIVE) && !type.equals(LiveCommonEnum.CONTENT_LIVE_COURT)) {
            Content content = contentBiz.selectById(cid);
            if(content == null){
                return LiveCommonEnum.CONSTANT_TWO_FU;
            }
            if(content.getContentType().equals(LiveCommonEnum.CONTENT_ACTIVITY)){
                content.setContentType(LiveCommonEnum.CONTENT_NEWS);
            }
            type = content.getContentType();
            isLive = LiveCommonEnum.CONSTANT_ZERO;
        }
        Integer i = favMapper.delFav(cid,uid ,type);
        if(i.equals(LiveCommonEnum.CONSTANT_ONE)){
            if (isLive.equals(LiveCommonEnum.CONSTANT_ZERO)) {
                contentBiz.updateFavs(cid ,type);
            }else{
                liveBiz.updateFavs(cid ,type);
            }
        }
        return i;
    }


    public Content selectFaved(Integer cid, Integer uid, @RequestParam(defaultValue = "0") Integer type) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(LiveCommonEnum.CONTENT_LIVE);
        list.add(LiveCommonEnum.CONTENT_LIVE_COURT);
        if (!list.contains(type)) {
            Content content = contentBiz.selectById(cid);
            if (content == null) {
                return null;
            }
            if (content.getContentType() == 13) {
                content.setContentType(2);
            }
            type = content.getContentType();
        }
        Content content = new Content();
        content.setId(cid);
        content.setCreateUid(Long.parseLong(uid + ""));
        content.setContentType(type);
        Content cont = contentBiz.selectOne(content);
        if (cont == null) {
            return null;
        } else {
            return cont;
        }
    }

    /**
     * 添加收藏  如果重复收藏依然返回成功
     * @param uid
     * @param cid
     * @param type
     * @return
     */
    public boolean add(Integer uid, Integer cid, Integer type) {
        boolean isLive = false;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(LiveCommonEnum.CONTENT_LIVE);
        list.add(LiveCommonEnum.CONTENT_LIVE_COURT);
        Content content = null;
        if (list.contains(type)) {
            content = new Content();
            content.setId(cid);
            content.setCreateUid(Long.parseLong(uid + ""));
            content.setContentType(type);
            Content cont = contentBiz.selectOne(content);
            Live live = liveBiz.selectById(cid);
            //如果重复收藏依然返回成功
            if (live == null) {
                return true;
            }
            isLive = true;
        } else {
            content = contentBiz.selectById(cid);
            //如果重复收藏依然返回成功
            if (content == null) {
                return true;
            }
            if (content.getContentType().equals(LiveCommonEnum.CONTENT_ACTIVITY)) {
                content.setContentType(2);
            }
            type = content.getContentType();
        }
        Fav fav = new Fav();
        fav.setCid(cid);
        fav.setType(type);
        fav.setUid(uid);
        fav.setCreateTime(new Date());
        int res = favMapper.insert(fav);
        if (res < LiveCommonEnum.CONSTANT_ONE) {
            return false;
        }
        if(isLive == false){
            Fav fav1 = new Fav();
            fav1.setCid(cid);
            fav1.setType(type);
            int count = favMapper.selectCount(fav1);
            content.setCollectCount(count);
            contentBiz.updateSelectiveById(content);
        }else{
            liveBiz.updateFavs(cid,type);
        }
        return true;
    }

}