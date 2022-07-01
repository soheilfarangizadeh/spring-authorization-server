package com.auth.config;

import com.auth.service.OAuthAuthorizationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final OAuthAuthorizationService oAuthAuthorizationService;

    public TokenFilterConfigurer(OAuthAuthorizationService oAuthAuthorizationService) {
        this.oAuthAuthorizationService = oAuthAuthorizationService;
    }

    @Override
    public void configure(@NotNull HttpSecurity http) {
        TokenFilter jwtTokenFilter = new TokenFilter(oAuthAuthorizationService);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
