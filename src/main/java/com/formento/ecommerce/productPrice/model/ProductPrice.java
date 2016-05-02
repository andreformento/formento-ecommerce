package com.formento.ecommerce.productPrice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formento.ecommerce.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"product", "initialDate"}))
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

}
