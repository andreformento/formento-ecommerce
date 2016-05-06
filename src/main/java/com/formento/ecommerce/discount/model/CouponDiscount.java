package com.formento.ecommerce.discount.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "COUPON")
public class CouponDiscount extends AbstractDiscount implements Discount {

    private Coupon coupon;
    private BigDecimal value;
    private BigDecimal liquidValue;

}
