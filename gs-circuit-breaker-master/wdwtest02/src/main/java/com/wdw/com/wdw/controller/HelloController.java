package com.wdw.com.wdw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2018/8/9.
 */
@RestController
public class HelloController {
    @RequestMapping("/index")
    private String index(){
        System.out.println("hello world ! kanfa");
        return "hello world ! kanfa234";
    }
}
