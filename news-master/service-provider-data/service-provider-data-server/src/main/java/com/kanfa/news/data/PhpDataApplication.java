package com.kanfa.news.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 资讯服务应用提供
 *
 * @author jezy
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.kanfa.news.data.mapper")
@RemoteApplicationEventScan(basePackages = "com.kanfa.news.auth.common.event")
public class PhpDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhpDataApplication.class, args);
    }
}
