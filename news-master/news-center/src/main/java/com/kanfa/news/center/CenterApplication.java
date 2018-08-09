package com.kanfa.news.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <h3>使用说明</h3>
 * 服务注册中心采用Eureka进行服务注册中心提供给其他应用进行对话，提供服务内容包含模块如下：
 * <ul>
 * <li>new-service-provider</li>
 * <li>new-service-consumer</li>
 * <li>...</li>
 * </ul>
 * 正式环境下注册中心高可用两台对应端口号：8761、8762防止注册中心挂掉，后期不够用再加应用；<br>
 * 测试环境下域名可以采用项目模块名命名，如：<example>http:${model-name}:port/center</example>
 *
 * <h3>实现方式</h3>
 * SpringBoot通过注解方式实现服务的注册，通过{@link EnableEurekaServer}注解实现注册中心服务，<br>
 * 系统实现的所有微服务都必须注册到注册中心。
 *
 * @author Jezy
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class CenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class, args);
    }
}
