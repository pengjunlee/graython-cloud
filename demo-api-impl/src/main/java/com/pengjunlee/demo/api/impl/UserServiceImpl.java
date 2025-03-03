package com.pengjunlee.demo.api.impl;


import com.pengjunlee.demo.common.biz.BIZException;
import com.pengjunlee.demo.common.entity.GrayUser;
import gray.bingo.common.exceptions.BingoException;
import gray.bingo.mybatis.datasource.BingoDatasource;
import com.pengjunlee.demo.api.UserService;
import com.pengjunlee.demo.infrastructure.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.pengjunlee.demo.common.biz.BIZExceptionCodeEnum.USERNAME_PASSWORD_ERROR;

@Service
@BingoDatasource(dbName = "admin")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    @PostConstruct
    public void postConstruct(){
        log.info("[    HELPER_REGISTRAR] >>> 完成实例化 [ {} ]", UserServiceImpl.class);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("[    HELPER_REGISTRAR] >>> 准备销毁 [ {} ]", UserServiceImpl.class);
    }

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

//
//
//    @Override
//    public void setBeanName(String s) {
//        log.info("[    HELPER_REGISTRAR] >>> BeanNameAware [ {} ]", UserServiceImpl.class);
//
//    }
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        log.info("[    HELPER_REGISTRAR] >>> BeanFactoryAware [ {} ]", UserServiceImpl.class);
//
//    }
//
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        log.info("[    HELPER_REGISTRAR] >>> ApplicationContextAware [ {} ]", UserServiceImpl.class);
//
//    }
}
