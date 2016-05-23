package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.payment.model.Payment;

import java.math.BigDecimal;

public interface PaymentEditService {

    Payment getPaymentById(Long id);

    Payment create(Payment payment, BigDecimal paymentValue);

}
