package com.kanfa.news.info.rest;


import com.kanfa.news.info.mongodb.biz.CountViewContentService;
import com.kanfa.news.info.mongodb.biz.ViewContentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("mongodb")
public class MongodbController {

    @Resource
    private ViewContentService viewContentService;

    @Resource
    private CountViewContentService countViewContentService;



    @RequestMapping(value = "/viewcontent/pvvv")
    @ResponseBody
    public Map<String,Object> listByDate(@RequestParam String startDate, @RequestParam String endDate){

        return  viewContentService.getPvAndVv(startDate,endDate);
    }
/*    @RequestMapping(value = "/countviewcontent/{start}/{end}")
    @ResponseBody
    public Map<String,Object> listByDate(@PathVariable String start, @PathVariable String end, @RequestParam(required = false) Boolean isCount){
        return countViewContentService.getTotalAndAllPv(start,end,isCount);
    }*/


}
