package com.pengjunlee.product.remote;

import com.pengjunlee.product.entity.ProductEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")  // 这里写被调用服务的名称
public interface UserClient {
    @GetMapping("/product/change")
        // 这里写 user-service 的 Controller 路径
    String change(@RequestBody ProductEntity product);
}
