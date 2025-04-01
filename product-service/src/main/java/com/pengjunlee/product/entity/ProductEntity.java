package com.pengjunlee.product.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductEntity implements Serializable {

    private Long id;

    private Integer quantity;
}
