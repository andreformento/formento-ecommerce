package com.formento.ecommerce.user.model.service;


import com.formento.ecommerce.security.UserAuthentication;
import com.formento.ecommerce.user.model.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    User updateToken(UserAuthentication userAuthentication);

    User getByEmailAndPassword(String email, String password);

    Integer countByEmail(String email);

    Optional<User> getByEmail(String email);

    Optional<UserAuthentication> getUserOfSession();

    UserAuthentication getValidatedUserOfSession();

    Optional<User> loadUser();

    User loadUserValidated();

    void validateTokenSaved(String email, String authToken);
}
