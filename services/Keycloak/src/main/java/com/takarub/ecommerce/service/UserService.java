package com.takarub.ecommerce.service;


import com.takarub.ecommerce.model.NewUser;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface UserService {

    void createUser(NewUser user);

    void sendVerificationEmail(String userId);

    void deleteUser(String userId);

    void forgetPassword(String username);

    UserResource getUser(String userId);

    List<RoleRepresentation> getUserRole(String userId);

    List<GroupRepresentation> getUserGroup(String userId);
}
