package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.product.service.ProductService;
import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.repository.ItemShoppingCartRepository;
import com.formento.ecommerce.user.model.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@Service
public class ItemShoppingCartServiceDefault implements ItemShoppingCartService {

    @Autowired
    private ItemShoppingCartRepository repository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private ItemShoppingCart save(ItemShoppingCart itemShoppingCart) {
        return repository.save(itemShoppingCart);
    }

    @Override
    public Iterable<ItemShoppingCart> getAllFromLoggedUser() {
        return repository.getCurrentFromUser(userService.loadUserValidated().getEmail());
    }

    @Override
    public void save(Iterable<ItemShoppingCart> itemShoppingCarts) {
        repository.save(itemShoppingCarts);
    }

    @Override
    public Iterable<ItemShoppingCart> addItemShoppingCart(ItemShoppingCart itemShoppingCart) {
        Optional.ofNullable(itemShoppingCart.getProduct()).orElseThrow(() -> new BusinessEcommerceException("itemShoppingCart.product.cannotBeNull"));
        Optional.ofNullable(itemShoppingCart.getQuantity()).orElseThrow(() -> new BusinessEcommerceException("itemShoppingCart.quantity.cannotBeNull"));

        save(new ItemShoppingCart
                .Builder()
                .withShoppingCart(shoppingCartService.getOrCreateCurrentFromUser())
                .withProduct(productService.findById(itemShoppingCart.getProduct().getId()))
                .withQuantity(itemShoppingCart.getQuantity())
                .build());
        return getAllFromLoggedUser();
    }

}
