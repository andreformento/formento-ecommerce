package com.formento.ecommerce.installment.factory;

import com.formento.ecommerce.payment.model.MethodPayment;

import java.math.BigDecimal;

public interface MethodPaymentFactory {

    MethodPayment makeMethodPayment(BigDecimal totalValue, Integer count);

}
