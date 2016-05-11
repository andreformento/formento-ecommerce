package com.formento.ecommerce.ecommerceOrder.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.model.template.ShoppingCartTemplate;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EcommerceOrderTest {

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
        EcommerceOrder ecommerceOrder = new EcommerceOrder.Builder().withShoppingCart(shoppingCart).build();

        // then
        assertNotNull(ecommerceOrder);
        assertEquals(shoppingCart.getUser(), ecommerceOrder.getUser());
        assertEquals(shoppingCart.getTotalValue(), ecommerceOrder.getTotalValue());
    }

}