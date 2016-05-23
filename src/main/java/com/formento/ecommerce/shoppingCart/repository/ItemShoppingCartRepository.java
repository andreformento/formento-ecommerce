package com.formento.ecommerce.shoppingCart.repository;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemShoppingCartRepository extends PagingAndSortingRepository<ItemShoppingCart, Long> {

    @Query(" select itemShoppingCart " +
            "  from ItemShoppingCart itemShoppingCart " +
            "  inner join itemShoppingCart.shoppingCart shoppingCart " +
            "  inner join itemShoppingCart.product product " +
            " where shoppingCart.user.email = ?1" +
            "   and shoppingCart.shoppingDate is null" +
            " order by product.name")
    Iterable<ItemShoppingCart> getCurrentByUser(String email);

    @Query(" select itemShoppingCart " +
            "  from ItemShoppingCart itemShoppingCart " +
            "  inner join itemShoppingCart.shoppingCart shoppingCart " +
            " where shoppingCart.user.email = ?1" +
            "   and itemShoppingCart.product.productId = ?2" +
            "   and shoppingCart.shoppingDate is null")
    Optional<ItemShoppingCart> getCurrentByUserAndProduct(String email, Long productId);

}
