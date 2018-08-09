package com.kanfa.news.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 活动服务端提供
 *
 * @author Jezy
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.kanfa.news.activity.mapper")
@RemoteApplicationEventScan(basePackages = "com.kanfa.news.auth.common.event")
public class ActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }
}
