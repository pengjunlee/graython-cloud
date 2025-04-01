package com.pengjunlee.product.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderEntity implements Serializable {

    private Long id;

    private Long productId;

    private Integer quantity;
}
