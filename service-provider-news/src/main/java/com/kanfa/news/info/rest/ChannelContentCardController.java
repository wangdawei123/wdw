package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ChannelContentBiz;
import com.kanfa.news.info.biz.ChannelContentCardBiz;
import com.kanfa.news.info.entity.ChannelContentCard;
import com.kanfa.news.info.vo.admin.info.ChannelContentCardInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("channelContentCard")
public class ChannelContentCardController extends BaseController<ChannelContentCardBiz,ChannelContentCard> {

    @RequestMapping(value = "/getCardType",method = RequestMethod.POST)
    public ObjectRestResponse<ChannelContentCardInfo> getCardType(@RequestBody ChannelContentCardInfo channelContentCardInfo){
        ChannelContentCard channelContentCard=new ChannelContentCard();
        BeanUtils.copyProperties(channelContentCardInfo,channelContentCard);
        List<ChannelContentCard> channelContentCards = this.baseBiz.selectList(channelContentCard);
        if(channelContentCards!=null && channelContentCards.size()>0){
            ChannelContentCard channelContentCard1 = channelContentCards.get(0);
//            BeanUtils.copyProperties(channelContentCard1,channelContentCardInfo);
            channelContentCardInfo.setId(channelContentCard1.getId());
            channelContentCardInfo.setCardType(channelContentCard1.getCardType());
            /*if(channelContentCardInfo.getCardType().equals(NewsEnumsConsts.ChannelContentOfCardType.ThreeImg.key())){
                try {
                    List<String> unserialize = (List<String>)PHPSerializer.unserialize(channelContentCard1.getImage().getBytes());
                    channelContentCardInfo.setImageList(unserialize);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    ObjectRestResponse<ChannelContentCardInfo> restResponse=new ObjectRestResponse<>();
                    restResponse.setRel(true);
                    restResponse.setMessage("查询失败");
                    restResponse.setStatus(500);
                    return restResponse;
                }
            }else{

            }*/
            channelContentCardInfo.setImage(channelContentCard1.getImage());
        }else {
            channelContentCardInfo.setCardType(0);
        }
        ObjectRestResponse<ChannelContentCardInfo> restResponse=new ObjectRestResponse<>();
        restResponse.setData(channelContentCardInfo);
        return restResponse;
    }

    @RequestMapping(value = "/updateCardType",method = RequestMethod.POST)
    public ObjectRestResponse<Integer> updateCardType(@RequestBody ChannelContentCard channelContentCard){
        Integer integer = this.baseBiz.updateCardType(channelContentCard);
        ObjectRestResponse<Integer> objectRestResponse = new ObjectRestResponse<>();
        if(integer<=0){
            objectRestResponse.setStatus(506);
            objectRestResponse.setRel(true);
            objectRestResponse.setMessage("更新失败");
        }
        objectRestResponse.setData(integer);
        return objectRestResponse;
    }
}