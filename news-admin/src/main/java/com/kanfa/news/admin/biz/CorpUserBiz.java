package com.kanfa.news.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.mapper.CorpUserMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 公司人员表
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-12 14:22:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CorpUserBiz extends BaseBiz<CorpUserMapper, CorpUser> {

    //我的 上传视频里面的记者字典
    public List<CorpUser> getCorpUserIdName() {
        ArrayList<CorpUser> corpUsers = new ArrayList<>();
        List<CorpUser> corpUserIdName1 = this.mapper.getCorpUserIdName();
        List<CorpUser> cropUserIdName2 = this.mapper.selectCropUserIdName();
        corpUsers.addAll(corpUserIdName1);
        corpUsers.addAll(cropUserIdName2);
        return corpUsers;
    }

    public TableResultResponse<Map<String, Object>> getPage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = this.mapper.search(
                params.get("name").toString(),
                Integer.parseInt(params.get("level1").toString()),
                Integer.parseInt(params.get("level2").toString()),
                Integer.parseInt(params.get("corpJobId").toString()),
                Integer.parseInt(params.get("status").toString())
        );
        return new TableResultResponse<>(result.getTotal(), list);
    }
}