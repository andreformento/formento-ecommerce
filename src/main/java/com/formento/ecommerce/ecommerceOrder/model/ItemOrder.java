package com.formento.ecommerce.ecommerceOrder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.product.converter.OptionalProductSerializer;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemOrder implements ProductPrice, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JsonSerialize(using = OptionalProductSerializer.class)
    @JsonProperty
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private EcommerceOrder ecommerceOrder;

    @Min(1)
    private Integer quantity;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private BigDecimal totalPrice;

    public Product getProductOrder() {
        return this.product;
    }

    public static class Builder {
        private ItemOrder instance = new ItemOrder();

        public Builder withId(Long id) {
            instance.id = id;
            return this;
        }

        public Builder withProduct(Product product) {
            instance.product = product;
            return this;
        }

        public Builder withEcommerceOrder(EcommerceOrder ecommerceOrder) {
            instance.ecommerceOrder = ecommerceOrder;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            instance.quantity = quantity;
            return this;
        }

        public Builder withUnitPrice(BigDecimal unitPrice) {
            instance.unitPrice = unitPrice;
            return this;
        }

        public Builder withTotalPrice(BigDecimal totalPrice) {
            instance.totalPrice = totalPrice;
            return this;
        }

        public ItemOrder build() {
            return this.instance;
        }
    }

}
