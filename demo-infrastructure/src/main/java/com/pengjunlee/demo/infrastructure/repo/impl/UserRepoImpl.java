package com.pengjunlee.demo.infrastructure.repo.impl;

import com.pengjunlee.demo.common.entity.GrayUser;
import com.pengjunlee.demo.infrastructure.mapper.GrayUserMapper;
import com.pengjunlee.demo.infrastructure.repo.UserRepo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  用户持久层服务实现
 * </p>
 *
 * @author graython
 * @since 2024-12-22 18:27:34
 */

@RequiredArgsConstructor
@Service
public class UserRepoImpl extends ServiceImpl<GrayUserMapper, GrayUser> implements UserRepo {

    private final GrayUserMapper userMapper;

    @Override
    public GrayUser getByUsername(String username) {
        return new GrayUser(1L,"graython","123456");
    }
}
