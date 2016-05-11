package com.formento.ecommerce.payment.factory;

import com.formento.ecommerce.payment.model.boleto.BoletoFundingInstrument;
import com.formento.ecommerce.payment.model.FundingInstrument;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BoletoFundingInstrumentFactoryTest {

    @Test
    public void shouldMakeBoletoFundingInstrument() {
        // given
        BoletoFundingInstrumentFactory boletoFundingInstrumentFactory = new BoletoFundingInstrumentFactory();

        // when
        FundingInstrument fundingInstrument = boletoFundingInstrumentFactory.makeFundingInstrument();

        // then
        assertTrue(fundingInstrument instanceof BoletoFundingInstrument);
    }

}