package com.formento.ecommerce.ecommerceOrder.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;

import java.util.Optional;

public interface EcommerceOrderService {

    EcommerceOrder createIntegration();

    Optional<EcommerceOrder> getCurrentOrder();

    EcommerceOrder getValidatedCurrentOrder();

    EcommerceOrder save(EcommerceOrder ecommerceOrder);

    EcommerceOrder changeStatusOrder(Long orderId, EcommerceOrder ecommerceOrderToChange);
}
