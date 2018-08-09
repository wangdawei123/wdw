package com.kanfa.news.app.interceptor;

import feign.Request;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 统一添加request头信息数据
 *
 * @author Jezy
 */
@Component
public class RequestInterceptor implements feign.RequestInterceptor{
    @Override
    public void apply(RequestTemplate requestTemplate) {
        headerInterceptor().apply(requestTemplate);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if(attributes==null){
            return;
        }
        HttpServletRequest httpServletRequest = attributes.getRequest();
        Request request = requestTemplate.request();
        request.headers();
    }

    @Bean
    public RequestInterceptor headerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();
                if(attributes==null){
                    return;
                }
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        requestTemplate.header(name, values);
                    }
                }
            }
        };
    }

}
