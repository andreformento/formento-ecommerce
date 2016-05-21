package com.formento.ecommerce.shoppingCart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formento.ecommerce.ecommerceOrder.model.ItemOrder;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.product.model.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private Long itemShoppingCartId;

    @NotNull
    @ManyToOne
    private Product product;

    @JsonIgnore
    @NotNull
    @ManyToOne
    private ShoppingCart shoppingCart;

    private Integer quantity;

    public Long getId() {
        return this.itemShoppingCartId;
    }

    public Product getProductItem() {
        return product;
    }

    public ItemShoppingCart(Product product, Integer quantity) {
        this.product = product;
        setQuantity(quantity);
    }

    private void setQuantity(Integer quantity) {
        if (quantity.compareTo(product.getAvailability()) > 0) {
            throw new BusinessEcommerceException("shoppingCart.quantityGreaterThenAvaliabityOfProduct");
        }

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

    private void addQuantity(Integer quantityToAdd) {
        setQuantity(this.quantity + quantityToAdd);
    }

    public static class Builder {
        private ItemShoppingCart instance = new ItemShoppingCart();

        public Builder withSelf(ItemShoppingCart self) {
            return withShoppingCartId(self.itemShoppingCartId)
                    .withProduct(self.product)
                    .withShoppingCart(self.shoppingCart)
                    .withQuantity(self.quantity);
        }

        public Builder withShoppingCartId(Long itemShoppingCartId) {
            instance.itemShoppingCartId = itemShoppingCartId;
            return this;
        }

        public Builder withProduct(Product product) {
            instance.product = product;
            return this;
        }

        public Builder withShoppingCart(ShoppingCart shoppingCart) {
            instance.shoppingCart = shoppingCart;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            instance.setQuantity(quantity);
            return this;
        }

        public Builder addQuantity(Integer quantityToAdd) {
            instance.addQuantity(quantityToAdd);
            return this;
        }

        public ItemShoppingCart build() {
            return instance;
        }
    }

}
