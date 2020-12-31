package com.sct.sms.exception;

import org.slf4j.Logger;

public class SmsLoginException extends Exception {
    public SmsLoginException(String message, Throwable cause, Logger logger) {
        super(message, cause);
        logger.error(message, cause);
    }
}
