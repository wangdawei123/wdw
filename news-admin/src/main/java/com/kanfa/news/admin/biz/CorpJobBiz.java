package com.kanfa.news.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.common.constant.news.CorpJobEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.stereotype.Service;

import com.kanfa.news.admin.entity.CorpJob;
import com.kanfa.news.admin.mapper.CorpJobMapper;
import com.kanfa.news.common.biz.BaseBiz;

import java.util.List;
import java.util.Map;

/**
 * 公司岗位表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-06-11 20:09:29
 */
@Service
public class CorpJobBiz extends BaseBiz<CorpJobMapper, CorpJob> {
    public TableResultResponse<Map<String, Object>> getPage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = this.mapper.search(
                Integer.parseInt(params.get("level1").toString()),
                Integer.parseInt(params.get("level2").toString()),
                Integer.parseInt(params.get("corpJobId").toString()),
                Integer.parseInt(params.get("stat").toString())
        );
        return new TableResultResponse<>(result.getTotal(), list);
    }

    public ObjectRestResponse<CorpJob> getAll() {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(this.mapper.getAll());
        return objectRestResponse;
    }
}