package com.auth.config;

import com.auth.service.impl.OAuth2RegisteredClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.List;

@Configuration
public class OAuth2RegisteredClientConfiguration {
    private final OAuth2RegisteredClientService oAuth2RegisteredClientService;

    public OAuth2RegisteredClientConfiguration(OAuth2RegisteredClientService oAuth2RegisteredClientService) {
        this.oAuth2RegisteredClientService = oAuth2RegisteredClientService;
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        List<RegisteredClient> oAuth2RegisteredClient = oAuth2RegisteredClientService.getOAuth2RegisteredClient();
        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
        oAuth2RegisteredClient.forEach(it -> {
            if (registeredClientRepository.findByClientId(it.getClientId()) == null) {
                registeredClientRepository.save(it);
            }
        });
        return registeredClientRepository;
    }
}
