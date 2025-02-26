package com.pengjunlee.demo.adapter.controller;

import com.pengjunlee.demo.adapter.config.UserServiceClient;
import com.pengjunlee.demo.common.entity.GrayUser;
import gray.bingo.common.entity.R;
import com.pengjunlee.demo.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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


    @GetMapping(value = "/abc")
    public R<Map> abc() {
        return R.ok(userServiceClient.test());
    }

    @GetMapping(value = "/test")
    public Map test() {
        return new HashMap(){{
            put("name", "pengjunlee");
        }};
    }
}
