package com.kanfa.news.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author jezy
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**","/public/**","/MP_verify_WTATYQxrFarxJzHz.txt").addResourceLocations("classpath:/app/static/","classpath:/app/public/","classpath:/app/MP_verify_WTATYQxrFarxJzHz.txt");
        super.addResourceHandlers(registry);
    }
}
