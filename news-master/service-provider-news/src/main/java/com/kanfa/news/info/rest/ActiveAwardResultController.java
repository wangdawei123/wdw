package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActiveAwardResultBiz;
import com.kanfa.news.info.entity.ActiveAwardResult;
import com.kanfa.news.info.vo.admin.activity.ActiveAwardResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("activeAwardResult")
public class ActiveAwardResultController extends BaseController<ActiveAwardResultBiz,ActiveAwardResult> {


    @Autowired
    private ActiveAwardResultBiz activeAwardResultBiz;

    @RequestMapping(value = "getActiveAwardResult",method = RequestMethod.GET)
    public List<ActiveAwardResultInfo> getActiveAwardResult(@RequestParam("activityRaffleId")Integer activityRaffleId) {
       return activeAwardResultBiz.getActiveAwardResult(activityRaffleId);
    }

}