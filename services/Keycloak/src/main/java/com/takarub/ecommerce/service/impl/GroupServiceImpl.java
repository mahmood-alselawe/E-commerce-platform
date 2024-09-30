package com.takarub.ecommerce.service.impl;

import com.takarub.ecommerce.service.GroupService;
import com.takarub.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;

    private final UserService userService;



    @Override
    public void assignGroup(String userId, String groupId) {

        UserResource user = userService.getUser(userId);
        user.joinGroup(groupId);



    }

    @Override
    public void deleteGroup(String userId, String groupId) {
        UserResource user = userService.getUser(userId);
        user.leaveGroup(groupId);
    }
}
