package com.kanfa.news.admin;

import com.ace.cache.EnableAceCache;
import com.kanfa.news.auth.client.EnableNewsAuthClient;
import com.kanfa.news.search.client.EnableNewsSearchClient;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-05-25 12:44
 */
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients({
        "com.kanfa.news.auth.client.feign",
        "com.kanfa.news.admin.feign",
        "com.kanfa.news.search.client.feign",
        "com.kanfa.news.data.client.feign"})
@EnableScheduling
@MapperScan("com.kanfa.news.admin.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAceCache
@EnableTransactionManagement
@EnableSwagger2Doc
@EnableNewsAuthClient
@EnableNewsSearchClient
public class AdminBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminBootstrap.class).web(true).run(args);
    }
}
