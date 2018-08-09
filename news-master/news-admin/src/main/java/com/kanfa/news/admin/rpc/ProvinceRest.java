package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Province;
import com.kanfa.news.admin.feign.IProvinceServiceFeign;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chen
 * on 2018/3/18 16:42
 */

@RestController
@RequestMapping("province")
public class ProvinceRest {

    @Autowired
    private IProvinceServiceFeign provinceServiceFeign;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<Province> get(@PathVariable("id") Integer id) {
        return provinceServiceFeign.get(id);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ObjectRestResponse all(){
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        List<Province> all = provinceServiceFeign.all();
        objectRestResponse.setData(all);
        return objectRestResponse;
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }

}
