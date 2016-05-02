package com.formento.ecommerce.shoppingCart.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.shoppingCart.model.template.ShoppingCartTemplate;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ShoppingCartTest {

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.shoppingCart.model.template");
    }

    @Test
    public void shouldBePossibleCreate() {
        // when
        ShoppingCart shoppingCart = Fixture.from(ShoppingCart.class).gimme(ShoppingCartTemplate.VALID_SHOPPING_CART);

        // then
        assertNotNull(shoppingCart);
        assertNotNull(shoppingCart.getId());
        assertNotNull(shoppingCart.getUser());
    }

}
