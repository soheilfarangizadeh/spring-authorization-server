package com.auth.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.Optional.*;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.*;

public class OAuth2ResourceOwnerPasswordAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(@NotNull HttpServletRequest request) {
        String grantType = request.getParameter(GRANT_TYPE);
        if (!AuthorizationGrantType.PASSWORD.getValue().equals(grantType)) {
            return null;
        }
        String username = ofNullable(request.getParameter(USERNAME)).orElseThrow();
        String password = ofNullable(request.getParameter(PASSWORD)).orElseThrow();
        Authentication clientPrincipal =
                ofNullable(SecurityContextHolder.getContext().getAuthentication()).orElseThrow();
        Map<String, Object> additionalParameters = Map.of(USERNAME, username, PASSWORD, password);
        return new OAuth2ResourceOwnerPasswordAuthenticationToken(AuthorizationGrantType.PASSWORD, clientPrincipal,
                additionalParameters);
    }
}
