package com.sct.commons.web.core.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class RestTemplateRequestHandler {
    public static HttpHeaders createHttpHeaders(MultiValueMap<String, String> headers) {
        if (headers == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            return httpHeaders;
        } else {
            HttpHeaders httpHeaders = new HttpHeaders(headers);
            return httpHeaders;
        }
    }

    public static MultiValueMap<String, Object> paramValueMap() {
        //注意:此处必须是LinkedMultiValueMap
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        return params;
    }
}
