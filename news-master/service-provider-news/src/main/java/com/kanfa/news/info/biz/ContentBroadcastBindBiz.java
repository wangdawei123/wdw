package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import com.kanfa.news.info.vo.admin.live.LiveSpecialBindInfo;
import com.kanfa.news.info.vo.admin.video.ContentBroadcastBindInfo;
import com.kanfa.news.info.vo.admin.web.ContentDetailBindInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.ContentBroadcastBind;
import com.kanfa.news.info.mapper.ContentBroadcastBindMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 关联内容表
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:46:08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentBroadcastBindBiz extends BaseBiz<ContentBroadcastBindMapper,ContentBroadcastBind> {

    @Autowired
    private ContentBroadcastBindMapper contentBroadcastBindMapper;

    //视频列表 关联内容
    public TableResultResponse<ContentBroadcastBindInfo> getPage(Query query,
                                                                 Integer contentId){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<ContentBroadcastBindInfo> list = contentBroadcastBindMapper.getPage(contentId);
        //为ContentTypeCh赋值
        for (ContentBroadcastBindInfo contentBroadcastBindInfo:list) {
            Integer contentType = contentBroadcastBindInfo.getContentType();
            contentBroadcastBindInfo.setContentTypeCh(NewsEnumsConsts.ContentOfContentType.getType(contentType));
        }
        return new TableResultResponse<ContentBroadcastBindInfo>(result.getTotal(), list);
    }


    // 关联内容的按标题搜索
    public TableResultResponse<ContentBroadcastBindInfo> getSearchPage(Query query,
                                                                  Integer contentId,
                                                                  String title){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<ContentBroadcastBindInfo> searchlist = contentBroadcastBindMapper.getSearchPage(contentId,title);
        //为contentTypeCh的属性赋值
        for (ContentBroadcastBindInfo contentBroadcastBindInfo:searchlist) {
            contentBroadcastBindInfo.setContentTypeCh(NewsEnumsConsts.ContentOfContentType.getType(contentBroadcastBindInfo.getContentType()));
        }
        return new TableResultResponse<ContentBroadcastBindInfo>(result.getTotal(), searchlist);
    }

    /**
     * 查询已经绑定的内容
     * @param contentBroadcastBind
     * @return
     */
    public List<ContentBroadcastBindInfo> getContentBind(ContentBroadcastBind contentBroadcastBind) {
        return contentBroadcastBindMapper.getContentBind(contentBroadcastBind);
    }

    /**
     * 绑定分页查询
     * @param params
     * @return
     */
    public TableResultResponse<ContentBroadcastBindInfo> getContentBindPage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        ContentBroadcastBind contentBroadcastBind = new ContentBroadcastBind();
        contentBroadcastBind.setContentId(Integer.valueOf(params.get("contentId").toString()));
        List<ContentBroadcastBindInfo> searchlist = contentBroadcastBindMapper.getContentBind(contentBroadcastBind);
        return new TableResultResponse<>(result.getTotal(), searchlist);
    }
    public Integer getMaxOrderNumber(){
        return contentBroadcastBindMapper.getMaxOrderNumber();
    }

    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    public List<ContentBroadcastBind> getBroadcastBind(List<Integer> ids, Integer contentId) {
       return contentBroadcastBindMapper.getBroadcastBind(ids,contentId);
    }

    /**
     * 批量更新
     * @param contentBroadcastBindList
     * @return
     */
    public Integer batchUpdate(List<ContentBroadcastBind> contentBroadcastBindList) {
        int num = 0;
        for (ContentBroadcastBind contentBroadcastBind : contentBroadcastBindList) {
            int flag = contentBroadcastBindMapper.updateByPrimaryKeySelective(contentBroadcastBind);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }

    /**
     * 视频分享查询绑定内容
     * @param id
     * @param
     * @return ids
     */
    public List<Integer> getBroadcastBindBindIds(Integer id){
        return contentBroadcastBindMapper.getBroadcastBindBindIds(id);
    }


     /* 查询绑定集合
     * @param cid
     * @return
     */
    public List<ContentInfo> getContentBroadBindByCid(Integer cid) {
        return contentBroadcastBindMapper.getContentBroadBindByCid(cid);
    }

    /**
     * 分享绑定内容集合
     * @param cid
     * @return
     */
    public List<ContentInfo> getContentBindShareList(Integer cid) {
        return contentBroadcastBindMapper.getContentBindShareList(cid);
    }

}