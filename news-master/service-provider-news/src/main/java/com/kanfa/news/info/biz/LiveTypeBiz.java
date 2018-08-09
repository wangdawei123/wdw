package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.mapper.UserMapper;
import com.kanfa.news.info.vo.admin.live.LiveTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.LiveType;
import com.kanfa.news.info.mapper.LiveTypeMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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