package com.auth.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface OAuthAuthorizationService {
    @NotNull UsernamePasswordAuthenticationToken authorizeUser(@Nullable String token);
}
