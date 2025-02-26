package com.pengjunlee.demo.adapter;

import gray.bingo.starter.BingoStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @作者 graython
 * @版本 1.0
 * @日期 2024-01-28 13:01
 */
@EnableAsync
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.pengjunlee.demo","gray.bingo"})
@MapperScan(basePackages = {"com.pengjunlee.demo.infrastructure.mapper"})
public class DemoApp extends BingoStarter {



    public static void main(String[] args) {
        run(DemoApp.class, args);
    }
}
