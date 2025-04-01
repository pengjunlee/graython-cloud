package com.pengjunlee.product.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.stereotype.Component;
import java.util.function.Predicate;

@Component
public class AuthHeaderRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthHeaderRoutePredicateFactory.Config> {

    public AuthHeaderRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            ServerHttpRequest request = exchange.getRequest();
            String authHeader = request.getHeaders().getFirst("Auth"); // 获取请求头 `Auth`

            // 校验 Auth 是否匹配配置的值
            return authHeader != null && authHeader.equals(config.getExpectedValue());
        };
    }

    public static class Config {
        private String expectedValue; // 期望的 Auth 值

        public String getExpectedValue() {
            return expectedValue;
        }

        public void setExpectedValue(String expectedValue) {
            this.expectedValue = expectedValue;
        }
    }
}
