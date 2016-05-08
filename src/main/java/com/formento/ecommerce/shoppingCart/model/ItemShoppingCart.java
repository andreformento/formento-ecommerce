package com.formento.ecommerce.shoppingCart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.order.model.ItemOrder;
import com.formento.ecommerce.product.model.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class ItemShoppingCart implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Product product;

    @JsonIgnore
    @NotNull
    private ShoppingCart shoppingCart;

    private Integer quantity;

    public ItemShoppingCart(Product product, Integer quantity) {
        if (quantity.compareTo(product.getAvailability()) > 0) {
            throw new BusinessEcommerceException("shoppingCart.quantityGreaterThenAvaliabityOfProduct");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return product.getCurrentProductPrice().orElseThrow(() -> new BusinessEcommerceException("shoppingCart.priceOfProductNotDefined")).getTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return getProductPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public ItemOrder generateItemOrder() {
        return new ItemOrder(product, getProductPrice(), quantity, getTotalPrice());
    }

}
