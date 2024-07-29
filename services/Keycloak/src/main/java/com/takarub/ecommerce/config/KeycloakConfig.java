package com.takarub.ecommerce.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${app.keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.serverUrl}")
    private String serverUrl;

    @Value("${app.keycloak.admin.clientSecret}")
    private String adminClientSecret;

    @Value("${app.keycloak.admin.clientId}")
    private String adminClientId;

    @Bean
    public Keycloak keycloak() {
        return  KeycloakBuilder
                .builder()
                .clientId(adminClientId)
                .clientSecret(adminClientSecret)
                .grantType("client_credentials")
                .realm(realm)
                .serverUrl(serverUrl)
                .build();
    }
}
