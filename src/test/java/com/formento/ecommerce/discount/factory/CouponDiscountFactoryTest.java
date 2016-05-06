package com.formento.ecommerce.discount.factory;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.model.Discount;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class CouponDiscountFactoryTest {

    @Test
    public void shouldGiveMeAnInstanceOfCouponDiscount() throws Exception {
        // given
        Coupon coupon = new Coupon(1l, "ABCD", BigDecimal.valueOf(5), LocalDate.now());
        DiscountFactory discountFactory = new CouponDiscountFactory(coupon, BigDecimal.TEN, LocalDate.now());

        // when
        Discount discount = discountFactory.makeDiscount();

        // then
        assertNotNull(discount);
    }

}
