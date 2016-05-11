package com.formento.ecommerce.ecommerceOrder.repository;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface EcommerceOrderRepository extends PagingAndSortingRepository<EcommerceOrder, Long> {

}
