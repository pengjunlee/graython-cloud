package com.pengjunlee.system;

import com.pengjunlee.common.security.annotation.EnableCustomConfig;
import com.pengjunlee.common.security.annotation.EnableRyFeignClients;
import gray.bingo.starter.BingoStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 系统模块
 * 
 * @author graython
 */
@EnableCustomConfig
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SystemApplication extends BingoStarter
{
    public static void main(String[] args)
    {
       SpringApplication.run(SystemApplication.class, args);
    }
}
