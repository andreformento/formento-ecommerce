package com.formento.ecommerce.productPrice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formento.ecommerce.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "un_product_price", columnNames = {"product", "initial_date"}))
public class ProductPrice {

    @Id
    @GeneratedValue
    private final Long id;

    @NotNull
    private final LocalDate initialDate;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private final Product product;

    @NotNull
    private final BigDecimal price;

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

        public ProductPrice build() {
            return new ProductPrice(id, initialDate, product, price);
        }
    }
    
}
