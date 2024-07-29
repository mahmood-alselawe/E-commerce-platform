package com.takarub.ecommerce.service.impl;

import com.takarub.ecommerce.config.KeycloakConfig;
import com.takarub.ecommerce.model.NewUser;
import com.takarub.ecommerce.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    private String realm;


    @Override
    public void createUser(NewUser user) {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> existingUsers = usersResource.search(user.username(), true);

        if (!existingUsers.isEmpty()) {
            log.warn("User with username {} already exists", user.username());
            throw new RuntimeException("User with username " + user.username() + " already exists");
        }

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(user.firstName());
        userRepresentation.setLastName(user.lastName());
        userRepresentation.setUsername(user.username());
        userRepresentation.setEmail(user.username());
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(user.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        Response response = usersResource.create(userRepresentation);
        log.info("status code: {}", response.getStatus());

        if (response.getStatus() != 201) {
            log.error("Failed to create user. Status code: {}", response.getStatus());
            throw new RuntimeException("Failed to create user. Status code: " + response.getStatus());
        }

        List<UserRepresentation> createdUsers = usersResource.searchByEmail(user.username(), true);
        UserRepresentation createdUser = createdUsers.get(0);
        sendVerificationEmail(createdUser.getId());
    }

    @Override
    public void sendVerificationEmail(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(userId);
    }

    @Override
    public void forgetPassword(String username) {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByEmail(username, true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);

        UserResource userResource = usersResource.get(userRepresentation1.getId());

        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));


    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }
}
