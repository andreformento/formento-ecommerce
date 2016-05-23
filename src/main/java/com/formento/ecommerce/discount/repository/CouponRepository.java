package com.formento.ecommerce.discount.repository;

import com.formento.ecommerce.discount.model.Coupon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

}
