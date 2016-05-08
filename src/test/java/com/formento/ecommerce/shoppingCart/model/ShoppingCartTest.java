package com.formento.ecommerce.shoppingCart.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.shoppingCart.model.template.ShoppingCartTemplate;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShoppingCartTest {

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.productPrice.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.product.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.shoppingCart.model.template");
    }

    @Test
    public void shouldBePossibleToCreate() {
        // given
        ShoppingCart shoppingCart = Fixture.from(ShoppingCart.class).gimme(ShoppingCartTemplate.VALID_SHOPPING_CART);

        // then
        assertNotNull(shoppingCart);
        assertNotNull(shoppingCart.getId());
        assertNotNull(shoppingCart.getUser());
        assertNotNull(shoppingCart.getItemShoppingCarts());
    }

    @Test
    public void shouldCalculateTotal() {
        // given
        ShoppingCart shoppingCart = Fixture.from(ShoppingCart.class).gimme(ShoppingCartTemplate.VALID_SHOPPING_CART);

        // when
        BigDecimal totalValue = shoppingCart.getTotalValue();

        // then
        assertEquals(BigDecimal.valueOf(21.0).setScale(2, RoundingMode.DOWN), totalValue);
    }

}
