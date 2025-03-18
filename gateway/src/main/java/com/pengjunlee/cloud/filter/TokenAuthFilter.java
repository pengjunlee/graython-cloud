package com.pengjunlee.cloud.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;

@Slf4j
// @Component
public class TokenAuthFilter implements GlobalFilter, Ordered {

    private static final String TOKEN_HEADER = "Authorization";  // Token 头部名称
    private static final String TOKEN_PREFIX = "Bearer "; // Token 前缀（如 "Bearer "）

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

        // 调用自定义方法校验 Token（例如解析 JWT）
        if (!validateToken(token)) {
            log.warn("【TokenAuthFilter】Token 校验失败，拒绝访问");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        log.info("【TokenAuthFilter】Token 校验通过，允许访问");
        return chain.filter(exchange); // 继续执行后续过滤器
    }

    @Override
    public int getOrder() {
        return -1; // 过滤器优先级，数值越小，优先级越高
    }


    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // 256-bit 秘钥

    private boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // 使用密钥解析 JWT
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            log.info("解析后的 JWT 内容: {}", claims);
            return true;
        } catch (Exception e) {
            log.error("JWT 校验失败: {}", e.getMessage());
            return false;
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
