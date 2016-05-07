package com.formento.ecommerce.installment.factory;

import com.formento.ecommerce.installment.model.PaymentInstallment;

import java.math.BigDecimal;

public interface PaymentInstallmentFactory {

    PaymentInstallment makePaymentInstallment(BigDecimal totalValue, Integer count);

}
