package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart findOne(Long id);

}
