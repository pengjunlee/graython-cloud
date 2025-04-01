package com.pengjunlee.product.mapper;

import com.pengjunlee.product.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {
    void update(@Param("product") ProductEntity product);

    ProductEntity getById(@Param("id") Long id);
}
