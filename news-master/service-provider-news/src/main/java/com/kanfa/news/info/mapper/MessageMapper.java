    package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Message;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

    /**
 * 后台发送消息
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-09 19:43:53
 */
public interface MessageMapper extends Mapper<Message> {

    List<Message> getMessagePage();
}
