package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.Message;
import com.kanfa.news.info.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 后台发送消息
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-09 19:43:53
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageBiz extends BaseBiz<MessageMapper,Message> {

    @Autowired
    private MessageMapper messageMapper;


    /**
     * 添加推送
     * @param message
     * @return
     */
    public Message addPushMessage(Message message) {
        return null;
    }

    /**
     * 搜索所有消息分页
     * @param params
     * @return
     */
    public TableResultResponse<Message> getMessagePage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<Message> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Message> list = messageMapper.getMessagePage();
        return new TableResultResponse<Message>(result.getTotal(), list);
    }
}