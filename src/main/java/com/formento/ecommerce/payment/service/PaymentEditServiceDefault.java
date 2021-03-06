package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.model.PaymentEntity;
import com.formento.ecommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentEditServiceDefault implements PaymentEditService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentValidator paymentValidator;

    @Override
    public Payment getPaymentById(Long id) {
        return paymentValidator.validateAccess(paymentRepository.findOne(id));
    }

    @Override
    public Payment create(Payment payment, BigDecimal paymentValue) {
        PaymentEntity entity = new PaymentEntity(payment, paymentValue);
        paymentValidator.validateBeforeCreate(entity);
        return paymentRepository.save(entity);
    }

}
