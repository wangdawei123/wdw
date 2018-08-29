package com.kanfa.news.info.rest.web;

import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ${DESCRIPTION}
 *
 * @author Jezy
 * @version 1.0
 */
@RestController
@RequestMapping("image")
public class ImageController {
    @RequestMapping("/index")
    public String index(Model model, int cid, int cate, HttpServletRequest request) {
        return null;
    }
}
