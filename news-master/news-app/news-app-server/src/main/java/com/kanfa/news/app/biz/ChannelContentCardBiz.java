package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.ChannelContentCard;
import com.kanfa.news.app.mapper.ChannelContentCardMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ChannelContentCardMapper channelContentCardMapper;

    public Integer updateCardType(ChannelContentCard channelContentCard) {
        int i = channelContentCardMapper.updateByPrimaryKeySelective(channelContentCard);
        if(channelContentCard.getId()==null){
            int insertSelective = channelContentCardMapper.insertSelective(channelContentCard);
            return insertSelective;
        }
        return i;
    }
}