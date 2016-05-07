package com.formento.ecommerce.payment.factory;

import com.formento.ecommerce.payment.model.BoletoFundingInstrument;
import com.formento.ecommerce.payment.model.FundingInstrument;
import lombok.Getter;

public class BoletoFundingInstrumentFactory extends AbstractFundingInstrumentFactory implements FundingInstrumentFactory {

    @Override
    public FundingInstrument makeFundingInstrument() {
        return new BoletoFundingInstrument();
    }

}
