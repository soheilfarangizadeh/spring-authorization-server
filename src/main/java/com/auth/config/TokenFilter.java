package com.auth.config;

import com.auth.exception.BaseException;
import com.auth.service.OAuthAuthorizationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth.utils.HttpUtils.resolveToken;
import static java.lang.Integer.parseInt;

public class TokenFilter extends OncePerRequestFilter {
    private final OAuthAuthorizationService oAuthAuthorizationService;

    public TokenFilter(OAuthAuthorizationService oAuthAuthorizationService) {
        this.oAuthAuthorizationService = oAuthAuthorizationService;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = resolveToken(request);
            if (token != null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        oAuthAuthorizationService.authorizeUser(token);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (BaseException e) {
            SecurityContextHolder.clearContext();
            response.sendError(parseInt(e.getCode()), e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
