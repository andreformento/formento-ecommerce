package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.exception.AccessDeniedEcommerceException;
import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidatorDefault implements PaymentValidator {

    @Autowired
    private UserService userService;

    @Override
    public Payment validateBeforeCreate(Payment entity) {
        return validateAccess(entity);
    }

    @Override
    public Payment validateAccess(Payment entity) {
        if (entity.getEcommerceOrder().isPresent()) {
            EcommerceOrder ecommerceOrder = entity.getEcommerceOrder().get();
            if (!ecommerceOrder.getUser().getEmail().equals(userService.getValidatedUserOfSession().getEmail())) {
                throw new AccessDeniedEcommerceException("payment.accessDenied");
            }
        }

        return entity;
    }
}
