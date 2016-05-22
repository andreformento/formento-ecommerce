package com.formento.ecommerce.ecommerceOrder.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;

import java.util.Optional;

public interface EcommerceOrderService {

    EcommerceOrder createIntegration();

    Optional<EcommerceOrder> getOrderById(Long orderId);

    EcommerceOrder getValidatedOrderById(Long orderId);

    EcommerceOrder save(EcommerceOrder ecommerceOrder);

    EcommerceOrder changeStatusOrder(Long orderId, EcommerceOrder ecommerceOrderToChange);

}
