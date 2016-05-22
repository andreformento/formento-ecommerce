package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.service.request.PaymentRequest;

public interface PaymentService<T extends PaymentRequest> {

    Payment createPayment(Long orderId, T paymentRequest);

}
