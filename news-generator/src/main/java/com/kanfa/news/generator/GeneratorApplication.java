package com.kanfa.news.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h2>使用说明：</h2>
 * 该项目模块功能在于构建业务脚手架，生成前端后端代码，可以根据自己业务规则生成前后端代码；<br>
 * 根据自己项目需要配置自己所需要参数，配置详情请查看：
 * <code>application.yml</code>&emsp;<code>generator.properties</code>
 *
 * @author Jezy
 * @version 1.0
 */

@SpringBootApplication
@MapperScan("com.kanfa.news.generator.mapper")
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
