package com.formento.ecommerce.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formento.ecommerce.productPrice.model.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
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
    private Long id;

    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 1024)
    private String description;

    @Min(0)
    @NotNull
    private Integer availability;

    @JsonIgnore
    @OneToMany
    private Collection<ProductPrice> productPrices;

    public Boolean isAvailable() {
        return availability.compareTo(0) > 0;
    }

    public Optional<ProductPrice> getCurrentProductPrice() {
        return getProductPriceBy(LocalDate.now());
    }

    public Optional<ProductPrice> getProductPriceBy(LocalDate initialDate) {
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
        private Long id;
        private String name;
        private String description;
        private Integer availability;
        private Collection<ProductPrice> productPrices = new ArrayList<>();

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withAvailability(Integer availability) {
            this.availability = availability;
            return this;
        }

        public Builder addProductPrice(ProductPrice productPrice) {
            this.productPrices.add(productPrice);
            return this;
        }

        public Product build() {
            return new Product(id, name, description, availability, productPrices);
        }

    }

}
