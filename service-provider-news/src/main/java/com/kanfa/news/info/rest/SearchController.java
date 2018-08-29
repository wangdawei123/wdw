package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ChannelBiz;
import com.kanfa.news.info.biz.ContentBiz;
import com.kanfa.news.info.biz.LiveBiz;
import com.kanfa.news.info.biz.SearchBiz;
import com.kanfa.news.info.entity.Search;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import com.kanfa.news.info.vo.admin.search.SearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("search")
public class SearchController extends BaseController<SearchBiz,Search> {

    @Autowired
    private SearchBiz searchBiz;
    @Autowired
    private ChannelBiz channelBiz;
    @Autowired
    private LiveBiz liveBiz;


    /**
     *  app首页搜索
     */
    @RequestMapping(value = "/nGetSearchList", method = RequestMethod.POST)
    public AppObjectResponse nGetSearchList(@RequestParam("keyword") String keyword,
                                            @RequestParam(value = "id",required = false) Integer id,
                                            @RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pcount") Integer pcount,
                                            @RequestParam(value = "type") Integer type){
        AppObjectResponse<Object> response = new AppObjectResponse<>();
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
            Object[] arr = new Object[]{};
            map.put("list",arr);
            response.setData(map);
            return response;
        }
        map.put("list",list);
        response.setData(map);
        return response;
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

    /**
     * 搜索关键字列表的分页
     * @param
     * @return
     */
    @RequestMapping(value = "/getSearchPage",method = RequestMethod.POST)
    public TableResultResponse<SearchInfo> getSearchPage(@RequestBody SearchInfo entity){
        return  searchBiz.getSearchPage(entity);
    }
}