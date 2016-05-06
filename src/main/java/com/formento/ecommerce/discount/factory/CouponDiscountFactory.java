package com.formento.ecommerce.discount.factory;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.model.CouponDiscountCalculator;
import com.formento.ecommerce.discount.model.Discount;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class CouponDiscountFactory implements DiscountFactory {

    private final Coupon coupon;
    private final BigDecimal value;
    private final LocalDate date;

    @Override
    public Discount makeDiscount() {
        return new CouponDiscountCalculator(coupon, value, date).calculate();
    }

}
