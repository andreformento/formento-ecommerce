package com.formento.ecommerce.payment.model.creditCard;

import com.formento.ecommerce.payment.model.FundingInstrument;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardEcommerceFundingInstrument implements FundingInstrument, Serializable {

    private CreditCard creditCard;

    @Override
    public String getMethod() {
        return "CREDIT_CARD";
    }

}
