package com.formento.ecommerce.user.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.user.model.template.UserTemplate;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserTest {

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
    }

    @Test
    public void shouldBePossibleCreate() {
        // when
        User user = Fixture.from(User.class).gimme(UserTemplate.VALID_USER);

        // then
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getEmail());
        assertNotNull(user.getEmail());
    }

}
