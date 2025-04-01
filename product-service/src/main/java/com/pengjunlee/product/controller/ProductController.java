package com.pengjunlee.product.controller;

import com.pengjunlee.product.entity.ProductEntity;
import com.pengjunlee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/change")
    public String change(@RequestBody ProductEntity product) {
        productService.change(product);
        return "success";
    }
}
