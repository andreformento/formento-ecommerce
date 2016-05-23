package com.formento.ecommerce.shoppingCart.repository;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {

    @Query(" select shoppingCart " +
            " from ShoppingCart shoppingCart " +
            " where shoppingCart.user.email = ?1" +
            "   and shoppingCart.shoppingDate is null")
    Optional<ShoppingCart> getCurrentFromUser(String email);

}
