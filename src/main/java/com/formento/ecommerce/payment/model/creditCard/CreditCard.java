package com.formento.ecommerce.payment.model.creditCard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CreditCard {

    private String hash;

    @NotNull
    private CreditCardHolder holder;

}
