package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.repository.ShoppingCartRepository;
import com.formento.ecommerce.user.model.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@Service
public class ShoppingCartServiceDefault implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    @Autowired
    private UserService userService;

    public ShoppingCart save(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getOrCreateCurrentFromUser() {
        return getCurrentFromUser()
                .orElse(save(new ShoppingCart
                        .Builder()
                        .withUser(userService.loadUserValidated())
                        .build()));
    }

    @Override
    public ShoppingCart findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public ShoppingCart finalizeCurrentFromUser() {
        return new ShoppingCart
                .Builder()
                .withSelf(getCurrentFromUser().orElseThrow(() -> new BusinessEcommerceException("order.shoppingCart.fromUserNotFound")))
                .finalizeShoppingCart()
                .build();
    }

    private Optional<ShoppingCart> getCurrentFromUser() {
        return userService
                .getUserOfSession()
                .flatMap(userAuthentication -> repository.getCurrentFromUser(userAuthentication.getEmail()));
    }

}
