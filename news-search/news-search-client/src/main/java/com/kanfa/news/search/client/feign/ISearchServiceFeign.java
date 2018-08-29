package com.kanfa.news.search.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/31 14:02
 */
@FeignClient(name = "news-search")
public interface ISearchServiceFeign {

    /**
     * 创建索引
     */
    @PostMapping("/search/addIndex/{indexName}")
    boolean addIndex(@PathVariable("indexName") String indexName);

    /**
     * 删除索引
     */
    @DeleteMapping("/search/deleteIndex/{indexName}")
    boolean deleteIndex(@PathVariable("indexName") String indexName);


    /**
     * 判断索引是否存在
     */
    @GetMapping("/search/existIndex/{indexName}")
    boolean isIndexExist(@PathVariable("indexName") String indexName);

    /**
     * 数据添加
     */
    @PostMapping("/search/addData/{indexName}/{typeName}")
    void addData(@PathVariable("indexName") String indexName, @PathVariable("typeName") String typeName, @RequestBody List<Map<String, Object>> list);


    /**
     * 通过ID删除数据
     */
    @DeleteMapping("/search/deleteData/{indexName}/{typeName}/{id}")
    boolean deleteDataById(@PathVariable("indexName") String indexName, @PathVariable("typeName") String typeName, @PathVariable("id") Integer id) ;


    /**
     * 通过ID更新数据
     * jsonObject 要增加的数据
     * index      索引，类似数据库
     * type       类型，类似表
     * id         数据ID
     */
    @PutMapping("/search/updateDataById/{indexName}/{typeName}")
    void updateDataById(@PathVariable("indexName") String indexName,
                        @PathVariable("typeName") String typeName,
                        @RequestBody List<Map<String, Object>> list);


    /**
     * 通过条件更新数据
     * jsonObject 要增加的数据
     * index      索引，类似数据库
     * type       类型，类似表
     * id         数据ID
     */
    @PutMapping("/search/updateDataByCondition/{indexName}/{typeName}")
    void updateDataByCondition(@PathVariable("indexName") String indexName,
                               @PathVariable("typeName") String typeName,
                               @RequestParam(value = "conditionSourceMap") Map<String, Object> conditionSourceMap);


    /**
     * 通过ID获取数据
     * index  索引，类似数据库
     * type   类型，类似表
     * id     数据ID
     * fields 需要显示的字段，逗号分隔（缺省为全部字段）
     */
    @GetMapping("/search/getData/{indexName}/{typeName}/{id}")
    Object searchDataById(@PathVariable("indexName") String indexName, @PathVariable("typeName") String typeName, @PathVariable("id") Integer id) ;




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
    @GetMapping("/search/getListDataPage/{indexName}/{typeName}")
    Object searchDataPage(@PathVariable("indexName") String indexName, @PathVariable("typeName") String typeName, @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize, @RequestParam("searchKey") String searchKey) ;

    @DeleteMapping("/search/deleteData/{indexName}/{typeName}/{channelId}/{id}")
    Object deleteDataByChannelIdAndId(@PathVariable("indexName") String indexName, @PathVariable("typeName") String typeName, @PathVariable("channelId") Integer channelId, @PathVariable("id") Integer id);
}
