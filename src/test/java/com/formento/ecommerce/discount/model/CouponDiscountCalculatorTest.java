package com.formento.ecommerce.discount.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class CouponDiscountCalculatorTest {

    @Test
    public void shouldDoCalculationOfCouponDiscount() throws Exception {
        // given
        Coupon coupon = new Coupon(2l, "EFG", BigDecimal.valueOf(5), LocalDate.now().plusDays(1));
        DiscountCalculator discountCalculator = new CouponDiscountCalculator(coupon, BigDecimal.TEN, LocalDate.now());

        // when
        Discount discount = discountCalculator.calculate();

        // then
        assertEquals(0, BigDecimal.TEN.compareTo(discount.getValue()));
        assertEquals(0, BigDecimal.valueOf(9.5).compareTo(discount.getLiquidValue()));
    }

}