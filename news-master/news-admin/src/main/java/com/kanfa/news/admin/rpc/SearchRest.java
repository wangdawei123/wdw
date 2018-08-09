package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Activity;
import com.kanfa.news.admin.entity.Search;
import com.kanfa.news.admin.feign.ISearchServiceFeign;
import com.kanfa.news.admin.vo.search.SearchInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/5/30 13:50
 */

@RestController
@RequestMapping("search")
public class SearchRest {

    @Autowired
    private ISearchServiceFeign searchServiceFeign;

    /**
     * 搜索关键字列表的分页
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getSearchPage",method = RequestMethod.POST)
    public TableResultResponse<SearchInfo> getSearchPage(@RequestBody SearchInfo entity){
        return  searchServiceFeign.getSearchPage(entity);
    }

    /**
     * 新增类型名称
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ObjectRestResponse<Search> add(@RequestParam("name")String name) {
        Search search = new Search();
        search.setName(name);
        search.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        search.setCreateTime(new Date());
        search.setUpdateTime(new Date());
        search.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        search.setEndTime(new Date());
        return searchServiceFeign.add(search);
    }


    /**
     * 删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse delete(@PathVariable("id") Integer id) {
        Search search = new Search();
        search.setId(id);
        //0:删除 1:正常
        search.setStat(0);
        return searchServiceFeign.update(id,search);
    }

}