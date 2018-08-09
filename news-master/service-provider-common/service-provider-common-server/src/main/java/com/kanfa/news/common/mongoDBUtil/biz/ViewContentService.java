package com.kanfa.news.common.mongoDBUtil.biz;

import com.kanfa.news.common.mongoDBUtil.entity.ViewContent;
import com.kanfa.news.common.mongoDBUtil.mapper.MongodbBaseDao;
import com.kanfa.news.common.mongoDBUtil.mapper.ViewContentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("viewContentService")
public class ViewContentService {

    @Resource
    private ViewContentDao viewContentDao;

    public ViewContent getViewContent(){
        return viewContentDao.findViewContent(new HashMap());
    }

    public List<ViewContent> listByDate(Integer start,Integer end){
        return viewContentDao.listViewContent(start,end);
    }
}
