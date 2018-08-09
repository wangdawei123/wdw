package com.kanfa.news.admin.rpc.kpicount;

import com.kanfa.news.admin.entity.KpiContent;
import com.kanfa.news.admin.feign.kpicount.IKpiContentServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("kpiContent")
public class KpiContentRest  {
    @Autowired
    private IKpiContentServiceFeign iKpiContentServiceFeign;

    @GetMapping("/getListByCid")
    public  List<KpiContent> getKpiContentList(@RequestParam("contentId") Integer contentId){
        return iKpiContentServiceFeign.getListByCid(contentId);
    }
}