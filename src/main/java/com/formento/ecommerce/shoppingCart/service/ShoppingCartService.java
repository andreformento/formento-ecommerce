package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart findOne(Long id);

    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart getOrCreateCurrentFromUser();

    ShoppingCart finalizeCurrentFromUser();

}
