package com.kanfa.news.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/31 14:02
 */
@FeignClient(name = "news-search")
public interface ISearchServiceFeign {

    /**
     * 使用分词查询,并分页
     * index          索引名称
     * type           类型名称,可传入多个type逗号分隔
     * currentPage    当前页
     * pageSize       每页显示条数
     * startTime      开始时间
     * endTime        结束时间
     * fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * sortField      排序字段
     * matchPhrase    true 使用，短语精准匹配
     * highlightField 高亮字段
     * matchStr       过滤条件（xxx=111,aaa=222）
     */
    @RequestMapping(value = "/search/getListDataPage/{indexName}/{typeName}", method = RequestMethod.POST)
    Object searchDataPage(@PathVariable("indexName") String indexName,
                          @PathVariable("typeName") String typeName,
                          @RequestParam("currentPage") Integer currentPage,
                          @RequestParam("pageSize") Integer pageSize,
                          @RequestParam("searchKey") String searchKey) ;

}
