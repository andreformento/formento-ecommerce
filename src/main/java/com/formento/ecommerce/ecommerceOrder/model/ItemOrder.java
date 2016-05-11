package com.formento.ecommerce.ecommerceOrder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.product.converter.ProductSerializer;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = ProductSerializer.class)
    private Product product;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private EcommerceOrder ecommerceOrder;

    @Min(1)
    private Integer quantity;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private BigDecimal totalPrice;

    public ItemOrder(Product product, BigDecimal unitPrice, Integer quantity, BigDecimal totalPrice) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public ItemOrder(ItemOrder itemOrder) {
        this(itemOrder.product,
                itemOrder.unitPrice,
                itemOrder.quantity,
                itemOrder.totalPrice);
    }

}
