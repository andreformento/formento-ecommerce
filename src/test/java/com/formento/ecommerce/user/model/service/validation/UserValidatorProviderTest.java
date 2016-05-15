package com.formento.ecommerce.user.model.service.validation;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.service.UserService;
import com.formento.ecommerce.user.model.template.UserTemplate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorProviderTest {

    @Mock
    private UserService userService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserValidator userValidator;

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
    }

    @Before
    public void init() {
        this.userValidator = new UserValidatorProvider(userService);
    }

    @Test
    public void shouldBePermittedSaveWhenNotExistsUserMailRepeated() {
        // given
        User user = Fixture.from(User.class).gimme(UserTemplate.VALID_USER_NO_ID);
        assertNotNull(user);

        // when...then
        when(userService.countByEmail(user.getEmail())).thenReturn(0);
        userValidator.beforeCreate(user);
    }

    @Test
    public void shouldNotBePermittedSaveWhenExistsUserMailRepeated() {
        // given
        User user = Fixture.from(User.class).gimme(UserTemplate.VALID_USER_NO_ID);
        assertNotNull(user);

        // then
        expectedException.expect(BusinessEcommerceException.class);
        expectedException.expectMessage("user.validator.emailExists");

        // when
        when(userService.countByEmail(user.getEmail())).thenReturn(1);
        userValidator.beforeCreate(user);
    }

}
