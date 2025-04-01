package com.pengjunlee.product.mapper;

import com.pengjunlee.product.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    void insert(@Param("order") OrderEntity orderEntity);
}
