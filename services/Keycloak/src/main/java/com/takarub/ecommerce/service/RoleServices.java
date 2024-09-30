package com.takarub.ecommerce.service;

public interface RoleServices {

    void assignRole(String userId,String roleName);
    void deleteRoleFromUser(String userId,String roleName);

}
