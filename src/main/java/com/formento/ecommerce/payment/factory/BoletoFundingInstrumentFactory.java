package com.formento.ecommerce.payment.factory;

import com.formento.ecommerce.payment.model.boleto.BoletoFundingInstrument;
import com.formento.ecommerce.payment.model.FundingInstrument;

public class BoletoFundingInstrumentFactory extends AbstractFundingInstrumentFactory implements FundingInstrumentFactory {

    @Override
    public FundingInstrument makeFundingInstrument() {
        return new BoletoFundingInstrument();
    }

}
