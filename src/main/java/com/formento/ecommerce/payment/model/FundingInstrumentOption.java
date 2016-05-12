package com.formento.ecommerce.payment.model;

import lombok.Getter;

@Getter
public enum FundingInstrumentOption {

    CREDIT_CARD(12),

    BOLETO(1);

    private final Integer countMax;

    FundingInstrumentOption(Integer countMax) {
        this.countMax = countMax;
    }

}
