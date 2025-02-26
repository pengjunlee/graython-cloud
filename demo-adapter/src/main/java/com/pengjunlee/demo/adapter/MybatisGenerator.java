package com.pengjunlee.demo.adapter;

import gray.bingo.mybatis.generator.BingoMybatisGenerator;
import gray.bingo.mybatis.generator.BingoMybatisGeneratorConfig;

public class MybatisGenerator {

    public static void main(String[] args) {
        // 数据库url
        final String DB_URL = "jdbc:mysql://192.168.192.66:3306/workstation?useUnicode=true&characterEncoding=UTF8&autoReconnect=true&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=Asia/Shanghai";
        // 数据库用户名
        final String USERNAME = "root";
        // 数据库密码
        final String PASSWORD = "Root@123456";
        // 需要生成代码的数据表
        String[] tableNames = {"gray_job"};
        BingoMybatisGeneratorConfig generatorConfig = BingoMybatisGeneratorConfig.build4MultiModule()
                .dataSource(DB_URL, USERNAME, PASSWORD)
                .author("graython")
                .entity("demo-common", "com.pengjunlee.demo.common.entity")
                .mapper("demo-infrastructure", "com.pengjunlee.demo.infrastructure.mapper")
                .service("demo-infrastructure", "com.pengjunlee.demo.infrastructure.repo", "demo-infrastructure", "com.pengjunlee.demo.infrastructure.repo.impl","Repo")
                .controller("demo-adapter", "com.pengjunlee.demo.adapter.controller")
                .build();

//        BingoPlusConfig generatorConfig = BingoPlusConfig.build4SingleModule()
//                .author("graython")
//                .enableSwagger(false)
//                .packageName("com.pengjunlee.demo.infrastructure")
//                .enableService(true)
//                .enableController(true)
//                .build();
        BingoMybatisGenerator.generate(tableNames, generatorConfig);
    }
}
