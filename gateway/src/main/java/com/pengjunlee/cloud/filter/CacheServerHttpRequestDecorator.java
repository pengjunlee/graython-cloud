package com.pengjunlee.cloud.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

/**
 * 对ServerHttpRequest进行二次封装，解决requestBody只能读取一次的问题
 */
@Slf4j
public class CacheServerHttpRequestDecorator extends ServerHttpRequestDecorator {

    private static final NettyDataBufferFactory NETTY_DATA_BUFFER_FACTORY = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));

    private byte[] bytes;
    private int getBufferTime = 0;

    public CacheServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        if (getBufferTime == 0) {
            getBufferTime++;
            return super.getBody()
                    .publishOn(Schedulers.single()) // 使用单独的线程处理流
                    .map(this::cache) // 缓存请求体
                    .doOnComplete(() -> trace(getDelegate())); // 请求结束时打印日志
        } else {
            return Flux.just(getBodyFromCache());
        }
    }

    /**
     * 从缓存中获取请求体
     */
    private DataBuffer getBodyFromCache() {
        return NETTY_DATA_BUFFER_FACTORY.wrap(bytes);
    }

    /**
     * 缓存请求体
     */
    private DataBuffer cache(DataBuffer buffer) {
        try (InputStream dataBuffer = buffer.asInputStream()) {
            bytes = IOUtils.toByteArray(dataBuffer);
            return NETTY_DATA_BUFFER_FACTORY.wrap(bytes);
        } catch (IOException e) {
            log.error("Error reading request body", e);
        }
        return null;
    }

    /**
     * 追踪请求信息并打印日志
     */
    private void trace(ServerHttpRequest request) {
        URI requestUri = request.getURI();
        String uriQuery = requestUri.getQuery();
        String url = requestUri.getPath() + (StringUtils.isNotBlank(uriQuery) ? "?" + uriQuery : "");
        HttpHeaders headers = request.getHeaders();
        MediaType mediaType = headers.getContentType();
        String schema = requestUri.getScheme();
        String method = request.getMethodValue().toUpperCase();

        // 仅处理 HTTP/HTTPS 请求
        if (!"http".equals(schema) && !"https".equals(schema)) {
            return;
        }

        String reqBody = null;
        if (Objects.nonNull(mediaType) && LogHelper.isUploadFile(mediaType)) {
            reqBody = "上传文件";
        } else {
            reqBody = getRequestBody(request, method, uriQuery, headers);
        }

        Log logDTO = buildLogDTO(url, reqBody, method, headers);
        log.info(LogHelper.toJsonString(logDTO));
    }

    /**
     * 获取请求体
     */
    private String getRequestBody(ServerHttpRequest request, String method, String uriQuery, HttpHeaders headers) {
        String reqBody = null;
        if (method.equals("GET") && StringUtils.isNotBlank(uriQuery)) {
            reqBody = uriQuery;
        } else if (headers.getContentLength() > 0) {
            reqBody = LogHelper.readRequestBody(request);
        }
        return reqBody;
    }

    /**
     * 构建日志 DTO
     */
    private Log buildLogDTO(String url, String reqBody, String method, HttpHeaders headers) {
        Log logDTO = new Log();
        logDTO.setLevel(Log.LEVEL.INFO);
        logDTO.setRequestUrl(url);
        logDTO.setRequestBody(reqBody);
        logDTO.setRequestMethod(method);
        logDTO.setRequestId(headers.getFirst(HeaderConstant.REQUEST_ID));
        logDTO.setIp(IpUtils.getClientIp(getDelegate()));
        return logDTO;
    }
}
