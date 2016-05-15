package com.formento.ecommerce.user.model.service.component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EncoderComponentProviderTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    private EncoderComponent encoderComponent;

    @Before
    public void init() {
        encoderComponent = new EncoderComponentProvider(passwordEncoder);
    }

    @Test
    public void shouldToEncodePassword() {
        // given
        String password = "password";

        // when
        when(passwordEncoder.encode(any())).thenReturn(password);
        String encodedPassword = encoderComponent.encode(password);

        // then
        assertNotNull(encodedPassword);
    }

}
