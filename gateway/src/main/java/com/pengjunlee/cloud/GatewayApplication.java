package com.pengjunlee.cloud;


import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


/**
 * -Dreactor.netty.http.server.accessLogEnabled=true 开启访问日志
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.pengjunlee.cloud","com.alibaba.cloud.sentinel"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
