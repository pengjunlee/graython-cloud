package com.pengjunlee.system;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import gray.bingo.db.datasource.DynamicDataSource;
import gray.bingo.starter.BingoStarter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pengjunlee.common.security.annotation.EnableCustomConfig;
import com.pengjunlee.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 系统模块
 * 
 * @author graython
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class },scanBasePackages = {"gray.bingo","com.pengjunlee"})
@MapperScan("com.pengjunlee.system.mapper")  // 指定精确包路径
public class SystemApplication extends BingoStarter
{
    public static void main(String[] args)
    {
        run(SystemApplication.class, args);

    }

    @Value("${mybatis.mapper-locations:classpath*:mapper/**/*.xml}")
    private String mapperLocations;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setTypeAliasesPackage("com.pengjunlee.**.domain");
        // 正确加载 XML 文件（支持多级目录）
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
        return sessionFactory.getObject();
    }
}
