package com.formento.ecommerce.payment.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public final class PaymentDefault implements Payment {
    private MethodPayment methodPayment;
    private FundingInstrument fundingInstrument;
}
