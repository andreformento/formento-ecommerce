package com.formento.ecommerce.payment.factory;

import com.formento.ecommerce.payment.model.creditCard.CreditCardFundingInstrument;
import com.formento.ecommerce.payment.model.FundingInstrument;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Ignore
public class CreditCardFundingInstrumentFactoryTest {

    @Test
    public void shouldMakeCreditCardFundingInstrument() {
        // given
        CreditCardFundingInstrumentFactory creditCardFundingInstrumentFactory = new CreditCardFundingInstrumentFactory();

        // when
        FundingInstrument fundingInstrument = creditCardFundingInstrumentFactory.makeFundingInstrument();

        // then
        assertTrue(fundingInstrument instanceof CreditCardFundingInstrument);
    }

}
