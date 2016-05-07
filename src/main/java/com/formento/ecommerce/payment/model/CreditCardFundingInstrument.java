package com.formento.ecommerce.payment.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CreditCardFundingInstrument implements FundingInstrument, Serializable {

    @Override
    public String getMethod() {
        return "CREDIT_CARD";
    }

}
