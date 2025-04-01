package com.pengjunlee.product;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * -Dreactor.netty.http.server.accessLogEnabled=true 开启访问日志
 * 完成功能安排：
 * 1. JWT token鉴权
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.pengjunlee.product"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
