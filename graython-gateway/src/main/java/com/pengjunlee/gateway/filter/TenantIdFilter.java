package com.pengjunlee.gateway.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengjunlee.common.core.constant.SecurityConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class TenantIdFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 仅拦截 /login POST 请求
        if (request.getURI().getPath().equals("/login") &&
            request.getMethod() == HttpMethod.POST &&
            request.getHeaders().getContentType() != null &&
            request.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)) {

            return exchange.getRequest().getBody()
                .collect(ByteArrayOutputStream::new,
                    (baos, dataBuffer) -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        baos.write(bytes, 0, bytes.length);
                    })
                .flatMap(baos -> {
                    try {
                        String body = baos.toString();
                        // 解析 JSON 体获取 tenantId
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(body);
                        JsonNode tenantNode = jsonNode.get("tenantId");

                        if (tenantNode != null && !tenantNode.asText().isEmpty()) {
                            String tenantId = tenantNode.asText();

                            // 重写请求并添加 header
                            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header(SecurityConstants.DETAILS_TENANT_ID, tenantId)
                                .build();

                            // 需要重新设置请求体，否则下游无法读取
                            DataBuffer bodyDataBuffer = exchange.getResponse().bufferFactory()
                                .wrap(body.getBytes(StandardCharsets.UTF_8));

                            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

                            ServerHttpRequestDecorator decoratedRequest = new ServerHttpRequestDecorator(mutatedRequest) {
                                @Override
                                public Flux<DataBuffer> getBody() {
                                    return bodyFlux;
                                }
                            };

                            return chain.filter(exchange.mutate().request(decoratedRequest).build());
                        }
                    } catch (IOException e) {
                        return Mono.error(e);
                    }

                    // tenantId 不存在或解析失败
                    return chain.filter(exchange);
                });
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // 确保在默认过滤器前执行
    }
}
