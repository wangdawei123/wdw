package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.LiveVideoBind;
import com.kanfa.news.info.mapper.LiveVideoBindMapper;
import com.kanfa.news.info.vo.admin.live.LiveVideoBindInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 直播关联内容表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-28 10:48:58
 */
@Service
public class LiveVideoBindBiz extends BaseBiz<LiveVideoBindMapper,LiveVideoBind> {

    @Autowired
    private LiveVideoBindMapper liveVideoBindMapper;

    public List<LiveVideoBindInfo> findAllBind(Integer liveId){
        List<LiveVideoBindInfo> contentBind = liveVideoBindMapper.getContentBind(liveId);
        for (LiveVideoBindInfo liveVideoBindInfo:contentBind) {
            liveVideoBindInfo.setFromTypeCh(NewsEnumsConsts.fromOfType.getType(liveVideoBindInfo.getFromType()));
        }
        List<LiveVideoBindInfo> liveBind = liveVideoBindMapper.getLiveBind(liveId);
        for (LiveVideoBindInfo liveVideoBindInfo:liveBind) {
            liveVideoBindInfo.setFromTypeCh(NewsEnumsConsts.fromOfType.getType(liveVideoBindInfo.getFromType()));
        }
        contentBind.addAll(liveBind);
        return contentBind;
    }

    // 关联内容的按标题搜索
    public TableResultResponse<LiveVideoBindInfo> getSearchPage(Query query,
                                                                  Integer fromType,
                                                                  Integer liveId,
                                                                  String title){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        if (fromType.equals(1)){
            List<LiveVideoBindInfo> videoSearchPage = liveVideoBindMapper.getVideoSearchPage(liveId, title);
            for (LiveVideoBindInfo liveVideoBindInfo :videoSearchPage) {
                return new TableResultResponse<LiveVideoBindInfo>(result.getTotal(),videoSearchPage );
            }
        }else{
            List<LiveVideoBindInfo> liveSearchPage = liveVideoBindMapper.getLiveSearchPage(liveId, title);
            for (LiveVideoBindInfo liveVideoBindInfo :liveSearchPage) {
                return new TableResultResponse<LiveVideoBindInfo>(result.getTotal(),liveSearchPage );
            }
        }
        return  null;
    }


    public Integer getMaxSort(){
        Integer maxSort = liveVideoBindMapper.getMaxSort();
        return maxSort;
    }

    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    public List<LiveVideoBind> getLiveVideoBind(List<Integer> ids, Integer contentId) {
        return liveVideoBindMapper.getLiveVideoBind(ids,contentId);
    }

//    /**
//     * 通过cid bindId 查出 id
//     * @param
//     * @param
//     * @return
//     */
//    public LiveVideoBind getLiveVideoBindId(@RequestParam("cid")Integer cid,
//                               @RequestParam("bindId") Integer bindId){
//        return liveVideoBindMapper.getLiveVideoBindId(cid,bindId);
//    }


    /**
     * 批量更新
     * @param
     * @return
     */

    public Integer batchUpdate(List<LiveVideoBind> liveVideoBinds) {
        int num = 0;
        for (LiveVideoBind liveVideoBind : liveVideoBinds) {
            Long l = System.currentTimeMillis() / 1000;
            Integer time = l.intValue();
            liveVideoBind.setSorttime(time);
            int flag = liveVideoBindMapper.updateByPrimaryKeySelective(liveVideoBind);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }

}