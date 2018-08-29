package com.kanfa.news.info.biz;

import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.info.entity.ContentSpecial;
import com.kanfa.news.info.mapper.ContentSpecialMapper;
import com.kanfa.news.info.vo.admin.info.SpecialCatalogInfo;
import com.kanfa.news.info.vo.admin.info.SubjectCatalogInfo;
import com.kanfa.news.info.vo.admin.info.SubjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.SpecialCatalog;
import com.kanfa.news.info.mapper.SpecialCatalogMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 专题-目录表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 15:36:06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecialCatalogBiz extends BaseBiz<SpecialCatalogMapper,SpecialCatalog> {

    @Autowired
    private SpecialCatalogMapper specialCatalogMapper;
    @Autowired
    private ContentSpecialMapper contentSpecialMapper;

    /**
     * 查询专题子目录
     * @param specialCatalog
     * @return
     */
    public List<SubjectCatalogInfo> selectSpecialCatalogList(SpecialCatalog specialCatalog) {
        return specialCatalogMapper.selectSpecialCatalogList(specialCatalog);
    }

    /**
     * 保存子目录
     * @param subjectInfo
     * @return
     */
    public Integer saveSpecialCatalog(SubjectInfo subjectInfo) {
        //删除子目录
        if(subjectInfo.getId()!=null){
            SpecialCatalog specialCatalog=new SpecialCatalog();
            specialCatalog.setCid(subjectInfo.getId());
            specialCatalog.setIsDelete(0);
            specialCatalogMapper.deleteSpecialCatalog(specialCatalog);
        }
        bindSubjectCatalog(subjectInfo);
        return 1;
    }
    private void bindSubjectCatalog(SubjectInfo subjectInfo) {
        Date date=new Date();
        List<SubjectCatalogInfo> childCatalogList = subjectInfo.getChildCatalogList();
        for (SubjectCatalogInfo subjectCatalogInfo : childCatalogList) {
            SpecialCatalog specialCatalog=new SpecialCatalog();
            specialCatalog.setCid(subjectInfo.getId());
            specialCatalog.setCreateTime(date);
            specialCatalog.setUpdateTime(date);
            specialCatalog.setCreateUid(subjectInfo.getCreateUid());
            specialCatalog.setUpdateUid(subjectInfo.getCreateUid());
            specialCatalog.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
            specialCatalog.setTitle(subjectCatalogInfo.getTitle());
            specialCatalogMapper.addSpecialCatalog(specialCatalog);
            if(subjectCatalogInfo.getContentIds() != null && subjectCatalogInfo.getContentIds().size() > 0){
                for (Integer  cid: subjectCatalogInfo.getContentIds()) {
                    ContentSpecial contentSpecial = new ContentSpecial();
                    contentSpecial.setCatalogId(specialCatalog.getId());
                    contentSpecial.setContentId(subjectInfo.getId());
                    contentSpecial.setTargetCid(cid);
                    contentSpecial.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
                    contentSpecial.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
                    contentSpecial.setPublishTime(date);
                    //排序值
                    contentSpecialMapper.addContentSpecial(contentSpecial);
                }
            }
        }
    }

    /**
     * 删除子目录
     * @param cataLogId
     */
    public void deleteSpecialCatalog(Integer cataLogId) {
        SpecialCatalog specialCatalog=new SpecialCatalog();
        specialCatalog.setId(cataLogId);
        specialCatalog.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
        specialCatalogMapper.updateByPrimaryKeySelective(specialCatalog);
        //查询绑定内容
        ContentSpecial contentSpecial=new ContentSpecial();
        contentSpecial.setCatalogId(cataLogId);
        List<ContentSpecial> select = contentSpecialMapper.select(contentSpecial);
        if(select!=null&&select.size()>0){//批量更新
            for (ContentSpecial special : select) {
                special.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
                contentSpecialMapper.updateByPrimaryKeySelective(special);
            }
        }
    }
}