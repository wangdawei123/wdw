package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.LiveSpecialBind;
import com.kanfa.news.app.mapper.LiveSpecialBindMapper;
import com.kanfa.news.app.vo.admin.live.LiveSpecialBindInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 专题与直播关联表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 15:43:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveSpecialBindBiz extends BaseBiz<LiveSpecialBindMapper,LiveSpecialBind> {

    @Autowired
    private LiveSpecialBindMapper liveSpecialBindMapper;

    //直播专题 关联内容
    public TableResultResponse<LiveSpecialBindInfo> getPage(Query query,
                                                            Integer liveSpecialId){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<LiveSpecialBindInfo> list = liveSpecialBindMapper.getPage(liveSpecialId);
        return new TableResultResponse<LiveSpecialBindInfo>(result.getTotal(), list);
    }



    // 关联内容的按标题搜索
    public TableResultResponse<LiveSpecialBindInfo> getSearchPage(Query query,
                                                                  Integer liveSpecialId,
                                                                  String title){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<LiveSpecialBindInfo> searchlist = liveSpecialBindMapper.getSearchPage(liveSpecialId,title);
        //为liveTypeCh的属性赋值
//        for (LiveSpecialBindInfo liveSpecialBindInfo:searchlist) {
//            liveSpecialBindInfo.setLiveTypeCh(NewsEnumsConsts.LiveOfStatus.getType(liveSpecialBindInfo.getLiveType()));
//        }
        return new TableResultResponse<LiveSpecialBindInfo>(result.getTotal(), searchlist);
    }


    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    public List<LiveSpecialBind> getLiveSpecialBind(List<Integer> ids, Integer contentId) {
        return liveSpecialBindMapper.getLiveSpecialBind(ids,contentId);
    }

    /**
     * 批量更新
     * @param
     * @return
     */

    public Integer batchUpdate(List<LiveSpecialBind> liveSpecialBinds) {
        int num = 0;
        for (LiveSpecialBind liveSpecialBind : liveSpecialBinds) {
            int flag = liveSpecialBindMapper.updateByPrimaryKeySelective(liveSpecialBind);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }


    public   Integer getMaxSort(Integer liveSpecialId){
        return liveSpecialBindMapper.getMaxSort(liveSpecialId);
    }
}