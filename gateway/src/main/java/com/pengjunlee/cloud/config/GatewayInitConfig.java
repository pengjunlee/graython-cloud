package com.pengjunlee.cloud.config;


import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class GatewayInitConfig {

    @Bean
    public SentinelGatewayFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("user-service")
                .setCount(2) // 5 QPS 限流
                .setIntervalSec(1)); // 1 秒窗口

        GatewayRuleManager.loadRules(rules);
    }

    @PostConstruct
    public void init() {
        GatewayCallbackManager.setBlockHandler((exchange, ex) -> {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", System.currentTimeMillis());

            if (ex instanceof ParamFlowException) {
                response.put("code", 1002);
                response.put("message", "请求参数限流，请稍后再试");
            } else {
                response.put("code", 1001);
                response.put("message", "请求过于频繁，请稍后再试");
            }

            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(response));
        });
    }
}
