package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.Search;
import com.kanfa.news.info.mapper.SearchMapper;
import com.kanfa.news.info.vo.admin.search.SearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Random;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 14:43:14
 */
@Service
public class SearchBiz extends BaseBiz<SearchMapper,Search> {
    @Autowired
    private SearchMapper searchMapper;


    public Search getSearchKeyword(){
        List<Search> searches = searchMapper.getSearchKeyword();
        Random rand = new Random();
        if(searches != null && searches.size() > 0){
            int i = rand.nextInt(searches.size());
            return searches.get(i);
        }else{
            return null;
        }
    }

    /**
     * 搜索关键字列表的分页
     * @param
     * @return
     */
    public TableResultResponse<SearchInfo> getSearchPage(@RequestBody SearchInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<SearchInfo> list = searchMapper.getSearchPage(entity);
        return new TableResultResponse<SearchInfo>(result.getTotal(),list);
    }


}