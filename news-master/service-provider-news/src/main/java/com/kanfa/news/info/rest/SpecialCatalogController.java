package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentSpecialBiz;
import com.kanfa.news.info.biz.SpecialCatalogBiz;
import com.kanfa.news.info.entity.ContentSpecial;
import com.kanfa.news.info.entity.SpecialCatalog;
import com.kanfa.news.info.vo.admin.info.SubjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("specialCatalog")
public class SpecialCatalogController extends BaseController<SpecialCatalogBiz,SpecialCatalog> {

    @Autowired
    private SpecialCatalogBiz specialCatalogBiz;
    @Autowired
    private ContentSpecialBiz contentSpecialBiz;

    /**
     * 保存子目录
     * @param subjectInfo
     * @return
     */
    @RequestMapping(value = "/saveSpecialCatalog",method = RequestMethod.POST)
    public ObjectRestResponse<Integer> saveSpecialCatalog(@RequestBody SubjectInfo subjectInfo){
        ObjectRestResponse<Integer> objectRestResponse=new ObjectRestResponse<>();
//        List<SubjectCatalogInfo> childCatalogList = subjectInfo.getChildCatalogList();
        Integer flag=specialCatalogBiz.saveSpecialCatalog(subjectInfo);
        objectRestResponse.setData(flag);
        return objectRestResponse;
    }

    /**
     *解除子目录绑定
     * @param cid
     * @param targetId
     * @return
     */
    @RequestMapping(value = "/deleteBind",method = RequestMethod.GET)
    public ObjectRestResponse<Integer> deleteBind(@RequestParam("cid") Integer cid, @RequestParam("targetId") Integer targetId){
        ContentSpecial contentSpecial=new ContentSpecial();
        contentSpecial.setContentId(cid);
        contentSpecial.setTargetCid(targetId);
        contentSpecialBiz.delete(contentSpecial);
        return new ObjectRestResponse<>();
    }

    /**
     * 删除子目录
     * @param cid
     * @param cataLogId
     * @return
     */
    @RequestMapping(value = "/deleteSpecialCatalog",method = RequestMethod.GET)
    public ObjectRestResponse<Integer> deleteSpecialCatalog(@RequestParam("cid") Integer cid, @RequestParam("cataLogId") Integer cataLogId){
        specialCatalogBiz.deleteSpecialCatalog(cataLogId);
        return new ObjectRestResponse<>();
    }
}