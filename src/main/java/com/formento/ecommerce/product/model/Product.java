package com.formento.ecommerce.product.model;

import com.formento.ecommerce.productPrice.model.ProductPrice;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class Product {

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

}
