package com.auth.controller;

import com.auth.service.OAuthAuthorizationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.auth.utils.HttpUtils.resolveToken;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
    private final OAuthAuthorizationService oAuthAuthorizationService;

    public OAuthController(OAuthAuthorizationService oAuthAuthorizationService) {
        this.oAuthAuthorizationService = oAuthAuthorizationService;
    }

    @GetMapping("/authorize")
    public UsernamePasswordAuthenticationToken authorizeUser(@NotNull HttpServletRequest request) {
        return oAuthAuthorizationService.authorizeUser(resolveToken(request));
    }
}
