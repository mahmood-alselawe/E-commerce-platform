package com.takarub.ecommerce.service;


import com.takarub.ecommerce.model.NewUser;

public interface UserService {

    void createUser(NewUser user);

    void sendVerificationEmail(String userId);

    void deleteUser(String userId);

    void forgetPassword(String username);
}
