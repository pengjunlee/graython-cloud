package com.pengjunlee.demo.adapter.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pengjunlee.demo.adapter.config.UserServiceClient;
import com.pengjunlee.demo.common.entity.GrayUser;
import gray.bingo.common.entity.R;
import com.pengjunlee.demo.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author graython
 * @since 2024-10-23 11:36:49
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RestTemplate restTemplate;
    private final UserServiceClient userServiceClient;
    /**
     * 新建职位
     *
     * @return
     */
    @PostMapping(value = "/login")
    public R<Map> login(@RequestBody GrayUser grayUser) {
        Map map = restTemplate.getForObject("http://user-demo/user/test", Map.class);
        return R.ok(map);
    }


    @GetMapping("/abc")
    @SentinelResource(
            blockHandler = "blockHandler",
            fallback = "fallbackHandler"
    )
    public R<Map> abc() {
        // 模拟随机异常
        if (System.currentTimeMillis() % 2 == 0) {
            throw new RuntimeException("业务异常");
        }
        return R.ok(Collections.singletonMap("data", "success"));
    }

    // 正确签名：无参数 + BlockException
    public R<Map> blockHandler(BlockException e) {
        return R.ok(Collections.singletonMap("msg", "请求被限流（自定义处理）"));
    }

    // 降级方法（参数可选）
    public R<Map> fallbackHandler(Throwable e) {
        return R.ok(Collections.singletonMap("msg", "服务降级：" + e.getMessage()));
    }

    @GetMapping(value = "/test")
    public Map test() {
        return new HashMap(){{
            put("name", "pengjunlee");
        }};
    }
}
