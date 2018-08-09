package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.Search;
import com.kanfa.news.app.mapper.SearchMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}