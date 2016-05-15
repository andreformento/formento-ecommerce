package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.payment.model.FundingInstrument;
import com.formento.ecommerce.payment.model.Payment;

import java.math.BigDecimal;

public interface PaymentService<T extends FundingInstrument> {

    Payment createPayment(BigDecimal totalValue, Integer quantity, T fundingInstrument);

}
