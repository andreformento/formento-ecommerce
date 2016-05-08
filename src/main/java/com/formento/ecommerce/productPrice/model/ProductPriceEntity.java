package com.formento.ecommerce.productPrice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.util.converter.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "un_product_price", columnNames = {"product", "initial_date"}))
public class ProductPriceEntity implements ProductPrice {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate initialDate;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @NotNull
    private BigDecimal price;

    public static class Builder {
        private Long id;
        private LocalDate initialDate;
        private Product product;
        private BigDecimal price;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withInitialDate(LocalDate initialDate) {
            this.initialDate = initialDate;
            return this;
        }

        public Builder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductPriceEntity build() {
            return new ProductPriceEntity(id, initialDate, product, price);
        }
    }

    public BigDecimal getTotalPrice() {
        return price.setScale(2, RoundingMode.DOWN);
    }

}
