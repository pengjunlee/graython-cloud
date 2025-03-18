package com.pengjunlee.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
// @Component
public class CustomHeaderGatewayFilter extends AbstractGatewayFilterFactory<CustomHeaderGatewayFilter.Config> {

    public CustomHeaderGatewayFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String customHeaderValue = request.getHeaders().getFirst(config.getHeaderName()); // 获取请求头值

            if (customHeaderValue == null || !customHeaderValue.equals(config.getExpectedValue())) {
                log.warn("【CustomHeaderGatewayFilter】请求头 `{}` 校验失败", config.getHeaderName());
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            log.info("【CustomHeaderGatewayFilter】请求头 `{}` 校验通过", config.getHeaderName());
            return chain.filter(exchange); // 继续请求
        };
    }

    public static class Config {
        private String headerName;    // 请求头名称
        private String expectedValue; // 期望的值

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public String getExpectedValue() {
            return expectedValue;
        }

        public void setExpectedValue(String expectedValue) {
            this.expectedValue = expectedValue;
        }
    }
}
