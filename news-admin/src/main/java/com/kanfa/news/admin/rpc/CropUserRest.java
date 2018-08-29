package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.feign.ICorpUserServiceFeign;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chen
 * on 2018/5/2 14:47
 */

@RestController
@RequestMapping("corpUser")
public class CropUserRest {

    @Autowired
    private ICorpUserServiceFeign corpUserServiceFeign;


    @RequestMapping(value = "/getCorpUserIdName",method = RequestMethod.GET)
    public ObjectRestResponse<List<CorpUser>> getCorpUserIdName(){
        List<CorpUser> cropUserIdName = corpUserServiceFeign.getCorpUserIdName();
        ObjectRestResponse<List<CorpUser>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(cropUserIdName);
        return listObjectRestResponse;
    }


}
