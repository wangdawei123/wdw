package com.kanfa.news.web.feign.callBack;

import com.kanfa.news.web.feign.IContentVideoServiceFeign;
import com.kanfa.news.web.vo.channel.ContentInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kanfa on 2018/3/27.
 */

@Service
public class ContentVideoServiceFallBack implements IContentVideoServiceFeign {

    @Override
    public ContentInfo getContentVideoById(int id){return null;}
}
