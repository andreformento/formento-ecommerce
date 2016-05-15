package com.formento.ecommerce.user.model.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.security.component.JwtTokenUtil;
import com.formento.ecommerce.security.service.AuthenticationRestService;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.repository.UserRepository;
import com.formento.ecommerce.user.model.service.component.EncoderComponent;
import com.formento.ecommerce.user.model.service.validation.UserValidator;
import com.formento.ecommerce.user.model.template.UserTemplate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceProviderTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private EncoderComponent encoderComponent;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private AuthenticationRestService authenticationRestService;

    private UserService userService;

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
    }

    @Before
    public void init() {
        this.userService = new UserServiceProvider(userRepository, userValidator, encoderComponent, jwtTokenUtil, authenticationRestService);
    }

    @Test
    public void shouldToSaveANewUser() {
        // given
        User user = Fixture.from(User.class).gimme(UserTemplate.VALID_USER_NO_ID);
        assertNotNull(user);

        // when
        when(encoderComponent.encode(user.getPassword())).thenReturn(user.getPassword());
        when(jwtTokenUtil.generateToken(user)).thenReturn("token");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.create(user);

        // then
        assertNotNull(savedUser);

        verify(userValidator, times(1)).beforeCreate(user);
    }
}
