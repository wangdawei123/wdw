package com.kanfa.news.info.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.info.entity.ChannelContent;
import com.kanfa.news.info.entity.ChannelContentCard;
import com.kanfa.news.info.mapper.ChannelContentCardMapper;
import com.kanfa.news.info.mapper.ChannelContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 内容频道卡片类型绑定表
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-23 20:01:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChannelContentCardBiz extends BaseBiz<ChannelContentCardMapper,ChannelContentCard> {

    @Autowired
    private ChannelContentMapper channelContentMapper;

    public Integer updateCardType(ChannelContentCard channelContentCard) {
        Integer insertSelective = 0;
        if(channelContentCard.getId()==null){
            insertSelective = this.mapper.insertSelective(channelContentCard);
        }else{
            insertSelective = this.mapper.updateByPrimaryKeySelective(channelContentCard);
        }
        if(channelContentCard.getContentId()!=null && channelContentCard.getChannelId()!=null){
            ChannelContent channelContent=new ChannelContent();
            channelContent.setContentId(channelContentCard.getContentId());
            channelContent.setChannelId(channelContentCard.getChannelId());
            List<ChannelContent> channelContents = channelContentMapper.select(channelContent);
            if(channelContents!=null && channelContents.size()>0){
                ChannelContent channelContent1 = channelContents.get(0);
                channelContent1.setCardType(channelContentCard.getCardType());
                channelContentMapper.updateByPrimaryKeySelective(channelContent1);
            }
        }
        return insertSelective;
    }
}