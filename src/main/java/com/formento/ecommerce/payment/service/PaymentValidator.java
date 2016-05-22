package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.payment.model.Payment;

public interface PaymentValidator {

    Payment validateBeforeCreate(Payment entity);

    Payment validateAccess(Payment entity);

}
