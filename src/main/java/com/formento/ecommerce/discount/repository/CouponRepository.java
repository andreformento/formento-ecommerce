package com.formento.ecommerce.discount.repository;

import com.formento.ecommerce.discount.model.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

    @Query(" select coupon " +
            " from Coupon coupon " +
            " where coupon.code = ?1")
    Optional<Coupon> getByCode(String code);

}
