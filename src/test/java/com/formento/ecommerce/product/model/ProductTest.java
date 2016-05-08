package com.formento.ecommerce.product.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import com.formento.ecommerce.productPrice.model.template.ProductPriceTemplate;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.productPrice.model.template");
    }

    @Test
    public void shouldBeCurrentPrice() {
        // given..when
        LocalDate initialDate = Optional
                .ofNullable(new Product.Builder()
                        .withId(1l)
                        .withName("Chair")
                        .withDescription("Beautiful chair")
                        .withAvailability(5)
                        .addProductPrice(Fixture.from(ProductPriceEntity.class).gimme(ProductPriceTemplate.VALID_CURRENT_PRODUCT_PRICE))
                        .addProductPrice(Fixture.from(ProductPriceEntity.class).gimme(ProductPriceTemplate.VALID_PRODUCT_PRICE_FROM_ONE_MONTH_AGO))
                        .addProductPrice(Fixture.from(ProductPriceEntity.class).gimme(ProductPriceTemplate.VALID_PRODUCT_PRICE_TO_NEXT_MONTH))
                        .build())
                .map(Product::getCurrentProductPrice)
                .map(Optional::get)
                .map(ProductPriceEntity::getInitialDate)
                .orElseThrow(() -> new BusinessEcommerceException("Cannot find product price"));

        // then
        assertEquals(LocalDate.now(), initialDate);
    }

}
