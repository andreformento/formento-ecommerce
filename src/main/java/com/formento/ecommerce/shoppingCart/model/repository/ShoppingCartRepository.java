package com.formento.ecommerce.shoppingCart.model.repository;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {
}
