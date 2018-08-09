package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.BurstType;
import com.kanfa.news.app.mapper.BurstTypeMapper;
import com.kanfa.news.app.vo.admin.burst.BurstTypeInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 新闻爆料类型表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BurstTypeBiz extends BaseBiz<BurstTypeMapper,BurstType> {

    @Autowired
    private BurstTypeMapper burstTypeMapper;

    public TableResultResponse<BurstTypeInfo> burstTypePage(Map<String, Object> params) {
        Query query=new Query(params);
        Page<BurstTypeInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<BurstTypeInfo> list = burstTypeMapper.getPage(params);
        return new TableResultResponse<>(result.getTotal(), list);
    }
}