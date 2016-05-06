package com.formento.ecommerce.discount.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCOUNT_TYPE")
public abstract class AbstractDiscount implements Discount {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

}
