package com.formento.ecommerce.ecommerceOrder.repository;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EcommerceOrderRepository extends PagingAndSortingRepository<EcommerceOrder, Long> {

    @Query(" select ecommerceOrder " +
            " from EcommerceOrder ecommerceOrder " +
            " where ecommerceOrder.user.email = ?1" +
            "   and ecommerceOrder.statusEcommerceOrder = 'CREATED'")
    Optional<EcommerceOrder> getCurrentOrder(String email);

}
