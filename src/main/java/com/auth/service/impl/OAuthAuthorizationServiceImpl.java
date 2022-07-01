package com.auth.service.impl;

import com.auth.exception.InvalidToken;
import com.auth.service.OAuthAuthorizationService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class OAuthAuthorizationServiceImpl implements OAuthAuthorizationService {
    private final OAuth2AuthorizationService authorizationService;

    public OAuthAuthorizationServiceImpl(OAuth2AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public @NotNull UsernamePasswordAuthenticationToken authorizeUser(@Nullable String token) {
        OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
        return (UsernamePasswordAuthenticationToken) ofNullable(authorization)
                .map(it -> it.getAttributes().get("java.security.Principal"))
                .orElseThrow(InvalidToken::new);
    }
}

