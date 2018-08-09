package com.kanfa.news.search.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.*;
import org.elasticsearch.common.transport.*;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.net.InetAddress;

/**
 * 描述: elasticsearch 配置
 *
 * @author yanpenglei
 * @create 2017-11-02 16:41
 **/
@Configuration
public class ElasticsearchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchConfig.class);

    /**
     * es集群地址
     */
    @Value("${spring.data.elasticsearch.ip}")
    private String hostName;

    /**
     * 端口
     */
    @Value("${spring.data.elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${spring.data.elasticsearch.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${spring.data.elasticsearch.pool}")
    private String poolSize;

    @Bean
    public TransportClient init() {

        TransportClient transportClient = null;

        try {
            // 配置信息
           /* Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName)
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
                    .build();*/
            //创建client
            transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));

        } catch (Exception e) {
            LOGGER.error("elasticsearch TransportClient create error!!!", e);
        }

        return transportClient;
    }


}
