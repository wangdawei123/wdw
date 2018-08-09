package com.kanfa.news.search.databatchimport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @author Jezy
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RemoteApplicationEventScan(basePackages = "com.kanfa.news.auth.common.event")
@EnableAutoConfiguration
public class SearchDataBatchImportApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchDataBatchImportApplication.class, args);
    }
}
