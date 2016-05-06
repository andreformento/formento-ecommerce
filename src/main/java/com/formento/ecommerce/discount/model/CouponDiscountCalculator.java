package com.formento.ecommerce.discount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CouponDiscountCalculator implements DiscountCalculator {

    private final Coupon coupon;
    private final BigDecimal value;
    private final LocalDate date;

    @Override
    public Discount calculate() {
        BigDecimal liquidValue = calculateLiquidValue();
        return new CouponDiscount(coupon, value, liquidValue);
    }

    public BigDecimal calculateLiquidValue() {
        coupon.validate(date);

        return BigDecimal
                .ONE.subtract(coupon.getPercent().divide(BigDecimal.valueOf(100)))
                .multiply(value);
    }

}
