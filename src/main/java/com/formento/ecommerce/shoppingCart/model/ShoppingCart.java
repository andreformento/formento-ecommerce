package com.formento.ecommerce.shoppingCart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.util.converter.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime shoppingDate;

    @JsonIgnore
    public Optional<LocalDateTime> getShoppingDate() {
        return Optional.ofNullable(shoppingDate);
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
                    .withShoppingDate(shoppingCart.shoppingDate)
                    .withItemShoppingCarts(shoppingCart.itemShoppingCarts);
        }

        private Builder withShoppingDate(LocalDateTime shoppingDate) {
            instance.shoppingDate = shoppingDate;
            return this;
        }

        public Builder finalizeShoppingCart() {
            instance.shoppingDate = LocalDateTime.now();
            return this;
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
            itemShoppingCarts.forEach(this::withItemShoppingCart);
            return this;
        }

        public Builder withItemShoppingCart(ItemShoppingCart itemShoppingCart) {
            instance.itemShoppingCarts.add(new ItemShoppingCart
                    .Builder()
                    .withShoppingCart(instance)
                    .withShoppingCartId(itemShoppingCart.getItemShoppingCartId())
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
