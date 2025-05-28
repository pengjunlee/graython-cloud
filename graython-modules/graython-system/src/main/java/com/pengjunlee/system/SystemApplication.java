package com.pengjunlee.system;

import com.pengjunlee.common.security.annotation.EnableCustomConfig;
import com.pengjunlee.common.security.annotation.EnableRyFeignClients;
import gray.bingo.starter.BingoStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 系统模块
 * 
 * @author graython
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.pengjunlee.system.mapper")  // 指定精确包路径
public class SystemApplication extends BingoStarter
{
    public static void main(String[] args)
    {
        run(SystemApplication.class, args);

    }
}
