package com.kanfa.news.admin.feign.datacount;

import com.kanfa.news.admin.entity.Dictionary;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 9:56
 */
@FeignClient(name = "service-provider-news")
public interface IDictionaryServiceFeign {
    /**
     * 列表
     * @return
     */
    @GetMapping("/dictionary/all")
    List<Dictionary> getList();


}
