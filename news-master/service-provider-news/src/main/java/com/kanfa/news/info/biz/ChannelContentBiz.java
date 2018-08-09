package com.kanfa.news.info.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.info.entity.Channel;
import com.kanfa.news.info.entity.ChannelContent;
import com.kanfa.news.info.mapper.ChannelContentMapper;
import com.kanfa.news.info.vo.admin.info.ChannelContentInfo;
import com.kanfa.news.info.vo.admin.info.ChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 内容帮顶频道中间表
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 17:06:36
 */
@Service
public class ChannelContentBiz extends BaseBiz<ChannelContentMapper,ChannelContent> {

    @Autowired
    private ChannelContentMapper channelContentMapper;

    /**
     * 更新发布状态
     * @param channelContent
     * @return
     */
    public Integer updateChannelConent(ChannelContent channelContent) {
        return channelContentMapper.updateChannelConent(channelContent);
    }

    /**
     * 查询选中的频道（内容频道列表）
     * @param id
     * @return
     */
    public List<ChannelInfo> selectListSelected(Integer id) {
        return channelContentMapper.selectListSelected(id);
    }

    /**
     * 获取频道内容绑定数据
     * @param ids
     * @return
     */
    public List<ChannelContentInfo> getChannelContent(List<Integer> ids,Integer channelId) {
        return channelContentMapper.getChannelContent(ids,channelId);
    }

    /**
     * 批量跟新内容排序
     * @param channelContentList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer batchUpdate(List<ChannelContent> channelContentList) {
        int num = 0;
        for (ChannelContent channelContent : channelContentList) {
            int flag = channelContentMapper.updateByPrimaryKeySelective(channelContent);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }

    /**
     * 统计审核数量
     * @param channelId
     * @param checkStatus
     * @return
     */
    public Integer selectCountByChannelId(Integer channelId, Integer checkStatus) {
       return channelContentMapper.selectCountByChannelId(channelId,checkStatus);
    }

    public List<Map<String,Object>> selectByContentIdFromType(Integer contentId,Integer fromType){
        return channelContentMapper.selectByContentIdFromType(contentId,fromType);
    }


    /**
     * 查询内容绑定的第一个频道id
     * @param contentId
     * @param cate
     * @return
     */
    public Channel getFirstChannelForAdv(Integer contentId, Integer cate) {
        return channelContentMapper.getFirstChannelForAdv(contentId,cate);
    }
}