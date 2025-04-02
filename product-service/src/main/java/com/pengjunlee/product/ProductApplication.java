package com.pengjunlee.product;


import gray.bingo.starter.BingoStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// @EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.pengjunlee.product.mapper"})
public class ProductApplication extends BingoStarter {

    public static void main(String[] args) {
        run(ProductApplication.class, args);
    }
}
