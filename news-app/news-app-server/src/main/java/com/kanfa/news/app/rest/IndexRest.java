package com.kanfa.news.app.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.app.biz.ChannelBiz;
import com.kanfa.news.app.biz.LiveBiz;
import com.kanfa.news.app.biz.SearchBiz;
import com.kanfa.news.app.entity.Search;
import com.kanfa.news.app.feign.IIndexServiceFeign;
import com.kanfa.news.app.feign.ISearchServiceFeign;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.AppCommonType;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.util.DateUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("index")
@RefreshScope
public class IndexRest extends BaseRest {
    @Autowired
    private SearchBiz searchBiz;
    @Autowired
    private ChannelBiz channelBiz;
    @Autowired
    private LiveBiz liveBiz;
    @Autowired
    private ISearchServiceFeign searchServiceFeign;


    /**
     *  app首页搜索 -- 新版 -- 数据库
     */
    @RequestMapping(value = "/getSearchList", method = RequestMethod.POST)
    public AppObjectResponse getSearchList(@RequestParam("keyword") String keyword,
                                            @RequestParam(value = "id" ,required = false) Integer id,
                                            @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                            @RequestParam(value = "pcount" ,defaultValue = "20") Integer pcount,
                                            @RequestParam(value = "type" ,defaultValue = "0") Integer type) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if(keyword == null ) {
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }else {
            if(id != null){
                Search search = searchBiz.selectById(id);
                if(search != null){
                    search.setClickNum(search.getClickNum()+ LiveCommonEnum.CONSTANT_ONE);
                    searchBiz.updateSelectiveById(search);
                }
            }
            //获取搜索数据
            Map<String ,Object> map = new HashMap<>(5);
            List<Map<String ,Object>> list = channelBiz.getKeywordData(null ,page ,pcount ,1 ,keyword ,1 ,type);
            if(list == null || list.size() == LiveCommonEnum.CONSTANT_ZERO){
                list = new ArrayList<>();
            }
            map.put("list",list);
            response.setData(map);
            return response;
        }
    }

    @RequestMapping(value = "/nGetSearchList", method = RequestMethod.POST)
    public AppObjectResponse nGetSearchList(@RequestParam("keyword") String keyword,
                                            @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                            @RequestParam(value = "pcount" ,defaultValue = "20") Integer pcount,
                                            @RequestParam(value = "type" ,defaultValue = "0") Integer type) throws ParseException {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        String indexName = "kf_news_dev_content";
        String typeName = "kf_content";
        if(type == 1){
            keyword = keyword + ",content_type=4" ;
        }else if(type == 3){
            keyword = keyword + ",source_type=1";
        }else if(type == 2){
            indexName = "kf_news_dev_live";
            typeName = "kf_live";
        }

        if(keyword == null ) {
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }
            //获取搜索数据
            Map<String ,Object> map = new HashMap<>(5);
            List<Map<String, Object>> list = (List<Map<String, Object>>)searchServiceFeign.searchDataPage(indexName,
                                                typeName,page ,  pcount ,keyword);
            map.put("list",list);
            response.setData(map);
            return response;
        }

    public Map<String, Object> getTags(){
        String tag_color = AppCommonType.tag_color;
        JSONObject object = JSON.parseObject(tag_color);
        String value = object.getString("value");
        JSONObject valueObject = JSON.parseObject(value);
        String contentJson = valueObject.getString("original");
        JSONObject contentObject = JSON.parseObject(contentJson);
        Map<String, Object> tags = new HashMap<>(14);
        tags.put("name","原创");
        tags.put("font_color",contentObject.getString("font_color"));
        tags.put("border_color",contentObject.getString("border_color"));
        return tags;
    }

    /**
     * getIndexTopArea 首页热词搜索和直播数据接口
     * @return [type] 搜索热词和直播数
     */
    @RequestMapping(value = "/getIndexTopArea", method = RequestMethod.POST)
    public AppObjectResponse getIndexTopArea(){
        Map<String ,Object> map = new HashMap<>(5);
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        Search searchKeyword = searchBiz.getSearchKeyword();
        if(searchKeyword != null){
            map.put("keyword",searchKeyword.getName());
            map.put("keyword_id",searchKeyword.getId());
            map.put("is_keyword",LiveCommonEnum.CONSTANT_ONE);
        }else{
            map.put("is_keyword",LiveCommonEnum.CONSTANT_ZERO);
            map.put("keyword",LiveCommonEnum.SEARCH_KEYWORD);
        }
        String result = liveBiz.getLiveAllData();
        map.put("live_num",result);
        response.setData(map);
        return response;
    }
    @Value("${app.video.album.showView}")
    private String keyPrefix;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public AppObjectResponse test(){
        System.out.println(keyPrefix);
        return null;
    }

}
