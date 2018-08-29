package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Search;
import com.kanfa.news.admin.vo.search.SearchInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/5/30 13:37
 */
@FeignClient(name = "service-provider-news")
public interface ISearchServiceFeign  {


    /** 新增类型名称
     * @param
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    ObjectRestResponse<Search> add(@RequestBody Search entity);

    /** 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/search/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<Search> update(@PathVariable("id") Integer id, @RequestBody Search entity);

    /** 搜索关键字列表的分页
     * @param
     * @return
     */
    @RequestMapping(value = "/search/getSearchPage",method = RequestMethod.POST)
    TableResultResponse<SearchInfo> getSearchPage(@RequestBody SearchInfo entity);

    /** 得到一个Search
     * @param
     * @return
     */
    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<Search> get(@PathVariable("id") int id);

}
