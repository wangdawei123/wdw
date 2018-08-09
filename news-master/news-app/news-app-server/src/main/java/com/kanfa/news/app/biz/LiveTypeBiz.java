package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.LiveType;
import com.kanfa.news.app.mapper.LiveTypeMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 直播类型表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-26 13:26:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveTypeBiz extends BaseBiz<LiveTypeMapper, LiveType> {
    @Autowired
    private LiveTypeMapper liveTypeMapper;

    public TableResultResponse<HashMap> page(Query query) {
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<HashMap> list = liveTypeMapper.getPage();
        return new TableResultResponse<HashMap>(result.getTotal(), list);
    }

    public List<LiveType> findLiveTypeList(){
        return liveTypeMapper.findLiveTypeList();
    }


}