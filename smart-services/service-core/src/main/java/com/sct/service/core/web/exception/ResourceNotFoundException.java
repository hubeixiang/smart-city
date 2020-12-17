package com.sct.service.core.web.exception;

public class ResourceNotFoundException extends APIException {
    public ResourceNotFoundException() {
        this(null);
    }

    public ResourceNotFoundException(Object data) {
        super(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Resource Not Found", data);
    }

    public static ResourceNotFoundException of() {
        return new ResourceNotFoundException();
    }

    public static ResourceNotFoundException of(Object data) {
        return new ResourceNotFoundException(data);
    }
}
