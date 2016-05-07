package com.formento.ecommerce.payment.factory;

import com.formento.ecommerce.payment.model.CreditCardFundingInstrument;
import com.formento.ecommerce.payment.model.FundingInstrument;

public class CreditCardFundingInstrumentFactory extends AbstractFundingInstrumentFactory implements FundingInstrumentFactory {

    @Override
    public FundingInstrument makeFundingInstrument() {
        return new CreditCardFundingInstrument();
    }

}
