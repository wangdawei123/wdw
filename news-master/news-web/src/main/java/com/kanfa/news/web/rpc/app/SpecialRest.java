package com.kanfa.news.web.rpc.app;

import com.kanfa.news.common.constant.UserConstant;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.ILiveServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web/special")
public class SpecialRest {

    @Autowired
    private ILiveServiceFeign liveServiceFeign;


    /**
     * 专题h5页面接口，app内外使用
     *
     * @param cid
     * @param cate
     * @return
     */
    @RequestMapping(value = "/getNewSpecial", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Object> indexPage(@RequestParam(value = "cid") Integer cid, @RequestParam(value = "cate") Integer cate, @RequestParam(value = "ispreview", required = false) Integer ispreview) {
        ObjectRestResponse response = new ObjectRestResponse();
        //校验参数合法性
        try {
            Assert.notNull(cid, "cid 不能为空");
            Assert.notNull(cate, "cate 不能为空");
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            response.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            return response;
        }
        ObjectRestResponse<Object> res = liveServiceFeign.getNewSpecial(cid, cate);
        return res;
    }

    /**
     * 测试 访问静态html资源
     *
     * @return
     */
    @RequestMapping(value = "/reqHtml", method = RequestMethod.GET)
    public Object indexPage() {
        System.out.println("html html");
        return "specialDetailtest";
    }

}
