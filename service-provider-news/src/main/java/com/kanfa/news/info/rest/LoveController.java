package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.LoveBiz;
import com.kanfa.news.info.entity.Love;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("love")
public class LoveController extends BaseController<LoveBiz,Love> {

    /**
     * 查询用户点赞
     * @param love
     * @return
     */
    @RequestMapping(value = "/getLoveList",method = RequestMethod.POST)
    public List<Love> getLoveList(@RequestBody Love love){
        return this.baseBiz.getLoveList(love);
    }

    /**
     * 点赞
     * @param love
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    @Override
    public ObjectRestResponse<Love> add(@RequestBody Love love) {
        Love loveOne = this.baseBiz.getLoveOne(love);
        if(loveOne!=null){
            getObjectRestResponse("您已经点过赞了~");
        }
        ObjectRestResponse<Love> add = super.add(love);
        add.setMessage("点赞成功");
        return add;
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}