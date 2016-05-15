package com.formento.ecommerce.ecommerceOrder.repository;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;

@RepositoryRestController
public interface EcommerceOrderRepository extends PagingAndSortingRepository<EcommerceOrder, Long> {

    @Query(" select ecommerceOrder " +
            " from EcommerceOrder ecommerceOrder " +
            " where ecommerceOrder.user.email = ?1")
    Optional<EcommerceOrder> getCurrentOrder(String email);

}
