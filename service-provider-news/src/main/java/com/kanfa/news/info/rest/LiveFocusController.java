package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.LiveFocusBiz;
import com.kanfa.news.info.entity.LiveFocus;
import com.kanfa.news.info.vo.admin.live.LiveFocusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("liveFocus")
public class LiveFocusController extends BaseController<LiveFocusBiz,LiveFocus> {

    @Autowired
    private LiveFocusBiz liveFocusBiz;

    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    public TableResultResponse<LiveFocusInfo> getPage(@RequestBody LiveFocusInfo entity) {
        return liveFocusBiz.getPage(entity);
    }


}