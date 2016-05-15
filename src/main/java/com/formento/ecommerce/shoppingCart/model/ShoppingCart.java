package com.formento.ecommerce.shoppingCart.model;

import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER)
    private Collection<ItemShoppingCart> itemShoppingCarts;

    public BigDecimal getTotalValue() {
        return itemShoppingCarts
                .stream()
                .map(ItemShoppingCart::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElseGet(() -> BigDecimal.ZERO);
    }

    public static class Builder {
        private ShoppingCart instance;

        public Builder() {
            this.instance = new ShoppingCart();
            this.instance.itemShoppingCarts = new ArrayList<>();
        }

        public Builder withSelf(ShoppingCart shoppingCart) {
            return withId(shoppingCart.id)
                    .withUser(shoppingCart.user)
                    .withItemShoppingCarts(shoppingCart.itemShoppingCarts);
        }

        public Builder withId(Long id) {
            instance.id = id;
            return this;
        }

        public Builder withUser(User user) {
            instance.user = user;
            return this;
        }

        public Builder withItemShoppingCarts(Collection<ItemShoppingCart> itemShoppingCarts) {
            instance.itemShoppingCarts.forEach(this::withItemShoppingCart);
            return this;
        }

        public Builder withItemShoppingCart(ItemShoppingCart itemShoppingCart) {
            instance.itemShoppingCarts.add(new ItemShoppingCart
                    .Builder()
                    .withShoppingCart(instance)
                    .withId(itemShoppingCart.getId())
                    .withProduct(itemShoppingCart.getProduct())
                    .withQuantity(itemShoppingCart.getQuantity())
                    .build());
            return this;
        }

        public ShoppingCart build() {
            return instance;
        }
    }

}
