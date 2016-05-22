package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.payment.model.Payment;

public interface PaymentEditService {

    Payment getPaymentById(Long id);

    Payment create(Payment payment);

}
