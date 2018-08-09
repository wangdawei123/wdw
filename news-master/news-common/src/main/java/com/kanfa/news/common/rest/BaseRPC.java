package com.kanfa.news.common.rest;

import com.kanfa.news.common.context.BaseContextHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseRPC {
    @Autowired
    protected HttpServletRequest request;
    public String getCurrentUserName(){
        return BaseContextHandler.getUsername();
    }

    public String getCurrentUserId(){
        return BaseContextHandler.getUserID();
    }
}
