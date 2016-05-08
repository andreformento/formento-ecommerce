package com.formento.ecommerce.order.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.order.model.converter.ItemOrdersSerializer;
import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
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

    @JsonSerialize(using = ItemOrdersSerializer.class)
    @OneToMany(mappedBy = "ecomerceOrder")
    private Collection<ItemOrder> itemOrders;

    public EcomerceOrder(Collection<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public static class Builder extends EcomerceOrder {

        public Builder withItemShoppingCarts(Collection<ItemShoppingCart> itemShoppingCarts) {
            super.itemOrders = itemShoppingCarts
                    .stream()
                    .map(ItemShoppingCart::generateItemOrder)
                    .collect(Collectors.toList());
            return this;
        }

        public EcomerceOrder build() {
            return this;
        }
    }

}
