package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {

    ShoppingCart newItemShoppingCart(ItemShoppingCart itemShoppingCart);

    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart findOne(Long id);

    Optional<ShoppingCart> getCurrentFromUser();

}
