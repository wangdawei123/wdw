package com.kanfa.news.common.rest;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.mongoDBUtil.biz.ViewContentService;
import com.kanfa.news.common.mongoDBUtil.entity.ViewContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mongodb")
public class MongoDBController {

    @Resource
    private ViewContentService viewContentService;

    @RequestMapping(value = "/viewcontent/{id}")
    @ResponseBody
    public ViewContent getViewContent(@PathVariable Integer id){

        ViewContent viewContent = viewContentService.getViewContent();
        return viewContent;
    }

    @RequestMapping(value = "/viewcontent/{start}/{end}")
    @ResponseBody
    public List<ViewContent> listByDate(@PathVariable String start, @PathVariable String end){

        int startDate  = transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        List<ViewContent> resultList = viewContentService.listByDate(startDate,endDate);
        return resultList;
    }

    public  Integer transForMilliSecondByTim(String dateStr,String tim){
        SimpleDateFormat sdf=new SimpleDateFormat(tim);
        Date date =null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? null : transForMilliSecond(date);
    }

    /**
     * 日期转时间戳
     * @param date
     * @return
     */
    public  Integer transForMilliSecond(Date date){
        if(date==null) return null;
        return (int)(date.getTime()/1000);
    }

}
