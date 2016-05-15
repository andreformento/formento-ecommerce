package com.formento.ecommerce.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JwtAuthenticationRequest implements Serializable, UserAuthentication {

    private String email;
    private String password;

}
