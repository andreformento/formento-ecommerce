package com.formento.ecommerce.shoppingCart.service;

import com.formento.ecommerce.exception.AccessDeniedEcommerceException;
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

    private static final String ITEM_SHOPPING_CART_ACCESS_DENIED_TO_LOGGED_USER = "itemShoppingCart.accessDeniedToLoggedUser";

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
        return repository.getCurrentByUser(userService.loadUserValidated().getEmail());
    }

    @Override
    public Optional<ItemShoppingCart> getByIdFromLoggedUser(Long itemShoppingCartId) {
        return Optional
                .ofNullable(repository.findOne(itemShoppingCartId))
                .map(itemShoppingCart -> {
                    if (!itemShoppingCart.getShoppingCart().getUser().getEmail().equals(userService.getValidatedUserOfSession().getEmail())) {
                        throw new AccessDeniedEcommerceException(ITEM_SHOPPING_CART_ACCESS_DENIED_TO_LOGGED_USER);
                    }
                    return itemShoppingCart;
                });
    }

    @Override
    public Optional<ItemShoppingCart> getByProductFromLoggedUser(Long productId) {
        return repository.getCurrentByUserAndProduct(userService.loadUserValidated().getEmail(), productId);
    }

    @Override
    public void save(Iterable<ItemShoppingCart> itemShoppingCarts) {
        repository.save(itemShoppingCarts);
    }

    @Override
    public void removeItemShoppingCart(Long itemShoppingCartId) {
        getByIdFromLoggedUser(itemShoppingCartId).ifPresent(itemShoppingCart -> repository.delete(itemShoppingCart));
    }

    @Override
    public ItemShoppingCart plusItemShoppingCart(Long itemShoppingCartId) {
        return save(new ItemShoppingCart
                .Builder()
                .withSelf(getByIdFromLoggedUser(itemShoppingCartId).orElseThrow(() -> new BusinessEcommerceException(ITEM_SHOPPING_CART_ACCESS_DENIED_TO_LOGGED_USER)))
                .addQuantity(1)
                .build());
    }

    @Override
    public ItemShoppingCart addItemShoppingCart(ItemShoppingCart itemShoppingCart) {
        Optional.ofNullable(itemShoppingCart.getProduct()).orElseThrow(() -> new BusinessEcommerceException("itemShoppingCart.product.cannotBeNull"));
        Optional.ofNullable(itemShoppingCart.getQuantity()).orElseThrow(() -> new BusinessEcommerceException("itemShoppingCart.quantity.cannotBeNull"));

        Optional<ItemShoppingCart> itemShoppingCartOptional = getByProductFromLoggedUser(itemShoppingCart.getProduct().getId())
                .map(saved -> new ItemShoppingCart
                        .Builder()
                        .withSelf(saved)
                        .addQuantity(itemShoppingCart.getQuantity())
                        .build());

        final ItemShoppingCart toSave;
        if (itemShoppingCartOptional.isPresent()) {
            toSave = itemShoppingCartOptional.get();
        } else {
            toSave = new ItemShoppingCart
                    .Builder()
                    .withShoppingCart(shoppingCartService.getOrCreateCurrentFromUser())
                    .withProduct(productService.findById(itemShoppingCart.getProduct().getId()).orElseThrow(() -> new BusinessEcommerceException("product.notFoundById")))
                    .withQuantity(itemShoppingCart.getQuantity())
                    .build();
        }

        return save(toSave);
    }

}
