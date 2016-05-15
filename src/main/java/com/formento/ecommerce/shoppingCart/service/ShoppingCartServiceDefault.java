package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.repository.ShoppingCartRepository;
import com.formento.ecommerce.user.model.User;
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

    @Override
    public ShoppingCart newItemShoppingCart(ItemShoppingCart itemShoppingCart) {
        User user = userService.loadUserValidated();

        Optional<ShoppingCart> currentFromUser = repository.getCurrentFromUser(user.getEmail());
        if (currentFromUser.isPresent()) {
            return save(new ShoppingCart
                    .Builder()
                    .withSelf(currentFromUser.get())
                    .withItemShoppingCart(itemShoppingCart)
                    .build());
        } else {
            return save(new ShoppingCart
                    .Builder()
                    .withUser(user)
                    .withItemShoppingCart(itemShoppingCart)
                    .build());
        }
    }

    public ShoppingCart save1(ShoppingCart shoppingCart) {
        return shoppingCart;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Optional<ShoppingCart> getCurrentFromUser() {
        return userService
                .getUserOfSession()
                .flatMap(userAuthentication -> repository.getCurrentFromUser(userAuthentication.getEmail()));
    }

}
