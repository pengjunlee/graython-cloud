package com.pengjunlee.cloud.config;


import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Configuration
public class GatewayInitConfig {


    @PostConstruct
    public void initGatewayRules() {
        // 配置 Nacos 数据源
        // Nacos 服务器地址
        String serverAddr = "127.0.0.1:8848";
        // 配置组
        String groupId = "DEFAULT_GROUP";
        // 数据 ID
        String dataId = "api-gateway-flow-rules";
        // Nacos 用户名
        String username = "nacos";
        // Nacos 密码
        String password = "nacos";

        // 创建 Properties 对象并设置用户名和密码
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("username", username);
        properties.put("password", password);

        // 创建 Nacos 数据源
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(
                properties,
                groupId,
                dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {})
        );

        // 注册数据源到 Sentinel 规则管理器
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
        List<FlowRule> rules = FlowRuleManager.getRules();
        log.info("rules size:{}", rules.size());
    }


//    @Bean
//    public SentinelGatewayFilter sentinelGatewayFilter() {
//        return new SentinelGatewayFilter();
//    }
//
//    @PostConstruct
//    public void initGatewayRules() {
//        Set<GatewayFlowRule> rules = new HashSet<>();
//        rules.add(new GatewayFlowRule("user-service")
//                .setCount(2) // 5 QPS 限流
//                .setIntervalSec(1)); // 1 秒窗口
//
//        GatewayRuleManager.loadRules(rules);
//    }

}
