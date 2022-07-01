package com.auth.exception;

public class InvalidToken extends BaseException {
    private static final ExceptionCode EXCEPTION_CODE = ExceptionCode.INVALID_TOKEN;

    public InvalidToken() {
        super(EXCEPTION_CODE.getCode(), EXCEPTION_CODE.getHttpStatus());
    }
}
