package com.formento.ecommerce.user.model.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncoderComponentProvider implements EncoderComponent {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public EncoderComponentProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String text) {
        return passwordEncoder.encode(text);
    }

}
