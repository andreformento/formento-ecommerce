package com.formento.ecommerce.ecommerceOrder.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.ecommerceOrder.model.converter.ItemOrdersSerializer;
import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class EcommerceOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonSerialize(using = ItemOrdersSerializer.class)
    @OneToMany(mappedBy = "ecommerceOrder")
    private Collection<ItemOrder> itemOrders;

    @NotNull
    private BigDecimal totalValue;

    private String integrationId;

    @NotNull
    @Enumerated(EnumType.STRING)
    public StatusEcommerceOrder statusEcommerceOrder;

    public EcommerceOrder(Collection<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public static class Builder {

        private final EcommerceOrder instance;

        public Builder() {
            this.instance = new EcommerceOrder();
        }

        private Builder withTotalValue(Collection<ItemOrder> itemOrders) {
            instance.totalValue = itemOrders
                    .stream()
                    .map(ItemOrder::getTotalPrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            return this;
        }

        private Builder withItemShoppingCarts(Collection<ItemShoppingCart> itemShoppingCarts) {
            instance.itemOrders = itemShoppingCarts
                    .stream()
                    .map(ItemShoppingCart::generateItemOrder)
                    .collect(Collectors.toList());
            return withTotalValue(instance.itemOrders);
        }

        private Builder withUser(User user) {
            instance.user = user;
            return this;
        }

        public Builder withShoppingCart(ShoppingCart shoppingCart) {
            return withItemShoppingCarts(shoppingCart.getItemShoppingCarts())
                    .withUser(shoppingCart.getUser());
        }

        public Builder withStatusEcommerceOrder(StatusEcommerceOrder statusEcommerceOrder) {
            instance.statusEcommerceOrder = statusEcommerceOrder;
            return this;
        }

        public Builder changeStatus(EcommerceOrder ecommerceOrder, String integrationId, StatusEcommerceOrder statusEcommerceOrder) {
            instance.id = ecommerceOrder.id;
            instance.user = ecommerceOrder.user;
            instance.itemOrders = ecommerceOrder.itemOrders;
            instance.totalValue = ecommerceOrder.totalValue;
            instance.integrationId = integrationId;
            instance.statusEcommerceOrder = statusEcommerceOrder;
            return this;
        }

        public EcommerceOrder build() {
            return instance;
        }
    }

}
