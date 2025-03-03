package com.pengjunlee.demo.adapter;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.pengjunlee.demo.api.UserService;
import gray.bingo.common.utils.SpringUtil;
import gray.bingo.mybatis.config.MybatisPlusConfig;
import gray.bingo.starter.BingoStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StopWatch;

/**
 * @作者 graython
 * @版本 1.0
 * @日期 2024-01-28 13:01
 */
@EnableAsync
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"gray.bingo","com.pengjunlee.demo"}, exclude = {
        DataSourceAutoConfiguration.class,          // 排除默认数据源配置
        // DataSourceTransactionManagerAutoConfiguration.class,
        // MybatisPlusAutoConfiguration.class          // 若使用MyBatis-Plus也需排除
})
@MapperScan(basePackages = {"com.pengjunlee.demo.infrastructure.mapper"})
public class DemoApp extends BingoStarter {


    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("耗时统计");
        stopWatch.start("容器启动");

        run(DemoApp.class, args);
        UserService bean = SpringUtil.getBean(UserService.class);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
