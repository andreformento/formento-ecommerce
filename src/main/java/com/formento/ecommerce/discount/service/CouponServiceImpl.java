package com.formento.ecommerce.discount.service;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.repository.CouponRepository;
import com.formento.ecommerce.exception.DiscountEcommerceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon getCouponByCode(String code) {
        return couponRepository
                .getByCode(code)
                .orElseThrow(() -> new DiscountEcommerceException("coupon.notFound"))
                .validateNow();
    }
}
