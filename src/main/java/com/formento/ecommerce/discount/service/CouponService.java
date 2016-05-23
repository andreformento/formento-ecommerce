package com.formento.ecommerce.discount.service;

import com.formento.ecommerce.discount.model.Coupon;

public interface CouponService {

    Coupon getCouponByCode(String code);
}
