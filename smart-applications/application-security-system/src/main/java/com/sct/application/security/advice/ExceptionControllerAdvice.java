package com.sct.application.security.advice;

import com.sct.commons.web.core.response.HttpResultEntity;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private final static String REQUEST_EXCEPTION_MESSAGE_FORMAT = "Request [%s] threw exception [%s].";
    private static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpEntity<HttpResultEntity> IllegalArgumentExceptionHandler(IllegalArgumentException e, WebRequest request) {
        logRequestExceptionError(request, e);
        return new ResponseEntity<>(HttpResultEntity.of(ExceptionCode.SERVER_API_PARAMETER_ERROR, "Parameter Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(APIException.class)
    public HttpEntity<HttpResultEntity> APIExceptionHandler(APIException e, WebRequest request) {
        logRequestExceptionError(request, e);
        return new ResponseEntity<>(HttpResultEntity.of(e.getCode(), e.getMessage(), e.getData()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public HttpEntity<HttpResultEntity> RuntimeExceptionHandler(RuntimeException e, WebRequest request) {
        logRequestExceptionError(request, e);
        return new ResponseEntity<>(HttpResultEntity.of(ExceptionCode.SERVER_API_Unrecognized_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logRequestExceptionError(WebRequest request, Throwable throwable) {
        logger.error(getRequestExceptionMessage(request, throwable), throwable);
    }

    private String getRequestExceptionMessage(WebRequest request, Throwable throwable) {
        return String.format(REQUEST_EXCEPTION_MESSAGE_FORMAT, request, throwable);
    }

}
