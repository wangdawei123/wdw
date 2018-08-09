package com.kanfa.news.auth.client.configuration;

import com.kanfa.news.auth.client.config.ServiceAuthConfig;
import com.kanfa.news.auth.client.config.UserAuthConfig;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
@ComponentScan({"com.kanfa.news.auth.client"})
@RemoteApplicationEventScan(basePackages = "com.kanfa.news.auth.common.event")
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }

}
