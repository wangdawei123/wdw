package com.kanfa.news.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 1、SpringCloud提供统一的配置中心，news-config-server统一注册到news-center中心注册，<br>
 * 通过git文件实现动态配置更新；<br>
 * 2、config-server正式环境建议两台。
 *
 * @author Jezy
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
