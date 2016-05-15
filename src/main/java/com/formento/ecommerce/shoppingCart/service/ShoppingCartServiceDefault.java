package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.repository.ShoppingCartRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ShoppingCartServiceDefault implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findOne(Long id) {
        return repository.findOne(id);
    }

}
