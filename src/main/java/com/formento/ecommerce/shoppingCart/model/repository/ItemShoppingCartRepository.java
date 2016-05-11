package com.formento.ecommerce.shoppingCart.model.repository;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ItemShoppingCartRepository extends PagingAndSortingRepository<ItemShoppingCart, Long> {
}
