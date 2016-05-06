package com.formento.ecommerce.discount.repository;

import com.formento.ecommerce.discount.model.Coupon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

}
