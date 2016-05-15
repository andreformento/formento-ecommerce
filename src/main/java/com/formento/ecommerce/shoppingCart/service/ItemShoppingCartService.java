package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;

public interface ItemShoppingCartService {

    Iterable<ItemShoppingCart> save(Iterable<ItemShoppingCart> itemShoppingCarts);

}
