package com.pengjunlee.product.service.impl;

import com.pengjunlee.product.entity.ProductEntity;
import com.pengjunlee.product.mapper.ProductMapper;
import com.pengjunlee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public void change(ProductEntity product) {
        ProductEntity byId = productMapper.getById(product.getId());
        if (byId == null) {
            throw new RuntimeException("数据不存在");
        }
        if (byId.getQuantity() < product.getQuantity()) {
            throw new RuntimeException("库存不足");
        }
        byId.setQuantity(byId.getQuantity() - product.getQuantity());
        productMapper.update(byId);
    }
}
