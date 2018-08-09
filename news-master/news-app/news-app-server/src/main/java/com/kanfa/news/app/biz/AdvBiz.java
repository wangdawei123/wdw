package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.Adv;
import com.kanfa.news.app.entity.Fav;
import com.kanfa.news.app.mapper.AdvMapper;
import com.kanfa.news.app.mapper.FavMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户对内容的收藏
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdvBiz extends BaseBiz<FavMapper,Fav> {

    @Autowired
    private AdvMapper advMapper;
    @Autowired
    protected HttpServletRequest request;

    /**
     *  app首页开屏广告
     */
    @RequestMapping(value = "/getStartUpAdv", method = RequestMethod.POST)
    public AppObjectResponse getStartUpAdv(){
        AppObjectResponse response = new AppObjectResponse();
        Adv adv = this.getAdv();
        if(adv == null){
            response.setData(new HashMap<>(3));
            return response;
        }
        Map<String ,Object> data = new HashMap<>(12);
        // 广告pv统计 需要中间跳转
        if(adv.getUrl()==null || adv.getUrl().equals("")){
            data.put("url",this.getCoutPvUrl(adv.getId()));
        }else {
            data.put("url",adv.getUrl());
        }
        data.put("keep",adv.getKeep());
        data.put("id",adv.getId());
        data.put("image",adv.getImage());

        response.setData(data);
        return response;
    }


    /**
     *  app首页开屏广告
     */
    public Adv getAdv(){
        List<Adv> advs = advMapper.getStartUpAdv();
        if(advs.size()== LiveCommonEnum.CONSTANT_ZERO){
            return null;
        }
        return advs.get(0);
    }

    /**
     * 获得统计pv跳转页地址
     */
    public String getCoutPvUrl(Integer id){
        return "http://"+request.getRemoteHost()+"/web/adv/show?adv_id="+id;
    }


}