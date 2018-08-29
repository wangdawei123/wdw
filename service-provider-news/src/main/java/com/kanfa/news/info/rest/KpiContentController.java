package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.KpiContentBiz;
import com.kanfa.news.info.entity.KpiContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("kpiContent")
public class KpiContentController extends BaseController<KpiContentBiz,KpiContent> {
    @Autowired
    private KpiContentBiz kpiContentBiz;

    @GetMapping("/getListByCid")
    List<KpiContent> getKpiContentList(@RequestParam Integer contentId){
        KpiContent entity=new KpiContent();
        entity.setContentId(contentId);
        List<KpiContent> list = kpiContentBiz.selectList(entity);
        return list;
    }
}