package com.pengjunlee.demo.api;

import com.pengjunlee.demo.common.entity.GrayUser;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> login(GrayUser grayUser);
}
