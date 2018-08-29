package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.feign.ILiveServiceFeign;
import com.kanfa.news.admin.vo.content.LocationInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private IContentServiceFeign contentServiceFeign;

    @Autowired
    private ILiveServiceFeign liveServiceFeign;

    //增加视频关联
    @RequestMapping(value = "/date", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse add(OB ob) {
        return new ObjectRestResponse();
    }


    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public ObjectRestResponse addLocation(@RequestBody LocationInfo locationInfo) {
        return contentServiceFeign.saveMogoLocation(locationInfo);
    }

    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    public ObjectRestResponse feign() {
        liveServiceFeign.get(1);
        return liveServiceFeign.get(1);
    }
}

class OB implements Serializable {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
