package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.LiveCourt;
import com.kanfa.news.app.mapper.LiveCourtMapper;
import com.kanfa.news.app.vo.admin.live.LiveCourtInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-18 15:24:35
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveCourtBiz extends BaseBiz<LiveCourtMapper,LiveCourt> {

    @Autowired
    private LiveCourtMapper liveCourtMapper;

    //直播法院的分页显示
    public TableResultResponse<LiveCourtInfo> page(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<LiveCourtInfo> list = liveCourtMapper.getPage();

        //为courtLevelCh赋值 1:最高法院 2:高级法院 3:中级法院 4:基层法院
        for (LiveCourtInfo liveCourtInfo:list) {
            liveCourtInfo.setCourtLevelCh(NewsEnumsConsts.CourtOfLevel.getType(liveCourtInfo.getCourtLevel()));
        }
        return new TableResultResponse<LiveCourtInfo>(result.getTotal(), list);
    }

    //直播法院的按法院名字的查询
    public TableResultResponse<LiveCourtInfo> getSearchPage(Query query,String name){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<LiveCourtInfo> searchlist = liveCourtMapper.getSearchPage(name);
        //为courtLevelCh赋值 1:最高法院 2:高级法院 3:中级法院 4:基层法院
        for (LiveCourtInfo liveCourtInfo:searchlist) {
            liveCourtInfo.setCourtLevelCh(NewsEnumsConsts.CourtOfLevel.getType(liveCourtInfo.getCourtLevel()));
        }

        return new TableResultResponse<LiveCourtInfo>(result.getTotal(), searchlist);
    }

    //直播法院的查询一个
    public LiveCourtInfo selectOne(Integer id){
        LiveCourtInfo liveCourtInfo = liveCourtMapper.selectOneLiveCourt(id);
        if (liveCourtInfo.getUpdateUser().equals("0")){
            liveCourtInfo.setUpdateUser("网络爬虫师");
        }
        liveCourtInfo.setCourtLevelCh(NewsEnumsConsts.CourtOfLevel.getType(liveCourtInfo.getCourtLevel()));
        return liveCourtInfo;
    }

    public List<LiveCourtInfo> getLevelCourtList(Integer court_level, Integer province_id,Integer stat ){
        LiveCourtInfo info = new LiveCourtInfo();
        info.setId(-1);
        info.setName("全部");
        List<LiveCourtInfo> list = liveCourtMapper.getLevelCourtList(court_level ,province_id ,stat);
        list.add(LiveCommonEnum.CONSTANT_ZERO,info);
        return list;
    }
}