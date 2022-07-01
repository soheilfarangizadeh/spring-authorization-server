package com.auth.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Configuration
public class MessageBundle {
    private final MessageSource errorMessageSource;
    private final MessageSource messageSource;

    public MessageBundle(MessageSource errorMessageSource, MessageSource messageSource) {
        this.errorMessageSource = errorMessageSource;
        this.messageSource = messageSource;
    }

    public String getErrorMessage(@NotNull Class<?> aClass, @NotNull HttpServletRequest request, Object... args) {
        String simpleName = aClass.getSimpleName();
        return errorMessageSource.getMessage(simpleName, args, simpleName, Locale.ENGLISH);
    }

    public String getMessage(String key) {
        return getMessage(key, null);
    }

    public String getMessage(String key, Object[] args) {
        return messageSource.getMessage(key, args, key, Locale.ENGLISH);
    }
}
