package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;

public interface ItemShoppingCartService {

    Iterable<ItemShoppingCart> addItemShoppingCart(ItemShoppingCart itemShoppingCart);

    Iterable<ItemShoppingCart> getAllFromLoggedUser();

    void save(Iterable<ItemShoppingCart> itemShoppingCarts);
}
