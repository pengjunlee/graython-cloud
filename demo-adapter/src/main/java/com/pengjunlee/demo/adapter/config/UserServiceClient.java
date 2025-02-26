package com.pengjunlee.demo.adapter.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Service
@FeignClient(name = "graython-demo") // 目标服务名称
public interface UserServiceClient {

    @GetMapping("/user/{id}") // 目标服务的 API 路径
    Map<String, Object> getUserById(@PathVariable("id") Long id);

    @GetMapping("/user/test") // 另一个 API 路径
    Map<String, Object> test();
}