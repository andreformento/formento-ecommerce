package com.formento.ecommerce.order.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.order.model.converter.ItemOrdersSerializer;
import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EcomerceOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private User user;

    @JsonSerialize(using = ItemOrdersSerializer.class)
    @OneToMany(mappedBy = "ecomerceOrder")
    private Collection<ItemOrder> itemOrders;

    @NotNull
    private BigDecimal totalValue;

    public EcomerceOrder(Collection<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public static class Builder extends EcomerceOrder {

        private Builder withTotalValue(Collection<ItemOrder> itemOrders) {
            super.totalValue = itemOrders
                    .stream()
                    .map(ItemOrder::getTotalPrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            return this;
        }

        private Builder withItemShoppingCarts(Collection<ItemShoppingCart> itemShoppingCarts) {
            super.itemOrders = itemShoppingCarts
                    .stream()
                    .map(ItemShoppingCart::generateItemOrder)
                    .collect(Collectors.toList());
            return withTotalValue(super.itemOrders);
        }

        private Builder withUser(User user) {
            super.user = user;
            return this;
        }

        public Builder withShoppingCart(ShoppingCart shoppingCart) {
            return withItemShoppingCarts(shoppingCart.getItemShoppingCarts())
                    .withUser(shoppingCart.getUser());
        }

        public EcomerceOrder build() {
            return this;
        }
    }

}
