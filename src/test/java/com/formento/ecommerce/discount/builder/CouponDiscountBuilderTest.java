package com.formento.ecommerce.discount.builder;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.model.Discount;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class CouponDiscountBuilderTest {

    @Test
    public void shouldGiveMeAnInstanceOfCouponDiscount() throws Exception {
        // given
        Coupon coupon = new Coupon(1l, "ABCD", BigDecimal.valueOf(5), LocalDate.now());
        DiscountBuilder discountBuilder = new CouponDiscountBuilder()
                .withCoupon(coupon)
                .withDate(LocalDate.now())
                .withValue(BigDecimal.TEN);

        // when
        Discount discount = discountBuilder.build();

        // then
        assertNotNull(discount);
    }

    @Test
    public void shouldGiveMeAnInstanceOfCouponDiscountWithProductPrice() throws Exception {
        // given
        ProductPriceEntity productPriceEntity = new ProductPriceEntity.Builder().withPrice(BigDecimal.TEN).build();
        Coupon coupon = new Coupon(1l, "ABCD", BigDecimal.valueOf(5), LocalDate.now());
        DiscountBuilder discountBuilder = new CouponDiscountBuilder()
                .withCoupon(coupon)
                .withDate(LocalDate.now())
                .withProductPrice(productPriceEntity);

        // when
        Discount discount = discountBuilder.build();

        // then
        assertNotNull(discount);
    }

}
