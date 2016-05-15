package com.formento.ecommerce.security.service;

import com.formento.ecommerce.exception.UnauthorizedEcommerceException;
import com.formento.ecommerce.security.JwtUserFactory;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserServiceServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService
                .getByEmail(email)
                .orElseThrow(() -> new UnauthorizedEcommerceException("user.idOrPasswordInvalid"));

        return JwtUserFactory.create(user);
    }

}
