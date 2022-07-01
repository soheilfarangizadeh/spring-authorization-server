package com.auth.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ExceptionCode {
    INVALID_TOKEN("100", UNAUTHORIZED),
    INVALID_REQUEST("101", NOT_ACCEPTABLE),
    INVALID_PHONE_NUMBER_FORMAT("102", BAD_REQUEST),
    EXPIRED_OTP("103", NOT_ACCEPTABLE),
    USER_IS_DISABLED("104", LOCKED),
    OBJECT_IS_DUPLICATED("105", BAD_REQUEST),
    INVALID_OTP("106", BAD_REQUEST),
    NOT_FOUND_PRODUCT("107", NOT_FOUND),
    NOT_FOUND_OBJECT("108", NOT_FOUND),
    OLDEST_PRODUCT("109", UNAVAILABLE_FOR_LEGAL_REASONS);
    private final String code;
    private final HttpStatus httpStatus;

    ExceptionCode(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
