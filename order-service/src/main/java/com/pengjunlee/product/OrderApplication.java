package com.pengjunlee.product;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.pengjunlee.product.mapper"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
