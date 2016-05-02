package com.formento.ecommerce.productPrice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formento.ecommerce.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "un_product_price", columnNames = {"product", "initial_date"}))
public class ProductPrice {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDate initialDate;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @NotNull
    private BigDecimal price;

}
