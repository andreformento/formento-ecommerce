package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.repository.ItemShoppingCartRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@NoArgsConstructor
@Service
public class ItemShoppingCartServiceDefault implements ItemShoppingCartService {

    @Autowired
    private ItemShoppingCartRepository repository;

    @Override
    public Iterable<ItemShoppingCart> save(Iterable<ItemShoppingCart> itemShoppingCarts) {
        return repository.save(itemShoppingCarts);
    }
}
