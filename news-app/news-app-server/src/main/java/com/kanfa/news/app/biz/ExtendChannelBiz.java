package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.ExtendChannel;
import com.kanfa.news.app.mapper.ExtendChannelMapper;
import com.kanfa.news.app.vo.admin.activity.ExtendChannelPageInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 推广渠道
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-16 11:26:02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExtendChannelBiz extends BaseBiz<ExtendChannelMapper,ExtendChannel> {

    @Autowired
    private ExtendChannelMapper extendChannelMapper;


    /**
     * 推广渠道的分页及查询
     * @return
     */
    public TableResultResponse<ExtendChannelPageInfo> getExtendChannelPage(ExtendChannelPageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //优惠券活动
        entity.setStatus(1);
        entity.setIsDelete(0);
        List<ExtendChannelPageInfo> list = extendChannelMapper.getExtendChannelPage(entity);
        return new TableResultResponse<ExtendChannelPageInfo>(result.getTotal(),list);
    }


    public List<ExtendChannel> extendChannelIdNames(){
        return  extendChannelMapper.extendChannelIdName();
    }


}