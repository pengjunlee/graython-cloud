package com.pengjunlee.product.filter;

import com.pengjunlee.product.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
//@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {
    private final ReactiveStringRedisTemplate redisTemplate;
    private static final String TOKEN_HEADER = "Authorization";  // Token 头部名称
    private static final String TOKEN_PREFIX = "Bearer "; // Token 前缀（如 "Bearer "）
    private static final String REDIS_TOKEN_PREFIX = "auth:token:";  // Redis Key 前缀
    private static final Duration TOKEN_EXPIRE_TIME = Duration.ofMinutes(70); // 70 分钟过期
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求头中的 Token
        String token = request.getHeaders().getFirst(TOKEN_HEADER);

        // 校验 Token
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            log.warn("【TokenAuthFilter】缺少或无效的 Token，拒绝访问");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // 直接返回 401
        }

        // 去掉 "Bearer " 前缀，获取真正的 Token 值
        token = token.substring(TOKEN_PREFIX.length());

        // redis是否存在token
        Boolean existToken = redisTemplate.hasKey(REDIS_TOKEN_PREFIX + token).block();
        if (!Boolean.TRUE.equals(existToken)) {
            log.warn("【TokenAuthFilter】缺少或无效的 Token，拒绝访问");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // 直接返回 401
        }
        String newToken = JWTUtil.validateToken(token);

        if (newToken == null) {
            return Mono.error(new RuntimeException("Token 无效或已过期"));
        }

        // 如果 Token 续期，则在响应头中返回新的 Token
        if (!newToken.equals(token)) {
            exchange.getResponse().getHeaders().set(TOKEN_HEADER, TOKEN_PREFIX + newToken);
            redisTemplate.opsForValue()
                    .set(REDIS_TOKEN_PREFIX + token, "", TOKEN_EXPIRE_TIME);
        }

        log.info("【TokenAuthFilter】Token 校验通过，允许访问");
        return chain.filter(exchange); // 继续执行后续过滤器
    }

    @Override
    public int getOrder() {
        return -1; // 过滤器优先级，数值越小，优先级越高
    }

}
