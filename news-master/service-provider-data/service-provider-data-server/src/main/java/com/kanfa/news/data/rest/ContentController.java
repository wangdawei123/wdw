package com.kanfa.news.data.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("content")
@Slf4j
public class ContentController /*extends BaseController<ContentBiz, Content> */{

//    @Autowired
//    private ContentBiz contentBiz;

    @RequestMapping(value = "/addContent", method = RequestMethod.GET)
    public ObjectRestResponse addContent(@RequestParam("id") Integer id) {
        ObjectRestResponse response = new ObjectRestResponse<>();
        response.setData("1234");
        return response;
    }

}