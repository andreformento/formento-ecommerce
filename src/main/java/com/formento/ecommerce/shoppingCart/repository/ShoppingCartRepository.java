package com.formento.ecommerce.shoppingCart.repository;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;

@RepositoryRestController
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {

    @Query(" select shoppingCart " +
            " from ShoppingCart shoppingCart " +
            " where shoppingCart.user.email = ?1")
    Optional<ShoppingCart> getCurrentFromUser(String email);

}
