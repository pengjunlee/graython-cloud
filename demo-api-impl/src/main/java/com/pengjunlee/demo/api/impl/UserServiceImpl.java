package com.pengjunlee.demo.api.impl;


import com.pengjunlee.demo.common.biz.BIZException;
import com.pengjunlee.demo.common.entity.GrayUser;
import gray.bingo.common.exceptions.BingoException;
import gray.bingo.mybatis.datasource.BingoDatasource;
import com.pengjunlee.demo.api.UserService;
import com.pengjunlee.demo.infrastructure.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.pengjunlee.demo.common.biz.BIZExceptionCodeEnum.USERNAME_PASSWORD_ERROR;

@Service
@RequiredArgsConstructor
@BingoDatasource(dbName = "admin")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public Map<String, Object> login(GrayUser grayUser) {
        // 业务逻辑处理示例，此处省略了异常判断，密码加密等逻辑
        // 1. 根据用户名获取用户
        GrayUser dbUser = userRepo.getByUsername(grayUser.getUsername());
        if (Objects.nonNull(dbUser) && grayUser.getPassword().equals(dbUser.getPassword())){
            dbUser.setPassword(null);
            // 生成token
            Map<String,Object> map = new HashMap<>();
            map.put("token","abc123");
            map.put("user",dbUser);
            return map;
        }

        throw new BIZException(USERNAME_PASSWORD_ERROR);
    }
}
