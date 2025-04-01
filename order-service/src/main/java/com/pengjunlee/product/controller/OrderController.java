package com.pengjunlee.product.controller;

import com.pengjunlee.product.entity.OrderEntity;
import com.pengjunlee.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public String add(@RequestBody OrderEntity orderEntity) {
        return orderService.add(orderEntity);
    }
}
