package com.formento.ecommerce.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formento.ecommerce.payment.factory.BoletoFundingInstrumentFactory;
import com.formento.ecommerce.payment.factory.CreditCardFundingInstrumentFactory;
import com.formento.ecommerce.payment.factory.FundingInstrumentFactory;
import lombok.Getter;

@Getter
public enum FundingInstrumentOption {

    CREDIT_CARD(12) {
        @Override
        public FundingInstrumentFactory getFundingInstrumentFactory() {
            return new BoletoFundingInstrumentFactory();
        }
    },

    BOLETO(1) {
        @Override
        public FundingInstrumentFactory getFundingInstrumentFactory() {
            return new CreditCardFundingInstrumentFactory();
        }
    };

    private final Integer countMax;

    FundingInstrumentOption(Integer countMax) {
        this.countMax = countMax;
    }

    @JsonIgnore
    public abstract FundingInstrumentFactory getFundingInstrumentFactory();

}
