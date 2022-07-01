package com.auth.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    private final String code;
    private final HttpStatus status;
    private final Object[] args;

    public BaseException(String code, HttpStatus status, Object... args) {
        this.code = code;
        this.status = status;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object[] getArgs() {
        return args;
    }
}
