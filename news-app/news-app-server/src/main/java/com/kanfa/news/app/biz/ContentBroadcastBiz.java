package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.ContentBroadcast;
import com.kanfa.news.app.mapper.ContentBroadcastMapper;
import com.kanfa.news.app.vo.admin.live.ContentBroadcastInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 节目直播表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 13:47:27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentBroadcastBiz extends BaseBiz<ContentBroadcastMapper,ContentBroadcast> {

    @Autowired
    private ContentBroadcastMapper contentBroadcastMapper;

    /**
     * 分页查询aliyun列表
     * @param entity
     * @return
     */
    public TableResultResponse<ContentBroadcastInfo> getPage(ContentBroadcastInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置晒出条件
        //'状态，1：正常，0：删除'
        entity.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        List<ContentBroadcastInfo> list = contentBroadcastMapper.getPage(entity);
        for (ContentBroadcastInfo contentBroadcastInfo:list) {
            if (contentBroadcastInfo.getIsPublish()==null){
                contentBroadcastInfo.setIsPublish(0);
            }
        }
        return new TableResultResponse<>(result.getTotal(), list);
    }
}