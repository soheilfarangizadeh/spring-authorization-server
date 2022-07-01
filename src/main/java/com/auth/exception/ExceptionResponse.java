package com.auth.exception;

public final class ExceptionResponse {
    private final String message;
    private final String code;

    public ExceptionResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
