package com.pengjunlee.product.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtil {

    private static final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyz0123456789graython"; // 256-bit 秘钥
    private static final Key SIGNING_KEY = generateSigningKey();
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 小时
    private static final long RENEW_THRESHOLD = 1000 * 60 * 10; // 剩 10 分钟自动续期


    public static String validateToken(String token) {
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
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6InBlbmdqdW5sZWUiLCJpYXQiOjE3NDIzNjE3MzUsImV4cCI6MTc0MjM2NTMzNX0.iz4y02LFpfDrva-sjfWMo1XCq0BF3Qeb15Fouj9z-Ck"));;

    }
}
