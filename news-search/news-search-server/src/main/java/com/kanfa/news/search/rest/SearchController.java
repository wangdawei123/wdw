package com.kanfa.news.search.rest;



import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.common.util.DateUtil;
import com.kanfa.news.search.utils.ElasticsearchUtils;
import com.kanfa.news.search.utils.EsPage;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


@RestController
@RequestMapping("search")
public class SearchController  {
//    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);


    /**
     * 创建索引
     */
    @RequestMapping("/addIndex/{indexName}")
    public boolean addIndex(@PathVariable String indexName) {
      return  ElasticsearchUtils.createIndex(indexName);
    }

    /**
     * 删除索引
     */
    @RequestMapping("/deleteIndex/{indexName}")
    public boolean deleteIndexTest(@PathVariable String indexName) {
       return ElasticsearchUtils.deleteIndex(indexName);
    }


    /**
     * 判断索引是否存在
     */
    @RequestMapping("/existIndex/{indexName}")
    public boolean isIndexExistTest(@PathVariable String indexName) {
        return ElasticsearchUtils.isIndexExist(indexName);
    }

    /**
     * 数据添加
     */
    @RequestMapping("/addData/{indexName}/{typeName}")
    public String addDataTest(@PathVariable String indexName,@PathVariable String typeName,@RequestBody List<Map<String,Object>> list) {
        int listSize = list.size();
        String id = null;
        for (int i = 0; i < listSize; i++) {
            Map<String, Object> map = list.get(i);

             id =  ElasticsearchUtils.addData(JSONObject.parseObject(JSONObject.toJSONString(map)), indexName, typeName, map.get("id").toString());


        }
        return  id;
    }

    /**
     * 通过ID删除数据
     */
    @RequestMapping("/deleteData/{indexName}/{typeName}/{id}")
    public boolean deleteDataByIdTest(@PathVariable String indexName, @PathVariable String typeName, @PathVariable Integer id) {

        return ElasticsearchUtils.deleteDataById(indexName, typeName, id.toString());

    }

    /**
     * 通过channelId,ID删除数据
     */
    @DeleteMapping("/deleteData/{indexName}/{typeName}/{channelId}/{id}")
    public boolean deleteDataByChannelIdAndId(@PathVariable("indexName") String indexName, @PathVariable("typeName") String typeName, @PathVariable("channelId") Integer channelId,@PathVariable("id") Integer id){

        return ElasticsearchUtils.deleteDataByChannelIdAndId(indexName, typeName,channelId.toString(), id.toString());

    }


    /**
     * 通过ID更新数据
     * <p>
     * jsonObject 要增加的数据
     * index      索引，类似数据库
     * type       类型，类似表
     * id         数据ID
     */
    @RequestMapping("/updateDataById/{indexName}/{typeName}")
    public String updateDataByIdTest(@PathVariable String indexName, @PathVariable String typeName,@RequestBody List<Map<String,Object>> list) {
        int listSize = list.size();
        String id = null;
        for (int i = 0; i < listSize; i++) {
            Map<String, Object> map = list.get(i);
           id = ElasticsearchUtils.updateDataById(JSONObject.parseObject(JSONObject.toJSONString(map)), indexName, typeName, map.get("id").toString());
        }
        return id;
    }
    /**
     * 通过条件更新数据
     * <p>
     * jsonObject 要增加的数据
     * index      索引，类似数据库
     * type       类型，类似表
     * id         数据ID
     */
    @RequestMapping("/updateDataByCondition/{indexName}/{typeName}")
    public void updateDataByCondition(@PathVariable String indexName, @PathVariable String typeName,@RequestParam Map<String,Object> conditionSourceMap ) {
            Map<String,Object> conditionMap = mapString2Map(conditionSourceMap.get("conditionMap").toString());
            Map<String,Object> sourceMap = mapString2Map(conditionSourceMap.get("sourceMap").toString());
            ElasticsearchUtils.updateDataByCondition(indexName, typeName,JSONObject.parseObject(JSONObject.toJSONString(conditionMap)), JSONObject.parseObject(JSONObject.toJSONString(sourceMap)));
    }

    public  Map mapString2Map(String mapString){
        Map map = new HashMap();
        java.util.StringTokenizer items;
        for(StringTokenizer entrys = new StringTokenizer(mapString, "^"); entrys.hasMoreTokens();
            map.put(items.nextToken(), items.hasMoreTokens() ? (mapStringToMap((String) (items.nextToken()))) : null)){
            items = new StringTokenizer(entrys.nextToken(), "'");
        }
        return map;
    }

    public  Map mapStringToMap(String text){
        HashMap<String,String> data = new HashMap<String,String>();
        Pattern p = compile("[\\{\\}\\=\\, ]++");
        String[] split = p.split(text);
        for ( int i=0; i+2 <= split.length; i+=2 ){
            data.put( split[i], split[i+1] );
        }
        return data;
    }

    /**
     * 通过ID获取数据
     * <p>
     * index  索引，类似数据库
     * type   类型，类似表
     * id     数据ID
     * fields 需要显示的字段，逗号分隔（缺省为全部字段）
     */
    @RequestMapping("/getData/{indexName}/{typeName}/{id}")
    public Object searchDataByIdTest(@PathVariable String indexName,@PathVariable String typeName,@PathVariable Integer id) {
        Map<String, Object> map = ElasticsearchUtils.searchDataById(indexName, typeName, id.toString(), null);
        if(null == map){
            return null;
        }
        return map;
    }




    /**
     * 使用分词查询,并分页
     * <p>
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
    @RequestMapping(value = "/getListDataPage/{indexName}/{typeName}", method = RequestMethod.POST)
    public Object searchDataPage(@PathVariable("indexName") String indexName, @PathVariable("typeName") String typeName, @RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize") Integer pageSize,@RequestParam("searchKey") String searchKey) {

        EsPage esPage = ElasticsearchUtils.searchDataPage(indexName, typeName, currentPage, pageSize, 0, 0, "", "sort_create_time", true, "title", "title="+searchKey);
        List<Map<String,Object>> list =  esPage.getRecordList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String,Object> map: list) {
            try {
                map.put("pub_time", null != map.get("pub_time")? DateUtil.fromTodayFormat(sdf.parse(map.get("pub_time").toString())):null);
//                map.put("title","<span style='color:black;font-size:19px' >"+map.get("title").toString()+"</span>");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return list;

    }



}