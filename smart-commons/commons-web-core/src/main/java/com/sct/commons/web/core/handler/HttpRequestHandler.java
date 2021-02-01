package com.sct.commons.web.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class HttpRequestHandler {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestHandler.class);

    /**
     * 访问的rest地址有返回值的情况
     *
     * @param url       请求的url
     * @param supplier  请求发起
     * @param success   请求成功处理
     * @param failure   请求失败处理
     * @param throwable 请求异常处理
     * @param <T>       访问的rest地址的返回值类型
     * @param <R>       访问的rest地址的返回值类型,经过转换后的实际返回类型
     * @return
     */
    public static <T, R> R handler(@Nullable URI url, @Nullable Supplier<ResponseEntity<T>> supplier,
                                   @Nullable Function<T, R> success,
                                   @Nullable BiFunction<T, HttpStatus, R> failure,
                                   @Nullable Function<Throwable, R> throwable) {
        return handler(url, supplier, HttpStatus.OK, success, failure, throwable);
    }

    /**
     * 访问的rest地址有返回值的情况
     *
     * @param url           请求的url
     * @param supplier      请求发起
     * @param successStatus 请求成功的标志
     * @param success       请求成功处理
     * @param failure       请求失败处理
     * @param throwable     请求异常处理
     * @param <T>           访问的rest地址的返回值类型
     * @param <R>           访问的rest地址的返回值类型,经过转换后的实际返回类型
     * @return
     */
    public static <T, R> R handler(@Nullable URI url, @Nullable Supplier<ResponseEntity<T>> supplier,
                                   @Nullable HttpStatus successStatus,
                                   @Nullable Function<T, R> success,
                                   @Nullable BiFunction<T, HttpStatus, R> failure,
                                   @Nullable Function<Throwable, R> throwable) {
        String id = requestId();
        long start = System.currentTimeMillis();
        logger.info("{}:{} get start ", id, url);
        Object responseSign = "ok";
        try {
            ResponseEntity<T> result = supplier.get();
            if (successStatus == null) {
                successStatus = HttpStatus.OK;
            }
            if (result.getStatusCode() == successStatus) {
                //调用成功
                T body = result.getBody();
                responseSign = "ok";
                if (logger.isDebugEnabled()) {
                    logger.debug("{}:{} end.response ok, response body {}", id, url, responseSign, body);
                }
                return success.apply(body);
            } else {
                //调用异常/错误
                responseSign = "failure";
                return failure.apply(result.getBody(), result.getStatusCode());
            }
        } catch (Throwable e) {
            responseSign = String.format("Throwable:%s", e.getMessage());
            logger.error("{}:{} end.response {},{}", id, url, responseSign, e);
            return throwable.apply(e);
        } finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("{}:{} end.cost {} ms.response {}", id, url, cost, responseSign);
        }
    }

    public static String requestId() {
        return UUID.randomUUID().toString();
    }

    public static <T> Supplier<ResponseEntity<T>> createGetSupplier(@Nullable RestTemplate restTemplate, @Nullable URI url, Class<T> responseType) {
        return () -> restTemplate.getForEntity(url, responseType);
    }

    public static <T> Supplier<ResponseEntity<T>> createPostSupplier(@Nullable RestTemplate restTemplate, @Nullable URI url, @Nullable Object request, Class<T> responseType) {
        return () -> restTemplate.postForEntity(url, request, responseType);
    }

    public static <T> Supplier<ResponseEntity<T>> createDeleteSupplier(@Nullable RestTemplate restTemplate, @Nullable URI url, @Nullable HttpEntity<?> requestEntity,
                                                                       Class<T> responseType) {
        return createExchangeSupplier(restTemplate, url, HttpMethod.DELETE, requestEntity, responseType);
    }

    public static <T> Supplier<ResponseEntity<T>> createPutSupplier(@Nullable RestTemplate restTemplate, @Nullable URI url, @Nullable HttpEntity<?> requestEntity,
                                                                    Class<T> responseType) {
        return createExchangeSupplier(restTemplate, url, HttpMethod.PUT, requestEntity, responseType);
    }

    public static <T> Supplier<ResponseEntity<T>> createExchangeSupplier(@Nullable RestTemplate restTemplate, @Nullable URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                                                         Class<T> responseType) {
        return () -> restTemplate.exchange(url, method, requestEntity, responseType);
    }
}
