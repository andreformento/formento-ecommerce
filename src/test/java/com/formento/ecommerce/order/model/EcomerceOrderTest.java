package com.formento.ecommerce.order.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.model.template.ShoppingCartTemplate;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EcomerceOrderTest {

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.productPrice.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.shoppingCart.model.template");
    }

    @Test
    public void shouldBeCreatedWithShoppingCart() {
        // given
        ShoppingCart shoppingCart = Fixture.from(ShoppingCart.class).gimme(ShoppingCartTemplate.VALID_SHOPPING_CART);

        // when
        EcomerceOrder ecomerceOrder = new EcomerceOrder.Builder().withShoppingCart(shoppingCart).build();

        // then
        assertNotNull(ecomerceOrder);
        assertEquals(shoppingCart.getUser(), ecomerceOrder.getUser());
        assertEquals(shoppingCart.getTotalValue(), ecomerceOrder.getTotalValue());
    }

}