package com.formento.ecommerce.security;


import com.formento.ecommerce.user.model.User;

import java.util.ArrayList;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {

        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getToken(),
                user.getPassword(),
                new ArrayList<>(),
                true,
                user.getLastLogin()
        );
    }

}
