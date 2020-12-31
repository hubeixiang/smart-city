package com.sct.commons.web.core.handler;

import com.sct.commons.web.core.response.HttpResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class HttpResultEntityHandler {
    private static Logger logger = LoggerFactory.getLogger(HttpResultEntityHandler.class);

    public static HttpResultEntity handler(Supplier<ResponseEntity<HttpResultEntity>> supplier,
                                           Function<HttpResultEntity, HttpResultEntity> success,
                                           BiFunction<HttpResultEntity, HttpStatus, HttpResultEntity> failure,
                                           Function<Throwable, HttpResultEntity> throwable) {
        try {
            ResponseEntity<HttpResultEntity> result = supplier.get();
            if (result.getStatusCode() == HttpStatus.OK) {
                HttpResultEntity body = (HttpResultEntity) result.getBody();
                if (body.getCode() == HttpResultEntity.Code.SUCCESS) {
                    //调用成功
                    return success.apply(body);
                } else {
                    //调用失败
                    return failure.apply(body, result.getStatusCode());
                }
            } else {
                //调用异常/错误
                return failure.apply(result.getBody(), result.getStatusCode());
            }
        } catch (Throwable e) {
            return throwable.apply(e);
        }
    }
}
