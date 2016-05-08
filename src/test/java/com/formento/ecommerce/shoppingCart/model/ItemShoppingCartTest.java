package com.formento.ecommerce.shoppingCart.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import com.formento.ecommerce.productPrice.model.template.ProductPriceTemplate;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class ItemShoppingCartTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.productPrice.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.product.model.template");
    }

    @Test
    public void shouldGiveProductPrice() {
        // given
        Product product = new Product.Builder()
                .withAvailability(3)
                .addProductPrice(Fixture.from(ProductPriceEntity.class).gimme(ProductPriceTemplate.VALID_CURRENT_PRODUCT_PRICE))
                .build();

        ItemShoppingCart itemShoppingCart = new ItemShoppingCart(product, 3);

        // when
        BigDecimal productPrice = itemShoppingCart.getProductPrice();
        BigDecimal totalPrice = itemShoppingCart.getTotalPrice();

        // then
        assertEquals(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.DOWN), productPrice);
        assertEquals(BigDecimal.valueOf(3.00).setScale(2, RoundingMode.DOWN), totalPrice);
    }

    @Test
    public void shouldNotBeCreatedWithMoreItensThatIsAvaliable() {
        // given
        Product product = new Product.Builder()
                .withAvailability(2)
                .addProductPrice(Fixture.from(ProductPriceEntity.class).gimme(ProductPriceTemplate.VALID_CURRENT_PRODUCT_PRICE))
                .build();

        // then
        expectedException.expect(BusinessEcommerceException.class);
        expectedException.expectMessage("shoppingCart.quantityGreaterThenAvaliabityOfProduct");

        // when
        ItemShoppingCart itemShoppingCart = new ItemShoppingCart(product, 3);
    }

    @Test
    public void shouldNotBeCalculateTheValueWhenProductDoNotAValue() {
        // given
        Product product = new Product.Builder()
                .withAvailability(1)
                .build();

        ItemShoppingCart itemShoppingCart = new ItemShoppingCart(product, 1);

        // then
        expectedException.expect(BusinessEcommerceException.class);
        expectedException.expectMessage("shoppingCart.priceOfProductNotDefined");

        // when
        BigDecimal productPrice = itemShoppingCart.getProductPrice();
    }

}
