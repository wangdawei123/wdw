package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.Channel;
import com.kanfa.news.info.entity.ChannelFocus;
import com.kanfa.news.info.mapper.ChannelFocusMapper;
import com.kanfa.news.info.mapper.ChannelMapper;
import com.kanfa.news.info.vo.admin.info.ChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.PcChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.VideoChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.VideoChannelFocusListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 频道焦点图
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-07 09:57:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChannelFocusBiz extends BaseBiz<ChannelFocusMapper,ChannelFocus> {

    @Autowired
    private ChannelFocusMapper channelFocusMapper;
    @Autowired
    private ChannelMapper channelMapper;


    public TableResultResponse<ChannelFocusInfo> getPage(Map<String, Object> params) {
        Query query=new Query(params);
        Page<ChannelFocusInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<ChannelFocusInfo> list = channelFocusMapper.getPage(params);
        return new TableResultResponse<>(result.getTotal(), list);
    }


    public List<ChannelFocusInfo> getFocusAll(ChannelFocusInfo entity){
        return channelFocusMapper.getFocusAll(entity);
    }


    public Integer batchDelete(List<Integer> ids) {
//        List<ChannelFocus>  list=new ArrayList<>(ids.size());
        int deleteCount=0;
        for (Integer id : ids) {
            ChannelFocus channelFocus=new ChannelFocus();
            channelFocus.setId(id);
            channelFocus.setIsDelete(0);
//            list.add(channelFocus);
            int flag = channelFocusMapper.updateByPrimaryKeySelective(channelFocus);
            if(flag>0){
                deleteCount++;
            }
        }
        return deleteCount;
    }


    //视频管理里面的 焦点图管理
    public TableResultResponse<VideoChannelFocusInfo> getVideoChannelFocusPage(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoChannelFocusInfo> list = channelFocusMapper.getVideoChannelFocusPage();
        return new TableResultResponse<VideoChannelFocusInfo>(result.getTotal(), list);
    }

    //视频管理-->焦点图管理-->管理焦点图
    public TableResultResponse<VideoChannelFocusListInfo> getVideoFocusList(Query query, Integer channelId){

        //获得该channelId的Channel相对应的名字
        Channel channel = channelMapper.selectByPrimaryKey(channelId);
        String name = channel.getName();

        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoChannelFocusListInfo> searchlist = channelFocusMapper.getVideoFocusList(channelId);

        //赋值
        for (VideoChannelFocusListInfo videoChannelFocusListInfo:searchlist) {
            videoChannelFocusListInfo.setChannelName(name);
        }
        return new TableResultResponse<VideoChannelFocusListInfo>(result.getTotal(), searchlist);
    }

    //vr管理-->焦点图管理
    public TableResultResponse<VideoChannelFocusInfo> getVRChannelFocusPage(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoChannelFocusInfo> list = channelFocusMapper.getVRChannelFocusPage();
        return new TableResultResponse<VideoChannelFocusInfo>(result.getTotal(), list);
    }

    //pc管理-->焦点图管理
    public ObjectRestResponse<List<PcChannelFocusInfo>> getPcChannelFocusPage() {
        ObjectRestResponse<List<PcChannelFocusInfo>> listObjectRestResponse = new ObjectRestResponse<>();
        List<PcChannelFocusInfo> pcChannelFocusPage = channelFocusMapper.getPcChannelFocusPage();
        listObjectRestResponse.setData(pcChannelFocusPage);
        return listObjectRestResponse;
    }

    /**
     * 查询焦点视图
     * @param ids
     * @return
     */
    public List<ChannelFocus> getChannelFocusByIdsAndChannelId(List<Integer> ids) {
        return channelFocusMapper.getChannelFocusByIdsAndChannelId(ids);
    }

    /**
     *  批量更新
     * @param channelFocusList
     * @return
     */
    public Integer batchUpdate(List<ChannelFocus> channelFocusList) {
        int num = 0;
        for (ChannelFocus channelContent : channelFocusList) {
            int flag = channelFocusMapper.updateByPrimaryKeySelective(channelContent);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }

    /**
     * 获取最大排序值
     * @return
     */
    public Integer getMaxOrderNumber() {
        return channelFocusMapper.getMaxOrderNumber();
    }
}