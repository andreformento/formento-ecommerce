package com.formento.ecommerce.security.service;

import com.formento.ecommerce.security.UserAuthentication;
import com.formento.ecommerce.user.model.User;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationRestService {

    void createAuthentication(UserAuthentication userAuthentication) throws AuthenticationException;

    User createAuthenticationToken(UserAuthentication userAuthentication) throws AuthenticationException;

}
