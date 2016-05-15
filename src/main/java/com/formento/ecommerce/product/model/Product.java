package com.formento.ecommerce.product.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.productPrice.converter.ProductPriceSerializer;
import com.formento.ecommerce.productPrice.converter.ProductPricesSerializer;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    private Long productId;

    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 1024)
    private String description;

    @Min(0)
    @NotNull
    private Integer availability;

    @JsonSerialize(using = ProductPricesSerializer.class)
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Collection<ProductPriceEntity> productPrices;

    public Boolean isAvailable() {
        return availability.compareTo(0) > 0;
    }

    @JsonSerialize(using = ProductPriceSerializer.class)
    public Optional<ProductPriceEntity> getCurrentProductPrice() {
        return getProductPriceBy(LocalDate.now());
    }

    public Long getId() {
        return this.productId;
    }

    public void setId(Long id) {
        this.productId = id;
    }

    public Optional<ProductPriceEntity> getProductPriceBy(LocalDate initialDate) {
        if (productPrices == null)
            return Optional.empty();

        return productPrices
                .stream()
                .filter(productPrice ->
                        productPrice.getInitialDate().compareTo(initialDate) <= 0)
                .max((productPrice1, productPrice2) ->
                        productPrice1.getInitialDate().compareTo(productPrice2.getInitialDate()));
    }

    public static class Builder {

        private Product product;

        public Builder() {
            this.product = new Product();
            product.productPrices = new ArrayList<>();
        }

        public Builder withId(Long id) {
            product.productId = id;
            return this;
        }

        public Builder withName(String name) {
            product.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            product.description = description;
            return this;
        }

        public Builder withAvailability(Integer availability) {
            product.availability = availability;
            return this;
        }

        public Builder addProductPrice(ProductPriceEntity productPrice) {
            product.productPrices.add(productPrice);
            return this;
        }

        public Builder addProductPrice(BigDecimal price, LocalDate initialDate) {
            return addProductPrice(
                    new ProductPriceEntity.Builder()
                            .withProduct(product)
                            .withPrice(price)
                            .withInitialDate(initialDate)
                            .build()
            );
        }

        public Product build() {
            return product;
        }

    }

}
