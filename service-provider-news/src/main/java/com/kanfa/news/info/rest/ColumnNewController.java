package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ColumnNewBiz;
import com.kanfa.news.info.entity.ColumnNew;
import com.kanfa.news.info.vo.admin.column.ColumnNewPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("columnNew")
public class ColumnNewController extends BaseController<ColumnNewBiz,ColumnNew> {

    @Autowired
    private ColumnNewBiz columnNewBiz;

    @RequestMapping(value = "/getColumnPage",method = RequestMethod.POST)
    public TableResultResponse<ColumnNewPageInfo> getColumnPage(@RequestBody ColumnNewPageInfo entity){
        return columnNewBiz.getColumnPage(entity);
    }

}