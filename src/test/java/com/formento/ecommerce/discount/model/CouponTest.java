package com.formento.ecommerce.discount.model;

import com.formento.ecommerce.exception.DiscountEcommerceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class CouponTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeAValidCouponThatExpiresToday() throws Exception {
        // given
        Coupon coupon = new Coupon(1l, "ABCD", BigDecimal.valueOf(5), LocalDate.now());
        assertNotNull(coupon);

        // when..then
        coupon.validateNow();
    }

    @Test
    public void shouldBeAValidCouponWithAFutureExpirationDate() throws Exception {
        // given
        Coupon coupon = new Coupon(1l, "ABCD", BigDecimal.valueOf(5), LocalDate.now().plusDays(2));
        assertNotNull(coupon);

        // when..then
        coupon.validateNow();
    }

    @Test
    public void shouldNotBeAValidCoupon() throws Exception {
        // given
        Coupon coupon = new Coupon(2l, "EFG", BigDecimal.valueOf(15), LocalDate.now().minusDays(1));
        assertNotNull(coupon);

        // then
        expectedException.expect(DiscountEcommerceException.class);
        expectedException.expectMessage("discount.coupon.invalid.expiredDate");

        // when
        coupon.validateNow();
    }

}
