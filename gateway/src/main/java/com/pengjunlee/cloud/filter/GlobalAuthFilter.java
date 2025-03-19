package com.pengjunlee.cloud.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    private static final String TOKEN_HEADER = "Authorization";  // Token 头部名称
    private static final String TOKEN_PREFIX = "Bearer "; // Token 前缀（如 "Bearer "）
    private static final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyz0123456789graython"; // 256-bit 秘钥
    private static final Key SIGNING_KEY = generateSigningKey();
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 小时
    private static final long RENEW_THRESHOLD = 1000 * 60 * 10; // 剩 10 分钟自动续期

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

        String newToken = validateToken(token);

        if (newToken == null) {
            return Mono.error(new RuntimeException("Token 无效或已过期"));
        }

        // 如果 Token 续期，则在响应头中返回新的 Token
        if (!newToken.equals(token)) {
            exchange.getResponse().getHeaders().set(TOKEN_HEADER, TOKEN_PREFIX + newToken);
        }

        log.info("【TokenAuthFilter】Token 校验通过，允许访问");
        return chain.filter(exchange); // 继续执行后续过滤器
    }

    @Override
    public int getOrder() {
        return -1; // 过滤器优先级，数值越小，优先级越高
    }

    private static String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY) // 使用密钥解析 JWT
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            log.info("解析后的 JWT 内容: {}", claims);
            Date expiration = claims.getExpiration();
            long remainingTime = expiration.getTime() - System.currentTimeMillis();

            // 如果 Token 剩余时间小于 10 分钟，则续期
            if (remainingTime < RENEW_THRESHOLD) {
                System.out.println("Token 续期...");
                return generateToken(claims.getSubject());
            }
        } catch (Exception e) {
            log.error("JWT 校验失败: {}", e.getMessage());
        }
        return null;
    }

    // 生成 Token
    public static String generateToken(String username) {
        long expirationTime = 1000 * 60 * 60; // 1 小时有效期
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER"); // 自定义字段
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key generateSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzQyMzQ3NDQxLCJleHAiOjE3NDIzNTEwNDF9.mn6xiTchpSfhPFxcwpeY3jWh6-lon5my0h_Sg7XmsSw"));
    }
}
