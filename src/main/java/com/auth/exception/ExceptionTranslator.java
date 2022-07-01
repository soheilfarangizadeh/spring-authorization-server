package com.auth.exception;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTranslator.class);
    private final MessageBundle messageBundle;

    public ExceptionTranslator(MessageBundle messageBundle) {this.messageBundle = messageBundle;}

    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException ex,
            @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        return getResponseEntity(ex, BAD_REQUEST, "input validation failure", "");
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        return getResponseEntity(ex, BAD_REQUEST, "input validation failure", "");
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAccessException(BaseException e, @NotNull HttpServletRequest request) {
        return getResponseEntity(e, e.getStatus(), messageBundle.getErrorMessage(e.getClass(), request, e.getArgs()),
                e.getCode());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e) {
        return getResponseEntity(e, INTERNAL_SERVER_ERROR, "internal", "internal");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) {
        return getResponseEntity(e, FORBIDDEN, e.getMessage(), "403");
    }

    private static @NotNull ResponseEntity<Object> getResponseEntity(Exception e, HttpStatus status, String message,
            String code) {
        LOGGER.info("translating exception", e);
        return ResponseEntity.status(status).body(List.of(new ExceptionResponse(message, code)));
    }
}
