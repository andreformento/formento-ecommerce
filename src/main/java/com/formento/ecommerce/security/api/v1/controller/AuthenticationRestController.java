package com.formento.ecommerce.security.api.v1.controller;

import com.formento.ecommerce.security.JwtAuthenticationRequest;
import com.formento.ecommerce.security.service.AuthenticationRestService;
import com.formento.ecommerce.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class AuthenticationRestController {

    @Autowired
    private AuthenticationRestService authenticationRestService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<Resource<User>> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        return ResponseEntity.ok(new Resource<>(authenticationRestService.createAuthenticationToken(authenticationRequest), linkTo(UserController.class).withSelfRel()));
    }

}
