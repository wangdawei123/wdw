package com.kanfa.news.web.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.IImageServiceFeign;
import com.kanfa.news.web.vo.channel.ContentImageInfo;
import com.kanfa.news.web.vo.image.ImageDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Jezy
 * @version 1.0
 */
@Controller
@RequestMapping("/web/image")
public class ImageRest {

    @Autowired
    private IImageServiceFeign imageServiceFeign;

    @RequestMapping("/index/{id}/{cate}")
    public String index(Model model, @PathVariable("id") Integer cid,@PathVariable("cate") Integer cate, HttpServletRequest request) throws Exception {
        String nightmode = request.getParameter("nightmode");
        if(cid==null||cate==null) {
            Assert.isNull("cid或cate不合法");
        }
        ObjectRestResponse<ImageDetailInfo> imageInfo = imageServiceFeign.getContentImage(cid,cate);
        ImageDetailInfo data = imageInfo.getData();
        if(null==data){
            //错误提示页面
            model.addAttribute("img","/static/app/assets/web/img/404.jpg");
            return "app/redirect/test";
        }
        List<ContentImageInfo> contentImageList = data.getContentImageList();
        if(contentImageList==null || contentImageList.size()<=0){
            //错误提示页面
            model.addAttribute("img","/static/app/assets/web/img/404.jpg");
            return "app/redirect/test";
        }
        model.addAttribute("data",data);
        model.addAttribute("imageNum",contentImageList.size());
        return "app/image/index";
    }

}
