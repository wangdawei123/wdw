package com.kanfa.news.web.feign.callBack;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.entity.Content;
import com.kanfa.news.web.feign.IContentServiceFeign;
import com.kanfa.news.web.vo.channel.ContentImageInfo;
import com.kanfa.news.web.vo.channel.ContentInfo;
import com.kanfa.news.web.vo.comment.CommentInfo;
import com.kanfa.news.web.vo.news.ContentDetailInfo;
import com.kanfa.news.web.vo.news.LoveInfo;
import com.kanfa.news.web.vo.news.ViewContentInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kanfa on 2018/3/27.
 */

@Service
public class ContentServiceFallBack implements IContentServiceFeign
{

    @Override
    public ObjectRestResponse<Content> get(int id){return null;}

    @Override
    public ContentInfo getContentArticle(int id){return null;}

    @Override
    public List<ContentInfo> hotgetlist(int contentType){return null;}

    @Override
    public List<ContentImageInfo> getContentImageById(int id){return null;}

    @Override
    public List<ContentImageInfo> getContentActive(ContentImageInfo entity){return null;}

    @Override
    public HashMap<String,Object> getContentPagePC(ContentImageInfo entity){return null;}

    @Override
    public ContentDetailInfo getNewIndex(Integer cid, Integer cate, String fontsize, String devid, Integer uid) {
        return null;
    }

    @Override
    public List<LoveInfo> getLoveList(LoveInfo love) {
        return null;
    }

    @Override
    public ObjectRestResponse<ContentDetailInfo> getNewDetail(Integer id) {
        return null;
    }

    @Override
    public ObjectRestResponse updateContentView(ViewContentInfo viewContentInfo) {
        return null;
    }


    @Override
    public  List<ContentImageInfo> searchLoad(String searchKey,
                                              Integer offset,
                                              Integer limit){return null;}

    @Override
    public Integer updateViews(Integer id){return null;}

    @Override
    public Map<String, Object> getSignPackage(String url) {
        return null;
    }

    @Override
    public ObjectRestResponse getVideoDetail(Integer id) {
        return null;
    }

    @Override
    public ObjectRestResponse getVideoAlbum(Integer id) {
        return null;
    }

    @Override
    public ObjectRestResponse updateVideoViews(Integer id,Integer fromApp) {
        return null;
    }

    @Override
    public ObjectRestResponse getCommentList(CommentInfo commentInfo) {
        return null;
    }

    public Map<String, Object> getWeiXinShareMap(String url) {
        return null;
    }


}
