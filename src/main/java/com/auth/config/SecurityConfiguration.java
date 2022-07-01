package com.auth.config;

import com.auth.service.OAuthAuthorizationService;
import com.auth.service.impl.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    private final UserService userService;
    private final OAuthAuthorizationService oAuthAuthorizationService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserService userService, OAuthAuthorizationService oAuthAuthorizationService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.oAuthAuthorizationService = oAuthAuthorizationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    protected void configureGlobal(@NotNull AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
                .and()
                .eraseCredentials(true);
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http
                .exceptionHandling(it -> it.authenticationEntryPoint(new HttpStatusEntryPoint(UNAUTHORIZED)))
                .authorizeRequests().anyRequest().authenticated()
                .and().apply(new TokenFilterConfigurer(oAuthAuthorizationService))
                .and()
                .build();
    }
}
