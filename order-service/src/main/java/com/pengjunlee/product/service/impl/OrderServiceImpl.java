package com.pengjunlee.product.service.impl;

import com.pengjunlee.product.entity.OrderEntity;
import com.pengjunlee.product.mapper.OrderMapper;
import com.pengjunlee.product.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    @GlobalTransactional
    public String add(OrderEntity orderEntity) {
        orderMapper.insert(orderEntity);
        return "success";
    }
}
