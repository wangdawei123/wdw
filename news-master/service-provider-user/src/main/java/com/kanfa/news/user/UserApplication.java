package com.kanfa.news.user;

import com.ace.cache.EnableAceCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Jezy
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.kanfa.news.user.mapper")
@RemoteApplicationEventScan(basePackages = "com.kanfa.news.auth.common.event")
@EnableAceCache
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
