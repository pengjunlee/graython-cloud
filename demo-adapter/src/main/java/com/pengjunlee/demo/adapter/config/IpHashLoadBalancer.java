package com.pengjunlee.demo.adapter.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

public class IpHashLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private final ServiceInstanceListSupplier serviceInstanceListSupplier;

    public IpHashLoadBalancer(ServiceInstanceListSupplier serviceInstanceListSupplier) {
        this.serviceInstanceListSupplier = serviceInstanceListSupplier;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        return serviceInstanceListSupplier.get()
                .next()
                .map(instances -> getInstance(instances, request));
    }

    private Response<ServiceInstance> getInstance(List<ServiceInstance> instances, Request request) {
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }

        // 获取客户端 IP 地址（需要从 Request 中解析）
        String clientIp = getClientIp(request);

        // 计算哈希值并选择实例
        int hash = clientIp.hashCode();
        int index = Math.abs(hash % instances.size());
        ServiceInstance instance = instances.get(index);

        return new DefaultResponse(instance);
    }

    private String getClientIp(Request request) {
        // 从 Request 中解析客户端 IP 地址
        // 这里需要根据具体业务逻辑实现
        // 示例：从请求头中获取 IP
        if (request.getContext() instanceof org.springframework.http.HttpHeaders) {
            org.springframework.http.HttpHeaders headers = (org.springframework.http.HttpHeaders) request.getContext();
            return headers.getFirst("X-Forwarded-For"); // 从请求头中获取 IP
        }
        return "127.0.0.1"; // 默认 IP
    }
}