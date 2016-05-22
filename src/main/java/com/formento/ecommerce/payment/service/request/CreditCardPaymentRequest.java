package com.formento.ecommerce.payment.service.request;

import com.formento.ecommerce.payment.model.creditCard.CreditCard;
import com.formento.ecommerce.payment.model.creditCard.CreditCardEcommerceFundingInstrument;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class CreditCardPaymentRequest extends AbstractPaymentRequest implements PaymentRequest, Serializable {

    private CreditCard creditCard;

    public CreditCardPaymentRequest(Integer installmentCount, CreditCard creditCard) {
        super(installmentCount);
    }

    @Override
    public CreditCardEcommerceFundingInstrument generateFundingInstrument() {
        return new CreditCardEcommerceFundingInstrument(creditCard);
    }

}
