package com.kanfa.news.web.rpc.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kanfa on 2018/6/15.
 */

@Controller
@RequestMapping("/web/redirect")
public class RedirectRest {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String jump(Model model) {
        model.addAttribute("img" , "assets/web/img/404.jpg");
        return "redirect/test";
    }



}
