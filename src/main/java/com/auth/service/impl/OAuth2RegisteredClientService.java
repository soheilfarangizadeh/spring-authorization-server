package com.auth.service.impl;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class OAuth2RegisteredClientService {
    public List<RegisteredClient> getOAuth2RegisteredClient() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client_id")
                .clientSecret(
                        "{bcrypt}$2a$10$n8L2G3pZCQBCvEy6Rxtq6e1fV2iOkSxV688gGbbmd/4iVY03TAFaS") // clientSecret is -> client_secret
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .scope("write")
                .scope("read")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .accessTokenTimeToLive(Duration.ofHours(2))
                        .build())
                .build();
        return Collections.singletonList(registeredClient);
    }
}
