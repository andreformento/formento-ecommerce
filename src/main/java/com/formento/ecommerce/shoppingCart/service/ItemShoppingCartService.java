package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;

import java.util.Optional;

public interface ItemShoppingCartService {

    ItemShoppingCart plusItemShoppingCart(Long itemShoppingCartId);

    ItemShoppingCart addItemShoppingCart(ItemShoppingCart itemShoppingCart);

    Iterable<ItemShoppingCart> getAllFromLoggedUser();

    Optional<ItemShoppingCart> getByIdFromLoggedUser(Long itemShoppingCartId);

    Optional<ItemShoppingCart> getByProductFromLoggedUser(Long productId);

    void save(Iterable<ItemShoppingCart> itemShoppingCarts);

    void removeItemShoppingCart(Long itemShoppingCartId);
}
