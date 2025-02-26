package com.pengjunlee.demo.infrastructure.repo;

import com.pengjunlee.demo.common.entity.GrayUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  用户服务接口类
 * </p>
 *
 * @author graython
 * @since 2024-12-22 18:27:34
 */
public interface UserRepo extends IService<GrayUser> {

    GrayUser getByUsername(String username);
}
