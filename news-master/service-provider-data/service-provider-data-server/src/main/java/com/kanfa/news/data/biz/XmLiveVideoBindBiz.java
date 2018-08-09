package com.kanfa.news.data.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.data.entity.XmLiveVideoBind;
import com.kanfa.news.data.mapper.XmLiveVideoBindMapper;
import com.kanfa.news.common.biz.BaseBiz;

import java.util.List;

/**
 * 直播关联内容表
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-08 10:28:46
 */
@Service
public class XmLiveVideoBindBiz extends BaseBiz<XmLiveVideoBindMapper,XmLiveVideoBind> {

    @Autowired
    private XmLiveVideoBindMapper xmLiveVideoBindMapper;

    public List<XmLiveVideoBind> selectXmLiveVideoBinds(Integer cid){
        return  xmLiveVideoBindMapper.selectXmLiveVideoBindsByCid(cid);
    }
}