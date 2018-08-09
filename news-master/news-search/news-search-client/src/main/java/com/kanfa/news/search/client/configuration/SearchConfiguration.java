package com.kanfa.news.search.client.configuration;

import com.kanfa.news.search.client.aspect.SearchAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/6/6 13:45
 */
@Configuration
@EnableAspectJAutoProxy
@Primary
public class SearchConfiguration implements WebMvcConfigurer  {

    @Bean
    public SearchAspect searchAspect(){
        return new SearchAspect();
    }
}
