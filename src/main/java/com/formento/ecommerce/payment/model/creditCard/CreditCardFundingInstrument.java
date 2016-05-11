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
//@NoArgsConstructor
public class CreditCardFundingInstrument implements FundingInstrument, Serializable {

    private CreditCardHolder creditCardHolder;

    @Override
    public String getMethod() {
        return "CREDIT_CARD";
    }

}
