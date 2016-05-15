package com.formento.ecommerce.security.service;

import com.formento.ecommerce.exception.UnauthorizedEcommerceException;
import com.formento.ecommerce.security.UserAuthentication;
import com.formento.ecommerce.security.component.JwtTokenUtil;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.service.UserService;
import com.formento.ecommerce.user.model.service.component.EncoderComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationRestServiceProvider implements AuthenticationRestService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private EncoderComponent encoderComponent;

    public void createAuthentication(UserAuthentication userAuthentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAuthentication.getEmail(), userAuthentication.getPassword());

        try {
            final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new UnauthorizedEcommerceException("user.idOrPasswordInvalid");
        }
    }

    public User createAuthenticationToken(UserAuthentication userAuthentication) throws AuthenticationException {
        return userService.updateToken(userAuthentication);
    }

}
