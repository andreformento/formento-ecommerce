package com.formento.ecommerce.discount.builder;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.model.CouponDiscountCalculator;
import com.formento.ecommerce.discount.model.Discount;
import com.formento.ecommerce.productPrice.model.ProductPrice;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponDiscountBuilder implements DiscountBuilder {

    private Coupon coupon;
    private BigDecimal value;
    private LocalDate date;

    public CouponDiscountBuilder withCoupon(Coupon coupon) {
        this.coupon = coupon;
        return this;
    }

    public CouponDiscountBuilder withValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public CouponDiscountBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public CouponDiscountBuilder withProductPrice(ProductPrice productPrice) {
        return withValue(productPrice.getTotalPrice());
    }

    @Override
    public Discount build() {
        Preconditions.checkNotNull(coupon, "discount.build.fieldCannotBeNull.coupon");
        Preconditions.checkNotNull(value, "discount.build.fieldCannotBeNull.value");
        Preconditions.checkNotNull(date, "discount.build.fieldCannotBeNull.date");

        return new CouponDiscountCalculator(coupon, value, date).calculate();
    }

}
